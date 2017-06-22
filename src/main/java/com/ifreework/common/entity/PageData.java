/**    
 * 文件名：PageData.java    
 *    
 * 版本信息：    
 * 日期：2016年7月4日    
 * Copyright  Corporation 2016     
 * 版权所有    
 *    
 */
package com.ifreework.common.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.pagehelper.Page;
import com.ifreework.util.ReflectUtil;
import com.ifreework.util.StringUtil;

/**
 * 描述：
 * 
 * @author：wangyh qq735789026
 * @创建时间：2016年7月4日 下午2:48:57
 * @修改人：wangyh
 * @修改时间：2016年7月4日 下午2:48:57
 * @version 1.0
 */
@SuppressWarnings("rawtypes")
public class PageData extends HashMap implements Map {


	private static final long serialVersionUID = 4182182477954304064L;

	private static final Logger log = LoggerFactory.getLogger(PageData.class);

	private static final String[] COLUMN_FIELDS = { "data", "name" };

	/**
	 * 
	 * 描述：返回分页每页显示条数
	 * @return 
	 */
	public Integer getLength() {
		String length = (String) this.get("length");
		if (StringUtil.isEmpty(length)) {
			return null;
		}
		return Integer.parseInt(length) < 0 ? 0 : Integer.parseInt(length);
	}

	/**
	 * 描述：设置分页查询每页显示条数
	 * @param length 
	 * @return
	 */
	public void setLength(Integer length) {
		this.put("length", length);
	}

	/**
	 * 
	 * 描述：获取分页查询页码
	 * @return 
	 * @return
	 */
	public Integer getStart() {
		String start = (String) this.get("start");
		if (StringUtil.isEmpty(start)) {
			return null;
		}
		return Integer.parseInt(start) == 0 ? 1 : Integer.parseInt(start);
	}

	/**
	 * 
	 * 描述：设置分页查询页码，从1开始
	 * @param start 
	 * @return
	 */
	public void setStart(Integer start) {
		this.put("start", start <= 0 ? 1 : start);
	}

	/**
	 * 
	 * 描述：分页查询时，获取数据总条数
	 * @return 
	 */
	public long getRecordsFiltered() {
		Long recordsFiltered = (Long) this.get("recordsFiltered");
		if (recordsFiltered == null) {
			recordsFiltered = 0L;
		}
		return recordsFiltered;
	}

	/**
	 * 描述：分页查询时，设置分页查询结果总数
	 * @param i 查询结果总条数
	 * @return
	 */
	public void setRecordsFiltered(long i) {
		this.put("recordsFiltered", i);
	}

	/**
	 * 
	 * 描述：设置分页查询结果信息
	 * @param list 同时实现page接口的List
	 * @return
	 */
	public void setPagination(List list) {
		if (list instanceof Page) {
			Page p = (Page) list;
			long i = p.getTotal();
			this.setData(list);
			this.setRecordsFiltered(i);
		} else {
			this.setRecordsFiltered(list.size());
		}
		this.setData(list);
	}

	/**
	 * 描述：返回结果
	 * @return 
	 */
	public String getResult() {
		return this.getString("result");
	}

	/**
	 * 
	 * 描述：设置返回结果
	 * @param result 
	 * @return
	 */
	public void setResult(Object result) {
		this.put("result", result.toString());
	}

	/**
	 * 描述：描述信息
	 * @return
	 */
	public String getMsg() {
		return this.getString("msg");
	}

	/**
	 * 
	 * 描述：设置描述信息
	 * @param msg  描述信息
	 * @return
	 */
	public void setMsg(String msg) {
		this.put("msg", msg);
	}

	/**
	 * 
	 * 描述：返回数据
	 * @return
	 */
	public Object getData() {
		return this.getString("data");
	}

	/**
	 * 描述：设置返回数据
	 * @param data 返回数据
	 * @return
	 */
	public void setData(Object data) {
		this.put("data", data);
	}

	Map<String, Object> map = null;
	HttpServletRequest request;

	/**
	 * 初始化PageData，并将request中的参数放入pagedata对象中    
	 * @param request 页面请求的request
	 */
	public PageData(HttpServletRequest request) {
		this.request = request;
		map = setParameters(request);
		if(map.get("length") != null){
			map.put("length", getLength());
		}
		setColumns(map);
		setOrder(map);
		log.debug("The page param is {} " , map);
	} 

	/**
	 * 
	 * 描述：将request中的parameter放入到map中
	 * @param request
	 * @return
	 */
	private Map<String, Object> setParameters(HttpServletRequest request) {
		Map<String, String[]> properties = request.getParameterMap();
		Map<String, Object> returnMap = new HashMap<String, Object>();
		for (String key : properties.keySet()) {
			String[] values = properties.get(key);
			StringBuffer valueStr = new StringBuffer();
			for (int i = 0; i < values.length; i++) {
				if (i == 0) {
					valueStr = valueStr.append(values[i]);
				} else {
					valueStr.append(",").append(values[i]);
				}
			}
			returnMap.put(key, valueStr.toString());
		}
		return returnMap;
	}


	/**
	 * 描述：设置页面表格查询中传过来的columns信息
	 * @param map 
	 * @return
	 */
	private void setColumns(Map<String, Object> map) {
		List<Column> columns = new ArrayList<Column>();
		try {
			int i = 0;//页面传过来的column的下标
			boolean isBreak = false; //是否结束循环
			while(!isBreak){
				Column column = columns.size() > i ? columns.get(i) : new Column();  //防止游标越界
				for (String columnField : COLUMN_FIELDS) {
					String key = "columns[" + i + "][" + columnField + "]";
					Object columnValue = map.remove(key);
					if (columnValue == null) {
						isBreak = true;
						break;
					} else {
						ReflectUtil.setValueByFieldName(column, columnField, columnValue);
					}
				}
				if(!isBreak){
					columns.add( column);
					i++;
				}
			}

			if(!columns.isEmpty()){
				map.put("columns", columns);
			}
		} catch (SecurityException | NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			log.error("Column set value error! {}" , e);
		}
	}

	/**
	 * 
	 * 描述：设置查询时字段的排序规则
	 * @param map 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private void setOrder(Map<String, Object> map) {
		List<Column> columns = (List<Column>) map.get("columns");
		if (columns != null && !columns.isEmpty()) {
			for (int i = 0; i > -1; i++) {
				String orderKey = "order[" + i + "][column]";
				String orderStr = (String) map.remove(orderKey);
				if (!StringUtil.isEmpty(orderStr)) {
					Integer index = Integer.parseInt(orderStr);
					Column column = columns.get(index);
					if (column != null) {
						column.setOrderable(true);
						orderKey = "order[" + i + "][dir]";
						orderStr = (String) map.remove(orderKey);
						if (StringUtil.isEmpty(orderStr)) {
							orderStr = "asc";
						}
						column.setOrderDir(orderStr);
					} else {
						break;
					}
				} else {
					break;
				}
			}
		}
	}

	public PageData() {
		map = new HashMap<String,Object>();
	}

	@Override
	public Object get(Object key) {
		Object obj = null;
		if (map.get(key) instanceof Object[]) {
			Object[] arr = (Object[]) map.get(key);
			obj = request == null ? arr : (request.getParameter((String) key) == null ? arr : arr[0]);
		} else {
			obj = map.get(key);
		}
		return obj;
	}

	public String getString(Object key) {
		return (String) get(key);
	}

	@Override
	public Object put(Object key, Object value) {
		return map.put((String) key, value);
	}

	@Override
	public Object remove(Object key) {
		return map.remove(key);
	}

	public void clear() {
		map.clear();
	}

	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		return map.containsKey(key);
	}

	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return map.containsValue(value);
	}

	public Set entrySet() {
		// TODO Auto-generated method stub
		return map.entrySet();
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return map.isEmpty();
	}

	public Set keySet() {
		// TODO Auto-generated method stub
		return map.keySet();
	}

	@SuppressWarnings("unchecked")
	public void putAll(Map t) {
		// TODO Auto-generated method stub
		map.putAll(t);
	}

	public int size() {
		// TODO Auto-generated method stub
		return map.size();
	}

	public Collection values() {
		// TODO Auto-generated method stub
		return map.values();
	}
	
	public Map<String,Object> getMap(){
		return map;
	}
}
