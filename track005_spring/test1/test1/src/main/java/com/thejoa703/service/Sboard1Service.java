package com.thejoa703.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.thejoa703.dto.Sboard1Dto;

public interface Sboard1Service { 
	public List<Sboard1Dto> selectAll() ;
	public Sboard1Dto select(int id);   
	public int insert2( MultipartFile file ,Sboard1Dto dto); 
}
