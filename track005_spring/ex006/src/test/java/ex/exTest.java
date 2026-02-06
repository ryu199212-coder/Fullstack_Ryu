package ex;

import java.net.UnknownHostException;
import java.sql.Timestamp;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ex006.dto.ExDto;
@ContextConfiguration(locations = "classpath:config/root-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class exTest {
	@Autowired private ApplicationContext context;
    @Autowired private DataSource datasource;
    @Autowired private SqlSession sqlSession;
    @Autowired private ex006.dao.ExDao dao;
    @Test
	public void test1() { System.out.println(context); }

	@Test
	public void test2() { System.out.println(datasource); }

	@Test
	public void test3() { System.out.println(sqlSession); }
	
	@Test public void test4() throws UnknownHostException{
		ExDto dto = new ExDto();
		dto.setMbtiTypeId(1); dto.setAppUserId(1);
		System.out.println(dao.adminEdit(dto));
		System.out.println(dao.selectAll());
        
		
		  
	}

}
