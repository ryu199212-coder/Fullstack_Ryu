package com.company.ioctest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ex3.AnimalFarm;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/beans1.xml")
public class ioctest {
	@Autowired ApplicationContext  context;
	
	@Test public void test() {
	AnimalFarm farm = (AnimalFarm) context.getBean("animalFarm"); 
	farm.print();
	}
}
