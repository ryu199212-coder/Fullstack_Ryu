package com.company.java017_newtwork_ex;


//1. Candy를 Mentol클래스가 상속 1초에 1개 팔렸다
class Candy{
	String name;
	public void sell() {System.out.println(name + "가 1개 팔렸습니다.");}
}
class MentorSeller extends Candy implements Runnable{
//2. MentorSeller extends Thread 불가능 Runnable 사용 
//3. 구현내용인 run 1초에 Thread.sleep(1000) 1개씩 팔렸다 sell()
	@Override public void run() {
		for(int i = 0; i < 5; i++) {
			sell();
			try { Thread.sleep(1000); } 
			catch (InterruptedException e) {  e.printStackTrace(); }
		}
	}
}
public class ThreadEx002 {
	public static void main(String[] args) {
//4. MentorSeller start 실행해주기
		MentorSeller seller = new MentorSeller();
		seller.name = "멘톨캔디";
		Thread count = new Thread(seller); count.start();
		for(int i = 0; i < 5; i++) {
			System.out.println("손님 기다리는중.....");
			
			try { Thread.sleep(1000); } 
			catch (InterruptedException e) {  e.printStackTrace(); }
		}
	}
}
