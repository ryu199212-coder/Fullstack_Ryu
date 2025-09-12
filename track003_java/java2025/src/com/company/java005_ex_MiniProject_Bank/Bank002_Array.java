package com.company.java005_ex_MiniProject_Bank;

import java.util.Arrays;
import java.util.Scanner;

public class Bank002_Array {
	public static void main(String[] args) {
		// 변수
		String[] id = new String[3];
		String[] pass = new String[3];
		double[] balance = new double[3];
		int num = -1;
		Scanner scanner = new Scanner(System.in);

		// 입력 + 처리 + 출력
		while (num != 9) { // 9가 아니라면 계속 반복
			System.out.println(Arrays.toString(id));
			System.out.println(Arrays.toString(pass));
			System.out.println(Arrays.toString(balance));

			// 메뉴판
			System.out.println("====== BANK ======\n*" + "1.추가\n*2.조회\n*3.입금\n*4.출금\n*5.삭제\n*9.종료\n*입력>>>");
			num = scanner.nextInt();

			// 1. 계정 추가
			if (num == 1) {
				int find = -1;
				for (int i = 0; i < id.length; i++) {
					if (id[i] == null) {
						find = i;
						break;
					}
				}

				if (find == -1) {
					System.out.println("더 이상 계정을 추가할 수 없습니다.");
				} else {
					System.out.println("id입력 >");
					id[find] = scanner.next();
					System.out.println("pass입력 >");
					pass[find] = scanner.next();
					System.out.println("잔액입력 >");
					balance[find] = scanner.nextDouble();
				}
			}

			// 2,3,4,5. 조회/입금/출금/삭제
			else if (num == 2 || num == 3 || num == 4 || num == 5) {
				System.out.println("id입력 >");
				String tempid = scanner.next();
				System.out.println("pass입력 >");
				String temppass = scanner.next();

				int find = -1;
				for (int i = 0; i < id.length; i++) {
					if (tempid.equals(id[i]) && temppass.equals(pass[i])) {
						find = i;
						break;
					}
				}

				if (find == -1) {
					System.out.println("사용자 정보 확인 실패");
					continue;
				}

				if (num == 2) {
					// 조회
					System.out.println("id > " + id[find]);
					System.out.println("pass > " + pass[find]);
					System.out.println("잔액 > " + balance[find]);
				} else if (num == 3) {
					// 입금
					System.out.println("입금할 금액 >");
					double money = scanner.nextDouble();
					balance[find] += money;
					System.out.println("잔액 > " + balance[find]);
				} else if (num == 4) {
					// 출금
					System.out.println("출금할 금액 >");
					double money = scanner.nextDouble();
					if (money > balance[find]) {
						System.out.println("잔액 부족");
					} else {
						balance[find] -= money;
						System.out.println("잔액 > " + balance[find]);
					}
				} else if (num == 5) {
					// 삭제
					id[find] = null;
					pass[find] = null;
					balance[find] = 0;
					System.out.println("삭제 완료");
				}
			}

			// 메뉴 외 잘못된 입력
			else if (num != 9) {
				System.out.println("메뉴를 확인해주세요");
			}
		} // end while
	} // end main
} // end class
