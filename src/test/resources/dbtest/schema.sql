drop table users if exists;
create table users (
 	id int not null primary key
 	,title varchar(30) not null
   	,firstName varchar(30) not null
   	,lastName  varchar(30) not null
)
