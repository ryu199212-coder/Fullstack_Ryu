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