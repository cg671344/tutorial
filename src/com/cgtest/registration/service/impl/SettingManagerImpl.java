package com.cgtest.registration.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cgtest.registration.dao.SettingDao;
import com.cgtest.registration.model.Setting;
import com.cgtest.registration.model.Setting.SettingType;
import com.cgtest.registration.service.SettingManager;

@Component("settingManager")
@Transactional
public class SettingManagerImpl implements SettingManager {
	
	@Autowired
	private SettingDao settingDao;
	
	public void update(Setting setting){
		settingDao.save(setting);
	}
	public Setting getUserByUsername(SettingType type){
		return settingDao.getSettingByType(type);
	}
}
