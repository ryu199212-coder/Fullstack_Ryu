package com.thejoa703.controller;

import java.net.URISyntaxException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thejoa703.external.ApiChatGpt;
import com.thejoa703.external.ApiEmailNaver;
import com.thejoa703.external.ApiKmaWeather;
import com.thejoa703.external.KakaoPayService;

import net.nurigo.java_sdk.exceptions.CoolsmsException;

@Controller
@RequestMapping("/api")
public class ApiController {

   ///////////////////////// ChatGpt
   // http://localhost:8484/api/openai
   @Autowired   ApiChatGpt  apiChatGpt;
   
   @GetMapping("/openai")
   public String openai_get() { return "external/openai"; }
   
   @PostMapping(value="/openai" , produces = "application/json;charset=UTF-8")
   @ResponseBody
   public String openai(@RequestBody String content) {
      return  apiChatGpt.getAIResponse(content);
   }

   ///////////////////////// Weather
   // http://localhost:8484/api/weather
   @Autowired   ApiKmaWeather     apiKmaWeather;
   
   @GetMapping("/weather")
   public String kma_get() { return "external/weather"; }
   
   @GetMapping(value="/weatherapi" , produces = "application/xml;charset=UTF-8")
   @ResponseBody
   public String kma() throws URISyntaxException {
      return  apiKmaWeather.getWeatherResponse();
   }


   ///////////////////////// Email
   @Autowired  ApiEmailNaver   apiEmailNaver;
   
   @GetMapping("/mail")
   public String mail_get() { return "external/mail"; }
   
   @PostMapping(value="/mail" ) 
   public String mail(String subject, String content, String email){ 
      apiEmailNaver.sendMail(subject , content , email);
      return "external/mail_result";
   }
   
   ///////////////////////// PostCode
   @GetMapping("/postcode")
   public String postcode() { return "external/postcode"; }
   
   ///////////////////////// Maps
   @GetMapping("/maps")
   public String maps() { return "external/navermap"; } 
   
   ///////////////////////// Chatbot
   @GetMapping("/chatbot")
   public String chatbot() { return "external/chatbot"; }
   
   ///////////////////////// KakaoController
   @Autowired KakaoPayService kakaoPayService;
   
   @GetMapping("/pay/kakao")
   public String kakaoPay() {return "external/kakoPay";}
   
   @GetMapping("/pay/ready")
   public String kakaoPayReady() {
	   Map<String, String>  result = kakaoPayService.kakaoPayReady();
	   return "redirect:" + result.get("redirectUrl");
   }
   
   @GetMapping("/pay/success")
   public String kakaoPaySuccess(@RequestParam("pg_token") String pgToken, Model model) {
	   Map<String, Object> result = kakaoPayService.kakaoPayApprove(pgToken);
	   model.addAttribute("result", result);
	   return "external/kakaoPaySuccess";
   }
   
   @GetMapping("/pay/fail")
   @ResponseBody
   public String kakaoPayFail() {
	   
	   return "결제실패";
   }
   
   @GetMapping("/pay/cancel")
   @ResponseBody
   public String kakaoPayCancel() {
	   
	   return "결제취소";
   }
}









