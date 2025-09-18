package com.company.java008_ex;

public class Repeat019 {
	
	public static void start() {
		System.out.println("부우웅...");
    
    }
	public static void scan(int n, char ch) {
    	for(int i = 0; i<7; i++) 
    	{System.out.println(ch);}
    	System.out.println();
    }
	public static String signalGrade(int strength) {
    	if(strength >= 80) {return "A";}
    	else if(strength >= 70) {return "B";}
    	else if(strength >= 60) {return "C";}
    	else{return "D";}
    	
    }
	public static int charge(int current, int added) {
    	return current + added;
    }
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		System.out.println("🤖 탐사 로봇이 기동합니다!");
        start();                    // "부우웅..." 출력
        
        System.out.println("\n🪐 행성 스캔 중...");
        scan(5, '#');              // ##### 출력
        
        System.out.println("\n📡 신호 강도 분석 결과:");
        System.out.println("신호 등급: " + signalGrade(65));  // C 출력
        
        System.out.println("\n🔋 배터리 충전 중...");
        System.out.println("충전 완료: " + charge(40, 40) + "%");  // 80 출력
    }	

    // start(): "부우웅..." 출력
    // scan(int n, char ch): ch 문자를 n번 반복 출력
    // signalGrade(int strength): strength 값에 따라 A/B/C/D 등급 반환
    // charge(int current, int added): 현재 배터리와 추가 충전량을 받아 총 퍼센트 반환
	
}