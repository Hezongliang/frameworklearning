-- root用户密码：ztesoft

-- 创建应用用户
create user 'ztesoft'@'%' identified by 'ztesoft';
flush privileges;

-- 创建数据库
create database frameworklearning default character set utf8 collate utf8_bin;

-- 用户赋权
grant all on frameworklearning.* to 'ztesoft'@'%';
flush privileges;