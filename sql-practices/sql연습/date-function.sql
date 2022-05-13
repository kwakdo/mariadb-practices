-- 함수: 날짜 함수

-- curdate(), current_date;
select curdate(), current_date;

-- curtime(), current_time;
select curtime(), current_time;

-- now() vs sysdate()
select now(), sysdate();					-- 앵간하면 now() 써라
select now(), sleep(2), now();				-- 쿼리를 실행하는 시간기준
select sysdate(), sleep(2), sysdate(); 		-- 함수를 실행하는 시간기준

-- date_format	(https://devjhs.tistory.com/89)
-- 2022년 05월 13일 11시 39분 40초
select date_format(now(), '%Y년 %m월 %d일 %h시 %i분 %s초');
select date_format(now(), '%y년 %c월 %d일 %h시 %i분 %s초');

-- period_diff
-- YYMM, YYYYMM
-- 예) 근무개월 수
select period_diff(date_format(curdate(), '%Y%m'), date_format(hire_date, '%Y%m')) as month from employees
order by month desc;

-- date_add(=adddate), date_sub(=subdate)
-- 잘짜를 date에 type(day, month, year) 형식의 표현식을 더하거나 뺀다.
-- 예제) 각 사원들의 근무년수가 5년이 되는 날은 언제인가?

select first_name, hire_date, date_add(hire_date, interval 5 year) as '근무 5년' from employees;

-- cast
select '12345' + '10', cast('12345' as int) + 10, concat('12345', '10');
select date_format('2022-10-10', '%Y년 %m월 %d일'), date_format(cast('2022-10-10' as date), '%Y년 %m월 %d일');
select cast(cast(1-2 as unsigned) as signed);

-- mysql type
-- varchar(사이즈 줄임: 4000까지), char(고정), text(긴 거), CLOB(더 긴 거)
-- signed(unsigned) 쓸일 잘 없음, int(integer), medium int, big int, int(11:백억)
-- float, double
-- time, date, datetime
-- LOB: CLOB(Charactor Large Object), BLOC(Binary Large Object)


