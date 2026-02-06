package com.company.java003;

import java.util.Scanner;

public class Repeat007 {
	public static void main(String[] args) {
		
		//변수
		Scanner scanner = new Scanner(System.in);
		int kor, eng, mat, total;
		double avg;
		//입력
		System.out.print("국어 점수"); kor=scanner.nextInt();
		System.out.print("영어 점수"); eng=scanner.nextInt();
		System.out.print("수학 점수"); mat=scanner.nextInt();
		//처리
		total = kor + eng + mat;
		avg = total/3.0;
		//출력
		System.out.println("총점은" + total);
		System.out.println("평균은" + avg);
		
		
		
		
	}

}
