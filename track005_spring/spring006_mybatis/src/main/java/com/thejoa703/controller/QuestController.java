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
	@RequestMapping(value="/write.quest" , method=RequestMethod.POST)
	public String write_post(  Sboard1Dto dto ,  RedirectAttributes rttr) { 
		String result ="글쓰기 실패";
		if( service.insert(dto)  > 0  ) {  result ="글쓰기 성공"; }
		rttr.addFlashAttribute("success", result);
		return "redirect:/list.quest"; 
	}
	 
	@RequestMapping("/detail.quest") //상세보기
	public String detail(int id , Model model) { 
		model.addAttribute("dto", service.select(id));
		return "quest_board/detail"; 
	}
	
	@RequestMapping(value="/edit.quest" , method=RequestMethod.GET)  //수정폼
	public String edit_get(int id , Model model) {
		model.addAttribute("dto", service.selectUpdateForm(id));
		return "quest_board/edit"; 
	}
	
	@RequestMapping(value="/edit.quest" , method=RequestMethod.POST) //수정기능
	public String edit_post(  Sboard1Dto dto ,  RedirectAttributes rttr) { 
		String result = "비밀번호를 확인해주세요";
		if( service.update(dto)  > 0  ) {  result ="수정 성공"; }
		rttr.addFlashAttribute("success" , result);
		return "redirect:/detail.quest?id=" + dto.getId(); 
	}
	//Q1. 수정기능도    비밀번호를 확인해주세요 알림창 + /detail.quest  경로넘기기
	
	@RequestMapping(value="/delete.quest" , method=RequestMethod.GET) //삭제폼
	public String delete_get() { return "quest_board/delete"; }
	
	@RequestMapping(value="/delete.quest" , method=RequestMethod.POST) //삭제기능
	public String delete_post(Sboard1Dto dto , RedirectAttributes rttr) { 
		String result = "비밀번호를 확인해주세요";
		if( service.delete(dto)  > 0  ) {  result ="삭제 성공"; }
		rttr.addFlashAttribute("success" , result);
		return "redirect:/list.quest"; 
	}
	//Q2. 삭제기능도   비밀번호를 확인해주세요 알림창 + /list.quest  경로넘기기
	
	
	/*  Upload	 */
	// 글쓰기 기능
	@RequestMapping(value="/upload.quest" , method=RequestMethod.POST)
	public String upload_post(  @RequestParam("file") MultipartFile file   
									,  Sboard1Dto dto ,  RedirectAttributes rttr) { 
		String result ="글쓰기 실패";
		if( service.insert2(   file   , dto)  > 0  ) {  result ="글쓰기 성공"; }
		rttr.addFlashAttribute("success", result);
		return "redirect:/list.quest"; 
	}
	
	//updateEdit.quest
	@RequestMapping(value="/updateEdit.quest" , method=RequestMethod.POST) //수정기능
	public String updateEdit_post(  @RequestParam("file") MultipartFile file   
										, Sboard1Dto dto ,  RedirectAttributes rttr) { 
		String result = "비밀번호를 확인해주세요";
		if( service.update2(file, dto)  > 0  ) {  result ="수정 성공"; }
		rttr.addFlashAttribute("success" , result);
		return "redirect:/detail.quest?id=" + dto.getId(); 
	}
	 
}


/*
/list.quest            /view/quest_board/list.jsp 
/write.quest           /view/quest_board/write.jsp    (글쓰기폼)
/detail.quest          /view/quest_board/detailjsp    (상세보기)
/edit.quest            /view/quest_board/edit.jsp     (수정하기폼)
/delete.quest          /view/quest_board/delete.jsp   (삭제하기폼)
*/