package com.company.java003_ex;

public class OperatorEx001 {
	public static void main(String [] args) {
		// 먼저      값      비교   조건   대입
		// ()  ++,+,-,*,/  >,<  &&||   = +=

    int a=3, b=10;  //a[3] , b[10]
    System.out.println(  b+=10 - a-- );
    //1. a-- 맨마지막 a[3], b[10]
    //2. 10-a   10-3 → 7
    //3. b+=7   10+=7  b=10+17 → 17
    //4. ; a--  a[2]
    System.out.println(  a+=5 );
    //1. a+=5  →  a=a+5  →  a=2+5
    //2. 7
    System.out.println(  a>=10 || a<0 && a>3);
    //1.                 7>=10 ||  7<0  &&  7>3
    //2.                 false || false &&  true
    //3.                              false
    //4.                 false ||     false
	
	

}
}
