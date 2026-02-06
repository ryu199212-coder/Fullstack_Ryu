--007_ddl_dml_tx_user.sql

-- ★ 데이터 베이스언어 
-- 1. dml 조작어(데이터)    insert(삽입)    select(조회)       update(수정)    delete(지우기)  → CRUD
-- 2. ddl 정의어(구조)      create(만들기)   alter(테이블수정)   drop(삭제: 복구안됨.)   →  CAD
-- 3. dcl 제어어(접근권한)  grant(주세요) ,  revoke(권한빼기)
-- 4. tcl 제어어(트랜잭션)  commit(반영)  ,  rollback(되돌리기),  savepoint(지점)


---------------------------------------------------------------------------------------------------
-- 1. dml 조작어(데이터)    ■insert(삽입)    select(조회)       update(수정)    delete(지우기)  → CRUD

-- #1. 테이블 복사해오기
create table dept_temp  as  select * from dept;
desc dept_temp;
select * from dept_temp;

-- #2. insert
--  insert into  테이블명 (필드1, 필드2,,,)  values (값1, 값2,,,,)

insert  into dept_temp (  deptno, dname, loc  )  values ( 50 , 'DATABASE' , 'SEOUL'   );
insert  into dept_temp                           valueS ( 60 , 'NETWORK'  , 'BUSAN'   );
insert  into dept_temp                           values ( 70 , 'WEB'      , NULL      );
insert  into dept_temp ( deptno, loc, dname  )   values ( 80 ,  ''        , 'FRONT'  );
insert  into dept_temp ( loc, dname ,deptno  )   values (  'INCHEON'      , 'BACK' , 90  );
insert  into dept_temp ( deptno , dname      )   values ( 99 , 'MOBILE' );

commit; -- 반영

select * from dept_temp;


-- Q1.  EMP테이블을 복사해 EMP_TEMP 테이블을 만드시오. ( 구조확인, 전체데이터 확인)
create table emp_temp  as  select * from emp;
desc emp_temp;
select * from emp_temp;

-- Q2.  모든필드 명시하는 방법이용해서   다음의 값 넣기 - 9999, '홍길동', 'PRESIDENT', NULL, '2001/01/01', 5000, 1000, 10
insert into emp_temp  (EMPNO, ENAME ,   JOB        , MGR ,  HIREDATE , SAL , COMM ,  DEPTNO )    
 values               (9999 , '홍길동', 'PRESIDENT', NULL, '2001/01/01', 5000, 1000, 10);

-- Q3.  필드 명시 안하는 방법이용해서    다음의 값 넣기    - 1111, '성춘향', 'MANAGER', 9999, '2001-01-05', 4000, NULL, 20
insert into emp_temp   
 values               (1111, '성춘향', 'MANAGER', 9999, '2001-01-05', 4000, NULL, 20);

commit;
select * from emp_temp;
 

---------------------------------------------------------------------------------------------------
-- 1. dml 조작어(데이터)    insert(삽입)    select(조회)       ■update(수정)    ■delete(지우기)  → CRUD

-- #1.  
select * from dept_temp;

-- #1. update
----------------------------------------------
update  테이블명    
set     바꿀필드1 = 바꿀값1 ,  바꿀필드2 = 바꿀값2 
where   조건;
----------------------------------------------


-- 전체데이터 업데이트 
update  dept_temp  set  LOC = 'INCHEON' ;                   -- 전체데이터 업데이트 
update  dept_temp  set  LOC = 'NEW YORK'  WHERE  deptno=10; -- 해당하는 조건
update  dept_temp  set  Loc = 'DALLAS'    WHERE  deptno=20  and  dname ='RESEARCH';

select * from dept_temp;


-- #2. delete
------------------------------
delete from 테이블명 where 조건
------------------------------
delete from dept_temp; -- 전체데이터 삭제
delete from dept_temp where deptno=10;
delete from dept_temp where deptno>=30;


-- #3.
commit;

select * from dept_temp;
select * from dept;

insert into dept_temp (deptno, dname, loc) 
                select deptno, dname, loc from dept;
                
-- Q1. dept_temp 모든데이터 삭제
delete from dept_temp;
-- Q2. insert 이용해서 데이터 삽입, 반영
insert into dept_temp (deptno, dname, loc) select deptno, dname, loc from dept;
commit;
-- Q3. dept_temp 테이블에서 40번 부서의 이름을 DATABASE, 지역을 SEOUL로 수정 
update  dept_temp  set  LOC = 'SEOUL', dname = 'DATABASE' where deptno = 40;    
commit;
select * from dept_temp;
-- Q4. 위에 데이터 되돌리기
rollback; -- 되돌리기

-- #0. 준비 -  다음 문제들을 풀으시오
-- Q1. DEPT 테이블을 복사해 DEPT_TCL테이블을 작성하시오.
create table dept_tcl  as  select * from dept;
-- Q2. DEPT_TCL 테이블에 다음과 같이 데이터를 입력, 수정, 삭제 하시오.
--    2-1.  데이터 삽입   50, 'DATABASE', 'SEOUL'
insert into dept_tcl  values (50, 'DATABASE', 'SEOUL');
--    2-2.  부서번호가 40인  LOC를 INCHEON 으로 수정
update dept_tcl set loc = 'INCHEON' where deptno = 40;
--    2-3.  DNAME이 RESEARCH 인데이터 삭제
delete from dept_tcl where dname = 'RESEARCH';
--    2-4.  전체데이터 확인
select * from dept_tcl;

-- #1. 데이터 반영(insert, update, incheon)
commit;

-- #2. 데이터 되돌리기(commit 이후에는 rollback 효과 x)
rollback;

insert into dept_tcl dept_tcl values (20, 'AI', 'INCHEON');
rollback;
select * from dept_tcl;

-- #3. savepoint
insert into dept_tcl values (60, 'AI', 'INCHEON'); -- insert
savepoint sp_insert; -- 기준점 설정1

update dept_tcl set deptno=20 where deptno=60; -- update 설정
savepoint sp_update; -- 기준점 설정2

rollback to sp_insert;

select * from dept_tcl;

--------------------------------------------------------------------------------
--------------------------------------------------------------------------------
-- 2. ddl 정의어(구조)      create(만들기)   alter(테이블수정)   drop(삭제: 복구안됨.)   →  CAD
--------------------------------------------------------------------------------
--------------------------------------------------------------------------------
-- #1. create
------------------------
create table 테이블명(
    필드명1 자료형 옵션,
    필드명2 자료형 옵션,
    필드명3 자료형 옵션
    )
------------------------
필드명 - 영문소문자
자료형 - 숫자 NUMBER, 문자열 VARCHAR2(개수), 날짜 DATE
옵션 - NOT NULL 필수, 기본값 PRIMARY KEY

create table dept_ddl(
  deptno NUMBER(2) PRIMARY KEY,
  dname  VARCHAR2(50) NOT NULL,
  loc    VARCHAR2(50)
); 

desc dept_ddl;

--------------------------------------
alter table 테이블명 add    필드명 자료형
                    drop   column 필드명
                    modify 필드명 자료형
--------------------------------------
-- 컬럼추가
alter table dept_ddl add admin VARCHAR2(50);
-- 컬럼수정
alter table dept_ddl modify admin VARCHAR2(100) not null;
-- 컬럼삭제
alter table dept_ddl drop column admin;
rollback;
desc dept_ddl;

✅ 문제 1:  다음과 같이 테이블을 작성하고 데이터를 삽입하세요
※AppUser 의 FK는 무시하세요.

#### 1. `MbtiType`
| 필드명 | 타입 | 설명 |
|--------|------|------|
| mbti_type_id | INT (PK) | MBTI 유형 ID |
| name | VARCHAR(10) | 유형 이름 (예: ENFP) |
| description | TEXT | 성향 설명 |

**예시 데이터**
```sql
(3, 'ENFP', '열정적이고 창의적인 성향')
(7, 'INTJ', '논리적이고 전략적인 성향')
```
create table MbtiType(
mbti_type_id number(5) PRIMARY KEY,
name VARCHAR(10) not null,
description VARCHAR2(1000));

insert into MbtiType values (3, 'ENFP', '열정적이고 창의적인 성향');
insert into MbtiType values (7, 'INTJ', '논리적이고 전략적인 성향');


#### 2. `AppUser`
| 필드명 | 타입 | 설명 |
|--------|------|------|
| app_user_id | INT (PK) | 사용자 고유 ID |
| email | VARCHAR(100) | 이메일 주소 |
| password | VARCHAR(255) | 암호화된 비밀번호 |
| mbti_type_id | INT (FK → MbtiType.mbti_type_id) | 연결된 MBTI 유형 |
| created_at | DATETIME | 가입일 |

create table AppUser(
app_user_id   number(5) PRIMARY KEY,
email         VARCHAR(100) not null,
password      VARCHAR(255) not null,
mbti_type_id  number(3), 
created_at    DATE
);

**예시 데이터**
```sql
(1, 'alice@example.com', 'hashed_pw_123', 3, 2, '2025-10-01 10:00:00')
(2, 'bob@example.com', 'hashed_pw_456', 7, 1, '2025-10-02 14:30:00')
```

✅ 문제 1: AppUser 테이블에 사용자 이름(name) 컬럼을 추가하세요.
컬럼 타입은 VARCHAR2(50)이며 NULL 허용
alter table AppUser add name VARCHAR2(50);

✅ 문제 2: AppUser 테이블의 email 컬럼에 NOT NULL 제약을 추가하세요.
alter table AppUser modify email VARCHAR2(100) not null;
 
✅ 문제 3: AppUser 테이블의 password 컬럼 길이를 255 → 100으로 줄이세요.
alter table AppUser modify password VARCHAR2(100) not null;
 
✅ 문제 4: MbtiType 테이블에 컬럼 category를 추가하세요.
타입은 VARCHAR2(20)이며 기본값은 '기본'
alter table MbtiType add category VARCHAR2(20) default '기본';

select column_name, data_default
from user_tab_columns
where table_name = 'MBTITYPE';

✅ 문제 5: MbtiType 테이블의 description 컬럼 이름을 details로 변경하세요.
alter table MbtiType rename column description to details로;
 
✅ 문제 6: AppUser 테이블의 name 컬럼을 삭제하세요.
alter table AppUser drop column name;
 
✅ 문제 7:  MbtiType 테이블 이름을 MbtiInfo로 변경하세요.
alter table MbtiType rename to MbtiInfo;

