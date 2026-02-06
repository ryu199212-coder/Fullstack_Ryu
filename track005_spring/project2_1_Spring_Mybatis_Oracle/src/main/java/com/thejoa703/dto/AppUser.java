package com.thejoa703.dto;

import lombok.Data;

@Data
public class AppUser {
	private int appUserId;
	private String email;
	private String password;
	private int mbtiTypeId;
	private String createdAt;
	private String ufile;
	private String mobile;
	private String nickname;
}

/*
SQL> desc appuser;
 Name                                      Null?    Type
 ----------------------------------------- -------- ----------------------------
 APP_USER_ID                               NOT NULL NUMBER(5)
 EMAIL                                     NOT NULL VARCHAR2(100)
 PASSWORD                                           VARCHAR2(100)
 MBTI_TYPE_ID                                       NUMBER(3)
 CREATED_AT                                         DATE
 UFILE                                              VARCHAR2(255)
 MOBILE                                             VARCHAR2(50)
 NICKNAME                                           VARCHAR2(50)

*/