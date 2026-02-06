package com.thejoa703.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	@Override public int insert(AppUserDto dto) { return dao.insert(dto); }
	@Override public int update(AppUserDto dto) { return dao.update(dto); }
	@Override public List<AppUserDto> selectAll() { return dao.selectAll(); }
	@Override public AppUserDto select(int appUserId) { return dao.select(appUserId); } 
	@Override public AppUserDto selectEmail(String email) { return dao.selectEmail(email); }
	@Override public int selectLogin(AppUserDto dto) { return dao.selectLogin(dto); }
	@SuppressWarnings("deprecation")
	
	
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
	
	@Override
    public int delete(AppUserDto inputDto) {
        
        // ----------------------------------------------------
        // 🚨 1단계: 입력 데이터 확인 및 ID 유효성 검사
        // ----------------------------------------------------
        System.out.println("DEBUG: 1. 입력 DTO ID: " + inputDto.getAppUserId()); 
        System.out.println("DEBUG: 1. 입력 DTO PW (평문): " + inputDto.getPassword()); 

        if (inputDto.getAppUserId() == null || inputDto.getAppUserId() == 0) {
            System.out.println("DEBUG: 1. ID가 유효하지 않아 삭제 실패");
            return 0;
        }

        // ----------------------------------------------------
        // 🚨 2단계: DB 조회 (해시된 비밀번호 포함)
        // ----------------------------------------------------
        // dao.select(int appUserId) 메서드 사용
        AppUserDto dbUser = dao.select(inputDto.getAppUserId()); 
        
        if (dbUser == null) {
            System.out.println("DEBUG: 2. DB에서 사용자 (ID: " + inputDto.getAppUserId() + ")를 찾을 수 없음");
            return 0;
        }
        System.out.println("DEBUG: 2. DB 조회 성공. 해시 PW: " + dbUser.getPassword()); 

        // ----------------------------------------------------
        // 🚨 3단계: 비밀번호 비교 (PasswordEncoder 사용)
        // ----------------------------------------------------
        // pwencoder.matches(평문 PW, 해시 PW)
        boolean passwordMatches = pwencoder.matches(
            inputDto.getPassword(), 
            dbUser.getPassword()   
        );
        System.out.println("DEBUG: 3. 최종 PW 일치 여부: " + passwordMatches); 

        if (passwordMatches) {
            // 4. 비밀번호 일치: 삭제 실행
            System.out.println("DEBUG: 4. 비밀번호 일치. DAO delete 호출.");
            return dao.delete(dbUser); 
        } else {
            // 4. 비밀번호 불일치: 0 반환
            System.out.println("DEBUG: 4. 비밀번호 불일치. 삭제 실패.");
            return 0;
        }
    }

	
	@Override public int iddouble(String email) { return  dao.iddouble(email); }
	
	@Override public int deleteAdmin(AppUserDto dto) { return dao.deleteAdmin(dto); }
	@Override public int updateAdmin(AppUserDto dto) { return dao.updateAdmin(dto); }
	
	/* security */
	/* security */
	@Override public int   insertAuth(AuthDto dto) {return dao.insertAuth(dto);}
	@Override public AppUserAuthDto readAuth(String email) {
		AppUserAuthDto dto = new AppUserAuthDto(); dto.setEmail(email);
		return dao.readAuth(dto);
	}
	@Autowired PasswordEncoder pwencoder;
	@Transactional
	@Override
	public int insert3(MultipartFile file, AppUserDto dto) {
	   AuthDto adto = new AuthDto(); adto.setEmail(dto.getEmail()); adto.setAuth("ROLE_MEMBER");
	   int step1 = dao.insertAuth(adto);
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
	   dto.setPassword(pwencoder.encode(dto.getPassword()));
	   dto.setUfile(fileName); 
	   return dao.insert3(dto);}

	@Override public int update3(MultipartFile file, AppUserDto dto) { 
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
	}
	
}
