package com.company.java010;

import com.company.java010.UserInfo;

// public (아무대서나) > protected (extends) > default (같은폴더) < private (같은클래스)


class UserSon1 extends UserInfo{
	public void show() {
		System.out.println("홍길동 아버지 이름 > " + super.name); // 자식 - public
		System.out.println("홍길동 아버지 금고번호 > " + super.safeCode); // 자식 - protected
		System.out.println("홍길동 아버지 집 > " + super.house); // 자식 - package(같은 폴더에서)
		//System.out.println("부 IQ > " + super.IQ);
		System.out.println("홍길동 아버지 IQ > " + super.getIQ());
		
	}
}
public class Modifier001 {
	public static void main(String[] args) {
		System.out.println("\n\n1. 홍길동 아버지 정보");
		UserInfo user = new UserInfo();
		user.name="홍상직";     // public 아무대서나 접근 가능
		user.safeCode="1234"; // protected 본인꺼 접근가능
		user.house = "전라남도 장성군";
		//user.IQ    = 148;
		user.setIQ(148);
		System.out.println(user.getIQ());
	}

}
