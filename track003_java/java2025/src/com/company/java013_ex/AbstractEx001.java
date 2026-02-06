package com.company.java013_ex;
/*
 *    Abstract (is A) 일반클래스 + 설계가 목적
 *    
 *   				 		  Object
 *    	   			 			 ↑
 *   				           Robot {abstract charge(), move(), speak()}
 *   				 ↑           ↑          ↑
 *       CleeningRobot     SecurityRobot    CookingRobot
*/
abstract class Robot{ //일반클래스 + 설계
	String name;
	int batteryLevel;
	abstract void charge();    // '추상 메서드'가 있으면 반드시 '추상 클래스'로 만들어줘야함
	abstract void move();      // 구현부가 없음{} = 추상메서드
	abstract void speak();     // 공통의 속성, 구체적인 내용이 없음
	// public Animal() { super();  }
}
class CleeningRobot extends Robot{
	@Override void charge() {System.out.println(super.name + "청소로봇 충전중... 배터리 80%");}
	@Override void move() {System.out.println(super.name + "청소로봇 바닥을 따라 이동!");}
	@Override void speak() {System.out.println(super.name + "청소로봇:먼지를 제거합니다!");}
}
class SecurityRobot extends Robot{
	@Override void charge() {System.out.println(super.name + "경비로봇 충전중... 배터리 70%");}
	@Override void move() {System.out.println(super.name + "경비로봇 바닥을 따라 이동");}
	@Override void speak() {System.out.println(super.name + "경비로봇:이상 없음. 안전 확보!");}	
}
class CookingRobot extends Robot{
	@Override void charge() {System.out.println(super.name + "요리로봇 인덕션 충전 중... 배터리 95%");}
	@Override void move() {System.out.println(super.name + "요리로봇 바닥을 따라 이동");}
	@Override void speak() {System.out.println(super.name + "요리로봇:오늘의 메뉴는 파스타입니다!");}	
}
	
	
public class AbstractEx001 {
	public static void main(String[] args) {
		//Robot robot = new Robot();  // 1. abstract class : 일반클래스 + 설계
		System.out.println("\n--- 로봇 배열 시뮬레이션 ---");
		Robot[] bots = {new CleeningRobot(), new SecurityRobot(), new CookingRobot()};
		int  [] levels = {50, 70, 95};
	
	for(int i=0; i<bots.length; i++) {
		bots[i].name = "Robo" + (i+1);
		bots[i].batteryLevel = levels[i];
		bots[i].charge();
		bots[i].speak();
	}
	}
}

/*
 * method area : 클래스 정보
 * 
 * heap area : 동적, 배열            stack area : 임시
 * 1번지{model, battery, charge()X}
Robo1 청소로봇 충전 중... 배터리 50%
Robo1 청소로봇: 먼지를 제거합니다!
Robo2 경비로봇 태양광 충전 중... 배터리 70%
Robo2 경비로봇: 이상 없음. 안전 확보!
Robo3 요리로봇 인덕션 충전 중... 배터리 95%
Robo3 요리로봇: 오늘의 메뉴는 파스타입니다!
*/
