package com.company.java005_ex_MiniProject_Bank;

import java.util.Scanner;

public class Portfolio001 {
    public static void main(String[] args) {
        String[] name = new String[3];         // 개발자 이름
        String[] stack = new String[3];        // 기술 스택
        int[] projectCount = new int[3];       // 프로젝트 수
        String[] github = new String[3];       // GitHub 링크
        int num = -1;
        Scanner scanner = new Scanner(System.in);

        while (num != 9) {
            System.out.println("\n========================================");
            System.out.println("👨‍💻 개발자 포트폴리오 관리도구");
            System.out.println("========================================");

            // 개발자 목록 출력
            System.out.print("👥 등록된 개발자: [");
            for (int i = 0; i < name.length; i++) {
                if (name[i] != null) {
                    System.out.print(name[i]);
                    if (i < name.length - 1 && name[i + 1] != null) {
                        System.out.print(", ");
                    }
                }
            }
            System.out.println("]");

            // 기술 스택 목록 출력
            System.out.print("🛠️ 기술 스택: [");
            for (int i = 0; i < stack.length; i++) {
                if (stack[i] != null) {
                    System.out.print(stack[i]);
                    if (i < stack.length - 1 && stack[i + 1] != null) {
                        System.out.print(", ");
                    }
                }
            }
            System.out.println("]");

            // 프로젝트 수 목록 출력
            System.out.print("📁 프로젝트 수: [");
            for (int i = 0; i < projectCount.length; i++) {
                if (name[i] != null) {
                    System.out.print(projectCount[i]);
                    if (i < projectCount.length - 1 && name[i + 1] != null) {
                        System.out.print(", ");
                    }
                }
            }
            System.out.println("]");

            // 메뉴 출력
            System.out.println("\n📋 메뉴");
            System.out.println("----------------------------------------");
            System.out.println("[1] 개발자 추가");
            System.out.println("[2] 포트폴리오 조회");
            System.out.println("[3] 프로젝트 추가");
            System.out.println("[4] 프로젝트 삭제");
            System.out.println("[5] 개발자 삭제");
            System.out.println("[6] 기술 스택으로 개발자 검색");
            System.out.println("[7] 이력서 요약 출력");
            System.out.println("[9] 종료");
            System.out.println("----------------------------------------");
            System.out.print("입력 >>> ");
            num = scanner.nextInt();

            if (num == 1) {
                int find = -1;
                for (int i = 0; i < name.length; i++) {
                    if (name[i] == null) {
                        find = i;
                        break;
                    }
                }

                if (find == -1) {
                    System.out.println("⚠️ 더 이상 개발자를 추가할 수 없습니다.");
                } else {
                    System.out.print("👤 이름 입력 > ");
                    name[find] = scanner.next();
                    System.out.print("🛠️ 기술 스택 입력 > ");
                    stack[find] = scanner.next();
                    System.out.print("📁 프로젝트 수 입력 > ");
                    projectCount[find] = scanner.nextInt();
                    System.out.print("🌐 GitHub 링크 입력 > ");
                    github[find] = scanner.next();
                    System.out.println("✅ 개발자 등록 완료!");
                }
            }

            else if (num == 2 || num == 3 || num == 4 || num == 5) {
                System.out.print("👤 이름 입력 > ");
                String tempName = scanner.next();
                System.out.print("🛠️ 기술 스택 입력 > ");
                String tempStack = scanner.next();

                int find = -1;
                for (int i = 0; i < name.length; i++) {
                    if (tempName.equals(name[i]) && tempStack.equals(stack[i])) {
                        find = i;
                        break;
                    }
                }

                if (find == -1) {
                    System.out.println("❌ 개발자 정보 확인 실패");
                    continue;
                }

                if (num == 2) {
                    System.out.println("\n📄 포트폴리오 조회");
                    System.out.println("----------------------------------------");
                    System.out.println("👤 이름         : " + name[find]);
                    System.out.println("🛠️ 기술 스택    : " + stack[find]);
                    System.out.println("📁 프로젝트 수 : " + projectCount[find] + "개");
                    System.out.println("🌐 GitHub      : " + github[find]);
                    System.out.println("----------------------------------------");
                } else if (num == 3) {
                    System.out.print("➕ 추가할 프로젝트 수 > ");
                    int count = scanner.nextInt();
                    projectCount[find] += count;
                    System.out.println("📁 총 프로젝트 수 > " + projectCount[find] + "개");
                } else if (num == 4) {
                    System.out.print("➖ 삭제할 프로젝트 수 > ");
                    int count = scanner.nextInt();
                    if (count > projectCount[find]) {
                        System.out.println("⚠️ 프로젝트 수 부족");
                    } else {
                        projectCount[find] -= count;
                        System.out.println("📁 총 프로젝트 수 > " + projectCount[find] + "개");
                    }
                } else if (num == 5) {
                    name[find] = null;
                    stack[find] = null;
                    projectCount[find] = 0;
                    github[find] = null;
                    System.out.println("🗑️ 개발자 삭제 완료");
                }
            }

            else if (num == 6) {
                System.out.print("🔍 검색할 기술 스택 입력 > ");
                String keyword = scanner.next();
                boolean found = false;
                System.out.println("\n📊 검색 결과:");
                for (int i = 0; i < stack.length; i++) {
                    if (stack[i] != null && stack[i].equalsIgnoreCase(keyword)) {
                        System.out.println("👤 " + name[i] + " | 📁 프로젝트: " + projectCount[i] + "개");
                        found = true;
                    }
                }
                if (!found) {
                    System.out.println("❌ 해당 기술을 가진 개발자가 없습니다.");
                }
            }

            else if (num == 7) {
                System.out.println("\n📄 전체 이력서 요약");
                for (int i = 0; i < name.length; i++) {
                    if (name[i] != null) {
                        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
                        System.out.println("👤 이름         : " + name[i]);
                        System.out.println("🛠️ 기술 스택    : " + stack[i]);
                        System.out.println("📁 프로젝트 수 : " + projectCount[i] + "개");
                        System.out.println("🌐 GitHub      : " + (github[i] != null ? github[i] : "등록되지 않음"));
                        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
                    }
                }
            }

            else if (num == 9) {
                System.out.println("\n👋 포트폴리오 관리 프로그램을 종료합니다. 안녕!");
            }

            else {
                System.out.println("⚠️ 잘못된 입력입니다. 메뉴 번호를 확인해주세요.");
            }
        }
    }
}
