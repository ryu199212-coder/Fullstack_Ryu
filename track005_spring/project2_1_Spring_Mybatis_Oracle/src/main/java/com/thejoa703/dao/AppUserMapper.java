package com.thejoa703.dao;

import com.thejoa703.dto.AppUser;
import com.thejoa703.dto.AppUserAuthDto;
import com.thejoa703.dto.AuthDto;

@MbtiDao
public interface AppUserMapper {
	public int 			  insert(AppUser dto );
	public AppUserAuthDto readAuth(AppUser dto );  //email, password, 권한들
	public AppUser        select(AppUser dto ); 
	public AppUser        selectPassword(AppUser dto);
	public int update(AppUser dto );
	public int delete(AppUser dto );
	public int insertAuth(AuthDto dto);
	public int  iddouble(String email);
}

 