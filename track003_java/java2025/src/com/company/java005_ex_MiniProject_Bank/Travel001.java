package com.company.java005_ex_MiniProject_Bank;

import java.util.Scanner;

public class Travel001 {
	public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int num = -1;
        String travelerId = "", travelerPass = "";
        int travelBudget = 0;
        int amount = 0;
        String tempId = "", tempPass = "";

        // 여행지 목록 및 관련 데이터
        String[] destinations = {"서울", "부산", "대전", "대구", "광주"};
        String[][] schedules = {
            {"1일차: 경복궁 투어", "2일차: 북촌 한옥마을 산책", "3일차: 남산타워 야경 감상"},
            {"1일차: 해운대 해변 산책", "2일차: 광안리 카페 거리 탐방", "3일차: 자갈치시장 해산물 체험"},
            {"1일차: 한밭수목원 산책", "2일차: 대전시립미술관 관람", "3일차: 성심당 빵 투어"},
            {"1일차: 동성로 쇼핑", "2일차: 팔공산 케이블카", "3일차: 서문시장 야시장"},
            {"1일차: 국립아시아문화전당 관람", "2일차: 양림동 역사마을 탐방", "3일차: 무등산 등산"}
        };
        int[] dailyCosts = {70000, 80000, 65000, 60000, 75000}; // 하루 평균 비용
        String selectedDestination = "";

        while (true) {
            System.out.println("====== TRAVEL RESERVATION SYSTEM ======\n*" +
                "1.여행자 등록\n*2.정보 조회\n*3.예산 추가\n*4.예약 취소(예산 차감)\n*5.여행자 삭제\n*9.종료\n*입력>>>");
            num = scanner.nextInt();

            if (num == 9) {
                System.out.println("여행 예약 시스템 종료");
                break;
            }

            if (num == 1) {
                System.out.println("여행자 등록");
                System.out.print("ID 입력 > ");
                travelerId = scanner.next();
                System.out.print("비밀번호 입력 > ");
                travelerPass = scanner.next();
                System.out.print("여행 예산 입력 > ");
                travelBudget = scanner.nextInt();

                // 여행지 선택
                System.out.println("여행지를 선택하세요:");
                for (int i = 0; i < destinations.length; i++) {
                    System.out.println((i + 1) + ". " + destinations[i]);
                }
                System.out.print("선택 번호 > ");
                int destChoice = scanner.nextInt();
                if (destChoice >= 1 && destChoice <= destinations.length) {
                    selectedDestination = destinations[destChoice - 1];
                    System.out.println("선택된 여행지: " + selectedDestination);

                    // 일정 출력
                    System.out.println("📅 추천 일정:");
                    for (String dayPlan : schedules[destChoice - 1]) {
                        System.out.println(dayPlan);
                    }

                    // 예산 자동 계산
                    int estimatedCost = dailyCosts[destChoice - 1] * 3;
                    System.out.println("💰 예상 여행 경비 (3일 기준): " + estimatedCost + "원");
                    if (travelBudget >= estimatedCost) {
                        System.out.println("✅ 예산이 충분합니다! 즐거운 여행 되세요.");
                    } else {
                        System.out.println("⚠️ 예산이 부족할 수 있습니다. 일정 조정 또는 예산 추가를 고려하세요.");
                    }

                } else {
                    System.out.println("잘못된 선택입니다. 기본값 '서울'로 설정됩니다.");
                    selectedDestination = "서울";
                }
            }

            else if (num == 2 || num == 3 || num == 4 || num == 5) {
                System.out.print("ID 입력 > ");
                tempId = scanner.next();
                System.out.print("비밀번호 입력 > ");
                tempPass = scanner.next();

                if (!(travelerId.equals(tempId) && travelerPass.equals(tempPass))) {
                    System.out.println("정보가 일치하지 않습니다.");
                    continue;
                }
            }

            if (num == 2) {
                System.out.println("여행자 정보");
                System.out.println("ID > " + travelerId);
                System.out.println("비밀번호 > " + travelerPass);
                System.out.println("여행 예산 > " + travelBudget + "원");
                System.out.println("여행지 > " + selectedDestination);
            }

            if (num == 3) {
                System.out.print("추가할 예산 > ");
                amount = scanner.nextInt();
                travelBudget += amount;
                System.out.println("예산 > " + travelBudget + "원");
            }

            if (num == 4) {
                System.out.print("취소로 차감할 금액 > ");
                amount = scanner.nextInt();
                if (amount > travelBudget) {
                    System.out.println("예산 부족");
                } else {
                    travelBudget -= amount;
                    System.out.println("예산 > " + travelBudget + "원");
                }
            }

            if (num == 5) {
                travelerId = "";
                travelerPass = "";
                travelBudget = 0;
                selectedDestination = "";
                System.out.println("여행자 정보 삭제 완료");
            }
        }

        scanner.close();
	

    }

}
