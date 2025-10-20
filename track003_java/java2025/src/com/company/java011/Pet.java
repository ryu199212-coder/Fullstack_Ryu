package com.company.java011;

import java.util.Scanner;

public class Pet{  
	   private String name;  
	   private int walkTime, snackCount, cuddleCount, moodScore;  
	   private String snackStars, tailWag, todayMessage;
	   
	      
	   public Pet(String name, int walkTime, int snackCount, int cuddleCount) {
		super();
		this.name = name;
		this.walkTime = walkTime;
		this.snackCount = snackCount;
		this.cuddleCount = cuddleCount;
	}

	   public Pet() {
		super();
		
		// TODO Auto-generated constructor stub
	}

	   public void input() {
	         Scanner scanner = new Scanner(System.in);
	         System.out.print("이름 입력 : ");
	         this.name = scanner.nextLine();
	         System.out.print("산책시간 입력 : ");
	         this.walkTime = scanner.nextInt();
	         System.out.print("간식개수 입력 : ");
	         this.snackCount = scanner.nextInt();
	         System.out.print("쓰다듬횟수 입력 : ");
	         this.cuddleCount = scanner.nextInt();
	      }
    
  
	   public void moodScore() {this.moodScore = this.walkTime + (this.snackCount * 10) + (this.cuddleCount * 5);}
	   
	   public void snackStars() { 
			  if(this.moodScore>= 90) {this.snackStars = "★★★★★";}
		 else if(this.moodScore >= 70) {this.snackStars = "★★★★";}
	 	 else if(this.moodScore >= 50) {this.snackStars = "★★★";}
	 	 else if(this.moodScore >= 30) {this.snackStars = "★★";}
			else{this.snackStars = "★";}
		   }
	   
	   public void tailWag() {
		      if(this.moodScore>= 90) {this.tailWag = "흔들흔들흔들";}
		 else if(this.moodScore >= 60) {this.tailWag =  "흔들흔들";}
	 	 else if(this.moodScore >= 40) {this.tailWag =  "살짝 흔들";}
			else{this.tailWag =  "꼬리 내림";}
	   }
	   
	   public void todayMessage() {
		      if(this.moodScore>= 90) {this.todayMessage = "오늘은 정말 행복했어요!";}
		 else if(this.moodScore >= 70) {this.todayMessage = "좋은 하루였어요!";}
		 else if(this.moodScore >= 50) {this.todayMessage = "조금 더 놀아줘요!";}
		    else{this.todayMessage = "외로웠어요...";}
	   }


	   public String getName() {
		return name;
	}

	   public void setName(String name) {
		   this.name = name;
	   }

	   public int getWalkTime() {
		   return walkTime;
	   }

	   public void setWalkTime(int walkTime) {
		   this.walkTime = walkTime;
	   }

	   public int getSnackCount() {
		   return snackCount;
	   }

	   public void setSnackCount(int snackCount) {
		   this.snackCount = snackCount;
	   }

	   public int getCuddleCount() {
		   return cuddleCount;
	   }

	   public void setCuddleCount(int cuddleCount) {
		   this.cuddleCount = cuddleCount;
	   }

	   public int getMoodScore() {
		   return moodScore;
	   }

	   public void setMoodScore(int moodScore) {
		   this.moodScore = moodScore;
	   }

	   public String getSnackStars() {
		   return snackStars;
	   }

	   public void setSnackStars(String snackStars) {
		   this.snackStars = snackStars;
	   }

	   public String getTailWag() {
		   return tailWag;
	   }

	   public void setTailWag(String tailWag) {
		   this.tailWag = tailWag;
	   }

	   public String getTodayMessage() {
		   return todayMessage;
	   }

	   public void setTodayMessage(String todayMessage) {
		   this.todayMessage = todayMessage;
	   }

	   public void show() {
		   moodScore();
		   snackStars();
		   tailWag();
		   todayMessage();
		   
		   System.out.printf("%s   %d   %d   %d   %d   %s   %s   %s\n",
				   this.name, this.walkTime, this.snackCount, this.cuddleCount, 
				   this.moodScore, this.snackStars, this.tailWag, this.todayMessage);
			
	   }
	   public static void info() {
		   System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		   System.out.println("이름   산책시간   간식개수   쓰다듬횟수   행복도   간식보상   꼬리흔들기   오늘의멘트");
		   System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
	   }


}
