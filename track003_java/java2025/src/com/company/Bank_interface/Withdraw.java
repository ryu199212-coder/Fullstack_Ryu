package com.company.Bank_interface;

import java.util.List;
import java.util.Scanner;

public class Withdraw implements Bank_Controller {

	@Override
	public int exec(List<UserInfo> users, int find) {
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("출금할 금액 > ");
		double output = 0;
		
		UserInfo info = users.get(find);
		output = scanner.nextDouble();
		info.setBalance(info.getBalance() - output);
		
		System.out.println("출금완료 : " + info);

		return 0;
	}

}
