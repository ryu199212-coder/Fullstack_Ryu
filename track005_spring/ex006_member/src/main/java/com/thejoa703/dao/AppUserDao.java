package com.thejoa703.dao;

import java.util.List;
import com.thejoa703.dto.AppUserDto;

@MbtiDao
public interface AppUserDao {
	public int  insert(AppUserDto dto);
	public int  update(AppUserDto dto);
	public int  delete(AppUserDto dto);
	public List<AppUserDto>  selectAll();
	public AppUserDto  select(int appUserId);
	public AppUserDto  selectEmail(String email);
	public int  selectLogin(AppUserDto dto);
	
	/* upload */
	public int  insert2(AppUserDto dto);
	public int  update2(AppUserDto dto);
	
	/* iddouble */
	public int  iddouble(String email);
	
	/* admin 유저관리  - 전체유저정보 selectAll 
	 * 				  / 아이디주면 해당 유저정보찾기select  
	 * 				  / 수정하기 updateAdmin 
	 * 				  / 삭제하기 deleteAdmin    */
	public int   deleteAdmin(AppUserDto dto);
	public int   updateAdmin(AppUserDto dto);
}
