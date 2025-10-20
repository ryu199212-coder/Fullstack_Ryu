package com.company.java015_lambda_stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Stream002 {
	public static void main(String[] args) {
		
		Integer[]arr = {1,2,3,4,5,2,3,4,5,3,4,5,4,5,2,3,4,5,2,3,4,5,};
		List<Integer> list = Arrays.asList(arr);
		
		//1단계 - stream
		Stream<Integer> sarr = Arrays.stream(arr);
		Stream<Integer> slist = list.stream();
		
		//2단계 - 중간연산
		//Predicate : boolean java.util.function.Predicate.test( T t )
		//Consumer  : void java.util.stream.Stream.forEach( T t )
		sarr.filter( t -> t%2 != 0) 	//홀수 필터링
			.distinct()					//중복 제거
			.sorted()					//정렬
			.skip(1)					//skip
										//3단계 - 최종연산
		    .forEach( t-> {System.out.print(t);});
		
		System.out.println();
		
		slist.filter( t -> t%2 != 0) 	//홀수 필터링
			 .distinct()				//중복 제거
			 .sorted()					//정렬
			 .skip(1)					//skip
										//3단계 - 최종연산
		     .forEach( t-> {System.out.print(t);});
		
		System.out.println();
		
		//#3. collect
		System.out.println(Arrays.stream(arr).collect(Collectors.toList()));
		System.out.println(list.stream().collect(Collectors.toList()));
	}
}
