-- edms_form 
INSERT INTO edms_form (edms_form_no, edms_form_name, edms_form_type, edms_form_path, description, create_dt)
VALUES ('FORM001', '회계 결재 양식', '회계/인사', '/forms/accounting_form.pdf', '회계 관련 결재 양식입니다.', TO_DATE('2023-01-10', 'YYYY-MM-DD'));

INSERT INTO edms_form (edms_form_no, edms_form_name, edms_form_type, edms_form_path, description, create_dt)
VALUES ('FORM002', '인사 기록 결재 양식', '회계/인사', '/forms/hr_form.pdf', '인사 관련 기록 결재 양식입니다.', TO_DATE('2023-02-15', 'YYYY-MM-DD'));

INSERT INTO edms_form (edms_form_no, edms_form_name, edms_form_type, edms_form_path, description, create_dt)
VALUES ('FORM003', '근태 결재 양식', '근태', '/forms/attendance_form.docx', '근태 관련 결재 양식입니다.', TO_DATE('2023-03-20', 'YYYY-MM-DD'));

INSERT INTO edms_form (edms_form_no, edms_form_name, edms_form_type, edms_form_path, description, create_dt)
VALUES ('FORM004', '교육 결재 양식', '교육', '/forms/education_form.pdf', '교육 관련 결재 양식입니다.', TO_DATE('2023-04-10', 'YYYY-MM-DD'));

INSERT INTO edms_form (edms_form_no, edms_form_name, edms_form_type, edms_form_path, description, create_dt)
VALUES ('FORM005', '일반 결재 양식', '일반', '/forms/general_form.pdf', '일반적인 결재 양식입니다.', TO_DATE('2023-05-05', 'YYYY-MM-DD'));

INSERT INTO edms_form (edms_form_no, edms_form_name, edms_form_type, edms_form_path, description, create_dt)
VALUES ('FORM006', '비밀서약서 양식', '회계/인사', '/forms/confidentiality_agreement.pdf', '비밀 유지에 관한 서약서 양식입니다.', TO_DATE('2023-01-15', 'YYYY-MM-DD'));

ALTER TABLE edms_doc MODIFY approval_status VARCHAR2(6);

-- edms_doc 
INSERT INTO edms_doc (edms_doc_no, id, title, content, file_name, attatch, submit_dt, approval_dt, approval_status, edms_form_no, share_status, share_folder_no)
VALUES ('DOC001', 'USER001', '회계 결재', '회계 관련 결재 내용입니다.', 'accounting.pdf', 'attachment1.zip', TO_DATE('2023-10-01', 'YYYY-MM-DD'), TO_DATE('2023-10-05', 'YYYY-MM-DD'), '승인', 'FORM001', 'Y', 'FOLDER001');

INSERT INTO edms_doc (edms_doc_no, id, title, content, file_name, attatch, submit_dt, approval_dt, approval_status, edms_form_no, share_status, share_folder_no)
VALUES ('DOC002', 'USER002', '인사 기록 결재', '인사 관련 기록 결재 내용입니다.', 'hr.pdf', 'attachment2.zip', TO_DATE('2023-09-20', 'YYYY-MM-DD'), TO_DATE('2023-09-22', 'YYYY-MM-DD'), '반려', 'FORM002', 'N', 'FOLDER002');

INSERT INTO edms_doc (edms_doc_no, id, title, content, file_name, attatch, submit_dt, approval_dt, approval_status, edms_form_no, share_status, share_folder_no)
VALUES ('DOC003', 'USER003', '근태 결재', '근태 관련 결재 내용입니다.', 'attendance.docx', 'attachment3.zip', TO_DATE('2023-08-15', 'YYYY-MM-DD'), TO_DATE('2023-08-16', 'YYYY-MM-DD'), '승인', 'FORM003', 'Y', 'FOLDER003');

INSERT INTO edms_doc (edms_doc_no, id, title, content, file_name, attatch, submit_dt, approval_dt, approval_status, edms_form_no, share_status, share_folder_no)
VALUES ('DOC004', 'USER004', '교육 결재', '교육 관련 결재 내용입니다.', 'education.pdf', 'attachment4.zip', TO_DATE('2023-07-10', 'YYYY-MM-DD'), TO_DATE('2023-07-15', 'YYYY-MM-DD'), '대기', 'FORM004', 'N', 'FOLDER004');

INSERT INTO edms_doc (edms_doc_no, id, title, content, file_name, attatch, submit_dt, approval_dt, approval_status, edms_form_no, share_status, share_folder_no)
VALUES ('DOC005', 'USER005', '일반 결재', '일반적인 결재 내용입니다.', 'general.pdf', 'attachment5.zip', TO_DATE('2023-06-25', 'YYYY-MM-DD'), TO_DATE('2023-06-30', 'YYYY-MM-DD'), '승인', 'FORM005', 'Y', 'FOLDER005');

commit;

