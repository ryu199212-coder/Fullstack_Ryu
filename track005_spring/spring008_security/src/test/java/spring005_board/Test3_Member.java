package  spring005_board;


import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.thejoa703.dao.AppUserDao;
import com.thejoa703.dto.AppUserDto;
import com.thejoa703.dto.AuthDto;
import com.thejoa703.service.AppUserService;
 

@RunWith(SpringJUnit4ClassRunner.class)  //1. 스프링구동
@ContextConfiguration(locations = {"classpath:config/root-Context.xml" ,"classpath:config/security-context.xml"}) //2. 설정
public class Test3_Member {
	@Autowired  ApplicationContext context; //3. ioc - Bean (스프링이 관리하는 객체) 생성~소멸
	@Autowired  DataSource   ds;
	@Autowired  SqlSession   session; 
	@Autowired  AppUserDao   dao;
	@Autowired  AppUserService  service; 
	
	@Autowired  @Qualifier("passwordEncoder") PasswordEncoder pwencoder;

	@Test public void test8() {
	/*	AppUserDto dto = new AppUserDto();
		dto.setEmail("3@3"); dto.setPassword(pwencoder.encode("3")); dto.setMbtiTypeId(1);
		System.out.println("2. " + service.insert(dto));
			
		System.out.println(dao.selectAll());
	}*/
	AuthDto adto = new AuthDto();
	adto.setEmail("1@1"); adto.setAuth("ROLE_MEMBER");

	System.out.println(dao.insertAuth(adto));
	}
}









