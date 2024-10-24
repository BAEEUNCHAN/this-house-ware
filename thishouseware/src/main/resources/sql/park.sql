select 
    department_no,
    department_name
from department;

-- 직원 조회
SELECT
    id,
    name,
    password,
    hire_dt,
    phone,
    image_link,
    department_no,
    position_code,
    RESIGN_DT,
    D_SIGNATURE,
    email
FROM
    employees;
-- 조인 문 추가
		SELECT
		    id,
		    name,
		    email,
		    password,
		    hire_dt,
		    phone,
		    image_link,
		    e.department_no,
		    position_code,
		    RESIGN_DT,
    		D_SIGNATURE,
            d.department_name
		FROM
		    employees e
        JOIN 
            department d
        on (e.department_no = d.department_no)
		WHERE id = 'emp100';

-- 업데이트문 추출
UPDATE employees 
		SET HIRE_DT = to_timestamp('01/12/2023 00:00:00.000', 'mm/dd/yyyy hh24:mi:ss.ff3'),
		    PHONE = '010-1111-0000', 
		    EMAIL = 'test@1                                            ', 
		    IMAGE_LINK = 'img/emp/2024/10/17/001d00a6-30fe-422e-9ed2-efaab1b32a57_ss(8).jpg', 
		    DEPARTMENT_NO = 11, 
		    POSITION_CODE = 'a4', 
		    NAME = 'Park' 
		WHERE id = 'emp106';

-- 비밀번호 암호화 인코딩 
UPDATE employees
    SET 
        password = '$2a$10$/9OOLt.FG1BWEhKGX1p7qOzYoq6MvD.OO7NnctbZ9nz6lpw2IpSU2';

-- 이메일로 검색
		SELECT
		    id,
		    name,
		    email,
		    password,
		    hire_dt,
		    phone,
		    image_link,
		    e.department_no,
		    position_code,
		    RESIGN_DT,
    		D_SIGNATURE,
            d.department_name
		FROM
		    employees e
        JOIN 
            department d
        on (e.department_no = d.department_no)
		WHERE email = '1';
        
-- 이메일 타입 수정
ALTER TABLE employees MODIFY email VARCHAR2(100);

-- 인증 값 수정
UPDATE authentications
    SET 
        AUTHENTICATIONS_VALUE = 1
        ,AUTHENTICATIONS_TIME = SYSDATE
    WHERE id = 'emp115';
-- 시간값 계산 결과에서 분 추출
select  ROUND((sysdate - Authentications_time) * 24 * 60)
FROM    Authentications;

-- 함수 실행하여 결과 가져오기
SELECT 
    CAN_CHANGE_PW(
         'emp115'
        ,'HLL7KWU3SQW0566'
        ,'1234'
    ) AS result
FROM DUAL;
UPDATE employees
    SET
        password = '1234'
    WHERE id = 'emp115';
select id, password from employees where id = 'emp115';

insert into attendances values(
    5,1,1,sysdate,1,1
);
select *
FROM attendances
WHERE attendances_no = 
(   SELECT max(attendances_no) 
    FROM attendances
    WHERE id = '1');
-- 수정문 실행후 commit 하자
commit;



