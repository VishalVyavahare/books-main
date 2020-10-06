create schema if not exists books_lib;

create table if not exists books_lib.User
(user_id varchar(128) not null  , user varchar(256) not null,password varchar(2048) not null ,
 creation_date timestamp default now(), update_date timestamp default now(), is_deleted int(1),
PRIMARY KEY (user_id));