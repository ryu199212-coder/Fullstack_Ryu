package com.company.java016_javaio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Network001_google {
	public static void main(String[] args) {
		
		try {
			// 1. Url
			URL url = new URL("https://www.google.com/");
			
			// 2. 연결객체
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			// 3. 요청설정(Request)
			conn.setRequestMethod("GET");
			conn.setDoInput(true); conn.setDoOutput(true); conn.setReadTimeout(1000); //ms
			
			// 4. 응답코드(Response)
			int code = conn.getResponseCode(); //구글에서 응답
			System.out.println(code);  // 200응답 성공!	404페이지없음		500코드없음
			
			// 5. 응답데이터		BufferedReader #   >	[Network001-프로그램]		 > BufferedWriter
			BufferedReader br;
			if(code == 200) {
				// 한줄씩 읽을 수 있게 속도향상-바이트스트림을 문자스트림 - 응답 데이터 스트림
				br = new BufferedReader(new InputStreamReader(conn.getInputStream()));}
			else {
				br=new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}
			String line = "";	StringBuffer sb = new StringBuffer();
			while((line=br.readLine()) != null) {
				sb.append(line+"\n");
			}
			
			System.out.println(sb.toString());
			br.close(); conn.disconnect();
			
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		


	}
}

/*
 1. id/pw
Client ID	: RJZT8nINZSr3AjHXOlN5
Client Secret : aV1sRKJTga

2. 
https://openapi.naver.com/v1/search/blog.xml - XML
https://openapi.naver.com/v1/search/blog.json - JSON

3. HTTP 메서드 : GET

4. 파라미터 - 요청내용을 주소표시창 줄에 데이터 넣어서 줄께 - 파라미터를 쿼리 스트링 형식으로 전달
query   String   Y   검색어. UTF-8로 인코딩되어야 합니다.

https://openapi.naver.com/v1/search/blog.xml?query=사용자가 요청한값
https://openapi.naver.com/v1/search/blog.json?query=사용자가 요청한값
 */
