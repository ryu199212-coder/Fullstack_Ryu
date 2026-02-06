package com.thejoa703.controller;
 
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thejoa703.dto.Sboard1Dto;
import com.thejoa703.service.Sboard1Service;

@RestController    // @Controller + @ResponseBody
public class SearchController {

	@Autowired   Sboard1Service service;
	
	@RequestMapping("/searchTest") 
	public String hi() { 
		// 처리하고
		return "Hi"; //값줄께
	} 
	
	// http://localhost:8282/spring005_board/selectSearch?search=t 
	@RequestMapping("/selectSearch") 
	public List<Sboard1Dto> selectSearch( @RequestParam("search") String search ) {  
		return service.selectSearch(search);  //값줄께
	}  
}

 
//////// step1)
/*
@Controller
public class SearchController {

	@Autowired   Sboard1Service service;
	
	@RequestMapping("/searchTest")
	@ResponseBody
	public String hi() {
		// 처리하고
		return "Hi";  //값줄께
	} 
	
}
*/