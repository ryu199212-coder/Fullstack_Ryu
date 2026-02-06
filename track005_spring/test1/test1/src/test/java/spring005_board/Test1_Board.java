package spring005_board;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.thejoa703.dao.Sboard1Dao;
import com.thejoa703.dto.Sboard1Dto;
import com.thejoa703.service.Sboard1Service;

@RunWith(SpringJUnit4ClassRunner.class)  //1. 스프링구동
@ContextConfiguration(locations = "classpath:config/root-context.xml") //2. 설정

public class Test1_Board {
	@Autowired  ApplicationContext context; //3. ioc - Bean (스프링이 관리하는 객체) 생성~소멸
	@Autowired  DataSource   ds;
	@Autowired  SqlSession   session;
	@Autowired  Sboard1Dao   dao;
	@Autowired  Sboard1Service service;
	
	@Ignore @Test public void test1() { System.out.println(context); }
	@Ignore @Test public void test2() { System.out.println(ds);      }
	@Ignore @Test public void test3() { System.out.println(session);      }
	 
	
	@Ignore @Test public void test4() throws UnknownHostException { 
    	//5. delete
//    	Sboard1Dto dto = new Sboard1Dto(); 
//    	dto.setBpass("1111");  dto.setId(5);
//    	System.out.println(dao.delete(dto));
    	
    	//4. update
//    	Sboard1Dto dto = new Sboard1Dto(); 
//    	dto.setBtitle("title-new");   dto.setBcontent("content-new");  dto.setBpass("1111");  dto.setId(5);
//    	System.out.println(dao.update(dto));
    	
    	//3. select
//    	System.out.println(dao.select(7));
    	
    	//2. insert
    	Sboard1Dto dto = new Sboard1Dto();
    	dto.setAppUserId(24);  dto.setBtitle("title");   dto.setBcontent("content");  dto.setBpass("1111");
    	dto.setBip(  InetAddress.getLocalHost().getHostAddress()  ); 
    	
    	System.out.println(dto);
    	
    	System.out.println(dao.insert(dto));
    	
    	//1. selectAll
    	System.out.println(dao.selectAll());
   }

}









