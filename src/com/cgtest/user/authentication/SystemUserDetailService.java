package com.cgtest.user.authentication;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.cgtest.registration.model.User;
import com.cgtest.registration.service.UserManager;

public class SystemUserDetailService implements  org.springframework.security.core.userdetails.UserDetailsService {

	private static Logger logger = LoggerFactory
			.getLogger(SystemUserDetailService.class);

	@Autowired
	UserManager userManager ;
	
	public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException {
		try {
			List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
			grantedAuthorities.add(new SimpleGrantedAuthority("supervisor"));
			User user = userManager.getUserByUsername(username);
			AccountDetails acount = new AccountDetails(username, user.getPassword(), true,
					true, true, true, grantedAuthorities);
			return acount;
		} catch (Exception e) {
			logger.error("Spring security certificate user error", e);
			throw new UsernameNotFoundException(e.getMessage());
		}
	}
}
