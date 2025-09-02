package com.company.java002;

public class Var {
	public static void main(String[] args) {
		//1. 변수? 변하는 수 - 자료형  변수명
		int a = 0;   // %d  1 2 3        a[    ]
		System.out.println("1:" + a);
		
		a = 1000000; // A=B (=대입해주세요)  a[1000000]
		System.out.println("2:" + a);  //1000000
		
		a = a-90000; //a[1000000-90000]  변하는 수
		System.out.println("3:" + a);
		
		//2. 변수의 범위  {여기서부터  }여기까지
		{ int b=20; System.out.println(b); }
		//b=1000;  b cannot be resolved to a variable
		
		//3. 변수명  $_소문자  
		//오픈박스(변수)
		int $1=1;  int _2 = 2;   int a3bc=3;
		int HOME=4;
		//int static;  // Syntax error on token "static", invalid VariableDeclarator
		//int package;
		//int void;
		int main;
		
	}
}

