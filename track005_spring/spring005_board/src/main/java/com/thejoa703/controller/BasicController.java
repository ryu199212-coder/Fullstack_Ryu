package com.thejoa703.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BasicController { 
	
	@RequestMapping("/basic.do")
	public String basic(Model model) {
		//1.해결사-service 
		model.addAttribute("result" , "Hello");
		return "basic.jsp";  //2. 해당view   -- error 없다고 나올것임.    ## 2 오류확인
	}
	
	@RequestMapping("/basic2.do")     //## 1
	public String basic2(Model model) {
		//1.해결사-service 
		model.addAttribute("result" , "Hello");
		return "basic";  //2. 해당view     /view/ +  basic(파일명) + .jsp    ## 0
	}
}
