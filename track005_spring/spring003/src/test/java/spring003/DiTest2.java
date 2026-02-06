package spring003;
 
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import spring003_2.IceCreamShop;

@RunWith(SpringJUnit4ClassRunner.class) //spring 구동
@ContextConfiguration(locations = "classpath:config/beans2.xml")  //설정
public class DiTest2 {
	@Autowired ApplicationContext  context; // bean
	
	@Test public void test() {
		IceCreamShop bera31 = context.getBean("iceCreamShop",  IceCreamShop.class);
		bera31.open();
	}
}
