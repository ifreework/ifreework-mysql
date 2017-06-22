package com.ifreework.job.system;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.alibaba.fastjson.JSON;
import com.ifreework.common.manager.SpringManager;
import com.ifreework.common.manager.WebsocketManager;
import com.ifreework.util.DateUtil;

public class WindowSendMemeryJob implements Job{
	private static final String REDIS_MEMERY_SCALE_CACHE = "REDIS_MEMERY_SCALE_CACHE1";
	
	@SuppressWarnings("unchecked")
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		CacheManager cacheManager = SpringManager.getCacheManager();
		Cache<String, Map<String,Object>> cache = cacheManager.getCache(REDIS_MEMERY_SCALE_CACHE);
		Map<String,Object> memeryMap = new HashMap<String,Object>();
		
		List<String> labels = getTimes();  //获取时间点
		List<List<Double>> dataSet = new ArrayList<List<Double>>();
		
		for (Map<String,Object> map : cache.values()) {
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
		
		memeryMap.put("labels", labels);
		memeryMap.put("dataSet", dataSet);
		
		WebsocketManager.send("/topic/memeryScale", JSON.toJSONString(memeryMap));
	}
	
	/**
	 * 获取时间点
	 * @return
	 */
	private List<String> getTimes(){
		List<String> list = new ArrayList<String>();
		Calendar cal = Calendar.getInstance();
		for(int i = 0 ;i < 30 ; i++){
			cal.add(Calendar.MINUTE, -1);
			String time = DateUtil.getDate(cal.getTime(), "HH:mm");
			list.add(0, time);
		}
		return list;
	}
}
