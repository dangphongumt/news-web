use jsp_servlet12_2018;

create table role(
id bigint not null primary key auto_increment,
name varchar(255) not null,
code varchar(255) not null,
createdate timestamp null,
modifieddate timestamp null,
createby varchar(255) null,
modifiedby varchar(255) null
);


create table user(
id bigint not null primary key auto_increment,
username varchar(255) not null,
password varchar(255) not null,
fullname varchar(255) null,
status int not null, -- boolean
roleid bigint not null,
createdate timestamp null,
modifieddate timestamp null,
createby varchar(255) null,
modifiedby varchar(255) null
);


alter table user add constraint fk_user_role foreign key (roleid) references role(id);
 
 
 
 create table news(
id bigint not null primary key auto_increment,
title varchar(255) null,
thumbnail varchar(255) null,
shortdescription text null,
content	text null,
categoryid bigint not null,
createdate timestamp null,
modifieddate timestamp null,
createby varchar(255) null,
modifiedby varchar(255) null
);

create table category(
id bigint not null primary key auto_increment,
name varchar(255) not null,
code varchar(255) not null,
createdate timestamp null,
modifieddate timestamp null,
createby varchar(255) null,
modifiedby varchar(255) null
);

alter table news add constraint fk_news_category foreign key (categoryid) references category(id);


create table comment(
id bigint not null primary key auto_increment,
content text not null,
user_id bigint not null,
news_id bigint not null,
createdate timestamp null,
modifieddate timestamp null,
createby varchar(255) null,
modifiedby varchar(255) null
);

alter table comment add constraint fk_user_comment foreign key (user_id) references user(id);
alter table comment add constraint fk_news_comment foreign key (news_id) references news(id);