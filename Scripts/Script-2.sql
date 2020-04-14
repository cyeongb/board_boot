show databases;
use boot;
show tables;
select * from board;
desc board;
insert into board (title , contents, creator_id ,created_time) values('title1','admin 의 contents','admin',now());

update title from board where board_idx =1;

