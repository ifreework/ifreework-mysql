package com.ifreework.common.quartz.factory;

import java.util.List;
import java.util.Map;

import org.quartz.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ifreework.common.quartz.manager.QuartzManager;
import com.ifreework.util.StringUtil;

/**
 * 描述：系统启动时，添加所有的配置的Job任务    
 * @author：wangyh
 * @createDate：2017年5月24日
 * @modify：wangyh    
 * @modifyDate：2017年5月24日 
 * @version 1.0
 */
public class QuartzJobFactory {
	private static Logger logger = LoggerFactory.getLogger(QuartzJobFactory.class);
	
	/**
	 * 
	 * 通过spring初始化该类。
	 * 在该类初始化时，通过配置参数，将所有的job添加到quartz中    
	 *    
	 * @param jobs job的集合
	 * 		  job : jobName :任务名，唯一不可为空;
	 * 				jobClass : 实现org.quartz.Job的类;
	 * 				runTime : job运行时间  规则参考quartz相关文档;
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public QuartzJobFactory(List<Map<String,String>> jobs) throws ClassNotFoundException{
		if(jobs != null && !jobs.isEmpty()){
			for (Map<String, String> job : jobs) {
				String jobName = job.get("jobName");
				String jobClass = job.get("jobClass");
				String runTime = job.get("runTime");
				if(StringUtil.isEmpty(jobName)){
					throw new NullPointerException("jobName can't be null!");
				}
				if(StringUtil.isEmpty(jobClass)){
					throw new NullPointerException("jobClass can't be null!");
				}
				if(StringUtil.isEmpty(runTime)){
					throw new NullPointerException("runTime can't be null!");
				}
				
				Class<Job> cls = (Class<Job>) Class.forName(jobClass);
				QuartzManager.addJob(jobName, cls, runTime);
				
				logger.info("{}[{}] {} has added!",jobName,jobClass,runTime);
			}
		}
	}
}
