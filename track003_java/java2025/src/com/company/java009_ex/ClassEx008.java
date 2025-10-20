package com.company.java009_ex;

import java.util.Scanner;

class AnimalCharacter {
    // 상태 - 멤버변수  
    String name;  
    String type;  // 육식 or 초식  
    int baseSpeed;  
    int specialBoost;  
    double finalSpeed;

	// 행위 - 멤버함수  
    void input() {Scanner scanner = new Scanner(System.in);
    	System.out.println("동물 이름 > ");
    	this.name=scanner.next();
    	System.out.println("동물 타입 > ");
    	this.type=scanner.next();
    	System.out.println("기본 속도 > ");
    	this.baseSpeed=scanner.nextInt();
    	System.out.println("특수 능력치 > ");
    	this.specialBoost=scanner.nextInt();} // 사용자 입력 받기 
    
    @Override
	public String toString() {
		return "AnimalCharacter [name=" + name + ", type=" + type + ", baseSpeed=" + baseSpeed + ", specialBoost="
				+ specialBoost + ", finalSpeed=" + finalSpeed + "]";
	}

	public AnimalCharacter(String name, String type, int baseSpeed, int specialBoost) {
		super();
		this.name = name;
		this.type = type;
		this.baseSpeed = baseSpeed;
		this.specialBoost = specialBoost;
	}

	public AnimalCharacter() {
		super();
		// TODO Auto-generated constructor stub
	}

	void calculateSpeed() {
    	if(this.type.equals("육식")){finalSpeed = baseSpeed+specialBoost*0.2; }
    	else if(this.type.equals("초식")){finalSpeed = baseSpeed+specialBoost*0.1;}
    	
    	
    	// 타입에 따라 속도 계산  
    }
	void show() {
    	System.out.println("🦁 동물 캐릭터 : " + this.name);
    	System.out.println("🌿 타입 : " + this.type);
    	System.out.println("🚀 최종 속도 : " + this.finalSpeed);
    // 캐릭터 정보 출력
    }
}

 
 
public class ClassEx008 {
    public static void main(String[] args) {
        AnimalCharacter a1 = new AnimalCharacter("치타", "육식", 100, 30);
        a1.show();

        AnimalCharacter a2 = new AnimalCharacter();
        a2.input();
        a2.show();
    }
}

/*패키지명: com.company.java009_ex 
클래스명: ClassEx008 주제: 동물 캐릭터를 생성하고, 능력치를 계산하여 출력하는 프로그램

■설명
AnimalCharacter 클래스를 만들어서 동물 이름, 타입(육식/초식), 기본 속도, 특수 능력치를 입력받고, 최종 속도를 계산해 출력한다. 
특수 능력치는 타입에 따라 다르게 적용된다.
육식 동물: 특수 능력치가 속도에 +20%
초식 동물: 특수 능력치가 속도에 +10%

-- 생성자 작성하시오.

class AnimalCharacter {
    // 상태 - 멤버변수  
    // String name;  
    // String type;  // 육식 or 초식  
    // int baseSpeed;  
    // int specialBoost;  
    // double finalSpeed;

    // 행위 - 멤버함수  
    // void input() : 사용자 입력 받기  
    // void calculateSpeed() : 타입에 따라 속도 계산  
    // void show() : 캐릭터 정보 출력
}

public class ClassEx008 {
    public static void main(String[] args) {
        AnimalCharacter a1 = new AnimalCharacter("치타", "육식", 100, 30);
        a1.show();

        AnimalCharacter a2 = new AnimalCharacter();
        a2.input();
        a2.show();
    }
}



■ 출력내용 
🦁 동물 캐릭터: 치타
🌿 타입: 육식
🚀 최종 속도: 106.00

동물 이름> 토끼
동물 타입(육식/초식)> 초식
기본 속도> 60
특수 능력치> 40
🦁 동물 캐릭터: 토끼
🌿 타입: 초식
🚀 최종 속도: 64.00
*/