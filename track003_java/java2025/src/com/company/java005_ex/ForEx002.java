package com.company.java005_ex;

import java.util.Scanner;

public class ForEx002 {
	public static void main(String[] args) {
		
		int num;
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("단을 입력하시오 >"); num=scanner.nextInt();
		
//		for(int i =2; i<=9; i=i+1){System.out.print( i + "\t");}
		
		//2*1 =2
		//2*2 =4
		//2*3 =6
		System.out.print(2+"*"+1+"="+2*1);
		System.out.print(2+"*"+2+"="+2*2);
		System.out.print(2+"*"+3+"="+2*3);
		
		for(int i =1; i <=9; i++){
			System.out.println(num+"*"+i+"="+num*i);}
		
		
//		for(int i=2; i<=6; i++) {System.out.print(i+"\t");}
//		System.out.print("2"+"*"+"2"+"=");
//		for(int i=2; i<=6; i=i*2) {System.out.print(i+"\t");}
//		System.out.print("2"+"*"+"3"+"=");
//		for(int i=2; i<=6; i=i*3) {System.out.print(i+"\t");}
		
	}

}
/*연습문제2)  
패키지명 : com.company.java005_ex
클래스명 :  ForEx002
출력내용 :   for 이용

   사용자에게 단을 입력받아 해당하는 
   단을 출력해주는 프로그램을 작성하시오. FOR문을 이용하시오.
*/