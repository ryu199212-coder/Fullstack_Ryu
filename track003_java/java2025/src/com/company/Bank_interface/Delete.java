package com.company.Bank_interface;

import java.util.List;

public class Delete implements Bank_Controller {

	@Override
	public int exec(List<UserInfo> users, int find) {
		
	
		UserInfo u = users.remove(find);

		
		System.out.println("정보 삭제" + u);
		return 0;
		
	}
	

}
