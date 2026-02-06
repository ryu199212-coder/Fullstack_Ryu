package com.company.java003_ex;

import java.util.Scanner;

public class CastingIntegerEx {
	public static void main(String[] args) {
		//1)자바의 자료형은 기본형과 참조형으로 나뉨
		//2)boolean , 정수형(byte<short, char<int★<long), 실수형(float<double★)
		
	Scanner scanner=new Scanner(System.in);
	short sh1 = 1; // sh1 [1] 2byte
	short sh2 = 2; // sh2 [2] 2byte
	
	// 산술연산 시(+) 자동으로 int 변환
	// int 보다 작은 타입들 다 적용됨 - byte, short, char
	short result1 = (short) (sh1 + sh2);  // 1(int) + 2(int)
	int   result2 = sh1 + sh2;
	
	
	}

}

//short sh1 = 1;
//short sh2 = 2;
//short result = sh1 +sh2;