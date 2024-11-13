-- 근태 기록 더미데이터로 직원 한명에 대해서만 작동한다. (발표전 107 계정에 대하여 실행!)
DECLARE
num NUMBER :=1;
BEGIN
    -- 기존데이터 제거
    DELETE attendances
    WHERE id = 'emp107';
    LOOP
    -- 출근 정보 추가
    insert into attendances (ATTENDANCES_NO,ATTENDANCES_CODE,DEPARTMENT_NO,TIME,WORKING_TIME,ID) values 
        (attendances_seq.NEXTVAL,'j1',1
        ,TO_DATE('2024-09-01 09:00:00', 'YYYY-MM-DD HH24:MI:SS')
        +num,null
        ,'emp107');
    -- 퇴근 정보 추가 (9 ~ 오후 6)
    insert into attendances (ATTENDANCES_NO,ATTENDANCES_CODE,DEPARTMENT_NO,TIME,WORKING_TIME,ID) values 
        (attendances_seq.NEXTVAL,'j2',1
        ,TO_DATE('2024-09-01 18:00:00', 'YYYY-MM-DD HH24:MI:SS')
        +num,540
        ,'emp107');
    -- 주말 출퇴근 값 제거
    delete attendances
    where id='emp107'
    AND TO_CHAR(time, 'D') in(1,7);
    -- 공휴일 제거 (각각 날짜별로 제거한다.)
    delete attendances
    where id='emp107'
    AND TO_CHAR(time, 'MM/DD') in(
    '09/16',
    '09/17',
    '09/18',
    '10/01',
    '10/03',
    '10/09',
    '12/25',
    '01/01',
    '01/28',
    '01/29',
    '01/30',
    '03/01',
    '03/03'
    );
    num := num + 1;
    EXIT WHEN num > 72;
    END LOOP;
    commit;
END;
/