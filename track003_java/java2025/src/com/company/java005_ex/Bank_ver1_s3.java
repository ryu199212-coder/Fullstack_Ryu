package com.company.java005_ex;

import java.util.Scanner;

public class Bank_ver1_s3 {
	public static void main(String[] args) {

		// 변수
		Scanner scanner = new Scanner(System.in);
		int num=-1;
		String id = "", pass = null;
		double balance=0;
		

		// 입력+처리+출력
		// 1-1
		for (;;) {
			System.out.println("====== BANK ======\n*" 
		+ "1.추가\n*2.조회\n*3.입금\n*4.출금\n*5.추가\n*9.종료\n*입력>>>");
			num = scanner.nextInt();
			     if (num == 9) {System.out.println("ATM 종료"); break;}
			else if (num == 1) {System.out.println("추가");
			     System.out.println("id입력 >"); id=scanner.next();
				 System.out.println("pass입력 >"); pass=scanner.next();
				 System.out.println("잔액입력 >"); balance=scanner.nextDouble();
				 }
			else if (num == 2) {System.out.println("조회");
			     String tempid="";
			     String temppass="";
			     {System.out.println("id 입력");} tempid=scanner.next();
			     {System.out.println("pass 입력");} temppass=scanner.next();
			     
			     
			     if(id.equals(tempid) && pass.equals(temppass)){
			     System.out.println("사용자 정보");
			     System.out.println("id >" +id);
			     System.out.println("pass >" +pass);
			     System.out.println("balance >" +balance);}
			     else{System.out.println("정보 확인");}
			}
			     
			
			     
		    else if (num == 3) {System.out.println("입금");}
		    else if (num == 4) {System.out.println("출금");}
			else if (num == 5) {System.out.println("삭제");}
			
			
			}//for end
			     
			    
			
		}
			     
			     
			
			
			
			
	
	}

			
			
			
			
		
/*
 *Step3.  2-1 사용자에게 임시아이디와 임시비밀번호 입력받기
	      2-2 임시아이디와 임시비번이 같다면 사용자 정보출력
		  2-3 다르면 정보를 확인해주세요
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */