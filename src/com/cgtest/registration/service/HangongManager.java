package com.cgtest.registration.service;

import com.cgtest.registration.model.Hangong;
import com.cgtest.registration.model.Setting;
import com.cgtest.registration.model.Setting.SettingType;
public interface HangongManager {
	public void save(Hangong hangong);
	public void update(Hangong u);
	public Hangong getHangongbyNo(String hangongNo);
}