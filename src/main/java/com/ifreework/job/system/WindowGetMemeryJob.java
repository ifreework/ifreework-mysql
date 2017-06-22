package com.ifreework.job.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.ifreework.common.manager.SpringManager;
import com.ifreework.util.DateUtil;
import com.ifreework.util.WindowsInfoUtil;

public class WindowGetMemeryJob implements Job{
	private static final String REDIS_MEMERY_SCALE_CACHE = "REDIS_MEMERY_SCALE_CACHE1";
	
	/**
	 * 
	 * 描述：定时查询服务器内存
	 * @param arg0
	 * @throws JobExecutionException 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		CacheManager cacheManager = SpringManager.getCacheManager();
		
		String macAddress = WindowsInfoUtil.getMACAddress(); //获取MAC地址
		Double memeryScale = WindowsInfoUtil.getMemeryScale(); 
		
		
		Cache<Object, Object> cache = cacheManager.getCache(REDIS_MEMERY_SCALE_CACHE);
		
		Map<String,Object> map =  (Map<String, Object>) cache.get(getKey(macAddress));
		
		if(map == null){
			String serverName = WindowsInfoUtil.getServerName();//获取计算机名称
			map = new HashMap<String,Object>();
			map.put("serverName",serverName);
		}
		
		List<Map<String,Object>> datas = (List<Map<String,Object>>) map.get("dataSet");
		
		if(datas == null){
			datas = new ArrayList<Map<String,Object>>();
			map.put("dataSet",datas);
		}
		
		if( datas.size() >= 30 ){
			datas.remove(0);
		}
		
		Map<String,Object> memeryMap = new HashMap<String,Object>();
		memeryMap.put(DateUtil.getDate("HH:mm"), memeryScale);
		
		datas.add(memeryMap);
		cache.put(getKey(macAddress), map);
		
	}
	
	private String getKey(String macAddress){
		return "memery-" + macAddress;
	}
	
	/**
	 * 校验超时的缓存数据
	 * @param list
	 */
//	private void validate(List<Map<String,Object>> list){
//		for (Map<String, Object> map : list) {
//			long time = (long) map.get("time");
//			long nowTime = new Date().getTime();
//			if(nowTime - time >= 1000 * 60 * 35 ){
//				list.remove(map);
//			}
//		}
//	}
}
