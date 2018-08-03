/*
 * 该表用于存放用户的信息
 */
drop
	table
		if exists sys_user;

create
	table
		sys_user(
			user_id varchar(20) not null comment '用户',
			user_password varchar(60) not null comment '密码',
			status varchar(20) not null comment '用户状态，NORMAL：正常，LOCKED：锁定（密码错误达到指定上限，锁定当前用户，次日自动解锁），ONLINE：在线，NEED_ACTIVATE：待激活',
			email varchar(40) not null comment '电子邮箱',
			valid_time datetime not null comment '生效时间',
			expire_time datetime not null comment '失效时间',
			last_login_time datetime default null comment '最后一次登录时间',
			create_time datetime default now() not null comment '创建时间'
		) engine = innodb default charset = utf8 collate = utf8_bin comment = '用户表';

-- 创建主键
alter table sys_user add constraint pk_sysuser primary key sys_user(user_id);

-- 创建索引
create index idx_sysuser_status on sys_user(status);
create index idx_sysuser_createtime on sys_user (create_time);
create index idx_sysuser_validtime on sys_user (valid_time);
create index idx_sysuser_expiretime on sys_user (expire_time);
