package com.company.java_ex;

/*Q8
 abstract =     extends사용해서 상속 / 다중상속 불가 / static사용가능 / 생성자o
 interface = implaments사용해서 상속 / 다중상속 가능 / static final만 사용가능 / 생성자x
*/
interface Vehicle {
	public void run();
	default void helmet() {}
} 
class MotorCycle implements Vehicle {
	@Override public void run() { System.out.println("오토바이가 달립니다."); }
	@Override public void helmet() { System.out.println("헬멧을 착용합니다."); }
}
class Car implements Vehicle {
	@Override public void run() { System.out.println("자동차가 달립니다."); }

}
class Driver{
	 public void drive(Vehicle vehicle) {vehicle.helmet();vehicle.run();}
	 public void run() {}

}
public class Repeat008_ {
	public static void main(String[] args) {
		Driver driver = new Driver();		
		MotorCycle motorcycle = new MotorCycle();
		Car car = new Car();
		
		driver.drive(car);
		
		System.out.println(">> 자동차가 고장나서 교통수단을 바꿉니다!");
	
		driver.drive(motorcycle);
		
				
				
	
			
				
				
				
	}
}