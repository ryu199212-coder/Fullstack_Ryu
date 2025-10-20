-- #1. 테이블 구조 확인
desc emp;
decs dept;

show user; -- 현재 줄 선택하고 ctrl + enter
select table_name from user_tables; -- 사용할 수 있는 테이블 확인

-- #2. 전체테이블 조회
select * from emp;

-- #3. 열 조회
select empno, ename from emp;
select ename, job   from emp;

-- #4. 중복 제거(distinct)
select distinct job from emp;
select all      job from emp;

-- #5. 연산 및 별칭
select ename "유저", sal "월급", sal+sal+sal as "3달치 월급" from emp;

-- #6. 정렬
select ename, sal 
from emp 
order by sal asc; -- asc는 오름차순

select ename, sal 
from emp 
order by sal desc; -- desc는 내림차순

-- step3
-- ## 연습문제
-- https://sally03915.github.io/stackventure_250825/004_oracle/oracle002_select_basic#45
-- Q1. emp 테이블 구성
desc emp;

-- Q2. dept 테이블 구성
desc dept;

-- Q3. salgrade 테이블 구성
desc salgrade;

-- Q4. emp 테이블 전체열 조회
select * from emp;

-- Q5. emp 테이블에서 empno, ename, deptno 열 조회
select empno, ename, deptno from emp;

-- Q6. emp 테이블에서 deptno 열중복 제거
select distinct deptno from emp;

-- Q7. emp 테이블에서 job, deptno 열중복 제거
select distinct job, deptno from emp;

-- Q8. emp 테이블에서 job, deptno 전체표시
select all job, deptno from emp;
select     job, deptno from emp;

-- Q9. emp 테이블에 연산식을 이용해서 연간 총수입
select ename, sal, sal*12+comm, comm from emp;

-- Q10. emp 테이블에서 열+열 더하기 연산식
select ename, sal, sal+sal+sal+sal+sal+sal+sal+sal+sal+sal+sal+comm, comm from emp

-- Q11. emp 테이블에서 별명짓기
select ename, sal, sal*12+comm as annsal, comm from emp;

-- Q12. emp 테이블에서 모든 열을 급여기준 오름차순
select * from emp;
order by sal asc; -- asc 생략 가능

-- Q13. emp 테이블에서 모든 열을 급여기준 내림차순
select * from emp;
order by sal desc;

-- Q14. emp 테이블에서 부서번호(오름차순) 급여(내림차순)
select * from emp order by empno asc; sal decs;

-- ※ 테이블 emp
-- 사용가능한 테이블 확인
select table_name from user_tables;

-- emp테이블 삭제 (복구X)
drop table emp;
select table_name from user_tables;

-- emp 테이블 만들기 정의어(DDL) - create, alter, drop
CREATE TABLE emp (
  empno NUMBER(4),
  ename VARCHAR2(10),
  job VARCHAR2(9),
  mgr NUMBER(4),
  hiredate DATE,
  sal NUMBER(7,2),
  comm NUMBER(7,2),
  deptno NUMBER(2)
);
desc emp;

-- emp 테이블 값 넣기
INSERT INTO emp VALUES (7839, 'KING', 'PRESIDENT', NULL, TO_DATE('1981-11-17','YYYY-MM-DD'), 5000, NULL, 10);
INSERT INTO emp VALUES (7698, 'BLAKE', 'MANAGER', 7839, TO_DATE('1981-05-01','YYYY-MM-DD'), 2850, NULL, 30);
INSERT INTO emp VALUES (7782, 'CLARK', 'MANAGER', 7839, TO_DATE('1981-06-09','YYYY-MM-DD'), 2450, NULL, 10);
INSERT INTO emp VALUES (7566, 'JONES', 'MANAGER', 7839, TO_DATE('1981-04-02','YYYY-MM-DD'), 2975, NULL, 20);
INSERT INTO emp VALUES (7902, 'FORD', 'ANALYST', 7566, TO_DATE('1981-12-03','YYYY-MM-DD'), 3000, NULL, 20);
INSERT INTO emp VALUES (7369, 'SMITH', 'CLERK', 7902, TO_DATE('1980-12-17','YYYY-MM-DD'), 800, NULL, 20);
INSERT INTO emp VALUES (7788, 'SCOTT', 'ANALYST', 7566, TO_DATE('1987-07-13','YYYY-MM-DD'), 3000, NULL, 20);
INSERT INTO emp VALUES (7876, 'ADAMS', 'CLERK', 7788, TO_DATE('1987-07-13','YYYY-MM-DD'), 1100, NULL, 20);
INSERT INTO emp VALUES (7934, 'MILLER', 'CLERK', 7782, TO_DATE('1982-01-23','YYYY-MM-DD'), 1300, NULL, 10);
INSERT INTO emp VALUES (7654, 'MARTIN', 'SALESMAN', 7698, TO_DATE('1981-09-28','YYYY-MM-DD'), 1250, 1400, 30);
INSERT INTO emp VALUES (7499, 'ALLEN', 'SALESMAN', 7698, TO_DATE('1981-02-20','YYYY-MM-DD'), 1600, 300, 30);
INSERT INTO emp VALUES (7844, 'TURNER', 'SALESMAN', 7698, TO_DATE('1981-09-08','YYYY-MM-DD'), 1500, 0, 30);
INSERT INTO emp VALUES (7900, 'JAMES', 'CLERK', 7698, TO_DATE('1981-12-03','YYYY-MM-DD'), 950, NULL, 30);
INSERT INTO emp VALUES (7521, 'WARD', 'SALESMAN', 7698, TO_DATE('1981-02-22','YYYY-MM-DD'), 1250, 500, 30);

commit;

-- ## 연습문제
-- https://sally03915.github.io/stackventure_250825/004_oracle/oracle002_select_basic#45
select distinct job from emp;

select  empno as employee_no, 
        ename as employee_name, 
        job,
        mgr as manager, 
        sal as salary,
        comm as commission,
        deptno as erpartment_no
from emp
order by deptno desc, ename;


