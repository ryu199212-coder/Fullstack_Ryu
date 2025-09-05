package com.company.java004_ex;

import java.util.Scanner;

public class IfEx006 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int num = 0;
		
		System.out.print("숫자입력>"); scanner.nextInt();
		
		if(num%2==0) {System.out.println("남자");}
		else {System.out.println("여자");}
		
	}

}



/*연습문제6)
패키지명 : com.company.java004_ex
클래스명 :  IfEx006
출력내용 : 숫자를입력을받아
   홀수면 남자, 짝수면 여자를 출력하는 프로그램을 작성하시오.
   ※  num%2==0  짝수*/
