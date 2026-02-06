package com.thejoa703.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.thejoa703.security.CustomUser; 
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thejoa703.dto.AppUserDto;
import com.thejoa703.service.AppUserService;

@Controller
public class AppUserController {
	@Autowired AppUserService service; 

	@RequestMapping("/list.users")   //       
	public String list(Model model) { 
		model.addAttribute("list", service.selectAll());   // 처리하고
		return "member/list";   //해당화면
	}
	// 글쓰기 폼
	@RequestMapping(value="/join.users" , method=RequestMethod.GET)
	public String join_get() { return "member/join"; }
	// 글쓰기 기능
	@RequestMapping(value="/join.users" , method=RequestMethod.POST)
	public String join_post( AppUserDto dto ,  RedirectAttributes rttr) { 
		String result ="회원가입 실패";
		if( service.insert(dto)  > 0  ) {  result ="회원가입 성공"; }
		rttr.addFlashAttribute("success", result);
		return "redirect:/login.users"; 
	}
	 
	@RequestMapping("/login.users")  
	public String login_get(  ) {  
		return "member/login"; 
	}
	
	@RequestMapping(value="/login.users" , method=RequestMethod.POST)
	public String login_post( AppUserDto dto ,  HttpServletRequest  request,  RedirectAttributes rttr) { 
		String result ="로그인 실패";
		int ok = service.selectLogin(dto);
		if( ok  == 1 ) {  
			HttpSession session = request.getSession();
			session.setAttribute("email"     , dto.getEmail() ); 
			result ="로그인 성공"; 
		}
		rttr.addFlashAttribute("success", result);
		return  ok==1? "redirect:/mypage.users" : "redirect:/login.users" ; 
	}
	
	@RequestMapping("/mypage.users") 
	public String mypage( HttpServletRequest  request , Model model) { 
		HttpSession session = request.getSession(); 
		String email = (String)session.getAttribute("email"); 
		model.addAttribute("dto", service.selectEmail(email)); 
		return "member/mypage"; 
	}
	@RequestMapping("/logout.users") 
	public String mypage( HttpServletRequest  request  ) {  
		HttpSession session = request.getSession();  
		session.invalidate();  
		return "redirect:/login.users"; 
	} 
	
	@RequestMapping("/edit.users")  
	public String edit_get( int appUserId, Model model ) {  
		model.addAttribute("dto",  service.select(appUserId));
		return "member/edit"; 
	}  
	@RequestMapping(value="/edit.users" , method=RequestMethod.POST) //수정기능
	public String edit_post(  AppUserDto dto ,  RedirectAttributes rttr) { 
		String result = "비밀번호를 확인해주세요";
		if( service.update(dto)  > 0  ) {  result ="수정 성공"; }
		rttr.addFlashAttribute("success" , result);
		return "redirect:/mypage.users"; 
	}
	 


	@RequestMapping("/delete.users")
	public String delete_get(Model model) {
	    
	    // 1. 현재 로그인된 사용자 인증 정보 가져오기
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    
	    if (authentication != null && authentication.isAuthenticated()) {
	        Object principal = authentication.getPrincipal();

	        // 🚨 CustomUser 타입으로 검사
	        if (principal instanceof com.thejoa703.security.CustomUser) {
	            
	            // 🚨 CustomUser로 캐스팅
	            CustomUser customUser = (CustomUser) principal;
	            
	            // CustomUser 내부의 AppUserAuthDto (필드 이름: dto)를 사용
	            com.thejoa703.dto.AppUserAuthDto authDto = customUser.getDto(); 
	            
	            // AppUserAuthDto에서 appUserId 추출 (필드가 있다고 가정)
	            // 만약 AppUserAuthDto에 appUserId 필드가 없다면 이 코드는 수정되어야 합니다.
	            Integer appUserId = authDto.getAppUserId(); 
	            
	            // Model에 담아 JSP로 전달
	            model.addAttribute("appUserId", appUserId);
	            
	            System.out.println("DEBUG: delete_get에서 추출된 ID: " + appUserId);
	            
	        } else {
	            // 익명 사용자이거나 예상치 못한 타입일 경우
	             if (!(principal instanceof String)) {
	                System.err.println("DEBUG: 예상치 못한 principal 타입: " + principal.getClass().getName());
	             }
	        }
	    } else {
	        return "redirect:/login.users";
	    }
	    
	    return "delete"; 
	}
	/*  UPLOAD	 */
	@RequestMapping(value="/uploadJoin.users" , method=RequestMethod.POST)
	public String uploadJoin_post( @RequestParam("file") MultipartFile file   
			,  AppUserDto dto ,  RedirectAttributes rttr) { 
		String result ="회원가입 실패";
		if( service.insert2(file,dto)  > 0  ) {  result ="회원가입 성공"; }
		rttr.addFlashAttribute("success", result);
		return "redirect:/login.users"; 
	}
	 
	@RequestMapping(value="/uploadEdit.users" , method=RequestMethod.POST) //수정기능
	public String uploadEdit_post(  @RequestParam("file") MultipartFile file   
			,  AppUserDto dto ,  RedirectAttributes rttr) { 
		String result = "비밀번호를 확인해주세요";
		if( service.update3(file,dto)  > 0  ) {  result ="수정 성공"; }
		rttr.addFlashAttribute("success" , result);
		return "redirect:/mypage.users"; 
	} 
	 
}



