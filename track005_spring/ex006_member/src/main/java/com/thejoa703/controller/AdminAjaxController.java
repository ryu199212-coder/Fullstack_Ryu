package com.thejoa703.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
 
@Controller
@RequestMapping("/api")
@Api(tags = "테스트 API")
public class AdminAjaxController {

	@RequestMapping(value="/hello" , method=RequestMethod.GET) 
    @ApiOperation(value = "Hello API", notes = "간단한 AJAX 테스트용 API")
    public String hello(@RequestParam String name) {
        return "Hello " + name;
    }
}



