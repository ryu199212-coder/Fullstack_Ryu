package com.company.java017_newtwork;

class Animal{String name;}

class Dog extends Animal implements Runnable{
	@Override public void run() {
		for(int i = 0; i < 5; i++) {
			System.out.println("멍");
			try { Thread.sleep(1000); } 
			catch (InterruptedException e) {  e.printStackTrace(); }
		}		
	}
}
public class Thread003_Runnable {
	public static void main(String[] args) {
		Thread sound = new Thread(new Dog()); sound.start();
		for(int i = 0; i <5; i++) {
			System.out.println("◖⚆ᴥ⚆◗");
			try { Thread.sleep(1000); } 
			catch (InterruptedException e) {  e.printStackTrace();
			}
		}		
	}
}

/*
1. 프로세스 : 실행중인 하나의 프로그램
2. 멀티프로세스 : 동시에 여러프로세스를 실행
	크롬 실행 → 프로세스1
			실행 → 프로세스2
				실행 → 프로세스3
3. 프로세스 구성
- 자원(Resource) + Thread(자원으로 실제작업을 수행)
- 모든 프로세스는 최소한 하나의 스레드(main)
- 같은 프로세스 내의 스레드는 서로 자원을 공유

4. 동시성과 병렬성
- 동시에 실행하는 것 같은 효과(동시성과 병렬성)
- 동시성 : 하나의 코어, 멀티스레드가 [번갈아가면서] 실행
	  작업수 > 일하는 일꾼(코어)
	  1일꾼 : 1작업 → 2작업 → 3작업 
- 병렬성 : 멀티코어에서 개별스레드를 [동시에] 실행
	  작업수 < 일하는 일꾼(코어)

5. 작업스레드 생성
1) Thread 상속 
2) Runnable 인터페이스 구현

  
*/