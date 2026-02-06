package com.thejoa703.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thejoa703.dto.AppUserDto;
import com.thejoa703.service.AppUserService;

//@Contrller      처리(service) + 화면
@RestController  //처리(service) + 나온값
public class AjaxSearchController {

	@Autowired AppUserService service; 
	
    @RequestMapping("/iddouble")
    public Map<String,Object>  iddouble(@RequestParam String email){
    	Map<String,Object>  result = new HashMap<>();
    	result.put("cnt"  , service.iddouble(email));
    	return result;
    }
    
    /////////////////////////////////////////////
	//    전체유저정보 selectAll 
	//    아이디주면 해당 유저정보찾기select  
	//    수정하기 updateAdmin 
	//    삭제하기 deleteAdmin 
    /////////////////////////////////////////////
    // http://localhost:8282/ex006_member/selectAll   appUserId번호
    @RequestMapping("/selectAll")
    public List<AppUserDto>  selectAll(){
    	return  service.selectAll();
    } 
    /////////////////////////////////////////////
    // http://localhost:8282/ex006_member/select?appUserId=65
    @RequestMapping("/select")
    public Map<String,Object>  select(@RequestParam int appUserId){
    	Map<String,Object>  result = new HashMap<>();
    	result.put("result"  , service.select(appUserId));
    	return result;
    }

    /////////////////////////////////////////////
    // http://localhost:8282/ex006_member/updateAdmin?appUserId=68&mbtiTypeId=2
    // @RequestParam   int appUserId →  ?appUserId=68
    @RequestMapping("/updateAdmin")
    public Map<String, Object>   updateAdmin(@RequestParam int appUserId ,
    										 @RequestParam int mbtiTypeId ){
    	Map<String, Object>   result = new HashMap<>();
    	AppUserDto dto  = new AppUserDto();
    	dto.setAppUserId(appUserId);   dto.setMbtiTypeId(mbtiTypeId);
    	result.put("result", service.updateAdmin(dto));
    	return result;
    }
    
    /////////////////////////////////////////////
    // http://localhost:8282/ex006_member/deleteAdmin?appUserId=68 
    // @RequestParam   int appUserId →  ?appUserId=68
    @RequestMapping("/deleteAdmin")
    public Map<String, Object>   deleteAdmin(@RequestParam int appUserId  ){
    	Map<String, Object>   result = new HashMap<>();
    	AppUserDto dto  = new AppUserDto();
    	dto.setAppUserId(appUserId);    
    	result.put("result", service.deleteAdmin(dto));
    	return result;
    }
     
}
 





