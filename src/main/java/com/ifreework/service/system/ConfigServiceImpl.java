package com.ifreework.service.system;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifreework.common.constant.Constant;
import com.ifreework.common.entity.PageData;
import com.ifreework.entity.system.Config;
import com.ifreework.mapper.system.ConfigMapper;
import com.ifreework.util.StringUtil;


@Service("configService")
public class ConfigServiceImpl  implements ConfigService {

	@Autowired
	private ConfigMapper configMapper;

	/**
	 * 批量跟新系统设置数据
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PageData update(PageData pd) {
		// TODO Auto-generated method stub
		PageData resultData = new PageData();
		for (Object key:pd.keySet()) {
			if(!StringUtil.isEmpty(pd.getString(key))){
				Map config = new HashMap();
				config.put("configKey", key);
				config.put("configValue", pd.get(key) );
				configMapper.update(config);
			}
		}
		Config.reset();
		resultData.setResult(Constant.SUCCESS);
		return resultData;
	}


}