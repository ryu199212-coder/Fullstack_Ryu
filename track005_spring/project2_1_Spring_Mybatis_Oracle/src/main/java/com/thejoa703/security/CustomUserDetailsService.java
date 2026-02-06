package com.thejoa703.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.thejoa703.dto.AppUserAuthDto;
import com.thejoa703.service.AppUserSecurityService; 

public class CustomUserDetailsService  implements UserDetailsService {
	
	@Autowired  AppUserSecurityService service;  //##
	
	@Override public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUserAuthDto  dto = service.readAuth(username);   // 해당유저정보를 가져오기: email, password, 권한들
		
		return  dto == null?  null : new CustomUser(dto);   
	}

}
