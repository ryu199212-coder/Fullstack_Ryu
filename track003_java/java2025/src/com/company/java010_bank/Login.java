package com.company.java010_bank;

import java.util.Scanner;

public class Login {
private UserInfo userinfo;

	
	public Login() { super(); } 
	public Login(UserInfo userinfo) { super(); this.userinfo = userinfo; }

	
	public int exec() {
		String id;
		String pass;
		int find=-1;
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("아이디 입력 > "); id=scanner.next();
		System.out.println("비밀번호 입력 > "); pass=scanner.next();
		
		if(id.equals(this.userinfo.getId()) && pass.equals(this.userinfo.getPass())) {
			find=1;		
		}
		return find;
	}
}
