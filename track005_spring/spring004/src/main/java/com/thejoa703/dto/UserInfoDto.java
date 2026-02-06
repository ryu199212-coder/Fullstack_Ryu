package com.thejoa703.dto;

import lombok.Data;

@Data
public class UserInfoDto {
	private int no;
	private String email;
	private int age;
}
/*
 * ■1. 테이블구조 SQL> desc userinfo; 
 *  Name            Null?                        Type
 * ----------------------------------------- --------
 *  NO            NOT NULL                    NUMBER 
 *  EMAIL         NOT NULL                    VARCHAR2(100)
 *  AGE                                       NUMBER
 * 
 */