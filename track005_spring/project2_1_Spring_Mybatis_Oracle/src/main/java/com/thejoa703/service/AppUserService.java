package com.thejoa703.service;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.thejoa703.dto.AppUserAuthDto;
import com.thejoa703.dto.AppUserDto;
import com.thejoa703.dto.AuthDto;

public interface AppUserService {
	public int  insert(AppUserDto dto);
	public int  update(AppUserDto dto);
	public int  delete(AppUserDto dto);
	public List<AppUserDto>  selectAll();
	public AppUserDto  select(int appUserId);
	public AppUserDto  selectEmail(String email);
	public int  selectLogin(   AppUserDto dto); 
	
	
	/* Upload */
	public int  insert2( MultipartFile file    ,AppUserDto dto);
	public int  update2( MultipartFile file    ,AppUserDto dto);

	/* iddouble */
	public int  iddouble(String email);
	 
	/* admin 유저관리  - 전체유저정보 selectAll 
	 * 				  / 아이디주면 해당 유저정보찾기select  
	 * 				  / 수정하기 updateAdmin 
	 * 				  / 삭제하기 deleteAdmin    */
	public int   deleteAdmin(AppUserDto dto);
	public int   updateAdmin(AppUserDto dto); 

	/* security */
	/* security */
	public int   insertAuth(AuthDto dto);
	public AppUserAuthDto readAuth (String email);
	
	public int insert3(MultipartFile file, AppUserDto dto  );
	public int delete3(AppUserDto dto);
	public int update3(MultipartFile file, AppUserDto dto);
}
