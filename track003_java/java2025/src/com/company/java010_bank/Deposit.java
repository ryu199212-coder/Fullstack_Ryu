package com.company.java010_bank;

import java.util.Scanner;

public class Deposit {
	private UserInfo userinfo;

	public Deposit() { super(); } 
	public Deposit(UserInfo userinfo) { super(); this.userinfo = userinfo; }
	
	public void exec() {
		Scanner scanner = new Scanner(System.in); 
		double input = 0;
		System.out.println("입금할 금액 > "); 
		input = scanner.nextDouble();
		this.userinfo.setBalance(this.userinfo.getBalance() + input);
		System.out.println("입금완료 : " + this.userinfo);
	
	}
}

 