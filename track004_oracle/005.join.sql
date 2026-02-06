-- 1. JOIN
-- 여러 테이블을 한개의 테이블처럼 사용하는것
-- 공통컬럼 기준으로 연결

-- 1-1. 
-- 등가join(=) : where절 emp.demptno = dept.deptno
-- 비등가join(=X) : sal between losal and hisal
-- 자체조인 : emp e1, emp e2

-- 1-2. 
-- outer join : 매칭되지 않은 행도 포함. left, right, full outer join

-- ERD : Entitiy(테이블), Relationship(관계), Diagram(표)
--     : Entitiy(테이블), Relationship(관계), Attribute(표)

-- #1. 내부조인(등가join)
select *
from emp, dept
where emp.deptno = dept.deptno;

select *
from emp e join dept d
on(e.deptno = d.deptno);

select *
from emp e join dept d
using(deptno);

select *
from emp e natural join dept d;

-- 1. emp e, dept d / empno, ename, deptno, dname
select empno, ename, e.deptno, dname
from emp e, dept d
where e.deptno = d.deptno;

select empno, ename, e.deptno, dname
from emp e join dept d
on(e.deptno = d.deptno);

select empno, ename, deptno, dname
from emp e join dept d
using(deptno);

select empno, ename, deptno, dname
from emp e natural join dept d;


-- #2. 외부조인(매칭X)

select * from dept; -- 10, 20, 30, 40
select * from emp;  -- 10, 20, 30

-- 1. (+) orcle only
select ename, dname
from emp e, dept d
where e.deptno = d.deptno(+);  -- emp보장

select ename, dname
from emp e, dept d
where e.deptno(+) = d.deptno;  -- dept보장

-- 2. left join / rigth join
select ename, dname
from emp e inner join dept d -- 내부조인, 겹치는애들만
on e.deptno(+) = d.deptno;

select ename, dname
from emp e right outer join dept d -- right 쪽에 있는 테이블 보장
on e.deptno = d.deptno;

select ename, dname
from emp e left outer join dept d -- left 쪽에 있는 테이블 보장
on e.deptno = d.deptno;

-- 3. full outer join(모든 데이터 결합)
select ename, dname
from emp e full outer join dept d
on e.deptno = d.deptno;

-- 비표준 = , (+)
-- 표준 natural join, join on, join using, left join, right join, full join 
--------------------------------------------------------------------------------
select *
from emp, dept
order by empno;

select *
from emp e, dept d
where emp.deptno = dept.deptno
order by empno;

select *
from emp e, dept d
where e.deptno = d.deptno
order by empno;

select empno, ename, deptno, dname, loc
from emp e, dept d
where e.deptno = d.deptno;

select e.empno, e.ename, d.deptno, d.dname, d.loc
from emp e, dept d
where e.deptno = d.deptno;

select empno, ename, sal, d.deptno, dname, loc
from emp e, dept d
where e.deptno = d.deptno
and sal >= 3000;

select empno, ename, sal, d.deptno, dname, loc
from emp e join dept d
on(e.deptno = d.deptno)
where sal >= 3000;

select empno, ename, sal, deptno, dname, loc
from emp e join dept d
using(deptno)
where sal >= 3000;

select empno, ename, sal, deptno, dname, loc
from emp e natural join dept d
where sal >= 3000;

select *
from emp e, salgrade s
where e.sal between s.losal
and s.hisal;

select *
from emp e join salgrade s
on e.sal between s.losal
and s.hisal;

select e1.empno, e1.ename, e1.mgr, e2.empno mgr_empno, e2.ename mgr_ename
from emp e1, emp e2
where e1.mgr = e2.empno
order by e1.mgr asc;

select e1.empno, e1.ename, e1.mgr, e2.empno mgr_empno, e2.ename mgr_ename
from emp e1 join emp e2
on(e1.mgr = e2.empno);

select e1.empno, e1.ename, e1.mgr, e2.empno mgr_empno, e2.ename mgr_ename
from emp e1, emp e2
where e1.mgr = e2.empno(+);

select e1.empno, e1.ename, e1.mgr, e2.empno mgr_empno, e2.ename mgr_ename
from emp e1, emp e2
where e1.mgr = e2.empno(+);

select e1.empno, e1.ename, e1.mgr, e2.empno mgr_empno, e2.ename mgr_ename
from emp e1, emp e2
where e1.mgr(+) = e2.empno; -- e1테이블 보장

select e.empno, e.ename, e.job, e.mgr, e.hiredate, e.sal, e.comm,
deptno, d.dname, d.loc
from emp e natural join dept d;

select e.empno, e.ename, e.job, e.mgr, e.hiredate, e.sal, e.comm,
deptno, d.dname, d.loc
from emp e join dept d
using(deptno)
where sal >= 3000;

select e.empno, e.ename, e.job, e.mgr, e.hiredate, e.sal, e.comm,
e.deptno, d.dname, d.loc
from emp e join dept d
on e.deptno = d.deptno
where sal <= 3000;

select e1.empno, e1.ename, e1.mgr, e2.empno mgr_empno, e2.ename mgr_ename
from emp e1 left outer join emp e2
on (e1.mgr = e2.empno);

select e1.empno, e1.ename, e1.mgr, e2.empno mgr_empno, e2.ename mgr_ename
from emp e1 right outer join emp e2
on (e1.mgr = e2.empno);

select e1.empno, e1.ename, e1.mgr, e2.empno mgr_empno, e2.ename mgr_ename
from emp e1 full outer join emp e2
on (e1.mgr = e2.empno);

-- EX001
select e.deptno, dname, d.deptno, empno, ename, sal
from emp e, dept d
where e.deptno = d.deptno
and sal > 2000;

select e.deptno, dname, d.deptno, empno, ename, sal
from emp e join dept d
on e.deptno = d.deptno
where sal > 2000;

-- EX002
select e.deptno, dname, trunc(avg(sal)) avg, max(sal), min(sal), count(*) cnt
from emp e, dept d
where e.deptno = d.deptno
group by e.deptno, dname, d.deptno;

select e.deptno, dname, trunc(avg(sal)) avg, max(sal), min(sal), count(*) cnt
from emp e join dept d
on e.deptno = d.deptno
group by e.deptno, dname, d.deptno;

-- EX003
select d.deptno, d.dname, e.empno, e.ename, e.job, e.sal
from dept d, emp e
where d.deptno = e.deptno(+);

select d.deptno, d.dname, e.empno, e.ename, e.job, e.sal
from dept d left outer join emp e
on d.deptno = e.deptno;

-- EX004
select  d.dname, e1.deptno, e1.empno, e1.ename, e1.mgr, e1.sal, d.deptno,
s.losal, s.hisal, s.grade,
e2.empno mgr_empno,
e2.ename mgr_ename
from emp e1, emp e2, salgrade s, dept d
where e1.deptno(+) = d.deptno
and e1.sal between s.losal(+) and s.hisal(+)
and e1.mgr = e2.empno(+)
order by e1.deptno;

select  d.dname, e1.deptno, e1.empno, e1.ename, e1.mgr, e1.sal, d.deptno,
s.losal, s.hisal, s.grade,
e2.empno mgr_empno,
e2.ename mgr_ename
from emp e1
left join emp e2 on e1.mgr = e2.empno
left join salgrade s on e1.sal between s.losal and s.hisal
right join dept d on e1.deptno = d.deptno
order by e1.deptno;




