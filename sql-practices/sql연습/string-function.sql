-- 함수: 문자열

-- upper (대문자 변환)
select upper('busan'), upper('BUSAN'),upper('DouZone');
select upper(first_name) from employees;


-- lower (소문자 변환)
select lower('busan'), lower('BUSAN'),lower('DouZone');
select lower(first_name) from employees;

-- substring(문자열, index, length)
select substring('Hello World', 3, 2);

-- 에졔: 1989년에 입사한 직원의 이름, 성별, 입사일을 출력
select concat(first_name,' ', last_name) as '이 름', gender as '성 별', hire_date '입사일' from employees 
where substring(hire_date, 1, 4) = '1989';

-- lpad(오른쪽 정렬), rpad(왼쪽 정렬)
select lpad('1234', 10, '-');
select rpad('1234', 10, '-');

-- 예제: 직원들의 월급을 오른쪽 정렬 (빈공간은 *)
select lpad(salary, 10, '*'), emp_no from salaries;

-- trim, ltrim, rtrim
select concat('---', ltrim('    hello    '), '---'), 
	   concat('---', rtrim('    hello    '), '---'),
	   concat('---', trim('    hello    '), '---'),
       concat('---', trim(both 'x' from 'xxxxhelloxxxxxx'), '---');


