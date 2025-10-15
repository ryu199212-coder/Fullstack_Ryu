-- 조건에 맞는 데이터 조회
-- #1. where
-- #2. 비교 연산자 : (같다) =, (다르다) !=, <>, ^=
-- #3. 논리 연산자 : AND / BETWEEN          AND(범위지정), OR / IN, NOT
-- #4. like       : 패턴검색
-- #5. null 처리  : null 여부확인
-- #6. 집합 연산자 : union(중복제거 합집합), union all(중복 합집합), minus(차집합), intersect(교집합)



-- #1. 전체 데이터 조회(emp 테이블)
select * from emp;

-- #2. where 조건조회
select * from emp where empno=7839;  -- 같다 =
select * from emp where empno!=7839; -- 다르다 !=
select * from emp where empno<>7839; -- 다르다 <>
select * from emp where empno^=7839; -- 다르다 ^=

select * from emp where ename='KING'

select * from emp where empno=7839 and ename='KING' -- and 두가지 다 조건이 맞아야함
select * from emp where empno=7839 and ename='SCOTT'
select * from emp where empno=7839 or  ename='SCOTT' -- or 두가지중 한 조건만 맞아도됨

select * from emp where sal*12 = 36000;
select * from emp where sal >= 3000;

select * from emp where ename >= 'S';
select * from emp where ename <= 'SOR';

select * from emp where deptno >= 20 and deptno <= 30; -- >=. <=
select * from emp where deptno between 20 and 30; -- 이상과 이하

select * from emp where deptno = 10 or deptno = 30;
select * from emp where deptno in(10,30);

select * from emp where ename = 'KING'; -- 이름을 알 때
select * from emp where ename LIKE 'A%'; -- A로 시작
select * from emp where ename LIKE '%A%'; -- A가 중간에라도 나오게
select * from emp where ename LIKE '%G'; -- G로 끝
select * from emp where ename LIKE '_I%'; -- 두번째글자가 I

select * from emp where comm = null; -- null은 자리는 있지만 빈칸(데이터x)
select * from emp where comm is null;
select * from emp where comm is not null;

-- 연습문제 (1~29)
select * from emp;

select * from emp where deptno = 30;

select * from emp where deptno = 30 and job = 'SALESMAN';

select * from emp where doptno = 30 or job = 'CLERK';

select * from emp where sal*12 = 36000;

-- sql 처리 순서(from → where → select)

-- select                      ③ * (모든컬럼값)
-- from   emp                  ① emp 테이블 읽어오기
-- where sal*12=36000          ② 각 행에 대해(한명 자료묶음) sal*12=36000 조건평가

select * from emp where sal  3000;

select * from emp where ename >= 'F';

select * from emp where ename <= 'FORZ';

select * from emp where sal != 3000;

select * from emp where sal <> 3000;

select * from emp where sal ^= 3000;

select * from emp where not sal = 3000;

select * from emp where job = 'MANAGER' or job = 'SALESMAN' or job = 'CLERK';

select * from emp where job in('MANAGER', 'SALESMAN', 'CLERK');

select * from emp where job != 'MANAGER' and job <> 'SALESMAN' and job ^= 'CLERK';

select * from emp where sal >= 2000 and sal <=3000;

select * from emp where sal between 2000 and 3000;

select * from emp where ename LIKE 'S%';

select * from emp where ename LIKE '_L%';

select * from emp where ename LIKE 'S%';

select * from emp where ename LIKE '%AN%';

select * from emp where ename not LIKE '%AN%';

select ename, sal, sal*12+comm as annsal, comm from emp;

select * from emp where comm = NULL;

select * from emp where comm is NULL;

select * from emp where mgr is not NULL;

-- 둘다 만족 없음 null은 값이 아님

-- 하나만 만족은 있음 null표기 가능

-- Ex1  emp 테이블에서 job 데이터 중복없이 조회 
select DISTINCT job from emp;
-- Ex2
--조회할 테이블은 EMP 테이블이며 모든 열을 출력하시오.
select empno as EMPLOYEE_NO, ename as EMPLOYEE_NAME, mgr as MANAGER, sal as SALARY, comm as COMMISSION, deptno as DEPARTMENT_NO 
from emp
order by empno desc, ename asc;
--별칭 (AS)
--EMPNO → EMPLOYEE_NO,
--ENAME → EMPLOYEE_NAME,
--MGR → MANAGER,
--SAL → SALARY,
--COMM → COMMISSION,
--DEPTNO → DEPARTMENT_NO

--부서번호를 기준으로 내림차순으로 정렬하되,
--부서번호가 같다면 사원이름을 기준으로 오름차순 정렬하시오.
-- Ex3  
-- EMP테이블에서 대소비교연산자(<= , >= ) and 를 이용하여 sal 열이 2000이상 3000이하인인 행을 조회
select * from emp where sal >= 2000 and sal <= 3000;
-- Ex4  
--  EMP테이블에서 BETWEEN AND 를 이용하여 sal 열이 2000이상 3000이하인인 행을 조회
select * from emp where sal between 2000 and 3000;
-- Ex5
-- EMP테이블에서 OR 를 이용하여 
-- JOB 열이 'MANAGER' ,'SALESMAN' , 'CLERK' 중 하나라도 포함되는 행을 조회
SELECT * FROM emp where job = 'MANAGER' or job = 'SALESMAN' or job = 'CLERK';
-- Ex6
-- EMP테이블에서 IN 를 이용하여 
-- JOB 열이 'MANAGER' ,'SALESMAN' , 'CLERK' 중 하나라도 포함되는 행을 조회
-- from → where  → select  
select *from emp where job in('MANAGER' ,'SALESMAN' , 'CLERK');
-- Ex7. EMP테이블에서 ENAME이 S로 시작하는 행
select *from emp where ename like 'S%';
-- Ex8. EMP테이블에서 ENAME의 두번째 글자가 L인 행을 조회
select *from emp where ename like '_L%';
-- Ex9. EMP테이블에서 ENAME에 AM이 포함되어 있는 행을 조회
select *from emp where ename like '%AM%';

-- #6. 집합 연산자 : union(중복제거 합집합), union all(중복 합집합), minus(차집합), intersect(교집합)
-------------------------------
select *
from emp
where deptno = 10
-------------------------------
union
-------------------------------
select *
from emp
where deptno = 10;
-------------------------------

-- #8. union all(중복 합집합)
-------------------------------
select *
from emp
where deptno = 10
-------------------------------
union all
-------------------------------
select *
from emp
where deptno = 10;
-------------------------------

-- #9. minus(차집합)
-------------------------------
select *
from emp
-------------------------------
minus
-------------------------------
select *
from emp
where deptno = 20;
-------------------------------

-- #10. intersect(교집합)
-------------------------------
select *
from emp
-------------------------------
intersect
-------------------------------
select *
from emp
where deptno = 20;
-------------------------------

-- dustmqanswp (30~37)
-- Q30 UNION을 이용하여 DEPTNO가 10이거나, 20인 데이터의 EMPNO, ENAME, SAL, DEPTNO 열을 조회하시오.
select empno, ename, sal, deptno from emp where deptno = 10 
union
select empno, ename, sal, deptno from emp where deptno = 20; 

-- Q31 에러가 나는 이유는?
SELECT EMPNO, ENAME, SAL, DEPTNO
FROM EMP
WHERE DEPTNO = 10
UNION
SELECT EMPNO, ENAME, SAL
FROM EMP
WHERE DEPTNO = 20;
-- A 열의 개수가 다름

-- Q32 에러가 나는 이유는?
SELECT EMPNO, ENAME, SAL, DEPTNO
FROM EMP
WHERE DEPTNO = 10
UNION
SELECT ENAME, EMPNO, DEPTNO, SAL
FROM EMP
WHERE DEPTNO = 20;
-- A 자료형이 다름

-- Q33 동작하는 이유는?
SELECT EMPNO, ENAME, SAL, DEPTNO
FROM EMP
WHERE DEPTNO = 10
UNION
SELECT SAL, JOB, DEPTNO, SAL
FROM EMP
WHERE DEPTNO = 20;
-- A 개수와 자료형이 같음

-- Q34 UNION과 UNION ALL의 차이는?
SELECT EMPNO, ENAME, SAL, DEPTNO
FROM EMP
WHERE DEPTNO = 10
UNION 
SELECT EMPNO, ENAME, SAL, DEPTNO
FROM EMP
WHERE DEPTNO = 10;
-- A union(중복 제외 합집합)

-- Q35 UNION과 UNION ALL의 차이는?
SELECT EMPNO, ENAME, SAL, DEPTNO
FROM EMP
WHERE DEPTNO = 10
UNION all
SELECT EMPNO, ENAME, SAL, DEPTNO
FROM EMP
WHERE DEPTNO = 10;
-- A union all(중복 포함 합집합)

-- Q36 MINUS의 의미는?
SELECT EMPNO, ENAME, SAL, DEPTNO
FROM EMP
MINUS
SELECT EMPNO, ENAME, SAL, DEPTNO
FROM EMP
WHERE DEPTNO = 10;
-- A 차집합

-- Q37 INTERSECT 의미는? 
SELECT EMPNO, ENAME, SAL, DEPTNO
FROM EMP
INTERSECT
SELECT EMPNO, ENAME, SAL, DEPTNO
FROM EMP
WHERE DEPTNO = 10;
-- A 교집합


-- 사고확장
-- EX001 EMP테이블에서 ENAME이 S로 끝나는 사원데이터를 모두 조회하시오
select * from emp where ename like '%S';

-- EX002 EMP테이블에서 DEPTNO가 30인 사원 중 직책이(JOB)이 SALESMAN 인 사원의 EMPNO, ENAME, JOB, SAL, DEPTNO를 조회하시오
select EMPNO, ENAME, JOB, SAL, DEPTNO 
from emp
where deptno = 30 and job = 'SALESMAN';

-- EX003 in사용 / union사용
-- EMP테이블에서 IN 연산자를 이용하여 DEPTNO 가 20 또는 30인 사원의 SAL이 2000 초과인 사원을 조회하시오
select *
from emp
where deptno in(20, 30)
and sal > 2000;

-- EMP테이블에서 IN 연산자를 이용하여 DEPTNO 가 20 또는 30인 사원의 SAL이 2000 초과인 사원을 조회하시오
select empno, ename, job, deptno
from emp
where deptno = 20
and sal > 2000
union
select empno, ename, job, deptno
from emp
where deptno = 30
and sal > 2000;

-- EX004 EMP테이블에서 NOT BETWEEN AND 연산자를 사용하지 않고 SAL이 2000이상 3000이하의 값을 가진 데이터만 조회하시오.
select * from emp
where sal < 2000 or sal > 3000;

-- EX005 EMP테이블에서 ENAME에 E가 포함되고 DEPTNO가 30인 사원의 급여가 1000~2000사이가 아닌 사원의 ENAME, EMPNO, SAL, DEPTNO 를 조회하시오.
select ENAME, EMPNO, SAL, DEPTNO
from emp
where ename like '%E%'
and deptno = 30
and sal not between 1000 and 2000;

-- EX006 EMP테이블에서 COMM 이 없고 , MGR은 존재하면 JOB 이 'MANAGER', 'CLERK' 인 사원 중 사원의 이름2번째 글자기 L 이 아닌 사원의 정보를 조회하시오.
select *
from emp
where comm is null
and mgr is not null
and job in('MANAGER', 'CLERK')
and ename not like '_L%';

