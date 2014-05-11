package com.cgtest.registration.dao;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import com.cgtest.registration.dao.UserDao;
import com.cgtest.registration.model.User;

@Repository("userDao")
public class UserDao{
	
	@Resource
	private HibernateTemplate hibernateTemplate; 
	
	public void save(User u) {
		hibernateTemplate.saveOrUpdate(u);
	}

	public boolean checkUserExistsWithName(String username) {
		@SuppressWarnings("unchecked")
		List<User> users = this.hibernateTemplate.find("from User u where u.username = ?",username);
		if(users.size() > 0) {
			return true;
		}else{
			return false;
		}
		/*long count = (Long)hibernateTemplate.getSessionFactory()
					.getCurrentSession().createQuery("select count(*) from User u where u.username = :username")
					.setString("username", username).uniqueResult();
		if(count > 0) return true;
		return false;*/
	}

	@SuppressWarnings("unchecked")
	public List<User> getUsers() {
		return (List<User>)this.hibernateTemplate.find("from User");
	}

	public User loadById(String id) {
		return (User)this.hibernateTemplate.get(User.class, id);
	}
	
	
	public User getUserByUsername(String username) {
		@SuppressWarnings("unchecked")
		List<User> list = (List<User>)this.hibernateTemplate.find("from User u where u.username = ? ",username);
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	
}
