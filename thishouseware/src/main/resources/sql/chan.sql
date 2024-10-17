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
INSERT INTO edms_doc (edms_doc_no, id, title, content, edms_form_no, file_name, attatch, submit_dt, approval_status, share_status, share_folder_no)
VALUES ('101', 'user01', '2024년 10월 매출 보고서', '2024년 10월 매출을 정리한 보고서입니다.', 'FORM003', 'sales_report_oct_2024.pdf', 'sales_attachment.zip', SYSDATE, '상신', 'yes', 'SH001');

INSERT INTO edms_doc (edms_doc_no, id, title, content, edms_form_no, file_name, attatch, submit_dt, approval_status, share_status, share_folder_no)
VALUES ('102', 'user02', '구매 신청서 - 사무용품', '사무용품 구매 신청입니다.', 'FORM002', 'office_supplies.pdf', NULL, SYSDATE, '결재중', 'no', 'SH002');

INSERT INTO edms_doc (edms_doc_no, id, title, content, edms_form_no, file_name, attatch, submit_dt, approval_status, share_status, share_folder_no)
VALUES ('103', 'user03', '비밀유지서약서', '신규 프로젝트에 대한 비밀유지 서약서입니다.', 'FORM006', 'nda_agreement.pdf', 'nda_attachment.zip', SYSDATE, '상신', 'yes', 'SH003');

INSERT INTO edms_doc (edms_doc_no, id, title, content, edms_form_no, file_name, attatch, submit_dt, approval_status, share_status, share_folder_no)
VALUES ('104', 'user04', '휴가 신청서 - 2024년 11월', '2024년 11월 5일부터 7일까지의 휴가 신청입니다.', 'FORM007', 'vacation_request_nov_2024.pdf', NULL, SYSDATE, '반려', 'no', 'SH004');

INSERT INTO edms_doc (edms_doc_no, id, title, content, edms_form_no, file_name, attatch, submit_dt, approval_status, share_status, share_folder_no)
VALUES ('105', 'user05', '출장 보고서 - 부산 출장', '부산 출장에 대한 보고서입니다.', 'FORM008', 'business_trip_busan.pdf', 'busan_trip_attachment.zip', SYSDATE, '승인', 'yes', 'SH005');

commit;

