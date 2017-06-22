package com.ifreework.controller.system;



import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ifreework.common.controller.BaseControllerSupport;
import com.ifreework.common.manager.SpringManager;
import com.ifreework.util.DateUtil;



/**    
 *     
 * 类名称：AttachmentController    
 * 类描述：    
 * 创建人：王宜华    
 * 创建时间：2014-11-26 下午4:46:18    
 * 修改人：王宜华    
 * 修改时间：2014-11-26 下午4:46:18    
 * 修改备注：    
 * @version     
 *     
 */
@Controller
@RequestMapping("/system/memery")
public class MemeryController extends BaseControllerSupport {
	private static final String REDIS_MEMERY_SCALE_CACHE = "REDIS_MEMERY_SCALE_CACHE1";
	
	@RequestMapping()
	public String gotoView(){
		return "/system/server/memery";
	}
	
	
	/**
	 * 获取时间点
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/load")
	@ResponseBody
	private Map<String,Object> load(){
		CacheManager cacheManager = SpringManager.getCacheManager();
		Cache<String, Map<String,Object>> cache = cacheManager.getCache(REDIS_MEMERY_SCALE_CACHE);
		Map<String,Object> memeryMap = new HashMap<String,Object>();
		
		List<String> labels = getTimes();  //获取时间点
		List<List<Double>> dataSet = new ArrayList<List<Double>>();
		List<String> names = new ArrayList<String>();
		
		for (Map<String,Object> map : cache.values()) {
			
			names.add((String) map.get("serverName"));
			
			List<Map<String,Object>> datas = (List<Map<String,Object>>) map.get("dataSet");
			
			for (Map<String, Object> data : datas) {
				memeryMap.putAll(data);
			}
			
			List<Double> dataList = new ArrayList<Double>();
			
			for (String label : labels) {
				Double m = (Double) memeryMap.get(label);
				if(m == null){
					dataList.add(0.0);
				}else{
					dataList.add(m);
				}
			}
			dataSet.add(dataList);
		}
		memeryMap.put("names", names);
		memeryMap.put("labels", labels);
		memeryMap.put("dataSet", dataSet);
		return memeryMap;
	}
	
	
	/**
	 * 获取时间点
	 * @return
	 */
	private List<String> getTimes(){
		List<String> list = new ArrayList<String>();
		Calendar cal = Calendar.getInstance();
		for(int i = 0 ;i < 30 ; i++){
			String time = DateUtil.getDate(cal.getTime(), "HH:mm");
			list.add(0, time);
			cal.add(Calendar.MINUTE, -1);
		}
		return list;
	}
}
