package com.ifreework.entity.system;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

import com.ifreework.common.constant.CacheConstant;
import com.ifreework.common.manager.SpringManager;
import com.ifreework.mapper.system.ConfigMapper;

public class Config implements Serializable{
	
	/**    
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）    
	 *    
	 * @version 1.0    
	 */    
	
	private static final long serialVersionUID = -4773165368555660431L;

	public static final String SYSTEM_NAME = "system_name"; //系统名称
	
	public static final String BUTTON_AUTH_ENABLE = "button_auth_enable"; //是否启用按钮权限
	
	public static final String FILE_PATH = "file_path"; //本地文件保存路径
	public static final String FILE_TEMP_PATH = "file_temp_path"; //临时文件保存路径
	
	
	public static final String FTP_ENABLE = "ftp_enable";  // 是否启用ftp服务器
	
	public static final String FTP_USERNAME = "ftp_username"; //ftp用户
	public static final String FTP_PASSWORD = "ftp_password"; //ftp密码
	public static final String FTP_ADDRESS = "ftp_address"; //ftp地址
	public static final String FTP_PORT = "ftp_port"; //ftp密码
	
	public static final String RESET_PWD = "reset_pwd"; //用户初始密码
	
	
	public static final String MAIL_SMTP_HOST = "mail_smtp_host"; //邮箱smtp地址
	public static final String MAIL_SMTP_PORT = "mail_smtp_port"; //邮箱smtp地址端口
	public static final String MAIL_SMTP_AUTH = "mail_smtp_auth"; //
	public static final String MAIL_STORE_PROTOCOL = "mail_store_protocol"; //
	public static final String MAIL_STORE_HOST = "mail_store_host"; //邮箱pop3地址
	public static final String MAIL_STORE_PORT = "mail_store_port"; //邮箱pop3地址端口
	public static final String MAIL_USER = "mail_user"; //邮箱用户名
	public static final String MAIL_PASSWORD = "mail_password"; //邮箱密码

	
	private static Config config;
	private Map<String,Object> map;
	
	private Config(Map<String,Object> map){
		this.map = map;
	}
	
	/**
	 * 
	 * 描述：单例模式下初始化Config
	 * @Title: init
	 * @param 
	 * @return   
	 * @throws
	 */
	public static Config init(){
		CacheManager cacheManager = SpringManager.getCacheManager();
		Cache<String,Config> cache = cacheManager.getCache(CacheConstant.CONFIG_CACHE_NAME.toString());
		config = cache.get(CacheConstant.CONFIG_CACHE_KEY_NAME.toString());
		
		if(config == null){
			ConfigMapper configMapper = (ConfigMapper) SpringManager.getBean("configMapper");
			List<Map<String,Object>> list = configMapper.queryConfigList();
			Map<String,Object> map = new HashMap<String,Object>();
			for(Map<String,Object> item : list ){
				map.put((String) item.get("CONFIG_KEY"), item.get("CONFIG_VALUE"));
			}
			config = new Config(map);
			cache.put(CacheConstant.CONFIG_CACHE_KEY_NAME.toString(), config);
		}
		return config;
	}
	
	public String get(String key){
		return (String) map.get(key);
	}
	
	
	/**
	 * 
	 * 描述：当cache被修改后，清空缓存
	 * @Title: reset
	 * @param 
	 * @return   
	 * @throws
	 */
	public static void reset(){
		CacheManager cacheManager = SpringManager.getCacheManager();
		Cache<String,Config> cache = cacheManager.getCache(CacheConstant.CONFIG_CACHE_NAME.toString());
		cache.clear();
	}
}


