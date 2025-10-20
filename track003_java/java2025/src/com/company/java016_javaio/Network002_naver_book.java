package com.company.java016_javaio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class Network002_naver_book {
	public static void main(String[] args) {
		//#1. URL
		String apiurl;
		try {
			apiurl = "https://openapi.naver.com/v1/search/blog.json?query="
					+ URLEncoder.encode("삼총사", "UTF-8");
		URL url = new URL(apiurl);
			
		//#2. HttpURLConnection
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		
		//#3. 요청설정
		conn.setRequestMethod("GET");
		conn.setRequestProperty("X-Naver-Client-Id", "0OURtR8HMXxp5CL5pVdi");
		conn.setRequestProperty("X-Naver-Client-Secret", "qtVfwbBEBl");
		
		//#4. 응답확인
		BufferedReader br;
		if(conn.getResponseCode()==200) {
			br= new BufferedReader(new InputStreamReader(conn.getInputStream()));
		}else {
			br= new BufferedReader(new InputStreamReader(conn.getInputStream()));
		}
		
		//#5. 응답데이터
		String line="";
		StringBuffer sb = new StringBuffer();
		while((line = br.readLine())!= null) {sb.append(line + "\n");}
		System.out.println(sb.toString());
		br.close(); conn.disconnect();
		
		}catch (MalformedURLException e) {  e.printStackTrace(); 
		}catch (UnsupportedEncodingException e) { e.printStackTrace();
		}catch (IOException e) {e.printStackTrace();	
		}
	}
}
/*
1. id/secret
0OURtR8HMXxp5CL5pVdi
qtVfwbBEBl

2. 요청 URL
https://openapi.naver.com/v1/search/blog.xml	
https://openapi.naver.com/v1/search/blog.json	

3. HTTP 메서드 : GET

4. 파라미터 - 요청내용을 주소표시창 줄에 데이터 넣어서 줄께 - 파라미터를 쿼리 스트링 형식으로 전달
query	String	Y	검색어. UTF-8로 인코딩되어야 합니다.

https://openapi.naver.com/v1/search/blog.xml?query=사용자가 요청한값
https://openapi.naver.com/v1/search/blog.json?query=사용자가 요청한값
*/