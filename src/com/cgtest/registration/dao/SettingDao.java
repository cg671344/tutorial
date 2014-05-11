package com.cgtest.registration.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.cgtest.registration.model.Setting;
import com.cgtest.registration.model.Setting.SettingType;

@Repository("settingDao")
public class SettingDao{
	
	@Resource
	private HibernateTemplate hibernateTemplate; 
	
	public void save(Setting u) {
		hibernateTemplate.saveOrUpdate(u);
	}
	
	public Setting getSettingByType(SettingType type) {
		@SuppressWarnings("unchecked")
		List<Setting> list = (List<Setting>)this.hibernateTemplate.find("from Setting s where s.name = ? ", type);
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}
}
