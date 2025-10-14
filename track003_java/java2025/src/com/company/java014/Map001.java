package com.company.java014;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

//1. 콜렉션 프레임워크 - [배열]의 단점을 개선 [객체]만 저장가능한 [동적배열]
//2. List, Set, Map
//List(기차) - index 순서 O, 중복허용 O, add / get(순서) / size / remove(순서) / contains
//Set(주머니) - index 순서 X, 중복허용 X, add / 향상for, iterator / size / remove(객체) / contains
//Map(사전) - key:value → entry(한쌍), put / get(key값) / 향상for, iterator / size / remove(객체) / contains

public class Map001 {
	public static void main(String[] args) {
		Map<String, Integer> map = new HashMap<>();
		map.put("one",   1); //키, 값
		map.put("two",   2); //키, 값
		map.put("three", 3); //키, 값
		
		System.out.println("1 : " + map);
		System.out.println("2 : " + map.get("two"));
		System.out.println("1 : " + map.size());
		System.out.println("4 : " + map.remove("two"));
		System.out.println("1 : " + map.containsKey("one"));
		System.out.println(map.entrySet()); // key : value 한묶음, 한쌍 - [one=1, three=3]
		
		for(Entry < String, Integer > one : map.entrySet()) 
		{System.out.println(one.getKey() + "/" + one.getValue());}
		
		Iterator<Entry<String, Integer>> iter = map.entrySet().iterator(); // 1) iter →[one=1, three=3]
		while(iter.hasNext()) {// 2) 처리대상 확인 [iter → one=1, three=3]
			Entry<String, Integer > temp = iter.next();
			System.out.println(temp.getKey() + "/" + temp.getValue());
		}

	}

}
