package com.thejoa703.service;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.thejoa703.dao.Sboard1Dao;
import com.thejoa703.dto.Sboard1Dto;

@Service   
public class Sboard1ServiceImpl  implements Sboard1Service{ 
	   @Autowired  Sboard1Dao   dao; 
	   
	   @Override public List<Sboard1Dto> selectAll() { return dao.selectAll(); }
	   @Override  public Sboard1Dto select(int id)    { dao.updateHit(id);   return dao.select(id); }    
	   @Override public int insert2(MultipartFile file, Sboard1Dto dto) {
		   if(!file.isEmpty()) {  // 파일이 비어있는게 아니라면
			   String fileName   = file.getOriginalFilename(); // 원본파일이름
			   String uploadPath = "C:/file/";
			   File   img        = new File(uploadPath + fileName);  //java.io.File
			   try { 
				   file.transferTo(img); //파일올리기
				   dto.setBfile(fileName); 
			   }catch (IOException e) { e.printStackTrace(); }
		   }
		   try { dto.setBip(  InetAddress.getLocalHost().getHostAddress()  ); } 
		   catch (UnknownHostException e) { e.printStackTrace(); }
		   return dao.insert2(dto); 
		}
	 
 
}


