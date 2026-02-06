package com.company.Bank_interface;

import java.util.List;
import java.util.Scanner;

public class Login implements Bank_Controller {
			
		@Override
		public int exec(List<UserInfo> users, int find) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("[1]아이디 입력 > "); String id = scanner.next();
			System.out.println("[2]비밀번호 입력 > "); String pass = scanner.next();
			
			
			for(int i=0; i<users.size(); i++) {
			if(id.equals(users.get(i).getId() ) && pass.equals(users.get(i).getPass() ) ) {
				return i;
			}
			else {System.out.println("계정 확인 필요");}
		} 
				
			return -1;
	}

}



