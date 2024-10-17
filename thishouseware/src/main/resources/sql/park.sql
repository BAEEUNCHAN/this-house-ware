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
    D_SIGNATURE
FROM
    employees
where id = 'emp100';
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

-- 수정문 실행후 commit 하자
commit;