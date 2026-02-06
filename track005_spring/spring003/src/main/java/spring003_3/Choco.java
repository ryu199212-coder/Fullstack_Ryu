package spring003_3;

import org.springframework.stereotype.Component;

@Component("choco")
public class Choco implements IceCream {
	public void taste() {    System.out.println("초코 아이스크림은 진하고 풍부해요!");   }

}

