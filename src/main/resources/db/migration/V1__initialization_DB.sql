create sequence hibernate_sequence start 1 increment 1;

create table student (
    id int8 not null,
    failed_runs_of_test int4 not null,
    full_name varchar(255),
    group_name varchar(255),
    last_commit_message varchar(255),
    last_commit_time timestamp,
    success_runs_of_test int4 not null,
    total_test int4 not null,
    user_id int8,
    primary key (id)
);

create table task (
    id int8 not null,
    file_location varchar(255),
    stack_trace varchar(2048),
    is_resolved boolean not null,
    task_name varchar(255),
    student_id int8,
    primary key (id)
);

create table user_role (
    user_id int8 not null,
    roles varchar(255)
);

create table usr (
    id int8 not null,
    activation_code varchar(255),
    email varchar(255),
    is_active boolean not null,
    password varchar(255) not null,
    username varchar(255) not null,
    primary key (id)
);

alter table if exists student
    add constraint student_usr_fk
    foreign key (user_id) references usr;

alter table if exists task
    add constraint task_student_fk
    foreign key (student_id) references student;

alter table if exists user_role
    add constraint ur_usr_fk
    foreign key (user_id) references usr;