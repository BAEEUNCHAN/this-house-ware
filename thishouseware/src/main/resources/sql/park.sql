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
        