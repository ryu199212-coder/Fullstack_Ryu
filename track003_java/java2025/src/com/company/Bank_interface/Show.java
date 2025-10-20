package com.company.Bank_interface;

import java.util.List;
import java.util.Scanner;

public class Show implements Bank_Controller {

	@Override
	public int exec(List<UserInfo> users, int find) {
		Scanner scanner = new Scanner(System.in);
		UserInfo info = users.get(find);
		System.out.println("ID > " + info.getId() );
		System.out.println("PASS > " + info.getPass() );
		System.out.println("BALANCE > " + info.getBalance() );
		// TODO Auto-generated method stub
		return 0;
		
	}

}
