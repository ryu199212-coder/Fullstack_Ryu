package spring003;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component("iceCreamShop")
public class IceCreamShop {
   private String   name;
   private IceCream iceCream;
   
   public void open() {
      System.out.print("æ∆¿ÃΩ∫≈©∏≤ ∞°∞‘ ø¿«¬! ø¿¥√¿« ∏¿¿∫: ");
      iceCream.taste();
   }
}