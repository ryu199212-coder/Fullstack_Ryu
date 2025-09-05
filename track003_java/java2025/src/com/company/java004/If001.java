package com.company.java004;

public class If001 {
	public static void main(String [] args) {
		//1. 1형식
		System.out.print("1형식");
		int axe=1;
		System.out.println("1형식 - 조건을 무조건 읽음");
		//if(axe가 1이라면){ 금도끼},if( 2라면){ 은도끼},if( 3이라면){ 낡은도끼}
		if(axe==1) {System.out.println("금도끼");}
		if(axe==2) {System.out.println("은도끼");}   //처리했는지 알수 없음
		if(axe==3) {System.out.println("낡은도끼");}  //처리했는지 알수 없음
		
		//2. 2형식(else 뒤에는 조건이 못온다)
		System.out.print("2형식");
		System.out.println("2형식 - 맞다/틀리다");
		axe=2;
		if(axe==1) {System.out.println("금도끼");}
		else {System.out.println("금도끼가 아니다");}
		
		//3. 다형식
		System.out.print("다형식");
		axe=3;
		     if(axe==1) {System.out.println("금도끼");}
		else if(axe==2) {System.out.println("은도끼");}
		else if(axe==3) {System.out.println("낡은도끼");}
		else {System.out.println("금도끼가 아니다");}
		
	}

}
