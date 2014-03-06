package com.cgtest.user.authentication;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class AccountDetails extends User{  
	private static final long serialVersionUID = -4254304239111440962L;

	public AccountDetails(String username, String password, boolean enabled,  
            boolean accountNonExpired, boolean credentialsNonExpired,  
            boolean accountNonLocked,  
            Collection<? extends GrantedAuthority> authorities) {  
        super(username, password, enabled, accountNonExpired, credentialsNonExpired,  
                accountNonLocked, authorities);  
    }  
}  