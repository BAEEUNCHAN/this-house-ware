
-- 프로시저에서 실행할 sql 문 테스트
    UPDATE posts
    SET display = 'q1' -- q1: 게시, q2: 비게시 
    WHERE display = 'q2'
      AND post_setting = 'q1' -- 게시기간 설정 q1: 유, q2: 무
      AND post_starttime <= SYSDATE;
    UPDATE posts
    SET display = 'q2'
    WHERE display = 'q1'
      AND post_endtime < SYSDATE;
-- 스케쥴 실행되는지 확인
