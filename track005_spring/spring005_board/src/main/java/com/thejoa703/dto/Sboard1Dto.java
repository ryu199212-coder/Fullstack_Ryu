package com.thejoa703.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sboard1Dto {
    private int id;               // ID
    private int appUserId;        // APP_USER_ID
    private String btitle;        // BTITLE
    private String bcontent;      // BCONTENT
    private String bpass;         // BPASS
    private String bfile;         // BFILE (default: 'no.png')
    private int bhit;             // BHIT (default: 0)
    private String bip;           // BIP
    //private LocalDateTime createdAt; // CREATE_AT (default: SYSTIMESTAMP)
    private String createdAt;
}


/*       
		SQL> desc sboard1;
		Name                                      Null?    Type
		----------------------------------------- -------- ----------------------------
		ID                                        NOT NULL NUMBER
		APP_USER_ID                               NOT NULL NUMBER
		BTITLE                                    NOT NULL VARCHAR2(1000)
		BCONTENT                                  NOT NULL CLOB
		BPASS                                     NOT NULL VARCHAR2(255)
		BFILE                                              VARCHAR2(255)   
		BHIT                                               NUMBER(10)      default  0
		BIP                                       NOT NULL VARCHAR2(255)
		CREATED_AT                                          TIMESTAMP(6)    default sysdate

		■ insert : 
			insert into sboard1 (             ID , APP_USER_ID , BTITLE , BCONTENT , BPASS , BIP)  
			values     (     sboard1_seq.nextval ,  21, 'title','content','1111' ,'127.0,0,1' ) 
		■ selectAll
			select * from sboard1  order by id desc;
		■ select
			select * from sboard1 where id=4;
		■ update
			update  sboard1  
			set     BTITLE='new1' , BCONTENT='content1'  
			where   id=4  and BPASS='1111';
		■ delete
			delete from sboard1 where   id=4    and   BPASS='1111';


*/