package com.company.Bank_interface;

import java.util.List;
import java.util.Scanner;

public class Deposit implements Bank_Controller {

	
	@Override
	public int exec(List<UserInfo> users, int find) {
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("입금할 금액 > ");
		double input = 0;
		
		UserInfo info = users.get(find);
		input = scanner.nextDouble();
		info.setBalance(info.getBalance() + input);
		
		System.out.println("입금완료 : " + info);
		return 0;
	}

}
