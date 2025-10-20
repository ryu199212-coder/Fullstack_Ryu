package com.company.Bank_interface;

import java.util.List;
import java.util.Scanner;

public class Add implements Bank_Controller {

	@Override
	public int exec(List<UserInfo> users, int find) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("[1]아이디 입력 > "); String id = scanner.next();
		System.out.println("[2]비밀번호 입력 > "); String pass = scanner.next();
		System.out.println("[3]잔액 입력 > "); double balance = scanner.nextDouble();
		users.add(new UserInfo(id, pass, balance));
		System.out.println("사용자 추가 완료");
		return 0;
	
	}



	
}	