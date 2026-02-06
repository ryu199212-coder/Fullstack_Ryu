package com.thejoa703.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.thejoa703.dao.AppUserDao;
import com.thejoa703.dto.AppUserAuthDto;
import com.thejoa703.dto.AppUserDto;
import com.thejoa703.dto.AuthDto;

@Service
public class AppUserServiceImpl  implements AppUserService{
	@Autowired  AppUserDao   dao;
	//@Autowired  PasswordEncoder pwencoder;
	@Autowired @Qualifier("passwordEncoder") PasswordEncoder pwencoder;
	
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
		   try { file.transferTo(img); }//파일올리기 
		   catch (IOException e) { e.printStackTrace(); }
		   
	   }else { fileName = "user" + ((int)((Math.random()*7)+1)) + ".png"; }

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
	
	/* security */
	/* security */
	@Override public int insertAuth(AuthDto dto) { return dao.insertAuth(dto); }
	@Override public AppUserAuthDto readAuth(String email) {
		AppUserAuthDto dto = new AppUserAuthDto();  dto.setEmail(email);
		return dao.readAuth(dto);
	}

	@Transactional
	@Override public int insert3(MultipartFile file, AppUserDto dto  ) { 
		   //1. 권한 (ROLE_MEMBER)
		   AuthDto adto = new AuthDto();  adto.setEmail(  dto.getEmail());   adto.setAuth("ROLE_MEMBER");
		   int step1 = dao.insertAuth(adto);  
		   //2. 해당유저 회원가입
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
		   dto.setPassword( pwencoder.encode(dto.getPassword()));
		   dto.setUfile(fileName); 
		   return dao.insert2(dto);
		}
	 
    @Override public int delete3(AppUserDto dto) {
        // DB에서 사용자 정보 조회
        AppUserAuthDto ddto = new AppUserAuthDto(); ddto.setEmail(dto.getEmail());
        AppUserAuthDto dbUser = dao.readAuth(ddto); 
        
        if (dbUser == null) { return 0; }// 사용자 없음

        // 입력한 비밀번호(raw)와 DB 비밀번호(encoded) 비교
        // 비밀번호 일치 → 삭제 진행
        if (pwencoder.matches( dto.getPassword(),  dbUser.getPassword())) {  return dao.deleteAdmin(dto); }
        else {  return 0; }// 비밀번호 불일치
    }
    
	@Override public int update3(MultipartFile file, AppUserDto dto) { 
        // DB에서 사용자 정보 조회
        AppUserAuthDto ddto = new AppUserAuthDto(); ddto.setEmail(dto.getEmail()); 
        System.out.println("............update"+  dto);
        System.out.println("............update"+  ddto);
        AppUserAuthDto dbUser = dao.readAuth(ddto);  
        System.out.println("............update"+  dbUser);
        if (dbUser == null) { return 0; }// 사용자 없음 
        // 입력한 비밀번호(raw)와 DB 비밀번호(encoded) 비교 
        
        if (pwencoder.matches( dto.getPassword(),  dbUser.getPassword())) { 
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
			   return dao.update3(dto);
        }else {  return 0; }// 비밀번호 불일치
	} 
}
