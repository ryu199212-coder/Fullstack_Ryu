package com.company.java013_ex;

interface Vehicle {
	public void run();
} 
class MotorCycle implements Vehicle {
	@Override public void run() { System.out.println("오토바이가 달립니다."); }
}
class Car implements Vehicle {
	@Override public void run() { System.out.println("자동차가 달립니다."); }
}
class Driver implements Vehicle {
	 public void drive(Vehicle vehicle) {vehicle.run();}
	@Override public void run() {}
}
public class InetrfaceEx002 {
	public static void main(String[] args) {
		Driver driver = new Driver();
				
				Car car = new Car();
				MotorCycle mo = new MotorCycle();
				
				driver.drive(car);
				driver.drive(mo);
				
	}
}

/*

연습문제2)  
패키지명 :  package com.company.java013_ex;
클래스명 :  public class InterfaceEx002

1. Driver 클래스를 작성하시오.  
2. 주어진조건
interface Vehicle {
	public void run();
} 
class MotorCycle implements Vehicle {
	@Override
	public void run() {
		System.out.println("오토바이가 달립니다.");
	}
}
class Car implements Vehicle {
	@Override
	public void run() {
		System.out.println("자동차가 달립니다.");
	}
}

3. 메인화면
public class Oop014_method_polymorphism {
	public static void main(String[] args) {
		Driver driver = new Driver();
		
		Car car = new Car();
		MotorCycle mo = new MotorCycle();
		
		driver.drive(car);
		driver.drive(mo);
	}
}

4. 실행화면
자동차가 달립니다.
오토바이가 달립니다.
*/