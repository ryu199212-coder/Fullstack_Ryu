package com.thejoa703.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thejoa703.dto.AppUser;
import com.thejoa703.service.AppUserSecurityService;

@Controller
@RequestMapping("/security/*")
public class AppUserSecurityController {
	
	@Autowired  AppUserSecurityService  service; //##
	 
	
    @RequestMapping("/iddouble")
    @ResponseBody
    public Map<String,Object>  iddouble(@RequestParam String email){
    	System.out.println("..........." + email);
    	Map<String,Object>  result = new HashMap<>();
    	result.put("cnt"  , service.iddouble(email));
    	return result;
    } 
	
	
	////// 멤버들 등급페이지
	@RequestMapping("/all") public String all() {  return "/member/all";   } 
	@RequestMapping("/member") public String member() {  return "/member/member";   }

	////// 회원가입
	@RequestMapping("/join") //회원가입폼
	public String joinForm() {  return "/member/join";   }
	
	
	@PreAuthorize("isAnonymous()")  //회원가입기능
	@RequestMapping(value="/join" , method=RequestMethod.POST , headers=("content-type=multipart/*")) 
	public String join( @RequestParam("file")  MultipartFile file, AppUser dto , RedirectAttributes rttr) {  
		
		String result = "회원가입 실패";
		
		if( service.insert(file, dto) > 0 ) {  result ="회원가입 성공"; }
		rttr.addFlashAttribute("success" , result);
		return "redirect:/security/login";   
	}
	
	////// 로그인
	@RequestMapping("/login") //로그인폼
	public String loginForm() {  return "/member/login";   }
	
	@RequestMapping("/fail") //로그인실패
	public String fail( HttpServletResponse  response , RedirectAttributes  rttr) {  
		String result ="로그인 실패";
		rttr.addFlashAttribute("loginError" , result);
		return "redirect:/security/login";   
	} 
	
	////// 마이페이지	
	@RequestMapping("/mypage") //마이페이지 - 로그인정보 Principal
	public String mypage( Principal  principal , Model model ) {  
		model.addAttribute("dto" ,  service.selectEmail(  principal.getName()   ));  //##
		return "/member/mypage";   
	} 
	
	////////////////////////////////////
	@RequestMapping("/update")
	public String updateForm(  Principal  principal , Model model ) {
		model.addAttribute("dto" , service.selectEmail( principal.getName()));
		return "/member/edit";
	}
	 
	@PreAuthorize("isAuthenticated()")  // 로그인을 했다면
	@RequestMapping(value="/update" , method=RequestMethod.POST  , headers=("content-type=multipart/*"))  //수정기능
	public String update( @RequestParam("file")  MultipartFile file, AppUser dto , RedirectAttributes rttr  ) {  
		
		String result ="비밀번호를 확인해주세요";
		if(  service.update(file, dto)  > 0 ) {   result="수정 성공";  }
		rttr.addFlashAttribute("success" , result);
		return "redirect:/security/mypage";  
	}
	//////////
	@RequestMapping("/delete")
	public String delete(  Principal  principal , Model model ) {
		model.addAttribute("dto" , service.selectEmail( principal.getName()));
		return "/member/delete";
	}
	
	@PreAuthorize("isAuthenticated()") 
	@RequestMapping(value="/delete" , method=RequestMethod.POST) //삭제기능
	public String delete_post(AppUser dto , RedirectAttributes rttr 
			,HttpServletRequest  request ,HttpServletResponse  response) { 
		
		String result = "비밀번호를 확인해주세요";
		if( service.delete(dto)  > 0  ) {  // 삭제 성공 → 로그아웃 처리 
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        if (auth != null) { new SecurityContextLogoutHandler().logout(request, response, auth); }
	        result = "회원탈퇴가 완료되었습니다.";
			rttr.addFlashAttribute("success" , result); 
			return "redirect:/security/login"; 
	        
		} else {
			result = "비밀번호를 확인해주세요.";
			rttr.addFlashAttribute("deleteError", result);
			return "redirect:/security/mypage";
		}
		
	}  
}






