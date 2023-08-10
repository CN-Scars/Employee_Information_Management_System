-- 部门管理
create table dept
(
    id          int unsigned primary key auto_increment comment '主键ID',
    name        varchar(10) not null unique comment '部门名称',
    create_time datetime    not null comment '创建时间',
    update_time datetime    not null comment '修改时间'
) comment '部门表';

-- 员工管理(带约束)
create table emp
(
    id          int unsigned primary key auto_increment comment 'ID',
    username    varchar(20) not null unique comment '用户名',
    password    varchar(32) default '123456' comment '密码',
    name        varchar(10) not null comment '姓名',
    gender      tinyint unsigned not null comment '性别, 说明: 1 男, 2 女',
    image       varchar(300) comment '图像',
    job         tinyint unsigned comment '职位, 说明: 1 班主任,2 讲师, 3 学工主管, 4 教研主管, 5 咨询师',
    entrydate   date comment '入职时间',
    dept_id     int unsigned comment '部门ID',
    create_time datetime    not null comment '创建时间',
    update_time datetime    not null comment '修改时间'
) comment '员工表';

-- 部门信息操作管理
create table dept_log
(
    id          int auto_increment comment '主键ID' primary key,
    create_time datetime null comment '操作时间',
    description varchar(300) null comment '操作描述'
) comment '部门操作日志表';

-- 操作日志表
create table operate_log
(
    id            int unsigned primary key auto_increment comment 'ID',
    operate_user  int unsigned comment '操作人ID',
    operate_time  datetime comment '操作时间',
    class_name    varchar(100) comment '操作的类名',
    method_name   varchar(100) comment '操作的方法名',
    method_params varchar(1000) comment '方法参数',
    return_value  varchar(2000) comment '返回值',
    cost_time     bigint comment '方法执行耗时, 单位:ms'
) comment '操作日志表';
