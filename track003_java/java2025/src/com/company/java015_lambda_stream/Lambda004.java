package com.company.java015_lambda_stream;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Lambda004 {
	public static void main(String[] args) {
		//()->{}
		//void java.util.function.Consumer.accept( T t ) // (T t)->{x} / accept
		//Consumer<String> consumer = (t)->{System.out.println("Hello" + t);};
		Consumer<String> consumer = t ->{System.out.println("Hello" + t);};
		consumer.accept("sally");
		consumer.accept("lambda");
		
		//T java.util.function.Supplier.get() / ()->{return} / get
		Supplier<String> supplier = () -> {return "Hello";};	
		System.out.println(supplier.get());
		
		//boolean java.util.function.Predicate.test( T t ) / ( T t )->{return boolean} / test
		Predicate<Integer> predicate = t -> {return t>0;};
		System.out.println(predicate.test(10));
		System.out.println(predicate.test(-10));
		
		//R java.util.function.Function.apply( T t ) / ()->{return R} / apply
		Function<String,Integer> function = (t)->{return Integer.parseInt(t);};
		System.out.println(function.apply("10")+3);
		
		//int java.util.function.IntBinaryOperator.applyAsInt( int left, int right )
		//( int left, int right )->{int} / applyAsInt 
		//IntBinaryOperator operator = ( int left, int right )->{return left>right? left:right;};
		IntBinaryOperator operator = (left, right)->left>right? left:right;
		System.out.println(operator.applyAsInt(10, 3));
		System.out.println(operator.applyAsInt(3, 10));
	}
}
		
	



/*
자바의 api 함수형 인터페이스

1. Consumer 받는용도
void java.util.function.Consumer.accept( T t )

2. Supplier 제공용도
3. Predicate 판단용도
4. Function 처리용도
5. Operator 연산용도


*/