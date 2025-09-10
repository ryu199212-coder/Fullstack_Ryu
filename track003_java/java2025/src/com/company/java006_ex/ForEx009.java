package com.company.java006_ex;

public class ForEx009 {
	public static void main(String[] args) {

		int num = 0;
		int result = 0;

		for (int i = 1; i <= 10; i++) {
			if (i % 3 == 0) {result += i;}}
				
		System.out.println("3의 배수의 합은" + result);
		
		int result1 = 0;
		int i = 1;
		while (i <= 10) {
			if (i % 3 == 0) {result1 += i;}
			i++;}
				
		System.out.println("3의 배수의 합은" + result1);
		
		int result2 = 0;
		i = 1;
		do{
			if (i % 3 == 0) {result2 += i;}
			i++;}while (i <= 10); 
		
		System.out.println("3의 배수의 합은" + result2);
		
		
		
		

	}

}

/*
 * 연습문제9) for/while/do while 패키지명 : com.company.java005_ex 클래스명 : RepeatEx009
 * for , while , do while 3가지 버젼으로 1~10까지 3의 배수의 합 : 18
 */