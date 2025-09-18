package com.company.java008_ex;

import java.util.Scanner;

public class MethodEx007 {
	   public static int add(int x, int y) {return x+y;}
	   
	   public static int  add(byte x, byte y)   {return x+y;}   
	   public static int  add(short x, short y) {return x+y;}   
//	   public static long add(int  x, int y)    {return x+y;}   
	   public static long add(long  x, long y)  {return x+y;}   
	   
	   public static void main(String[] args) { 
		   //Q1. 메서드 오버로딩? - 비슷한 목적의 메서드 이름을 같게 하는것
		   //Q2. 오류난 이유 - 파라미터의 개수와 자료형으로 구분
		   // 1,4번의 파라미터 자료형이 같음
	   }
	}

