package com.company.java008_ex;

import java.util.Scanner;

public class MethodEx010 {
	public static void pet_name(String[][] users) {

		// 변수
		Scanner scanner = new Scanner(System.in);
		String result = "정보를 확인해주세요.";
		String tempid = "";
		String temppass = "";
		int num = -1;

		// 입력
		// 1번

		//
		System.out.println("동물 이름 입력 > ");
		tempid = scanner.next();
	
		for (int u = 0; u < users.length; u++) {
			if (tempid.equals(users[u][0])) {
				result = tempid + "는" + users[u][2] + "예약되어 있습니다.";
				break;
			}
		}
		System.out.println(result);

		}

	public static void main(String[] args) {
		String[][] pets = { { "콩이", "010-1234-5678", "예방접종" }, 
				            { "초코", "010-2345-6789", "건강검진" },
				            { "보리", "010-3456-7890", "치아관리" } };
		
		Scanner scanner = new Scanner(System.in);
		int num = -1;
		while (num != 3) {
			System.out.println("🐾 동물 병원 시스템 메뉴");
			System.out.println("1. 진료 항목 조회");
			System.out.println("2. 진료 항목 변경");
			System.out.println("3. 종료");
			System.out.println("👉 메뉴 번호를 선택해주세요:");
			num = scanner.nextInt();

			if (num == 1) {
				System.out.println("1. 진료 항목 조회");
				pet_name(pets);
			} else if (num == 2) {
				System.out.println("2. 진료 항목 변경");
				menu(pets);
			} else if (num == 3) {
				System.out.println("종료");
			}
			
		}

		//		pet_name(pets);
		//		menu(pets);

	}

		
		
	

	public static void menu(String[][] users) {
		// 변수
		// 2번
		Scanner scanner = new Scanner(System.in);
		String temppass = "";
		String result = "정보를 확인해주세요.";
		String change = "";
		String tempid = "";
		int num = -1;
		int find = -1;
		// 입력
	
			System.out.println("🐱 동물 이름을 입력해주세요");
			tempid = scanner.next();
			System.out.println("📞 보호자 전화번호를 입력해주세요");
			temppass = scanner.next();
		// 처리
		for (int u = 0; u < users.length; u++) {
			if (tempid.equals(users[u][0]) && temppass.equals(users[u][1])) {
				System.out.print("🩺 변경하실 진료 항목을 입력해주세요");
				change = scanner.next();
				users[u][2] = change;
				System.out.println("✅ 예약 정보 확인 : [" + users[u][0] + ", " + users[u][1] + ", " + users[u][2] + "]");
				find = u;
				break;
			}
			
			
		
		}
		 // 출력
		if (find == -1) {
			System.out.println("정보를 확인해주세요");
					
		}
	}

}

/*
 * ■[1단계] 데이터 구성 다음과 같은 정보를 담고 있는 2차원 배열을 선언하세요:
 * 
 * String[][] pets = { {"콩이", "010-1234-5678", "예방접종"}, {"초코", "010-2345-6789",
 * "건강검진"}, {"보리", "010-3456-7890", "치아관리"} };
 * 
 * - 각 행은 한 마리의 동물에 대한 정보입니다. - [0] 동물 이름 - [1] 보호자 전화번호 - [2] 예약된 진료 항목
 * 
 * ■ [2단계] 메뉴 출력 및 선택 다음과 같은 메뉴를 **무한 반복**으로 출력하세요:
 * 
 * 🐾 동물 병원 시스템 메뉴 1. 진료 항목 조회 2. 진료 항목 변경 3. 종료 👉 메뉴 번호를 선택해주세요:
 * 
 * 
 * 
 * ■ [3단계] 기능 구현
 * 
 * #### ① 진료 항목 조회 - 동물 이름을 입력받아 해당 동물의 진료 항목을 출력하세요. > 출력예시 🐶 동물 이름을 입력해주세요 >
 * 콩이 ✅ 콩이는 예방접종 예약이 되어 있어요.
 * 
 * 
 * #### ② 진료 항목 변경 > 출력예시 - 동물 이름과 보호자 전화번호를 입력받아 정보가 맞으면 진료 항목을 변경하세요.
 * 
 * 🐱 동물 이름을 입력해주세요 > 초코 📞 보호자 전화번호를 입력해주세요 > 010-2345-6789 🩺 변경하실 진료 항목을
 * 입력해주세요 > 피부검사 ✅ 예약 정보 확인: [초코, 010-2345-6789, 피부검사]
 */
