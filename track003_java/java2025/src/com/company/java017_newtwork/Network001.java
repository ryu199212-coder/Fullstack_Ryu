package com.company.java017_newtwork;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Network001 {
	public static void main(String[] args) {
		try { InetAddress local = InetAddress.getLocalHost(); 
		System.out.println("1. IP HostName : " + local.getHostName());
		System.out.println("2. IP HostAddress : " + local.getHostAddress());
		
		local = InetAddress.getByName("www.naver.com");
		System.out.println("3. naver : " + local);
		}
		catch (UnknownHostException e) {  e.printStackTrace(); }
		
	}
}
/*
1. Network
- 통신기술들이 그물망처럼 연결된 통신을 이용해서 컴퓨터 자원을 공유

2. Client vs Server
- Client(서비스 사용)
- Server(서비스 제공)

3. IP주소
   예)192.168.0.1(건물)
   포트(건물 안의 문)
   80(HTTP), 443(HTTPS), 22(SSH 호스트), 25(SMTP 메일)
    
4. InetAddress - ip주소를 다루기 위한 클래스
*/