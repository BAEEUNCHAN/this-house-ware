-- edms_form 

INSERT INTO edms_form (edms_form_no, edms_form_name, edms_form_type, edms_form_path, description, create_dt)
VALUES ('FORM001', '지출결의서', '회계', '/forms/expense_request.pdf', '지출 결의서 양식입니다.', TO_DATE('2023-01-10', 'YYYY-MM-DD'));

INSERT INTO edms_form (edms_form_no, edms_form_name, edms_form_type, edms_form_path, description, create_dt)
VALUES ('FORM002', '구매신청서', '회계', '/forms/purchase_request.pdf', '구매 신청서 양식입니다.', TO_DATE('2023-02-15', 'YYYY-MM-DD'));

INSERT INTO edms_form (edms_form_no, edms_form_name, edms_form_type, edms_form_path, description, create_dt)
VALUES ('FORM003', '매출보고서', '회계', '/forms/sales_report.pdf', '매출 보고서 양식입니다.', TO_DATE('2023-03-20', 'YYYY-MM-DD'));

INSERT INTO edms_form (edms_form_no, edms_form_name, edms_form_type, edms_form_path, description, create_dt)
VALUES ('FORM004', '전자계약서(정규)', '인사', '/forms/e_contract_standard.pdf', '정규 전자 계약서 양식입니다.', TO_DATE('2023-04-10', 'YYYY-MM-DD'));

INSERT INTO edms_form (edms_form_no, edms_form_name, edms_form_type, edms_form_path, description, create_dt)
VALUES ('FORM005', '전자계약서(계약)', '인사', '/forms/e_contract_agreement.pdf', '계약 전자 계약서 양식입니다.', TO_DATE('2023-05-05', 'YYYY-MM-DD'));

INSERT INTO edms_form (edms_form_no, edms_form_name, edms_form_type, edms_form_path, description, create_dt)
VALUES ('FORM006', '비밀유지 서약서', '인사', '/forms/confidentiality_agreement.pdf', '비밀 유지에 관한 서약서 양식입니다.', TO_DATE('2023-01-15', 'YYYY-MM-DD'));

INSERT INTO edms_form (edms_form_no, edms_form_name, edms_form_type, edms_form_path, description, create_dt)
VALUES ('FORM007', '휴가신청서', '근태', '/forms/vacation_request.pdf', '휴가 신청서 양식입니다.', TO_DATE('2023-02-20', 'YYYY-MM-DD'));

INSERT INTO edms_form (edms_form_no, edms_form_name, edms_form_type, edms_form_path, description, create_dt)
VALUES ('FORM008', '출장보고서', '근태', '/forms/business_trip_report.pdf', '출장 보고서 양식입니다.', TO_DATE('2023-03-25', 'YYYY-MM-DD'));

INSERT INTO edms_form (edms_form_no, edms_form_name, edms_form_type, edms_form_path, description, create_dt)
VALUES ('FORM009', '연장근무 신청서', '근태', '/forms/overtime_request.pdf', '연장 근무 신청서 양식입니다.', TO_DATE('2023-04-05', 'YYYY-MM-DD'));

INSERT INTO edms_form (edms_form_no, edms_form_name, edms_form_type, edms_form_path, description, create_dt)
VALUES ('FORM010', '교육신청서(사내,사외)', '교육', '/forms/training_request.pdf', '교육 신청서 양식입니다.', TO_DATE('2023-04-20', 'YYYY-MM-DD'));

INSERT INTO edms_form (edms_form_no, edms_form_name, edms_form_type, edms_form_path, description, create_dt)
VALUES ('FORM011', '일반 기안 양식', '일반', '/forms/general_proposal.pdf', '일반 기안 양식입니다.', TO_DATE('2023-05-10', 'YYYY-MM-DD'));

INSERT INTO edms_form (edms_form_no, edms_form_name, edms_form_type, edms_form_path, description, create_dt)
VALUES ('FORM012', '협조문', '일반', '/forms/letter_of_cooperation.pdf', '협조문 양식입니다.', TO_DATE('2023-05-15', 'YYYY-MM-DD'));


commit;

ALTER TABLE edms_doc MODIFY approval_status VARCHAR2(6);

-- edms_doc 
-- 결재 상태: 임시저장
INSERT INTO edms_doc (edms_doc_no, id, title, content, edms_form_no, file_name, attatch, submit_dt, approval_status, share_status, share_folder_no)
VALUES ('201', 'emp100', '임시 저장 문서 1', '임시 저장 문서 1의 내용입니다.', 'FORM001', NULL, NULL, SYSDATE, '임시저장', 'no', 'SH001');

INSERT INTO edms_doc (edms_doc_no, id, title, content, edms_form_no, file_name, attatch, submit_dt, approval_status, share_status, share_folder_no)
VALUES ('202', 'emp100', '임시 저장 문서 2', '임시 저장 문서 2의 내용입니다.', 'FORM001', NULL, NULL, SYSDATE, '임시저장', 'no', 'SH002');

INSERT INTO edms_doc (edms_doc_no, id, title, content, edms_form_no, file_name, attatch, submit_dt, approval_status, share_status, share_folder_no)
VALUES ('203', 'emp100', '임시 저장 문서 3', '임시 저장 문서 3의 내용입니다.', 'FORM001', NULL, NULL, SYSDATE, '임시저장', 'no', 'SH003');

INSERT INTO edms_doc (edms_doc_no, id, title, content, edms_form_no, file_name, attatch, submit_dt, approval_status, share_status, share_folder_no)
VALUES ('204', 'emp100', '임시 저장 문서 4', '임시 저장 문서 4의 내용입니다.', 'FORM001', NULL, NULL, SYSDATE, '임시저장', 'no', 'SH004');

INSERT INTO edms_doc (edms_doc_no, id, title, content, edms_form_no, file_name, attatch, submit_dt, approval_status, share_status, share_folder_no)
VALUES ('205', 'emp100', '임시 저장 문서 5', '임시 저장 문서 5의 내용입니다.', 'FORM001', NULL, NULL, SYSDATE, '임시저장', 'no', 'SH005');

COMMIT;

-- 결재 상태: 결재완료
INSERT INTO edms_doc (edms_doc_no, id, title, content, edms_form_no, file_name, attatch, submit_dt, approval_status, share_status, share_folder_no)
VALUES ('206', 'emp100', '결재 완료 문서 1', '결재 완료 문서 1의 내용입니다.', 'FORM002', NULL, NULL, SYSDATE, '결재완료', 'yes', 'SH001');

INSERT INTO edms_doc (edms_doc_no, id, title, content, edms_form_no, file_name, attatch, submit_dt, approval_status, share_status, share_folder_no)
VALUES ('207', 'emp100', '결재 완료 문서 2', '결재 완료 문서 2의 내용입니다.', 'FORM002', NULL, NULL, SYSDATE, '결재완료', 'yes', 'SH001');

INSERT INTO edms_doc (edms_doc_no, id, title, content, edms_form_no, file_name, attatch, submit_dt, approval_status, share_status, share_folder_no)
VALUES ('208', 'emp100', '결재 완료 문서 3', '결재 완료 문서 3의 내용입니다.', 'FORM002', NULL, NULL, SYSDATE, '결재완료', 'yes', 'SH001');

INSERT INTO edms_doc (edms_doc_no, id, title, content, edms_form_no, file_name, attatch, submit_dt, approval_status, share_status, share_folder_no)
VALUES ('209', 'emp100', '결재 완료 문서 4', '결재 완료 문서 4의 내용입니다.', 'FORM002', NULL, NULL, SYSDATE, '결재완료', 'yes', 'SH001');

INSERT INTO edms_doc (edms_doc_no, id, title, content, edms_form_no, file_name, attatch, submit_dt, approval_status, share_status, share_folder_no)
VALUES ('210', 'emp100', '결재 완료 문서 5', '결재 완료 문서 5의 내용입니다.', 'FORM002', NULL, NULL, SYSDATE, '결재완료', 'yes', 'SH001');

-- 결재 상태: 결재대기
INSERT INTO edms_doc (edms_doc_no, id, title, content, edms_form_no, file_name, attatch, submit_dt, approval_status, share_status, share_folder_no)
VALUES ('211', 'emp100', '결재 대기 문서 1', '결재 대기 문서 1의 내용입니다.', 'FORM003', NULL, NULL, SYSDATE, '결재대기', 'no', 'SH005');

INSERT INTO edms_doc (edms_doc_no, id, title, content, edms_form_no, file_name, attatch, submit_dt, approval_status, share_status, share_folder_no)
VALUES ('212', 'emp100', '결재 대기 문서 2', '결재 대기 문서 2의 내용입니다.', 'FORM003', NULL, NULL, SYSDATE, '결재대기', 'no','SH005');

INSERT INTO edms_doc (edms_doc_no, id, title, content, edms_form_no, file_name, attatch, submit_dt, approval_status, share_status, share_folder_no)
VALUES ('213', 'emp100', '결재 대기 문서 3', '결재 대기 문서 3의 내용입니다.', 'FORM003', NULL, NULL, SYSDATE, '결재대기', 'no', 'SH005');

INSERT INTO edms_doc (edms_doc_no, id, title, content, edms_form_no, file_name, attatch, submit_dt, approval_status, share_status, share_folder_no)
VALUES ('214', 'emp100', '결재 대기 문서 4', '결재 대기 문서 4의 내용입니다.', 'FORM003', NULL, NULL, SYSDATE, '결재대기', 'no', 'SH005');

INSERT INTO edms_doc (edms_doc_no, id, title, content, edms_form_no, file_name, attatch, submit_dt, approval_status, share_status, share_folder_no)
VALUES ('215', 'emp100', '결재 대기 문서 5', '결재 대기 문서 5의 내용입니다.', 'FORM003', NULL, NULL, SYSDATE, '결재대기', 'no', 'SH005');

-- 결재 상태: 결재수신
INSERT INTO edms_doc (edms_doc_no, id, title, content, edms_form_no, file_name, attatch, submit_dt, approval_status, share_status, share_folder_no)
VALUES ('216', 'emp100', '결재 수신 문서 1', '결재 수신 문서 1의 내용입니다.', 'FORM004', NULL, NULL, SYSDATE, '결재수신', 'yes', 'SH002');

INSERT INTO edms_doc (edms_doc_no, id, title, content, edms_form_no, file_name, attatch, submit_dt, approval_status, share_status, share_folder_no)
VALUES ('217', 'emp100', '결재 수신 문서 2', '결재 수신 문서 2의 내용입니다.', 'FORM004', NULL, NULL, SYSDATE, '결재수신', 'yes', 'SH002');

INSERT INTO edms_doc (edms_doc_no, id, title, content, edms_form_no, file_name, attatch, submit_dt, approval_status, share_status, share_folder_no)
VALUES ('218', 'emp100', '결재 수신 문서 3', '결재 수신 문서 3의 내용입니다.', 'FORM004', NULL, NULL, SYSDATE, '결재수신', 'yes', 'SH002');

INSERT INTO edms_doc (edms_doc_no, id, title, content, edms_form_no, file_name, attatch, submit_dt, approval_status, share_status, share_folder_no)
VALUES ('219', 'emp100', '결재 수신 문서 4', '결재 수신 문서 4의 내용입니다.', 'FORM004', NULL, NULL, SYSDATE, '결재수신', 'yes', 'SH002');

INSERT INTO edms_doc (edms_doc_no, id, title, content, edms_form_no, file_name, attatch, submit_dt, approval_status, share_status, share_folder_no)
VALUES ('220', 'emp100', '결재 수신 문서 5', '결재 수신 문서 5의 내용입니다.', 'FORM004', NULL, NULL, SYSDATE, '결재수신', 'yes', 'SH002');

COMMIT;