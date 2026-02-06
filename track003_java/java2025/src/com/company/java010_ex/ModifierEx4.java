package com.company.java010_ex;

import com.company.java011.Pet;

public class ModifierEx4{
	public static void main(String[] args) {
		  Pet kong = new Pet();       
	      Pet nabi = new Pet("나비", 10, 1, 2);    

	      // Pet.info()위에 메서드작성해주z세요!  ##  
	      // setter를 이용해주세요!  
	      kong.setName("콩이"); kong.setWalkTime(60); kong.setSnackCount(3); kong.setCuddleCount(10);  
	      
	  
	      nabi.input();
	      Pet.info();     // 클래스메서드  
	      kong.show();          
	      nabi.show(); 
	      
	   }
}	
	

/*
연습문제5) 지정접근자  
패키지명 : com.company.java010_ex  
클래스명 : ModifierEx004  
다음과 같이 코드를 작성하시오.

ㅁ출력된화면  
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
이름   산책시간    간식개수     쓰다듬횟수  행복도    간식보상   꼬리흔들기   오늘의멘트  
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
콩이   60분       3개       10회       95       ★★★★★     흔들흔들흔들   "오늘은 정말 행복했어요!"  
나비   10분       1개       2회        40       ★★         살짝 흔들     "조금 더 놀아줘요!"  



ㅁ주어진조건  

1) 항목별 조건 및 계산 방식
 간식개수 (snackCount)
사용자가 직접 입력하는 값 (예: kong.setSnackCount(3);)
행복도 계산 시 10점씩 반영됨 → snackCount * 10

2) 쓰다듬횟수 (cuddleCount)
사용자가 직접 입력하는 값 (예: kong.setCuddleCount(10);)
행복도 계산 시 5점씩 반영됨 → cuddleCount * 5

3) 행복도 (moodScore)
계산 공식:
코드
moodScore = walkTime + (snackCount * 10) + (cuddleCount * 5)
예: 산책 60분, 간식 3개, 쓰다듬 10회 → 60 + 30 + 50 = 140

4) 간식보상 (snackStars)
행복도 점수 범위   간식보상 출력
90 이상   ★★★★★
70 이상   ★★★★
50 이상   ★★★
30 이상   ★★
그 외   ★

5)  꼬리흔들기 (tailWag)
행복도 점수 범위   꼬리흔들기 출력
90 이상   흔들흔들흔들
60 이상   흔들흔들
40 이상   살짝 흔들
그 외   꼬리 내림

6)  오늘의 멘트 (todayMessage)
행복도 점수 범위   출력 멘트
90 이상   "오늘은 정말 행복했어요!"
60 이상   "좋은 하루였어요!"
40 이상   "조금 더 놀아줘요!"
그 외   "외로웠어요..."


public class Pet{  
   private String name;  
   private int walkTime, snackCount, cuddleCount, moodScore;  
   private String snackStars, tailWag, todayMessage;  
} // java011_ex에 설정해주세요!

public class PetMoodEx{    // java011 패키지에 설정해주세요.  
   public static void main(String[] args) {  
      Pet kong = new Pet();       
      Pet nabi = new Pet("나비", 10, 1, 2);    

      // Pet.info()위에 메서드작성해주세요!  ##  
      // setter를 이용해주세요!  
      kong.setName("콩이"); kong.setWalkTime(60); kong.setSnackCount(3); kong.setCuddleCount(10);  

      Pet.info();     // 클래스메서드  
      kong.show();          
      nabi.show();   
   }  
 */