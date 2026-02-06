package com.company.java017_newtwork;


//1. Candy를 Mentol클래스가 상속 1초에 1개 팔렸다
class Candy{
	String name;
	public void sell() {System.out.println(name + "가 1개 팔렸습니다.");}
}
class MentorSeller extends Candy implements Runnable{
//2. MentorSeller extends Thread 불가능 Runnable 사용 
//3. 구현내용인 run 1초에 Thread.sleep(1000) 1개씩 팔렸다 sell()
	@Override public void run() {
		for(int i = 0; i < 10; i++) {
			if(Thread.currentThread().isInterrupted()) { // 방법2
				System.out.println("판매 중단 요청됨.");
				break;
			}
			sell();
			try { Thread.sleep(1000); } 
			catch (InterruptedException e) {Thread.currentThread().interrupt();}
				
			}
		
	}
	////////////////////////반복종료 = 방법1////////////////////////////////
	/*@Override public void run() {
		for(int i = 0; i < 10; i++) {
			sell();
			try { Thread.sleep(1000); } 
			catch (InterruptedException e) {  e.printStackTrace(); }
				System.out.println("판매 중단 요청됨.");
				break; 
		}
	}*/
	////////////////////////////////////////////////////////////////////
}
public class TreadEx004_interupt {
	public static void main(String[] args) {
//4. MentorSeller start 실행해주기
		MentorSeller seller = new MentorSeller();
		seller.name = "멘톨캔디";
		Thread count = new Thread(seller); count.start();
		for(int i = 0; i < 5; i++) {
			System.out.print("손님 기다리는중.....");		
			try { Thread.sleep(1000); } 
			catch (InterruptedException e) {  e.printStackTrace(); }
		}
		System.out.println(".....손님이 없어서 판매를 중단합니다.");
		count.interrupt();//### 스레드 중단 요청
	}
}
