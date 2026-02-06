package com.thejoa703.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.thejoa703.dao.AppUserDao;
import com.thejoa703.dto.AppUserDto;

@Service
public class AppUserServiceImpl  implements AppUserService{
	@Autowired  AppUserDao   dao;
	@Override public int insert(AppUserDto dto) { return dao.insert(dto); }
	@Override public int update(AppUserDto dto) { return dao.update(dto); }
	@Override public int delete(AppUserDto dto) { return dao.delete(dto); }
	@Override public List<AppUserDto> selectAll() { return dao.selectAll(); }
	@Override public AppUserDto select(int appUserId) { return dao.select(appUserId); } 
	@Override public AppUserDto selectEmail(String email) { return dao.selectEmail(email); }
	@Override public int selectLogin(AppUserDto dto) { return dao.selectLogin(dto); }
	
	/* Upload */
	@Override public int insert2(MultipartFile file, AppUserDto dto) { 
	   String fileName   = null;
	   if(  !file.isEmpty() ) {  // 파일이 비어있는게 아니라면
		   fileName   = file.getOriginalFilename(); // 원본파일이름
		   String uploadPath = "C:/file/";
		   File   img        = new File(uploadPath + fileName);  //java.io.File
		   try { file.transferTo(img); //파일올리기 
		   }catch (IOException e) { e.printStackTrace(); }
	   }else {
		   fileName = "user" + ((int)((Math.random()*7)+1)) + ".png";
	   }

	   dto.setUfile(fileName); 
	   return dao.insert2(dto);
	}
	@Override public int update2(MultipartFile file, AppUserDto dto) { 
		// 기존에 bfile 이 있어서 값이 처리됨.
	   if(  !file.isEmpty() ) {  // 파일이 비어있는게 아니라면
		   String fileName   = file.getOriginalFilename(); // 원본파일이름
		   String uploadPath = "C:/file/";
		   File   img        = new File(uploadPath + fileName);  //java.io.File
		   try { 
			   file.transferTo(img); //파일올리기
			   dto.setUfile(fileName); 
		   }catch (IOException e) { e.printStackTrace(); }
	   }
	   return dao.update2(dto);
	}
	
	@Override public int iddouble(String email) { return  dao.iddouble(email); }
	
	@Override public int deleteAdmin(AppUserDto dto) { return dao.deleteAdmin(dto); }
	@Override public int updateAdmin(AppUserDto dto) { return dao.updateAdmin(dto); }
	
}
