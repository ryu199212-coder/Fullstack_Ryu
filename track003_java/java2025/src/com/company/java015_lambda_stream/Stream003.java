package com.company.java015_lambda_stream;

import java.util.Arrays;
import java.util.List;

public class Stream003 {
	public static void main(String[] args) {
		Integer[] ages = {17, 21, 26, 45, 18};
		
		double avg = Arrays.stream(ages)
						   .mapToInt(age -> age)
						   .average()
						   .orElse(0.0);
		System.out.println("평균나이 : " + avg);
		
		//Ex2 짝수만 출력
		//step1) 스트림
		//step2) 짝수필터링
		//step3) 출력
		Arrays.stream(ages)
		
		.filter(t->t%2==0)
		.forEach(System.out::println);
		
		System.out.println();
		
		//Ex3  성이 김씨인친구들
	    List<String> names = Arrays.asList("김수지" , "이나영" , "김나영" , "유재석" , "강호동");
	    System.out.println(names.get(0).startsWith("김"));  // 힌트) 문자열에서 startWith("문자") 시작하는
	    //step1) 스트림만들기 
	    //step2) 김으로 시작하는 값찾기 -filter 이용
	    //step3) 출력  -  forEach
	    names.stream()
	    
        .filter(t -> t.startsWith("김"))
        .forEach(System.out::println);
	    
	    System.out.println();
	    
	    //Ex4 names 정렬 후 출력
	    List<String> names1 = Arrays.asList("김수지" , "이나영" , "김나영" , "유재석" , "강호동");
	    names1.stream()
	    .sorted()
        .forEach(System.out::print);
	    
	    System.out.println();
	    
	    //Ex5 제일 나이 많은(max) 사람
	    int max = Arrays.stream(ages).mapToInt(age->age).max().orElse(-1);
	    System.out.println(max);
        
		 
		
	}
}
