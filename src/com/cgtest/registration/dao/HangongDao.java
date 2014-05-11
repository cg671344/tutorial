package com.cgtest.registration.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.cgtest.registration.model.Hangong;

@Repository("hangongDao")
public class HangongDao{
	
	@Resource
	private HibernateTemplate hibernateTemplate; 
	
	public void save(Hangong u) {
		hibernateTemplate.save(u);
	}

	public void update(Hangong u) {
		hibernateTemplate.update(u);
	}
	
	public Hangong getHangongbyNo(String hangongNo) {
		@SuppressWarnings("unchecked")
		List<Hangong> list = (List<Hangong>)this.hibernateTemplate.find("from Hangong u where u.hangongNo = ? ",hangongNo);
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}
}
