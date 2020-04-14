show tables;

create table board(
boatd_idx int not null auto_increment primary key comment '글번호',
title varchar(50) not null comment '제목',
contents text not null comment '내용',
hit_cnt smallint not null default '0' comment '조회수',
created_time datetime not null comment '작성시간',
creator_id varchar(50) not null comment '작성자 아이디',
updated_time datetime default null comment '수정시간',
updater_id varchar(50) default null comment '수정자 아이디',
deleted_yn char not null default 'N' comment '삭제 여부'
);

desc board;


