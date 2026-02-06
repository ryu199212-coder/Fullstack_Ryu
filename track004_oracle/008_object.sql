-- 008_object.sql

-- 1. 시퀀스 : 자동번호 생성기 ★
-- 2. 뷰 : 가상 테이블 (select 문 결과를 저장한 객체) ★
-- 3. 동의어 : 객체에 대한 별칭
-- 4. 인덱스 : 검색 성능 향상 ★
-- 5. 클러스터 : 여러 테이블을 물리적으로 같은 공간에 저장
select * from recipes_img;
--------------------------------------------------------------------------------
-- 1. 시퀀스
-- #1. 시퀀스 만들기
create sequence emp_seq; -- 1부터 시작, 증가단위 1, 최대값 10^27

create sequence dept_seq;

create sequence emp_seq2
start with 1000 -- 시퀀스 시작값
increment by 1  -- 몇개씩 증가?
maxvalue 9999   -- 최대값
nocache;        -- 미리 데이터값을 몇개 저장해둘지...

-- #2. 시퀀스 사용하기
select emp_seq.nextval from dual;

select dept_seq.nextval from dual;

insert into dept_temp values (dept_seq.nextval, 'ai', 'INCHEON');
select * from dept_temp;

-- Q1. appuser_seq 시퀀스 만들기;
create sequence appuser_seq;
desc appuser;

alter table appuser modify email varchar2(100) unique;

select coulmn

create table appuser(
APP_USER_ID  NUMBER NOT NULL,   
EMAIL        VARCHAR2(100) NOT NULL UNIQUE,
PASSWORD     VARCHAR2(255),
MBTI_TYPE_ID     NUMBER,   
CREATED_AT       DATE   
);

SELECT table_name, constraint_name, column_name
FROM user_cons_columns
WHERE constraint_name IN (
  SELECT constraint_name
  FROM user_constraints
  WHERE constraint_type = 'U'
);

select * from appuser;
create sequence post_seq;
desc post;
drop table post;

create table post(
    ID           NUMBER NOT NULL,
    APP_USER_ID  NUMBER NOT NULL,        
    TITLE        VARCHAR2(200) NOT NULL , 
    CONTENT      CLOB NOT NULL ,   
    PASS         VARCHAR2(200),   
    CREATED_AT   DATE default sysdate,
    HIT NUMBER default 0,
    primary key(ID)
);

 [글쓰기]글쓰기 sql :
	insert into post (id,        app_user_id, title, content, pass)
			  values (post_seq.nextval,   1, 'title','content','1111');
			  
2. [전체보기]전체글가져오기, appuser테이블에서 email 도 같이 가져오기 sql :
	SELECT p.*, email
	FROM   post p join appuser u on p.app_user_id=u.app_user);
	
3. [상세보기]글번호 해당하는 글 가져오기 sql :
	select * from post where id=?
	update post set hit=hit+1 where id=?
	
4. 글 수정하기 sql :
	update post set title='new-title', content='new-content' where id=1 and pass='1111';

5. 글번호 해당하는 삭제
	delete from post where id=1 and pass='1111'
    
    
```sql

CREATE TABLE COMMUNITY_TB (
    postId      NUMBER(8) PRIMARY KEY,      -- 게시글 ID
    id           NUMBER(30) NOT NULL,      -- 작성자
    title        VARCHAR2(200) NOT NULL,    -- 제목
    content      CLOB NOT NULL,             -- 본문
    categoryId  NUMBER(3) NOT NULL,           -- 카테고리 번호
    views        NUMBER(6) DEFAULT 0,       -- 조회수
    createdAt   DATE DEFAULT SYSDATE,       -- 작성일
    updatedAt   DATE DEFAULT SYSDATE,                       -- 수정일
    FOREIGN KEY (id) REFERENCES users(APPUSERID),
    FOREIGN KEY (categoryId) REFERENCES CATEGORY_TB(categoryId)
);

select * from users;

desc users;

create sequence COMMUNITY_TB_seq;

```
commit;
select * from CATEGORY;
INSERT INTO CATEGORY VALUES (1, '전체');
INSERT INTO CATEGORY VALUES (2, '한식');
INSERT INTO CATEGORY VALUES (3, '양식');
INSERT INTO CATEGORY VALUES (4, '중식');
INSERT INTO CATEGORY VALUES (5, '일식');
INSERT INTO CATEGORY VALUES (6, '디저트');
INSERT INTO CATEGORY VALUES (7, '건강식');
INSERT INTO CATEGORY VALUES (8, '기타');


INSERT INTO COMMUNITY_TB (
    postId,
    id,
    title,
    content,
    categoryId,
    views,
    createdAt,
    updatedAt
) VALUES (
    1,               -- postId (NUMBER)
    1,               -- id (NUMBER, users.id 외래키)
    '테스트 제목',    -- title (VARCHAR2)
    '테스트 내용',    -- content (CLOB)
    1,               -- categoryId (NUMBER, CATEGORY_TB 외래키)
    0,               -- views (NUMBER)
    SYSDATE,         -- createdAt (DATE)
    SYSDATE          -- updatedAt (DATE)
);

commit;

===

CREATE TABLE CATEGORY_TB (
    categoryId   NUMBER(3)       PRIMARY KEY,   -- 카테고리 ID
    categoryName VARCHAR2(50)    NOT NULL       -- 카테고리명 (예: 한식, 양식, 중식, 일식)
);

CREATE TABLE users (
    id           NUMBER(8)     PRIMARY KEY,                 -- 사용자 고유 ID
    password    VARCHAR2(100)    NOT NULL,                -- 비밀번호 (암호화 저장)
    nickname    VARCHAR2(50)     NOT NULL,                -- 닉네임
    email         VARCHAR2(100)    UNIQUE,                      -- 이메일 (로그인용)
    mobile      VARCHAR2(20)    UNIQUE,                      -- 이메일 (로그인용)
    joinDate   DATE            DEFAULT SYSDATE               -- 가입일   ###
);

INSERT INTO users (id, password, nickname, email, mobile)
VALUES (1, '1234', '테스트유저', 'test@example.com', '010-1234-5678');

select * from COMMUNITY_TB;

CREATE TABLE BUG (
    APPUSERID   NUMBER        PRIMARY KEY,           
    PASSWORD    VARCHAR2(100)    NOT NULL,              
    NICKNAME    VARCHAR2(50)       UNIQUE,               
    EMAIL       VARCHAR2(100)      UNIQUE,                  
    MOBILE      VARCHAR2(20)       UNIQUE,
    BFILE       VARCHAR2(225),
    JOINDATE    DATE         DEFAULT SYSDATE              
);

ALTER TABLE BUG
ADD badmin number;

desc bug;
commit;

dorp c

ALTER TABLE users ADD CONSTRAINT pk_users PRIMARY KEY (APPUSERID);


SELECT constraint_name
FROM user_constraints
WHERE table_name = 'USERS' AND constraint_type = 'P';

SELECT cols.column_name
FROM all_constraints cons
JOIN all_cons_columns cols
  ON cons.constraint_name = cols.constraint_name
WHERE cons.table_name = 'USERS'
  AND cons.constraint_type = 'P';
  
  CREATE TABLE RECOMMEND_TB (
    recId NUMBER(8) PRIMARY KEY,        -- 추천 고유번호  
    id   NUMBER NOT NULL,       -- 사용자 ID
    foodId NUMBER(6) ,          -- 추천된 음식, 아직 확정되지않아 NULL가능으로 만듦.
    type VARCHAR2(30) NOT NULL,         -- 추천 유형 (선호식단, AI, 재료기반)
    feedback VARCHAR2(200),             -- AI 피드백 (예: 단백질 부족)   
    createdAt DATE DEFAULT SYSDATE,      -- 추천 일시  
    FOREIGN KEY (id) REFERENCES users(APPUSERID),
    FOREIGN KEY (foodId) REFERENCES FOOD_TB(foodId)        
);


CREATE TABLE userinfo (
    no NUMBER PRIMARY KEY,
    email VARCHAR2(100) NOT NULL,
    age NUMBER,
);

desc userinfo;
commit;

ALTER TABLE userinfo ADD created_at DATE DEFAULT SYSDATE;
ALTER TABLE userinfo DROP COLUMN created_at;


select sysdate from dual;

INSERT INTO userinfo (no, email, age) VALUES (1, '1@1', 34);

SELECT no, email, age FROM userinfo;

UPDATE userinfo SET email = 'updated@example.com', age = 30 WHERE no = 1;

DELETE FROM userinfo WHERE no = 1;

CREATE SEQUENCE userinfo_seq START WITH 1 INCREMENT BY 1;

INSERT INTO userinfo VALUES (userinfo_seq.NEXTVAL, '1@3', 36);


CREATE TABLE milk (
    mno NUMBER PRIMARY KEY,
    mname VARCHAR2(100) NOT NULL,
    mnum NUMBER  NOT NULL,
    mtotal NUMBER
);
CREATE SEQUENCE userinfo_seq START WITH 1 INCREMENT BY 1;
create sequence milk_seq start with 1 increment by 1;
insert into milk values(milk_seq.nextval, 'white', 1, 1000);
select * from milk order by mno desc:
select * from milk where mno=1;
update milk set mname='banana', mnum=2, mtotal=2000 where mno=1;
delete from milk where mno=1;

desc milk;

commit;


    SQL> desc sboard1;
      Name                                      Null?    Type
      ----------------------------------------- -------- ----------------------------
      ID                                        NOT NULL NUMBER
      APP_USER_ID                               NOT NULL NUMBER
      BTITLE                                    NOT NULL VARCHAR2(1000)
      BCONTENT                                  NOT NULL CLOB
      BPASS                                     NOT NULL VARCHAR2(255)
      BFILE                                              VARCHAR2(255)
      BHIT                                               NUMBER(10)
      BIP                                       NOT NULL VARCHAR2(255)
      CREATE_AT                                          TIMESTAMP(6)
      


CREATE TABLE sboard2 (
  ID NUMBER PRIMARY KEY,
  APP_USER_ID NUMBER NOT NULL,
  BTITLE VARCHAR2(1000) NOT NULL,
  BCONTENT CLOB NOT NULL,
  BPASS VARCHAR2(255) NOT NULL,
  BFILE VARCHAR2(255),
  BHIT NUMBER DEFAULT 0,
  BIP VARCHAR2(255) NOT NULL,
  CREATED_AT DATE DEFAULT SYSDATE
);

desc sboard2;

CREATE SEQUENCE sboard2_seq START WITH 1 INCREMENT BY 1;
commit;

INSERT INTO sboard2 (
    ID, APP_USER_ID, BTITLE, BCONTENT, BPASS, BFILE, BHIT, BIP, CREATED_AT
) VALUES (
    sboard2_seq.NEXTVAL, 1,
    '첫 번째 테스트 게시글',
    '첫 번째 게시글 내용입니다.',
    '1111',
    'test1.txt',
    0,
    '127.0.0.1',
    SYSDATE
);
INSERT INTO sboard1 (
  ID, APP_USER_ID, BTITLE, BCONTENT, BPASS, BFILE, BHIT, BIP, CREATE_AT
) VALUES (
  sboard1_seq.nextval, 21, 'title', 'content', '1111', NULL, 0, '127.293.2.1', SYSDATE
);

select * from sboard1 order by mno desc:
select * from sboard1 where mno=1;
update sboard1 set mname='banana', mnum=2, mtotal=2000 where mno=1;
delete from sboard1 where mno=1;


commit;

update sboard1
set BTITLE='new1', BCONTENT='content1'
where id=4 and BPASS='1111';s

DROP SEQUENCE sboard_seq;

desc appuser;

desc sboard1;

ALTER TABLE users MODIFY BFILE VARCHAR2(255);
ALTER TABLE users ADD FILE VARCHAR2(255);

commit;
DESC sboard1;
ALTER TABLE sboard1 
MODIFY CREATED_AT TIMESTAMP DEFAULT SYSTIMESTAMP;



ALTER TABLE authorities RENAME COLUMN USERID TO email;
desc authorities;

ADD ufile VARCHAR2(255) DEFAULT 'member.png';


select * from emp where ename like '%S%';

select * from sboard1 where APP_USER_ID like '1';

select * from appuser where EMAIL like '1@1';

SELECT empno, ename, job FROM emp;

select empno, ename, job, mgr, hiredate, sal, comm, deptno
from emp
where ename='SMITH';

commit;
select * from sboard1;

desc authorities;

create table authorities (
userid varchar2(100) not null,
auth varchar2(100) not null
)

desc sboard1;

insert into authorities ( email , auth) values ('1@1', 'member');
insert into authorities ( email , auth) values ('1@1', 'admin');

ALTER TABLE sboard1 modify bfile default 'no.jpg';

commit;

select * from ( select row_number() over(order by created_at desc) as rnum, id, app_user_id, btitle, bcontent, bpass, bfile, bip, bhit, create_at from sboard1 ) where A.rnum between 1 and 10;
select *
from (
    select row_number() over(order by create_at desc) as rnum,
           id, app_user_id, btitle, bcontent, bpass, bfile, bip, bhit, create_at
    from sboard1
) A
where A.rnum between 1 and 10;

select count(*) from sboard1;

commit;

ALTER TABLE sboard1 RENAME COLUMN CREATE_AT TO CREATED_AT;

desc users;

ALTER TABLE users ADD (bfile VARCHAR2(255));


INSERT INTO users (APPUSERID, password, nickname, email, mobile, BFILE)
VALUES (100, '1234', '테스트유저', '100@100', '010-1234-5678', '');

select * from users;
delete from users;
commit;
  
SELECT uc.constraint_name,
       ucc.column_name
FROM   user_constraints uc
JOIN   user_cons_columns ucc
       ON uc.constraint_name = ucc.constraint_name
WHERE  uc.table_name = 'USERS'
AND    uc.constraint_type = 'U';

SELECT * FROM users WHERE email = '1@1';

SELECT * FROM authorities WHERE email='1@1';

DELETE FROM authorities 
WHERE email = '1@1' 
AND auth = 'ROLE_MEMBER'
AND ROWNUM = 1;

commit;

ALTER TABLE users
DROP COLUMN bfile;

ALTER TABLE bug
DROP COLUMN badmin;

CREATE TABLE BUG3 (
    APPUSERID   NUMBER          PRIMARY KEY,         
    PASSWORD    VARCHAR2(100)   NOT NULL,           
    NICKNAME    VARCHAR2(50)    UNIQUE,              
    EMAIL       VARCHAR2(100)   UNIQUE,              
    MOBILE      VARCHAR2(20)    UNIQUE,              
    BFILE       VARCHAR2(225),                      
    JOINDATE    DATE DEFAULT SYSDATE                
);

select * from authorities;
select * from bug;
insert into authorities values ('3@3', 'ROLE_ADMIN');
commit;

DELETE FROM authorities
WHERE email = '3@3';


delete * from 
CREATE TABLE CATEGORY (
   CATEGORY      NUMBER PRIMARY KEY,
   CATEGORY_NAME VARCHAR2(100)
);


-- RECIPES 테이블
CREATE TABLE recipes (
    RECIPE_ID    NUMBER PRIMARY KEY,
    APPUSERID    NUMBER NOT NULL,
    TITLE        VARCHAR2(255) NOT NULL,
    CATEGORY     NUMBER, 
    IMAGE        VARCHAR2(255) DEFAULT 'user.png',
    COOK_TIME    NUMBER DEFAULT 0,
    DIFFICULTY   VARCHAR2(50),
    SERVINGS     NUMBER DEFAULT 1,
    DESCRIPTION  VARCHAR2(4000),
    INSTRUCTIONS VARCHAR2(4000),
    CREATED_AT   DATE DEFAULT SYSDATE,
    UPDATED_AT   DATE,
    VIEWS        NUMBER DEFAULT 0,

    FOREIGN KEY (APPUSERID) REFERENCES BUG(APPUSERID),
    FOREIGN KEY (CATEGORY)  REFERENCES CATEGORY(CATEGORY)
);


-- 테이블 최대값 + 1부터 시작하도록 재생성
CREATE SEQUENCE recipes_seq
START WITH 10
INCREMENT BY 1
NOCACHE;


-- 테이블 최대값 + 1부터 시작하도록 재생성
CREATE SEQUENCE recipes_ingre_map_seq
START WITH 100
INCREMENT BY 1
NOCACHE;

-- 이미지 테이블 (CASCADE 적용)
CREATE TABLE recipes_img (
    RECIPE_ID NUMBER,
    RURL      VARCHAR2(250),
    FOREIGN KEY (RECIPE_ID) REFERENCES recipes(RECIPE_ID) ON DELETE CASCADE
);

-- 재료 매핑 테이블 (CASCADE 적용)
CREATE TABLE recipes_ingre_map (
    RECIPE_ID    NUMBER,
    INGRE_MAP_ID NUMBER PRIMARY KEY,
    FOREIGN KEY (RECIPE_ID) REFERENCES recipes(RECIPE_ID) ON DELETE CASCADE
);

CREATE SEQUENCE recipes_ingre_map_seq
START WITH 1
INCREMENT BY 1
NOCACHE;

-- 레시피 PK용 시퀀스
CREATE SEQUENCE recipes_seq
START WITH 1
INCREMENT BY 1
NOCACHE;



-- 재료 상세 테이블 (CASCADE 적용)
CREATE TABLE recipes_ingre (
    INGRE_MAP_ID NUMBER,
    INGRE_NAME   VARCHAR2(100),
    INGRE_NUM    VARCHAR2(50),
    FOREIGN KEY (INGRE_MAP_ID) REFERENCES recipes_ingre_map(INGRE_MAP_ID) ON DELETE CASCADE
);


create table material (
    materialid          NUMBER(6)        PRIMARY KEY,           -- 재료 고유번호 (PK)
    title                  VARCHAR2(200)    NOT NULL,           -- 재료명
    imageurl            VARCHAR2(300)   default  'defult.png',  -- 이미지 경로 또는 URL
    season               VARCHAR2(100),                         -- 제철 정보
    temperature      VARCHAR2(50),                              -- 보관 온도
    calories100g      NUMBER(6),                                -- 100g당 열량
    efficacy             VARCHAR2(1000),
    buyguide            VARCHAR2(1000),                         -- 구입요령
    trimguide           VARCHAR2(1000),                         -- 손질법
    storeguide          VARCHAR2(1000)                         -- 보관법
);

select * from appUser;

desc appuser;

SELECT *
FROM USER_SEQUENCES
WHERE SEQUENCE_NAME = 'APPUSER_SEQ';

-- 1. MOBILE 컬럼 추가
ALTER TABLE APPUSER
ADD MOBILE VARCHAR2(20);

-- 2. NICKNAME 컬럼 추가
ALTER TABLE APPUSER
ADD NICKNAME VARCHAR2(50);

commit;

delete authorities;
commit;
