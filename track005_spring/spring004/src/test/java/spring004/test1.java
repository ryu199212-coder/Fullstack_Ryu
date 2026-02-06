package spring004;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.thejoa703.dao.TestDao;
import com.thejoa703.dao.UserInfoDao;
import com.thejoa703.dto.UserInfoDto;

@RunWith(SpringJUnit4ClassRunner.class) // 1. spring 구동테스트
@ContextConfiguration(locations = "classpath:config/root-context.xml") // 2. 설정
public class test1 {

	@Autowired
	ApplicationContext context; // 3. bean (스프링이 관리하는 객체) 생성~소멸
	@Autowired
	DataSource datasource;
	@Autowired
	SqlSession sqlSession;
	@Autowired
	TestDao dao;
	@Autowired
	UserInfoDao userDao;

	@Ignore // @test
	public void test1() {
		System.out.println(context);
	}

	@Ignore // @Test
	public void test2() {
		System.out.println(datasource);
	} // datasource

	@Ignore // @Test
	public void test3() {
		System.out.println(sqlSession);
	}

	@Ignore // @Test
	public void test4() {
		System.out.println(dao.now());
	}

	@Test
	public void test5() {
		// 삽입
	    UserInfoDto dto = new UserInfoDto(); dto.setEmail("a@a"); dto.setAge(1);
		System.out.println(userDao.insert(dto));
		// 전부검색
		System.out.println(userDao.selectAll());
		// 일부검색
		System.out.println(userDao.select(1));
		// 수정
		UserInfoDto dto = new UserInfoDto(); dto.setEmail("b@b"); dto.setAge(2);
	    System.out.println(userDao.insert(dto));
		//삭제 
	    System.out.println(userDao.delete(4));
	
	}
}
