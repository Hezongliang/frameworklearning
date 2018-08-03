/*
 * 该表用于存放系统参数信息
 */
drop
	table
		if exists sys_param;

create
	table
		sys_param(
			param_id varchar(60) not null comment '参数ID',
			param_value varchar(60) not null comment '参数值',
			param_name varchar(200) default null comment '参数名称'
		) engine = myisam default charset = utf8 collate = utf8_bin comment = '系统参数表';

-- 创建主键
alter table
	sys_param add constraint pk_sysparam primary key sys_param(param_id);

truncate
	table
		sys_param;

insert
	into
		sys_param(
			param_id,
			param_value,
			param_name
		)
	values(
		'REDIS_SESSION_EXPIRE_TIMEOUT',
		'1800',
		'session有效期'
	);

insert
	into
		sys_param(
			param_id,
			param_value,
			param_name
		)
	values(
		'REDIS_CAPTCHA_EXPIRE_TIMEOUT',
		'600',
		'验证码有效期'
	);

commit;
