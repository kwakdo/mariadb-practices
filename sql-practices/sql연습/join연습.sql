-- inner join

-- equi join
-- 예제1: 현재 근무하고 있는 직원의 이름과 직책을 출력하세요.
select a.emp_no, a.first_name, b.title
from employees a, titles b 
where a.emp_no = b.emp_no		-- join 조건(n-1)
and b.to_date = '9999-01-01';	-- row 선택 조건

-- 예제2: 현재 근무하고 있는 직원의 이름과 직책을 출력하되, 여성 엔지니어(engineer)만 출력하세요.
select a.emp_no, a.first_name, b.title
from employees a, titles b 
where a.emp_no = b.emp_no
and b.to_date = '9999-01-01'
and a.gender = 'f'
and b.title = 'engineer';


--
-- ANSI/ISO SQL1999 JOIN 표준문법
--
-- 1) natural join
-- 두 테이블에 이름이 같은 공통 컬럼이 있으면 조인 조건을 명시하지 않아도 암묵적으로 조인이 됨.
select a.emp_no, a.first_name, b.title
from employees a natural join titles b 
where b.to_date = '9999-01-01';

-- 2) join ~ using
-- natural join의 문제점: 이름 기반
select count(*)
from salaries a natural join titles b
where a.to_date = '9999-01-01'
and b.to_date = '9999-01-01';


select count(*)
from salaries a join titles b using (emp_no)
where a.to_date = '9999-01-01'
and b.to_date = '9999-01-01';


-- 3) join ~ on
select b.title, avg(a.salary)
from salaries a join titles b on a.emp_no = b.emp_no
where a.to_date = '9999-01-01'
and b.to_date = '9999-01-01'
group by b.title
order by avg(a.salary) desc;

-- 실습문제 1: 현재 회사 상황을 반영한 직원별 근무부서를  사번, 직원 전체이름, 근무부서 형태로 출력해 보세요.
select a.emp_no as '사번', concat(a.first_name, ' ', a.last_name) as '직원 전체이름', c.dept_name as 근무부서
from employees a, dept_emp b, departments c
where a.emp_no = b.emp_no
and b.dept_no = c.dept_no
and b.to_date = '9999-01-01';

-- 실습문제 2: 현재 회사에서 지급되고 있는 급여체계를 반영한 결과를 출력하세요. 사번,  전체이름, 연봉  이런 형태로 출력하세요
select a.emp_no as '사번', concat(a.first_name, ' ', a.last_name) as '직원 전체이름', b.salary as 연봉
from employees a, salaries b
where a.emp_no = b.emp_no
and b.to_date = '9999-01-01'
order by b.salary desc;

-- 실습문제 3: 현재 직책 별로 평균 연봉과 인원수를 구하되, 직책 별로 인원이 100명 이상인 직책만 출력하세요.
select b.title as '직책', avg(a.salary) as '평균연봉', count(a.emp_no)  as '인원수'
from salaries a, titles b
where a.emp_no = b.emp_no
and a.to_date = '9999-01-01'
and b.to_date = '9999-01-01'
group by b.title
having count(*) >= 100
order by 평균연봉 desc;

-- 실습문제 4: 현재 부서별로 현재 직책이 Engineer인 직원들의 대해서만 평균 급여를 구하세요.
select a.dept_no, b.title, avg(c.salary)
from dept_emp a, titles b, salaries c
where a.emp_no = b.emp_no
and b.emp_no = c.emp_no
and a.to_date = '9999-01-01'
and b.to_date = '9999-01-01'
and c.to_date = '9999-01-01'
and b.title = 'engineer'
group by dept_no;









