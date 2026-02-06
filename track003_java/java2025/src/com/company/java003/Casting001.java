package com.company.java003;

public class Casting001 {
	public static void main(String[] agrs) {
		//#1. 형변환 - 자동 타입 변환
		// byte < short < int < long < float < double
		byte  by = 1;
		short sh = 2;
		int   i  = 4;
		long  l  = 8L;
		float f  = 3.14f;
		double d = 3.14;
		boolean bl = true;
		sh = by;   // 정수(2byte) - 정수(1byte)
		i  = by;   // 정수(4byte) - 정수(1byte)
		l  = by;   // 정수(8byte) - 정수(1byte)
     // l  =  f;   // 정수(8byte) - 실수(4byte) 정수<실수
		           // Type mismatch: cannot convert from float to long
		f  =  l;   // 실수(4byte) - 정수(8byte) 정수<실수
     // i  = bl;   // boolean 제외 전부 형변환 가능
		           // Type mismatch: cannot convert from float to int
		
		//#2. 형변환 - 강제 타입 변환
		by = (byte) i;    // 정수(1byte) - 정수(4byte)
		
		int i2   = (int) 1.2;   // 정수(4byte) - 실수(double-8byte)
		float f2 = (float) 1.2;   // 실수(4byte) - 실수(double-8byte)
		
		//Q1) System.out.println(1.5+2.7); 3으로 나오게 만들기
		System.out.println((byte)1.5+(byte)2.7);
		System.out.println((int)1.5+(int)2.7);
   
	
	}
}
