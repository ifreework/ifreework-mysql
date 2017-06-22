package com.ifreework.mapper.system;
import java.util.List;
import java.util.Map;


public interface ConfigMapper  {
	public List<Map<String,Object>> queryConfigList();
	public void update(Map<String, String> config);
}
