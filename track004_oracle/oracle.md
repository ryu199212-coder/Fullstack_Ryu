#1. 저장단위
변수 < 배열 < 클래스 < 콜렉션 프레임워크 - file(DB)
※ DB(mysql, oracle, mssql)

> java : jdbc → dbcp → orm(mybatis, jpa)

#2. RDB(Relational Data Base) ★
- 관계형 데이터 베이스
- 테이블 관계
1. Entity - 테이블 - 관리대상(고객, 주문, 상품)
2. 속성(Attribute) - 컬럼 - 대상의 특징(주민번호, 이름, 나이)
3. 관계(Relationship) - 대상간의 연결 - 고객은 주문을 한다

#3. 데이터베이스 언어 ★
1. 정의어(DDL) - create, alter, drop ... cad로 줄여서 암기
2. 조작어(DML) - Create(INSERT 삽입), Read(SELECT 조회), update(수정), delete(지우기),  ... crud 
3. 제어어(DCL) - grant, revoke 

<<사원>>
SQL > decs emp;
 Name                                      Null?    Type
 ----------------------------------------- -------- ----------------------------
 EMPNO                                     NOT NULL NUMBER(4)
 ENAME                                              VARCHAR2(10)
 JOB                                                VARCHAR2(9)
 MGR                                                NUMBER(4)
 HIREDATE                                           DATE
 SAL                                                NUMBER(7,2)
 COMM                                               NUMBER(7,2)
 DEPTNO                                             NUMBER(2)

 <<부서>>
 SQL > desc dept;
 Name                                      Null?    Type
 ----------------------------------------- -------- ----------------------------
 DEPTNO                                    NOT NULL NUMBER(2)
 DNAME                                              VARCHAR2(14)
 LOC                                                VARCHAR2(13)

 > 부서는 많은 사원을 가질 수 있다.
 관리대상(table) : dept emp
 속성(attribute) : deptni empno, deptno
 연결(Relationship) : deptno

 #4. WHERE
-- 조건에 맞는 데이터 조회
-- #1. WHERE
-- #2. 비교연산자 : (같다) =, (다르다) !=, <>, ^=
-- #3. 논리연산자 : AND, OR, NOT / BETWEEN   AND (범위 지정), OR IN
-- #4. LIKE      : 패턴검색
-- #5. NULL처리   : NULL 여부확인
-- #6. 집합연산자  : UNION (중복 제거하고 합집합), UNION ALL (중복 포함하고 합집합), INTERSECT (교집합)