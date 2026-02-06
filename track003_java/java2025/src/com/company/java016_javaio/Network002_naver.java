package com.company.java016_javaio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class Network002_naver {
	public static void main(String[] args) {
		
		// 1. URL
		try {
			String apiurl = "https://openapi.naver.com/v1/search/blog.json?query="	//xml or json url뒤에 ?query=
					+ URLEncoder.encode("삼총사","UTF-8");							//한글 깨지므로 UTF-8로 인코딩
			URL url = new URL(apiurl);
			
			// 2. HttpURLConnection
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			// 3. 요청설정 - GET방식
			// > X-Naver-Client-Id: {애플리케이션 등록 시 발급받은 클라이언트 아이디 값}
			// > X-Naver-Client-Secret: {애플리케이션 등록 시 발급받은 클라이언트 시크릿 값}
			conn.setRequestMethod("GET");
			conn.setRequestProperty("X-Naver-Client-Id", "RJZT8nINZSr3AjHXOlN5");
			conn.setRequestProperty("X-Naver-Client-Secret", "aV1sRKJTga");
			
			// 4. 응답확인
			BufferedReader br;
			if((conn.getResponseCode()==200)) {
				br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			}else {
				br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}
			
			// 5. 응답데이터
			String line ="";
			StringBuffer sb = new StringBuffer();
			while((line=br.readLine())!=null) {
				sb.append(line+"\n");
			}
			System.out.println(sb.toString());
			br.close(); conn.disconnect();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
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