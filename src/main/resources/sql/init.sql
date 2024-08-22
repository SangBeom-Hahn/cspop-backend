DROP TABLE IF EXISTS final_form;
DROP TABLE IF EXISTS middle_form;
DROP TABLE IF EXISTS proposal;
DROP TABLE IF EXISTS submit;
DROP TABLE IF EXISTS graduation;
DROP TABLE IF EXISTS refresh_token;
DROP TABLE IF EXISTS comment;
DROP TABLE IF EXISTS notice_board;
DROP TABLE IF EXISTS student;
DROP TABLE IF EXISTS schedule;
DROP TABLE IF EXISTS schedule_board;
DROP TABLE IF EXISTS guide_board;

create table student
(
    `student_id` bigint not null auto_increment,
    `birth` date not null,
    `created_date` datetime(6) not null,
    `last_modified_date` datetime(6) not null,
    `student_name` varchar(10) not null,
    `student_number` varchar(15) not null,
    `login_id` varchar(20) not null,
    `phone_number` varchar(20) not null,
    `email` varchar(30) not null,
    `password` varchar(60) not null,
    `classification` enum ('DOUBLE_MAJOR','POSTGRADUATE_STUDENT','PROFESSOR','UNDERGRADUATE_STUDENT') not null,
    `department` enum ('AI','CS','SECURITY') not null,
    `grade` enum ('FIRST','FIRTH','SECOND','THIRD') not null,
    `role_type` enum ('ADMIN','STUDENT') not null,
    primary key (`student_id`)
) engine=InnoDB;

create table refresh_token
(
    `refresh_token_id` bigint not null auto_increment,
    `expired_time` datetime(6),
    `member_id` bigint not null,
    `token_value` varchar(255) not null,
    `created_date` datetime(6) not null,
    `last_modified_date` datetime(6) not null,
    primary key (`refresh_token_id`)
) engine=InnoDB;

create table comment
(
    comment_id bigint not null auto_increment,
    created_date datetime(6) not null,
    last_modified_date datetime(6) not null,
    notice_board_id bigint not null,
    student_id bigint not null,
    write_date datetime(6) not null,
    content varchar(100) not null,
    primary key (comment_id)
) engine=InnoDB;

create table notice_board
(
    notice_board_id bigint not null auto_increment,
    fix boolean not null,
    views integer not null,
    created_date datetime(6) not null,
    last_modified_date datetime(6) not null,
    student_id bigint not null,
    write_date datetime(6) not null,
    title varchar(20) not null,
    content varchar(5000) not null,
    primary key (notice_board_id)
) engine=InnoDB;

create table schedule
(
    schedule_id bigint not null auto_increment,
    end_date date not null,
    start_date date not null,
    created_date datetime(6) not null,
    last_modified_date datetime(6) not null,
    status enum ('FINISH','PROGRESS','WAIT') not null,
    step enum ('FINAL','MIDDLE','OTHER_QUALIFICATION','PASS','PROPOSAL','RECEIVE') not null,
    primary key (schedule_id)
) engine=InnoDB;

create table schedule_board
(
    schedule_board_id bigint not null auto_increment,
    final_report text not null,
    middle_report text not null,
    pass text not null,
    proposal text not null,
    receive text not null,
    other_qualification text not null,
    primary key (schedule_board_id)
);

create table final_form
(
    final_form_id bigint not null auto_increment,
    approve boolean,
    page integer not null,
    type enum ('IMPLEMENT','INVESTIGATE') not null,
    created_date timestamp(6) not null,
    last_modified_date timestamp(6) not null,
    student_id bigint not null unique,
    qualification varchar(45) not null,
    reject_reason varchar(100),
    title varchar(100) not null,
    primary key (final_form_id)
);

create table graduation
(
    graduation_id bigint not null auto_increment,
    capstone_completion boolean,
    graduate_date date,
    created_date timestamp(6) not null,
    last_modified_date timestamp(6) not null,
    student_id bigint not null unique,
    professor_name varchar(10),
    method enum ('OTHER','THESIS'),
    status enum ('APPROVAL','REJECT','UN_APPROVAL'),
    step enum ('FINAL','MIDDLE','OTHER_QUALIFICATION','PASS','PROPOSAL','RECEIVE'),
    primary key (graduation_id)
);

create table middle_form
(
    approve boolean,
    type enum ('IMPLEMENT','INVESTIGATE') not null,
    created_date timestamp(6) not null,
    last_modified_date timestamp(6) not null,
    middle_form_id bigint not null auto_increment,
    student_id bigint not null unique,
    reject_reason varchar(100),
    title varchar(100) not null,
    plan varchar(500) not null,
    text varchar(500) not null,
    primary key (middle_form_id)
);

create table proposal
(
    approve boolean,
    type enum ('IMPLEMENT','INVESTIGATE') not null,
    created_date timestamp(6) not null,
    last_modified_date timestamp(6) not null,
    proposal_id bigint not null auto_increment,
    student_id bigint not null unique,
    content varchar(45) not null,
    qualification varchar(45) not null,
    reject_reason varchar(100),
    title varchar(100) not null,
    primary key (proposal_id)
);

create table submit
(
    approve boolean,
    capstone_completion boolean,
    graduate_date date,
    created_date timestamp(6) not null,
    last_modified_date timestamp(6) not null,
    submit_id bigint not null auto_increment,
    professor_name varchar(10),
    reject_reason varchar(100),
    student_id bigint not null unique,
    primary key (submit_id)
);

create table guide_board
(
    guide_board_id bigint not null auto_increment,
    created_date timestamp(6) not null,
    last_modified_date timestamp(6) not null,
    content text not null,
    primary key (guide_board_id)
);

alter table comment
    add constraint fk_comment_notice_board
        foreign key (notice_board_id)
            references notice_board (notice_board_id);

alter table comment
    add constraint fk_comment_student
        foreign key (student_id)
            references student (student_id);

alter table notice_board
    add constraint fk_notice_board_student
        foreign key (student_id)
            references student (student_id);

alter table final_form
    add constraint fk_final_student
        foreign key (student_id)
            references student (student_id);

alter table graduation
    add constraint fk_graduation_student
        foreign key (student_id)
            references student (student_id);

alter table middle_form
    add constraint fk_middle_student
        foreign key (student_id)
            references student (student_id);

alter table proposal
    add constraint fk_proposal_student
        foreign key (student_id)
            references student (student_id);
