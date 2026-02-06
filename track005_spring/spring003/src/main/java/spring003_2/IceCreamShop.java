package spring003_2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import lombok.Data;

@Data
@Component
public class IceCreamShop {
   @Value("베라32") 
   private String name;
   @Autowired @Qualifier("white")
   private IceCream iceCream;
   
   public void open() {
      System.out.print("아이스크림 가게 오픈! 오늘의 맛은: ");
      iceCream.taste();
   }
}