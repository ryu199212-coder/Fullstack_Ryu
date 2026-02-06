package com.thejoa703.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thejoa703.dto.AppUserDto;
import com.thejoa703.service.AppUserService;

@Controller
@RequestMapping("/security/*")
public class SecurityController {
	
	@Autowired AppUserService service;
   
	   @RequestMapping("/all")
	   public String all() { return "member/all"; }
	   
	   @RequestMapping("/member")
	   public String member() { return "member/member"; }
	   
	   @RequestMapping("/admin")
	   public String admin() { return "member/admin"; }
	   
	   @RequestMapping("/login")
	   public String login() { return "member/login"; }
	   
	   @RequestMapping("/fail")
	   public String fail() { return "member/fail"; }
	   
		/*
		 * @RequestMapping("/mypage") public String mypage() { return "member/mypage2";
		 * }
		 */
	   @RequestMapping("/mypage") 
	   public String mypage(Principal principal, Model model) { 
		   String email = principal.getName();
		   model.addAttribute("dto", service.selectEmail(email));
		   return "member/mypage2";
	   }
	   

	   @RequestMapping(value="/join", method=RequestMethod.GET)
	   public String join_get() { return "member/join";}

		

	   @PreAuthorize("isAnonymous()") 
		@RequestMapping(value="/join"
		, method=RequestMethod.POST
		, headers=("content-type=multipart/*"))
		public String join(
			@RequestParam("file") MultipartFile file, AppUserDto dto, Model model) { // 🚨 Model로 변경
			
			String successMsg = "회원가입 실패";
	        
			if(service.insert3(file, dto) > 0) { 
	            successMsg = "회원가입 성공! 이제 로그인해 주세요.";
	        }

			model.addAttribute("joinSuccess", successMsg);
	        
			return "member/login"; 
		}
	   

}
