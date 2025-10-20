package com.company.java016_javaio;

public class JavaIo006_StringBuffer {
	public static void main(String[] args) {
		//#1. String       문자열누적 - 새로운 주소(변형x)
		String str = "ABC";
		System.out.println("1. str주소 > " + str + " " + System.identityHashCode(str));
		
		str += "D";
		System.out.println("2. str주소 > " + str + " " + System.identityHashCode(str));
		
		//#2. StringBuffer 문자열누적 - 기존 주소(변형o)
		StringBuffer sb = new StringBuffer();
		sb.append("ABC");
		System.out.println("3. sb주소 > " + sb + " " + System.identityHashCode(sb));
		
		sb.append("D");
		System.out.println("4. sb주소 > " + sb + " " + System.identityHashCode(sb));
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