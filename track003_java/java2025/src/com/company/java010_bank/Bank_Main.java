package com.company.java010_bank;

import java.util.Scanner;

//1. 클래스는 부품객체
//2. 상태와 행위
public class Bank_Main {
	// 상태 : 멤버변수
	UserInfo userinfo;
	Add      add;
	Show     show;
	Deposit deposit;
	Withdraw withdraw;
	Login login;
	Delete delete;
	// 행위 : 멤버함수
	
	public Bank_Main() {
		this.userinfo   = new UserInfo("","",0);
		this.add        = new Add(this.userinfo);
		this.show       = new Show(this.userinfo);
		this.deposit    = new Deposit(this.userinfo);
		this.withdraw   = new Withdraw(this.userinfo);
		this.login      = new Login(this.userinfo);
		this.delete     = new Delete(this.userinfo);
	}
	
	public void run() {
		Scanner scanner = new Scanner(System.in);
		int num = -1;
		while(num !=9) {
			 System.out.println(this.userinfo + "\t" + System.identityHashCode(this.userinfo));
			 System.out.print("\n\n🧠✨ WELCOME TO MEMORY ARCHIVE SYSTEM ✨🧠\n" + 
					    "\n[1] 🗃️ 새로운 기억 슬롯 만들기" + 
					    "\n[2] 🔍 기억 열람하기" + 
					    "\n[3] 📥 기억 저장하기" + 
					    "\n[4] 📤 기억 꺼내보기" + 
					    "\n[5] 🗑️ 기억 삭제하기" +
					    "\n\n👉 수행할 작업 번호를 선택하세요: ");
			 num=scanner.nextInt();
			 	 switch(num) {
					 case 1 : this.add.exec(); break;
					 case 2 : case 3 : case 4 : case 5:
				 //1. 유저정보 확인
						 if(this.login.exec()==-1) {System.out.println("⚠️ 기억의 주인을 확인할 수 없습니다.");break;}
				 //2. 각각 처리
						 switch(num) {
						     case 2 : this.show.exec(); break;
						     case 3 : this.deposit.exec(); break;
						     case 4 : this.withdraw.exec(); break;
						     case 5 : this.delete.exec(); break;
							 }
						 break;
						
			 }
		}
	}
		
	
	public static void main(String[] args) {
		Bank_Main bank = new Bank_Main();
		bank.run();
	}
}
