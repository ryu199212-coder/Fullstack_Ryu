package com.thejoa703.dao;

import java.util.HashMap;
import java.util.List;

import com.thejoa703.dto.Sboard1Dto;

@MbtiDao
public interface Sboard1Dao {
	public int insert(Sboard1Dto dto);
	public int update(Sboard1Dto dto);
	public int updateHit(int id);
	public int delete(Sboard1Dto dto);
	public List<Sboard1Dto>  selectAll();
	public Sboard1Dto        select(int id);
	
	/* Upload */ 
	public int insert2(Sboard1Dto dto);
	public int update2(Sboard1Dto dto);

	/* Ajax */
	public List<Sboard1Dto>  selectSearch( HashMap<String, String>  para);
	
	/* Paging */
	public List<Sboard1Dto>  select10(HashMap<String, Object>  para);
	public int        selectTotalCnt();
	
}


/*
■ insert : 
	insert into sboard1 (             ID , APP_USER_ID , BTITLE , BCONTENT , BPASS , BIP)  
	values     (   sboard1_seq.nextval ,  #{appUserId}, #{btitle},#{bcontent},#{bpass} ,  #{bip}  );
■ selectAll
	select * from sboard1  order by id desc 
■ select
	select * from sboard1 where id=#{id} 
■ update
	update  sboard1  
	set     BTITLE=#{btitle} , BCONTENT=#{bcontent} 
	where   id=#{id}   and BPASS= #{bpass}  
■ delete
	delete from sboard1 where   where   id=#{id}   and BPASS= #{bpass}  
*/