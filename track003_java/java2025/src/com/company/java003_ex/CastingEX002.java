package com.company.java003_ex;

import java.util.Scanner;

public class CastingEX002 {
	public static void main(String[] args) {
		//변수
		Scanner scanner=new Scanner(System.in);
		int kor, eng, mat, total, level;
		double avg;
		//입력
		System.out.print("국어점수 입력>"); kor=scanner.nextInt();
		System.out.print("영어점수 입력>"); eng=scanner.nextInt();
		System.out.print("수학점수 입력>"); mat=scanner.nextInt();
		//처리
		total=kor+eng+mat;
		avg=total/3.0;	
        level=(int)(avg/10);
		//출력
        System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        System.out.println(":: GOOD  IT SCORE ::");
        System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        System.out.printf("%-5s\t%-5s\t%-5s\t%-5s\t%-5s\t%-5s\n", "국어","영어","수학","총점","평균","레벨");
        System.out.printf("%-5d\t%-5d\t%-5d\t%-5d\t%-5.2f\t%-5d\n", kor, eng, mat, total, avg, level);
		
	}

}