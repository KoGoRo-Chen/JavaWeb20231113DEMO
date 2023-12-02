-- 刪除已建立訪客留言板
drop table if exists guestbook;

-- 建立訪客留言板
create table if not exists guestbook(
	id int not null auto_increment, -- 序號
    nickname varchar(50) not null,
    age int,
    sex varchar(10),
    message varchar(225) not null,
    date timestamp default current_timestamp,
    primary key(id)
);