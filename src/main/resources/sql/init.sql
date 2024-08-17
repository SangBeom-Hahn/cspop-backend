DROP TABLE IF EXISTS refresh_token;
DROP TABLE IF EXISTS comment;
DROP TABLE IF EXISTS notice_board;
DROP TABLE IF EXISTS student;
DROP TABLE IF EXISTS schedule;

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