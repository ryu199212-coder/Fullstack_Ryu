package com.company.java005_ex_MiniProject_Bank;

import java.util.Scanner;

public class Travel002 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = -1;

        // 최대 3명의 여행자 정보 저장
        String[] travelerId = new String[3];
        String[] travelerPass = new String[3];
        int[] travelBudget = new int[3];
        String[] selectedDestination = new String[3];

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

        while (true) {
            // 📌 간단한 여행자 목록 상단 출력
            System.out.println("\n📌 현재 등록된 여행자 목록:");
            boolean hasTraveler = false;
            for (int i = 0; i < travelerId.length; i++) {
                if (travelerId[i] != null) {
                    hasTraveler = true;
                    System.out.println(" - " + travelerId[i] + " (" + selectedDestination[i] + ")");
                }
            }
            if (!hasTraveler) {
                System.out.println(" - 등록된 여행자가 없습니다.");
            }

            System.out.println("\n====== TRAVEL RESERVATION SYSTEM(Ver-2) ======");
            System.out.println("* 1. 여행자 등록");
            System.out.println("* 2. 정보 조회");
            System.out.println("* 3. 예산 추가");
            System.out.println("* 4. 예약 취소 (예산 차감)");
            System.out.println("* 5. 여행자 삭제");
            System.out.println("* 6. 여행자 목록 보기");
            System.out.println("* 9. 종료");
            System.out.print("* 입력 >>> ");
            num = scanner.nextInt();

            if (num == 9) {
                System.out.println("여행 예약 시스템 종료");
                break;
            }

            // 여행자 등록
            if (num == 1) {
                int index = -1;
                for (int i = 0; i < travelerId.length; i++) {
                    if (travelerId[i] == null) {
                        index = i;
                        break;
                    }
                }

                if (index == -1) {
                    System.out.println("⚠️ 더 이상 여행자를 등록할 수 없습니다.");
                    continue;
                }

                System.out.print("ID 입력 > ");
                travelerId[index] = scanner.next();
                System.out.print("비밀번호 입력 > ");
                travelerPass[index] = scanner.next();
                System.out.print("여행 예산 입력 > ");
                travelBudget[index] = scanner.nextInt();

                // 여행지 선택
                System.out.println("여행지를 선택하세요:");
                for (int i = 0; i < destinations.length; i++) {
                    System.out.println((i + 1) + ". " + destinations[i]);
                }
                System.out.print("선택 번호 > ");
                int destChoice = scanner.nextInt();

                if (destChoice >= 1 && destChoice <= destinations.length) {
                    selectedDestination[index] = destinations[destChoice - 1];
                    System.out.println("선택된 여행지: " + selectedDestination[index]);

                    // 일정 출력
                    System.out.println("📅 추천 일정:");
                    for (String dayPlan : schedules[destChoice - 1]) {
                        System.out.println(dayPlan);
                    }

                    // 예산 자동 계산
                    int estimatedCost = dailyCosts[destChoice - 1] * 3;
                    System.out.println("💰 예상 여행 경비 (3일 기준): " + estimatedCost + "원");
                    if (travelBudget[index] >= estimatedCost) {
                        System.out.println("✅ 예산이 충분합니다! 즐거운 여행 되세요.");
                    } else {
                        System.out.println("⚠️ 예산이 부족할 수 있습니다. 일정 조정 또는 예산 추가를 고려하세요.");
                    }
                } else {
                    System.out.println("잘못된 선택입니다. 기본값 '서울'로 설정됩니다.");
                    selectedDestination[index] = "서울";
                }
            }

            // 인증 및 기능 수행
            else if (num == 2 || num == 3 || num == 4 || num == 5) {
                System.out.print("ID 입력 > ");
                String tempId = scanner.next();
                System.out.print("비밀번호 입력 > ");
                String tempPass = scanner.next();

                int index = -1;
                for (int i = 0; i < travelerId.length; i++) {
                    if (tempId.equals(travelerId[i]) && tempPass.equals(travelerPass[i])) {
                        index = i;
                        break;
                    }
                }

                if (index == -1) {
                    System.out.println("❌ 정보가 일치하지 않습니다.");
                    continue;
                }

                if (num == 2) {
                    System.out.println("👤 여행자 정보");
                    System.out.println("ID > " + travelerId[index]);
                    System.out.println("비밀번호 > " + travelerPass[index]);
                    System.out.println("여행 예산 > " + travelBudget[index] + "원");
                    System.out.println("여행지 > " + selectedDestination[index]);
                }

                if (num == 3) {
                    System.out.print("추가할 예산 > ");
                    int amount = scanner.nextInt();
                    travelBudget[index] += amount;
                    System.out.println("예산 > " + travelBudget[index] + "원");
                }

                if (num == 4) {
                    System.out.print("취소로 차감할 금액 > ");
                    int amount = scanner.nextInt();
                    if (amount > travelBudget[index]) {
                        System.out.println("⚠️ 예산 부족");
                    } else {
                        travelBudget[index] -= amount;
                        System.out.println("예산 > " + travelBudget[index] + "원");
                    }
                }

                if (num == 5) {
                    travelerId[index] = null;
                    travelerPass[index] = null;
                    travelBudget[index] = 0;
                    selectedDestination[index] = null;
                    System.out.println("🗑️ 여행자 정보 삭제 완료");
                }
            }

            // 여행자 목록 전체 보기
            else if (num == 6) {
                System.out.println("📋 여행자 목록");
                boolean hasTraveler1 = false;
                for (int i = 0; i < travelerId.length; i++) {
                    if (travelerId[i] != null) {
                        hasTraveler1 = true;
                        System.out.println("[" + (i + 1) + "번째 여행자]");
                        System.out.println("ID > " + travelerId[i]);
                        System.out.println("여행지 > " + selectedDestination[i]);
                        System.out.println("예산 > " + travelBudget[i] + "원");
                        System.out.println("-------------------------");
                    }
                }
                if (!hasTraveler1) {
                    System.out.println("⚠️ 등록된 여행자가 없습니다.");
                }
            }
        }

        scanner.close();
    }
}
