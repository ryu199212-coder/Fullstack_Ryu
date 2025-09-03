package com.company.java003;

import java.util.Scanner;

public class Operator001 {
	public static void main(String[] args) {
		// 먼저() 값(+,-,*,/,%,++,--) 비교(>,<) 조건(&&,||,?,:) 대입(=)
		
		// 1.값
		int a = 10, b=3;
		System.out.println(a+b);
		System.out.println(a-b);
		System.out.println(a*b);
		System.out.println(a/b); // 몫 3
		System.out.println(a%b); // 나머지 1
		
		//Q1. 나머지 연산자 - 짝수인가 홀수인가
		// 0%2 1%2 2%2 3%2
		System.out.println(0%2+"/t"+1%2+"/t"+2%2+"/t"+3%2);
		//Q2. 3의 배수?
		// 3으로 나눴을 때 0 / 나머지 0 1 2
		System.out.println(0%3+"/t"+1%3+"/t"+2%3+"/t"+3%3+"/t"+4%3);
		
		// 2. 비교 (==,!=,<,>,<=,>=)
		System.out.println(10  > 3);
		System.out.println(10 == 3);
		//Q1. a가 짝수니?
		System.out.println(a%2 == 0);
		//Q2. b가 3의 배수니?
		System.out.println(b%3 == 0);
		
		// 3. &&(모든조건 만족)  ||(여러조건중 하나 만족)
		System.out.println(true  &  true); //true
		System.out.println(true  && true); //모든조건 만족 시 true 
		System.out.println(false &  true); //처음에 false 여도 뒤에 있는 조건까지 읽음
		System.out.println(false && true); //Dead code
		
		System.out.println(true  |  true); //true
		System.out.println(true  || true); //Dead code
		System.out.println(false |  true); //처음에 false 여도 뒤에 있는 조건까지 읽음
		System.out.println(false || true); //
		
		//Q1. a가 2의 배수이면서 5의 배수라면 true / false
		System.out.println(a%2==0 && a%5==0);
		//Q2. a가 2의 배수이거나 3의 배수라면 true / false
		System.out.println(a%2==0 || a%3==0);
		
		
		// 4. 삼항연산자 (조건)? 참:거짓
		System.out.println(a==10? "10이다" : "10이 아니다");
		
		//Q3. 숫자를 입력받아 0보다 크면 양수, 0보다 작으면 음수, 아니면 0
		Scanner scanner = new Scanner(System.in);
		int q3 = scanner.nextInt();
		//                조건  ?   참   :    거짓
		String result = q3 > 0 ? "양수" : q3 < 0 ? "음수" : "0" ;
		
		
		
		
		
		
		
	}

}
