DROP TABLE IF EXISTS student;
DROP TABLE IF EXISTS refresh_token;

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
    primary key (`refresh_token_id`)
) engine=InnoDB;


