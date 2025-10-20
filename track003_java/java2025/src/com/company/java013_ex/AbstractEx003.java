package com.company.java013_ex;

abstract class Astronaut2 {
    protected String name;
    protected int stamina;

    public void setName(String name) { this.name = name; }
    public void setStamina(int stamina) { this.stamina = stamina; }
    public int getStamina() { return stamina; }

    // 공통 로직
    protected void decreaseStamina(int min, int range, String task) {
        int loss = (int)(Math.random() * range + min);
        stamina = Math.max(0, stamina - loss);
        System.out.println(name + " " + task + " 체력 -" + loss);
    }

    protected void increaseStamina(int amount, String task) {
        stamina += amount;
        System.out.println(name + " " + task + " 체력 +" + amount);
    }

    abstract void explore();
    abstract void report();
    abstract void rest();
}

class Engineer2 extends Astronaut2 {
    @Override void explore() {
        decreaseStamina(5, 20, "엔지니어 탐사: 기계 장치 점검 완료!");
    }
    @Override void report() {
        increaseStamina(10, "보고서: 에너지 시스템 안정적.");
    }
    @Override void rest() {
        increaseStamina(15, "휴식: 공구 정리하며 재충전 중...");
    }
}

class Biologist2 extends Astronaut2 {
    @Override void explore() {
        decreaseStamina(10, 15, "생물학자 탐사: 외계 식물 샘플 채취!");
    }
    @Override void report() {
        increaseStamina(8, "보고서: 생명체 흔적 발견.");
    }
    @Override void rest() {
        increaseStamina(20, "휴식: 생체 리듬 조절 중...");
    }
}

class Pilot2 extends Astronaut2 {
    @Override void explore() {
        decreaseStamina(5, 25, "파일럿 탐사: 항로 재설정 및 우주선 조종!");
    }
    @Override void report() {
        increaseStamina(12, "보고서: 궤도 진입 성공.");
    }
    @Override void rest() {
        increaseStamina(10, "휴식: 조종석에서 짧은 명상...");
    }
}



public class AbstractEx003 {
	public static void main(String[] args) {
        System.out.println("\n--- 우주 탐사 대원 시뮬레이션 ---");

        Astronaut2[] crew = { new Engineer2(), new Biologist2(), new Pilot2() };
        String[] names = { "Nova", "Flora", "Jet" };
        int[] staminaValues = { 75, 60, 85 };

        for (int i = 0; i < crew.length; i++) {
            crew[i].setName(names[i]);
            crew[i].setStamina(staminaValues[i]);

            crew[i].explore();
            crew[i].report();

            if (crew[i].getStamina() < 70) {
                crew[i].rest();
            }

            System.out.println("→ 현재 체력: " + crew[i].getStamina());
            System.out.println();
        }
	}
}


/*
> 주어진조건: 
1. explore() 시 체력 감소: stamina -= (int)(Math.random() * 20 + 5); → 5~24 사이의 랜덤한 체력 소모
2. report() 시 체력 회복: stamina += 10;
3. 체력이 70 미만이면 rest() 호출
4. 체력은 0 미만으로 떨어지지 않도록 제한


> 결과 예시 (실행 시마다 달라짐)
코드
Nova 엔지니어 탐사: 기계 장치 점검 완료! 체력 -18
Nova 보고서: 에너지 시스템 안정적. 체력 +10
→ 현재 체력: 67

Flora 생물학자 탐사: 외계 식물 샘플 채취! 체력 -12
Flora 보고서: 생명체 흔적 발견. 체력 +8
Flora 휴식: 생체 리듬 조절 중... 체력 +20
→ 현재 체력: 76

Jet 파일럿 탐사: 항로 재설정 및 우주선 조종! 체력 -23
Jet 보고서: 궤도 진입 성공. 체력 +12
→ 현재 체력: 74
*/