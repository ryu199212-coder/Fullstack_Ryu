package spring003_2;

import org.springframework.stereotype.Component;

@Component("white")
public class White implements IceCream {
	public void taste() {  System.out.println("바닐라 아이스크림은 부드럽고 달콤해요!"); }
	
}
