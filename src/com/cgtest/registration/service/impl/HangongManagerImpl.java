package com.cgtest.registration.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cgtest.registration.dao.HangongDao;
import com.cgtest.registration.dao.SettingDao;
import com.cgtest.registration.model.Hangong;
import com.cgtest.registration.model.Setting;
import com.cgtest.registration.model.Setting.SettingType;
import com.cgtest.registration.service.HangongManager;
import com.cgtest.registration.service.SettingManager;

@Component("hangongManager")
@Transactional
public class HangongManagerImpl implements HangongManager {
	
	@Autowired
	private HangongDao hangongDao;
	
	public void save(Hangong hangong){
		hangongDao.save(hangong);
	}
	
	public void update(Hangong u) {
		hangongDao.update(u);
	}
	
	public Hangong getHangongbyNo(String hangongNo){
		return hangongDao.getHangongbyNo(hangongNo);
	}
}
