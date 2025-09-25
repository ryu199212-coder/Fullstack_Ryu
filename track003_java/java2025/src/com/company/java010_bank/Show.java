package com.company.java010_bank;

public class Show {
	private UserInfo userinfo;

	public Show() { super(); }
	public Show(UserInfo userinfo) { super(); this.userinfo = userinfo; }

	// 행위 : 멤버함수
	public void exec() {
		System.out.println("\nID > " + this.userinfo.getId()
		                 + "\nPASS > " + this.userinfo.getPass()
	                   	 + "\nBALANCE > " + this.userinfo.getBalance());
	}
}
/*유저 정보 보여주기*/