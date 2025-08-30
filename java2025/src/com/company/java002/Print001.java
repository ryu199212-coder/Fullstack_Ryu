package com.company.java002; //지정위치

public class Print001 {//어디서든지 부품객체 Print001 
    
	public static void main(String[] args) {
        //1. system.our.println()
	    System.out.println("1. 줄바꿈");
	     
	    //2. System.out.print()
	    System.out.print("2. 줄바꿈안됨");
	    System.out.print("줄바꿈 특수 \n이용\n");

		//3. System.out.printf() %s "abc" %d 1 %f 1.23
	    System.out.printf("3. 정수 %d, 실수 %f, 문자열 %s" ,1, 1.2 , "abc" );
        //4. +의 의미
	    System.out.println(10+3);
	    System.out.println(10+3 +"+"+ 1+2); // 아래의 결과는?
	    // 아래의 결과는?     숫자+숫자 + 문자열
        //                   13    + "+" + 1(출력) + 2(출력)
	    //                   13    +       12
	    System.out.println(10+3     +"+" + (1+2)); //13+3
	    //                   13    +       (1+2)
	    //Q System.out.println(  1+2=3  ); 
	    System.out.println("1+2=3");
	    System.out.println(  1+"+"+2+"="+3  );
	
	}
	
} // ctrl + f11