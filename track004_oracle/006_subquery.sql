 -- 서브쿼리   select 안에 select
 
 -- #1. 단일행(2073) 서브쿼리  - EMP 평균급여보다 많이 받는 사원들
-- STEP1)
-- SELECT    ENAME, SAL  
-- FROM      EMP
-- WHERE     SAL >    2073(평균급여) 
-- ORDER BY  SAL DESC;

-- STEP2)
 SELECT    ENAME, SAL  
 FROM      EMP
 WHERE     SAL > (  SELECT  AVG(SAL) FROM EMP )
 ORDER BY  SAL DESC;
 
 -- #2.  다중행(IN)  EMP   DEPTNO가  10인 부서의 JOB인 사원들 ENAME, SAL 
 -- STEP1)
-- SELECT   ENAME, SAL   
-- FROM     EMP  
-- WHERE    JOB = (DEPTNO가  10인 부서의 JOB)
-- ORDER BY  SAL DESC ;

 SELECT   ENAME, SAL   
 FROM     EMP  
 WHERE    JOB IN ( SELECT JOB  FROM EMP WHERE DEPTNO=10 )
 ORDER BY  SAL DESC ; 
 
-------------------------------------------------------------------------------------------- 
 
-- #3. 다중행연산자
-- 1.  in          "이 값이 목록에 있나요?"    deptno in(10,20,30)
-- 2.  any, some   "하나라도 만족하면   ok"      ■ 최소값 기준비교 (선한개)
--      컬럼 > any(서브쿼리)  최소값보다 크면 true   
--      컬럼 < any(서브쿼리)  최대값보다 작으면 true   
--        컬럼 > any( 1,2,3  )       | (1)    |(2)   |(3)       암기) 컬럼 > any(1)    최소값보다크다
--        컬럼 < any( 1,2,3  )       | (1)    |(2)   |(3)

-- 3.  all         "모두 만족해야 ok"           ■ 최대값 기준비교 (모든선)
--      컬럼 > all(서브쿼리)  최대값보다 크면 true   
--      컬럼 < all(서브쿼리)  최소값보다 작으면 true   
--        컬럼 > all( 1,2,3  )       | (1)    |(2)   |(3)      
--        컬럼 < all( 1,2,3  )       | (1)    |(2)   |(3)
 
--  암기)  ■  컬럼 > any 1      컬럼 >all 3    
-- 4.  exists      "서브쿼리가 존재하면 ok"     

create  table  atest        as   select  1   num   from dual
                      union all  select  2         from dual
                      union all  select  3         from dual
                      union all  select  4         from dual
                      union all  select  5         from dual
                      union all  select  6         from dual;
                   
select * from atest;                   

-- 컬럼 > any 1   최소값보다 크다 , 최대값 작다  ,  컬럼> all 3   최대값보다 크다  최소값 작다
-- 애니1 , 올3
select num  from atest where  num < any(select num from atest  where num in(3,4,5)) order by num; -- 5보다 작다 1 2 3 4
select num  from atest where  num > any(select num from atest  where num in(3,4,5)) order by num; -- 3보다크다  4 5 6
select num  from atest where  num < all(select num from atest  where num in(3,4,5)) order by num; -- 3보다 작다 1 2
select num  from atest where  num > all(select num from atest  where num in(3,4,5)) order by num; -- 5보다 크다 6


--------------------------------------------------------------------------------------------
-- #4. 다중 열 서브쿼리
-- where     sal > any ,  sal in any
select  ename, deptno, job
from    emp
where   (deptno, job) in (  select  deptno, job  from emp  where sal>2000  );
 
 
-- #5. 인라인뷰  ( from )
select  empno, ename, d.deptno,dname
from    ( select * from emp where deptno=20 ) e, 
        ( select * from dept) d
where   e.deptno = d.deptno;
 
-- #6. WITH (단일쿼리)
with
  e6  as  ( select * from emp  where deptno=30)
, d6  as  ( select * from dept) 

select  empno, ename, d6.deptno,dname
from    e6 , d6
where   e6.deptno = d6.deptno;


with
  e6  as  ( select * from emp  where deptno=30)
select count(*) from e6;

-- #7. 스칼라서브쿼리
-- 데이터가 많은경우 성능저하
select    empno, ename, sal
          ,(  select grade  from salgrade  where  emp.sal  between  losal  and hisal   ) salgrade
          , deptno
          ,( select   loc  from dept  where emp.deptno = dept.deptno ) location
from      emp;


--------------------------------------------------------------------------------------------
 
 
 
 
 
-- https://sally03915.github.io/stackventure_250825/004_oracle/oracle007_select_subquery#14
-- # 실습Q1~Q7  (~10:20)
-- Q001 EMP 테이블에서 다음과 같이 출력하시오. 
--사원이름이 JONES 인 사원의 급여를 출력하시오.
--힌트)
--SELECT    SAL
--FROM      EMP 테이블
--WHERE     사원이름이 JONES  

SELECT   SAL
FROM     EMP
WHERE    ENAME= 'JONES';



-- Q002
--EMP 테이블에서 다음과 같이 출력하시오.
--급여가 2975보다 높은 사원정보를 출력하시오.
--힌트)
--SELECT    *
--FROM      EMP 테이블
--WHERE     급여가 2975보다 높은

SELECT   *
FROM     EMP
WHERE    SAL >  2975;


-- Q003
--EMP 테이블에서 다음과 같이 출력하시오.
--JONES의 급여보다 높은급여를 받는 사원정보를 출력하시오.
--힌트)
--SELECT    *
--FROM      EMP 테이블
--WHERE     SAL 높은급여 > (JONES의 급여 )

SELECT  *
FROM    EMP 
WHERE   SAL > (SELECT SAL  FROM  EMP  WHERE ENAME='JONES');


-- Q004
--EMP 테이블에서 다음과 같이 출력하시오.
--SCOTT보다 빨리 입사한 사원목록을 출력하시오.
--힌트)
--SELECT    *
--FROM      EMP 테이블
--WHERE     입사일 <빨리  (SCOTT보다  입사일 )

SELECT   *
FROM     EMP
WHERE    HIREDATE  < ( SELECT HIREDATE  FROM EMP  WHERE  ENAME = 'SCOTT');


-- Q005
--EMP 테이블에서 다음과 같이 출력하시오.
--20번부서에 속한 사원 중
--전체사원의 평균급여보다 높은 급여를받는 사원정보와 소속부서정보를 출력하시오.
--힌트)
--SELECT    EMPNO, ENAME, JOB, SAL, DEPTNO, DNAME, LOC
--FROM      EMP 테이블 , DEPT 테이블
--WHERE     EMP 테이블과 DEPT 테이블 연결 (JOIN)  
--AND       DEPTNO가 20번부서
--AND       높은 급여를받는   SAL >   (  평균급여  )

SELECT     EMPNO, ENAME, JOB, SAL, D.DEPTNO, DNAME, LOC
FROM       EMP E, DEPT D
WHERE      E.DEPTNO = D.DEPTNO
AND        E.DEPTNO = 20
AND        SAL > (SELECT AVG(SAL) FROM  EMP );

SELECT     EMPNO, ENAME, JOB, SAL, D.DEPTNO, DNAME, LOC
FROM       EMP E  JOIN DEPT D  ON(  E.DEPTNO = D.DEPTNO )
WHERE      E.DEPTNO = 20
AND        SAL > (SELECT AVG(SAL) FROM  EMP );

SELECT     EMPNO, ENAME, JOB, SAL,  DEPTNO, DNAME, LOC
FROM       EMP E  JOIN DEPT D  USING(DEPTNO)
WHERE      DEPTNO = 20    AND        SAL > (SELECT AVG(SAL) FROM  EMP );

SELECT     EMPNO, ENAME, JOB, SAL,  DEPTNO, DNAME, LOC
FROM       EMP E  NATURAL  JOIN DEPT D   
WHERE      DEPTNO = 20    AND    SAL > (SELECT AVG(SAL) FROM  EMP );

-- Q006
--EMP 테이블에서 다음과 같이 출력하시오.
--부서번호가 20이거나 30인 사원의 정보를 출력하시오.
--힌트)
--SELECT    *
--FROM      EMP 테이블
--WHERE     DEPTN가   20이거나 30인
SELECT     *
FROM       EMP
WHERE      DEPTNO IN(20,30);

-- Q007
--EMP 테이블에서 다음과 같이 출력하시오.
--각 부서별 최고급여와 동일한 급여를 받는 사원정보를 출력하시오.
--힌트)
--SELECT    *
--FROM      EMP 테이블
--WHERE    동일한 급여  IN  (각 부서별 최고급여)
SELECT   *
FROM     EMP
WHERE    SAL IN ( SELECT  MAX(SAL)  FROM EMP GROUP BY DEPTNO ) ;
  
----------------------------------------------------------------------------------------------
  
--  다중행  in ,  any(some) 하나라도, all모두 , exists 있으면 
--  ■ any 1 all 3
--   > any 1 (최소값 크다) , 최대값 작다   > all 3 (최대값 크다) 최소값 작다
-- https://sally03915.github.io/stackventure_250825/004_oracle/oracle007_select_subquery#29
-- Q8~17

--Q008
--EMP 테이블에서 다음과 같이 출력하시오.
--부서번호 별로 최대 급여를 출력하시오.  (in)
--select   *
--from     EMP  
--where    sal in  (부서별로 최대급여)

select   *
from     EMP  
where    sal in  (select max(sal) from emp  group by deptno);


--Q009
--EMP 테이블에서 다음과 같이 출력하시오.
--ANY 연산자를 이용하여 다음과같이 출력해보시오  (각부서별 최대급여)
-- IN 과 같은결과
select   *
from     EMP  
where    sal =any(select max(sal) from emp  group by deptno);


--Q010
--EMP 테이블에서 다음과 같이 출력하시오.
-- SOME 연산자를 이용하여 다음과같이 출력해보시오  (각부서별 최대급여)
-- IN 과 같은결과
select   *
from     EMP  
where    sal =some(select max(sal) from emp  group by deptno);


--Q0011
--EMP 테이블에서 다음과 같이 출력하시오.
--ANY를 이용하여 30번 부서 사원들의 최대 급여보다 적은 급여를 받는 사원정보를 출력하시오.
--  필드 > any 1    최소값 크다     ,    최대값 작다
select   *
from     EMP 
where    sal  < any(  select   distinct sal   from emp where deptno=30 )
order    by  sal, empno ;


--Q012
--EMP 테이블에서 다음과 같이 출력하시오.
--부서번호가 30인 사원들의 급여를 출력하시오.
select   distinct  sal
from     emp
where    deptno=30;   -- 2850 , 1250,1600,1500,950

--Q013
--EMP 테이블에서 다음과 같이 출력하시오.
--ANY를 이용하여 30번 부서 사원들의 최소 급여보다 많은 급여를 받는 사원정보를 출력하시오.
-- 필드 > any  1    최소값보다 크다  /   < 최대값보다  작다
-- [2850 , 1250,1600,1500],950
select  *
from    emp 
where   sal  >  any(   select sal  from emp  where deptno=30   );


--Q014
--EMP 테이블에서 다음과 같이 출력하시오.
--ALL를 이용하여 30번 부서 사원들의 최소 급여보다 더 적은 급여를 받는 사원정보를 출력하시오.
--  필드 > all 3    최대값 크다     ,   < 최소값 작다
-- 2850 , 1250,1600,1500,[950]
select *
from   EMP    
where  sal  < all( select sal from emp where deptno=30 )


--Q015
--EMP 테이블에서 다음과 같이 출력하시오.
--ALL를 이용하여 30번 부서 사원들의 최대 급여보다 더 많은 급여를 받는 사원정보를 출력하시오.
--  필드 > all 3    최대값 크다     ,   < 최소값 작다
select   *
from     EMP
where    sal  >  all( select sal  from emp  where deptno=30 );


--  ■암기)   any1 all3
--  필드 > any 1   최소값보다 크다,     <   최대값  작다
--  필드 > all 3   최대값보다 크다,     <   최소값  작다


--Q016
-- EMP 테이블에서 모든 행을 선택하되, 
-- 다음 조건을 만족하는 경우에만 출력하시오. EXISTS 이용
-- DEPT 테이블에 DEPTNO = 10인 행이 하나라도 존재하는 경우
-- in() , any() , all() , exists()

select *
from   emp
where  exists(  select dname from dept  where deptno=10  );


--Q017
-- EMP 테이블에서 모든 행을 선택하되, 
-- 다음 조건을 만족하는 경우에만 출력하시오. EXISTS 이용
-- DEPT 테이블에 DEPTNO = 50인 행이 하나라도 존재하는 경우
--결과값이 모두 존재하지 않기때문에 아무행도 출력안됨.

select *
from   emp
where  exists(  select dname from dept  where deptno=50  );


-- https://sally03915.github.io/stackventure_250825/004_oracle/oracle007_select_subquery#49
-- Q18~21
-- Q018
--EMP 테이블에서 다음과 같이 출력하시오.
--다중열 서브쿼리를 이용하여 WHERE (DEPTNO, SAL) IN ( ... )
--부서별 최대급여를 받는 사원정보를 출력하시오.
--힌트)
--select *
--from   EMP 테이블에서 
--where  (deptno, sal) 부서별 최대급여를 받는 사원정보  

SELECT   *
FROM     EMP
WHERE    (deptno, sal)  in  (  select  deptno, max(sal)  from emp  group by deptno ) ; 




-- Q019
--EMP 테이블에서 다음과 같이 출력하시오.
--FROM 절에서 사용하는 인라인 뷰를 이용하여
--부서번호가 10인 사용자 정보와 부서정보를 가져와
--EMPNO, ENAME, DEPTNO, DNAME, LOC 를 출력하시오.
--힌트)
--select  EMPNO, ENAME, DEPTNO, DNAME, LOC 
--from    (부서번호가 10인 사용자 정보) emp, (부서정보) dept
--where   e.deptno = d.deptno
 
select   EMPNO, ENAME, d.DEPTNO, DNAME, LOC
from     (select * from emp where deptno=10)  e
       , (select * from dept) d
where    e.deptno = d.deptno

select  EMPNO, ENAME, d.DEPTNO, DNAME, LOC
from    emp e join  dept d  on   e.deptno = d.deptno
where   e.deptno=10;

select  EMPNO, ENAME, DEPTNO, DNAME, LOC
from    emp e join  dept d  using( deptno )
where   deptno=10;

select  EMPNO, ENAME, DEPTNO, DNAME, LOC
from    emp e  natural join  dept  
where   deptno=10;





-- Q020
--WITH  앞에 문제를 with로 
--FROM 절에 명시하는 방식보다 몇십, 몇백줄의 규보가 되었을때 유용하게 사용됨.
--힌트)
--with
--  e  () 
--, d ()
--select  EMPNO, ENAME, DEPTNO, DNAME, LOC 
--from    e, d
--where   e.deptno = d.deptno
WITH
  e as (select * from emp where deptno=10)  
, d as (select * from dept)
select   EMPNO, ENAME, d.DEPTNO, DNAME, LOC
from     e , d
where    e.deptno = d.deptno;



-- Q021
--열에 명시하는 스칼라서브쿼리
--EMP 테이블의 EMPNO, ENAME, JOB, SAL
--EMP 테이블의 SAL을 이용하여 SALGRADE에서 등급(GRADE)을 구하고
--EMP 테이블의 DEPTNO를 이용하여 DEPTNO가 같은 부서명(DNAME)을 구하시오.
--힌트)
--select   EMPNO, ENAME, JOB, SAL , SAL을 이용하여 SALGRADE에서 등급(GRADE) ,   
--         DEPTNO를 이용하여 DEPTNO가 같은 부서명(DNAME)
--from     emp;

select  EMPNO, ENAME, JOB, SAL 
    , ( select  grade  from  salgrade  where e.sal  between losal and hisal  )  GRADE 
    , deptno
    , ( select dname  from dept  where e.deptno =  deptno  )DNAME    
from    emp e;



-- https://sally03915.github.io/stackventure_250825/004_oracle/oracle007_select_subquery#59
-- 사고확장 EX( EX001~EX004)
--EX001
--EMP 테이블에서 다음과 같이 출력하시오.
--전체 사원 중 ALLEN과 같은 직책(JOB)인 사원들의 사원정보, 부서정보를 다음과 같이 출력하시오.
--힌트)
--SELECT   JOB, EMPNO, ENAME, SAL, DEPTNO,DNAME
--FROM     EMP
--WHERE    JOB =   (ALLEN과 같은 직책(JOB))   

-- 1-1 등가 INNER JOIN
SELECT  JOB, EMPNO, ENAME, SAL, D.DEPTNO , DNAME
FROM    EMP E ,  DEPT D
WHERE   E.DEPTNO = D.DEPTNO 
AND     JOB =  (SELECT JOB FROM EMP  WHERE ENAME='ALLEN');
  
-- 1-2
SELECT  JOB, EMPNO, ENAME, SAL, D.DEPTNO , DNAME
FROM    EMP E JOIN  DEPT D  ON( E.DEPTNO = D.DEPTNO )
WHERE      JOB =  (SELECT JOB FROM EMP  WHERE ENAME='ALLEN');
  
-- 1-3
SELECT  JOB, EMPNO, ENAME, SAL, DEPTNO , DNAME
FROM    EMP E JOIN  DEPT D  USING(DEPTNO)
WHERE   JOB =  (SELECT JOB FROM EMP  WHERE ENAME='ALLEN');

-- 1-4.
SELECT  JOB, EMPNO, ENAME, SAL, DEPTNO , DNAME
FROM    EMP E JOIN  DEPT D  USING(DEPTNO)
WHERE   JOB  IN (SELECT JOB FROM EMP  WHERE ENAME='ALLEN');

-- 1-5. ★★★★★
WITH  ALLEN_JOB  AS  (SELECT JOB FROM EMP  WHERE ENAME='ALLEN')
SELECT  JOB, EMPNO, ENAME, SAL, DEPTNO , DNAME
FROM    EMP E JOIN  DEPT D      USING(DEPTNO)  
              JOIN  ALLEN_JOB   USING(JOB);

-- 1-6. 
SELECT  JOB, EMPNO, ENAME, SAL, DEPTNO , DNAME
FROM    EMP E JOIN  DEPT D  USING(DEPTNO)
WHERE   EXISTS  (SELECT 1 FROM EMP A  WHERE A.ENAME='ALLEN'  AND A.JOB= E.JOB );


-- =,  JOIN ON, JOIN USING, NATURAL JOIN ,   WITH( 테이블 선언) ,  IN , EXISTS
 
 
--EX002
--EMP 테이블에서 다음과 같이 출력하시오.
--전체 사원의 평균급여(SAL) 보다 높은 급여를 받는 사원들의 사원정보, 부서정보, 급여등급정보를 출력하시오.
--급여가 많은 순으로 정렬하되 급여가 같은경우에는 사원번호를 기준으로 오름차순으로 정렬
--힌트)
--SELECT   JOB, EMPNO,DNAME  , HIREDATE , LOC, SAL, GRADE
--FROM      EMP E, DEPT D, SALGRADE S
--WHERE     JOIN 조건,     
--AND       평균급여(SAL) 보다 높은 급여를 받는 사원들
--ORDER    BY    급여가 많은 순으로 정렬 , 사원번호를 기준으로 오름차순

-- 1-1
SELECT  JOB, EMPNO,DNAME  , HIREDATE , LOC, SAL, GRADE
FROM    EMP E, DEPT D,  SALGRADE S
WHERE   E.DEPTNO = D.DEPTNO  AND  E.SAL  BETWEEN  S.LOSAL AND S.HISAL
AND     E.SAL >  (SELECT AVG(SAL) FROM EMP)   
ORDER   BY  SAL  DESC, EMPNO;
   
-- 1-2
SELECT  JOB, EMPNO,DNAME  , HIREDATE , LOC, SAL, GRADE
FROM    EMP E  JOIN DEPT D      ON (E.DEPTNO = D.DEPTNO )
               JOIN SALGRADE S  ON ( E.SAL  BETWEEN  S.LOSAL AND S.HISAL)
WHERE   E.SAL >  (SELECT AVG(SAL) FROM EMP)   
ORDER   BY  SAL  DESC, EMPNO;

-- 1-3. WITH    (단일행)   ★★★★★
WITH    AVG_SAL  (AVG_SAL)  AS (SELECT AVG(SAL) FROM EMP)
SELECT  E.JOB, E.EMPNO, D.DNAME  , E.HIREDATE , D.LOC, E.SAL, S.GRADE
FROM    EMP E  JOIN DEPT D      ON (E.DEPTNO = D.DEPTNO )
               JOIN SALGRADE S  ON ( E.SAL  BETWEEN  S.LOSAL AND S.HISAL)
               JOIN AVG_SAL  A  ON  E.SAL > A.AVG_SAL
ORDER   BY  SAL  DESC, EMPNO;

-- 1-4. EXISTS
SELECT  JOB, EMPNO,DNAME  , HIREDATE , LOC, SAL, GRADE
FROM    EMP E  JOIN DEPT D      ON (E.DEPTNO = D.DEPTNO )
               JOIN SALGRADE S  ON ( E.SAL  BETWEEN  S.LOSAL AND S.HISAL)
WHERE   EXISTS (     
    SELECT 1  FROM  EMP X   WHERE  E.SAL > (  SELECT AVG(SAL) FROM EMP )
)  
ORDER   BY  SAL  DESC, EMPNO;



--EX003
--EMP 테이블에서 다음과 같이 출력하시오.
--10번부서에서 근무하는 사원 중 
--30번부서에는 존재하지 않는 직책을 가진 사원들의 사원정보, 부서정보를 다음과 같이 출력하는 SQL문을 작성하시오.
--힌트)
--SELECT    EMPNO, ENAME, JOB, DEPTNO, DNAME, LOC
--FROM      EMP E, DEPT D 
--WHERE     JOIN 조건
--AND       10번부서에서
--AND       JOIN NOT IN(30번부서에는 존재하지 않는 직책 )
-- 3-1
SELECT  EMPNO, ENAME, JOB, D.DEPTNO, DNAME, LOC
FROM    EMP E , DEPT D  
WHERE   E.DEPTNO = D.DEPTNO AND   E.DEPTNO = 10  AND  JOB NOT IN ( SELECT JOB  FROM EMP  WHERE DEPTNO=30 );

-- 3-2
SELECT  EMPNO, ENAME, JOB, D.DEPTNO, DNAME, LOC
FROM    EMP E  JOIN DEPT D  ON E.DEPTNO=D.DEPTNO
WHERE   E.DEPTNO = 10  AND  JOB NOT IN ( SELECT JOB  FROM EMP  WHERE DEPTNO=30 );

-- 3-3  ★★★★★
WITH   JOB_IN_30  AS (  SELECT JOB  FROM EMP  WHERE DEPTNO=30  )
SELECT  EMPNO, ENAME, JOB, D.DEPTNO, DNAME, LOC
FROM    EMP E  JOIN DEPT D  ON E.DEPTNO=D.DEPTNO
WHERE   E.DEPTNO = 10  AND  E.JOB NOT IN ( SELECT JOB FROM JOB_IN_30 ) ;
  
-- 3-4
SELECT  EMPNO, ENAME, JOB, D.DEPTNO, DNAME, LOC
FROM    EMP E  JOIN DEPT D  ON E.DEPTNO=D.DEPTNO
WHERE   E.DEPTNO = 10  
AND     NOT EXISTS (
   SELECT 1  FROM EMP X  WHERE X.DEPTNO=30  AND X.JOB=E.JOB  
);


--EX004
--EMP 테이블에서 다음과 같이 출력하시오.
--직책이 SALESMAN인 사람들의 최고급여보다 높은 급여를 급여를 받는 사원들의 사원정보, 급여등급정보를 다음과 같이 출력하시오.
--다중행 함수 사용하지 않는 방법 , 다중행 함수 사용하는 방법(IN, ANY, ALL, EXISTS) 2가지로 작성하시오.
--사원번호를 기준으로 오름차순으로 정렬하시오.
--힌트) 
--SELECT    EMPNO, ENAME,SAL, GRADE
--FROM      EMP E, SALGRADE S
--WHERE     JOIN 조건 ( e.sal  between  s.losal  and s.hisal )
--AND       sal  > (직책이 SALESMAN인 사람들의 최고급여 )
--ORDER BY  EMPNO;
SELECT  EMPNO, ENAME,SAL, GRADE
FROM    EMP E , SALGRADE S
WHERE   E.SAL BETWEEN  S.LOSAL AND S.HISAL
AND     SAL  > (  SELECT MAX(SAL) FROM EMP WHERE JOB='SALESMAN')  -- MAX 들어가야함!
ORDER  BY  EMPNO;  
 
SELECT  EMPNO, ENAME,SAL, GRADE
FROM    EMP E , SALGRADE S
WHERE   E.SAL BETWEEN  S.LOSAL AND S.HISAL
AND     SAL  > ALL(  SELECT SAL FROM EMP WHERE JOB='SALESMAN')    -- MAX 빠짐! 
ORDER  BY  EMPNO;

--힌트1)  애니1 올3 
--필드 >ANY 1   최소보다 크다, 최대보다 작다        > ALL 3  최대보다 크다, 최소보다 작다

