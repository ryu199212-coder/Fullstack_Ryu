package com.thejoa703.dao;

import java.util.List;

import com.thejoa703.dto.UserInfoDto;

@MyDao
public interface UserInfoDao {
	public int insert(UserInfoDto dto);
	public List<UserInfoDto> selectAll();
	public UserInfoDto select(int no);
	public int update(UserInfoDto dto);
	public int delete(int i); 
}
