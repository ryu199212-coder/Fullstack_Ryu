package com.company.java_ex;


class Papa extends Object{  
int money = 10000;     
public Papa() { super(); }
public void sing() {  System.out.println("GOD-거짓말");  }	
}

class Son extends Papa{ 
int money = 1500;
public Son() { super(); }
@Override public void sing() {  System.out.println("빅뱅-거짓말"); }	
}

public class Repeat007_ {
	public static void main(String[] args) { 
		Papa mypapa = new Son();    
		System.out.println(mypapa.money); 
		mypapa.sing(); 
		System.out.println(((Son) mypapa).money);
	}
}
