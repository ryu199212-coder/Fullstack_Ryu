package com.thejoa703;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Boot001Application { 
	public static void main(String[] args) {
		SpringApplication.run(Boot001Application.class, args);
	}

}


/*
  insert into sboard2 ( ID    , APP_USER_ID , btitle, bcontent, bpass, bfile,  bip )
  select  sboard2_seq.nextval , APP_USER_ID , btitle, bcontent, bpass, bfile,  bip   from sboard2;

*/