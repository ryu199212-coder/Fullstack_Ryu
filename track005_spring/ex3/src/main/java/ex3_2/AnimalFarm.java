package ex3_2;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component("animalFarm")
public class AnimalFarm {
    @Value("${name}")private String name;
    @Resource(name="${ani}") private Animal ani;
 
    public AnimalFarm() { super(); }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Animal getani() { return ani; }
    public void setani(Animal ani) { this.ani = ani; }
    
    private String cat;
    public String getCat() {return cat;}
    public void setCat(String cat) {this.cat = cat;}
    
    private String dog;
    public String getDog() {return dog;}
    public void setDog(String dog) {this.dog = dog;}
    
    public String aniSleep() { return name + ">" + ani.sleep(); }
    public String aniEat()   { return name + ">" + ani.eat(); }
    public String aniPoo()   { return name + ">" + ani.poo(); }
 
    public void print() { 
       System.out.println(  aniSleep()); 
       System.out.println(  aniEat()); 
       System.out.println(  aniPoo()); 
    }

 }
