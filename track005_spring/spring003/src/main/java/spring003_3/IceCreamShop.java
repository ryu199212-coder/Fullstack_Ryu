package spring003_3;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import lombok.Data;

@Data
@Component("iceCreamShop")
public class IceCreamShop {
   @Value("${name}")private String name;
   //@Autowired @Qualifier("choco")
   @Resource(name="${iceCream}") private IceCream iceCream;
   
   public void open() {
      System.out.print("æ∆¿ÃΩ∫≈©∏≤ ∞°∞‘ ø¿«¬! ø¿¥√¿« ∏¿¿∫: ");
      iceCream.taste();
   }
}