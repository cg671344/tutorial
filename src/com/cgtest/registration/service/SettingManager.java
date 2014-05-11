package com.cgtest.registration.service;

import com.cgtest.registration.model.Setting;
import com.cgtest.registration.model.Setting.SettingType;
public interface SettingManager {
	public void update(Setting setting);
	public Setting getUserByUsername(SettingType type);
}