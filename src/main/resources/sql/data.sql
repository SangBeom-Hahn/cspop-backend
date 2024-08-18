insert into student
    (birth, classification, created_date, department, email, grade, last_modified_date, login_id, student_name, student_number, password, phone_number, role_type)
values ('1999-09-17','UNDERGRADUATE_STUDENT',NOW(),'AI','1@naver.com','FIRTH',
        NOW(),'123','dummy','201811111','$2a$10$oXGskC./TduQr2ngwoAqg.RX6cU0Hi.d17DxIlY0bnJKeb/qSxQqu',
        '010-1111-1111','STUDENT');

insert into notice_board
    (student_id, content, created_date, fix, last_modified_date, title, views, write_date)
values (1, 'content1',NOW(),false,NOW(),'title1',1,NOW());

insert into notice_board
    (student_id, content, created_date, fix, last_modified_date, title, views, write_date)
values (1, 'content2',NOW(),false,NOW(),'title2',1,NOW());

insert into notice_board
    (student_id, content, created_date, fix, last_modified_date, title, views, write_date)
values (1, 'content3',NOW(),false,NOW(),'title3',1,NOW());

insert into comment
    (content, created_date, last_modified_date, notice_board_id, student_id, write_date)
values ('content1',NOW(),NOW(),1,1,NOW());

insert into comment
    (content, created_date, last_modified_date, notice_board_id, student_id, write_date)
values ('content2',NOW(),NOW(),1,1,NOW());

insert into comment
    (content, created_date, last_modified_date, notice_board_id, student_id, write_date)
values ('content3',NOW(),NOW(),1,1,NOW());

insert into schedule
    (created_date,end_date,last_modified_date,start_date,status,step)
values (NOW(),'2024-04-12',NOW(),'2024-03-07','FINISH','RECEIVE');

insert into schedule
    (created_date,end_date,last_modified_date,start_date,status,step)
values (NOW(),'2024-05-13',NOW(),'2024-04-12','FINISH','PROPOSAL');

insert into schedule
    (created_date,end_date,last_modified_date,start_date,status,step)
values (NOW(),'2024-06-18',NOW(),'2024-05-13','FINISH','MIDDLE');

insert into schedule
    (created_date,end_date,last_modified_date,start_date,status,step)
values (NOW(),'2024-06-18',NOW(),'2024-05-13','FINISH','FINAL');

insert into schedule
    (created_date,end_date,last_modified_date,start_date,status,step)
values (NOW(),'2024-06-21',NOW(),'2024-05-13','FINISH','OTHER_QUALIFICATION');

insert into schedule
    (created_date,end_date,last_modified_date,start_date,status,step)
values (NOW(),'2024-08-14',NOW(),'2024-06-14','FINISH','PASS');

insert into schedule_board
    (final_report, middle_report, other_qualification, pass, proposal, receive, schedule_board_id)
values ('finalReport','middleReport','other','pass','proposal','receive', 3);
