package com.thejoa703.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thejoa703.dto.Sboard1Dto;
import com.thejoa703.service.Sboard1Service;

@Controller
public class QuestController { 
	
	@Autowired   Sboard1Service service;
	/////////////////////////////////////////
	@RequestMapping("/list.quest")   //       
	public String list(Model model) { 
		model.addAttribute("list", service.selectAll());   // 처리하고
		return "quest_board/list";   //해당화면    /view/ 폴더안에    +  파일명    + .jsp
	}
	// 글쓰기 폼
	@RequestMapping(value="/write.quest" , method=RequestMethod.GET)
	public String write_get() { return "quest_board/write"; }
	// 글쓰기 기능
	@RequestMapping(value="/upload.quest" , method=RequestMethod.POST)
	public String upload_post(  @RequestParam("file") MultipartFile file   
									,  Sboard1Dto dto ,  RedirectAttributes rttr) { 
		String result ="글쓰기 실패";
		if( service.insert2(   file   , dto)  > 0  ) {  result ="글쓰기 성공"; }
		rttr.addFlashAttribute("success", result);
		return "redirect:/list.quest"; 
	}
	 
	@RequestMapping("/detail.quest") //상세보기
	public String detail(int id , Model model) { 
		model.addAttribute("dto", service.select(id));
		return "quest_board/detail"; 
	}
 
   
}

 