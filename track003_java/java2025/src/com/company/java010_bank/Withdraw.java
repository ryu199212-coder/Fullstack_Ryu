package com.company.java010_bank;

import java.util.Scanner;

public class Withdraw {
	private UserInfo userinfo;

	public Withdraw() { super(); } 
	public Withdraw(UserInfo userinfo) { super(); this.userinfo = userinfo; }
	
	public void exec() {
		Scanner scanner = new Scanner(System.in);
		double output = 0;
		System.out.println("출금할 금액 > "); 
		output = scanner.nextDouble();
		this.userinfo.setBalance(this.userinfo.getBalance() - output);
		System.out.println("출금완료 : " + this.userinfo);
	}

}
