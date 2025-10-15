package com.company.java014_ex;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

//Map(사전) - key:value → entry(한쌍), put / get(key값) / 향상for, iterator / size / remove(객체) / contains
public class MapEx001 {

	public static void main(String[] args) {
		Map<String, String> map = new HashMap<>();
		map.put("피구왕", "통키");
		map.put("제빵왕", "김탁구");
		map.put("요리왕", "비룡");
		
		System.out.println("==============================\r\n"
				+ "KING   NAME\r\n"
				+ "==============================\r\n"
				+ "피구왕   통키\r\n"
				+ "---------------------\r\n"
				+ "제빵왕   김탁구\r\n"
				+ "---------------------\r\n"
				+ "요리왕   비룡\r\n"
				+ "---------------------\r\n"
				+ "KING의 정보를 제공중입니다");
		
		Scanner scanner = new Scanner(System.in); 
		System.out.println("이름 입력 > "); String name = scanner.next();
		
		for(Entry<String, String> k : map.entrySet()) 
		{
			if(k.getKey().equals(name)) {
			System.out.println(k.getKey() + "/" + k.getValue());
			}
			}

	}

}
/*
연습문제2)  Collection  Framework
패키지명 : com.company.java014_ex
클래스명 : MapEx001
1. MAP 만들기
KEY   VALUE
피구왕   통키
---------------------
제빵왕   김탁구
---------------------
요리왕   비룡

Map<String, String> map = new HashMap<>();

2 다음과 같이 문제풀기
==============================
KING   NAME
==============================
피구왕   통키
---------------------
제빵왕   김탁구
---------------------
요리왕   비룡
---------------------
KING의 정보를 제공중입니다
이름을 입력하세요> 제빵왕

ㅁ제빵왕 : 김탁구
*/