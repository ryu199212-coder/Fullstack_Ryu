package com.company.java002;

public class DataType001 {
	public static void main(String[] args) {
		//1. 자바의 자료형분류 ( 기본형 / 참조형 )
		//2. 기본형 : 값저장
		//2-1.   논리, 정수, 실수
		boolean  bl = true;   // true/false
		System.out.println("1. 논리 : " + bl);
		
		//2-2. 정수 (1,2,4,8 : byte - short - int ★ - long:L )
		byte  by = 1; short sh = 2; int    i = 4; long   l = 8L;
		System.out.println("2. 정수  byte-short-int★-long");
		
		//기본형의 단위
		//int result = by + 1; // byte + int 
		
		//2-3. 실수 ( 3.14  float / double ★)
		float fl = 3.14f;  double d = 3.14;
		System.out.println("3. 실수 float-double★");
		
		//3. 정밀도 float(7자리 보장) / double(15자리 보장)
		float fper1 = 1.0000001f;  // 0 6개 + 1 = 7개   1.0000001 ->정확하게 표현
		float fper2 = 1.00000001f; // 0 7개 + 1 = 8개   1.0 -> 정밀도 초과 반올림
		float fper3 = 1.11111111f; // 1 7개 + 1 = 8개   1.1111112 -> 근사값
		System.out.println(fper1 + "\t" + fper2 + "\t" + fper3);
		
		double dper1 = 1.000000000000001;  // 0 14개 + 1 = 15개   ->정확하게 표현  
		double dper2 = 1.0000000000000001; // 0 15개 + 1 = 16개   ->1.0 반올림
		double dper3 = 1.1111111111111111; // 1 15개 + 1 = 16개   ->근사값
		System.out.println(dper1 + "\t" + dper2 + "\t" + dper3);
	}
}

