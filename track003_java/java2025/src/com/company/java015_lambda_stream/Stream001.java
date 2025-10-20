package com.company.java015_lambda_stream;

import java.util.Arrays;
import java.util.List;

public class Stream001 {
	public static void main(String[] args) {
		//데이터 종류에 상관없이 (Stream) 같은방식으로 처리
		
		Integer[]arr = {1,2,3,4,5};
		List<Integer> list = Arrays.asList(arr);
		
		Arrays.stream(arr).forEach( (t) -> {System.out.print(t); } );
		
		System.out.println();
		System.out.println();
		
		list.stream().forEach(System.out::print);
		//void java.util.function.Consumer.accept( T t )
		// ( T t ) -> x / accept
	}

}
