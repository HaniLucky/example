CREATE DATABASE ssm;
USE ssm;
CREATE TABLE `co_user` (
  `c_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `c_name` varchar(255) DEFAULT NULL COMMENT '用户名称',
  `c_sex` varchar(255) DEFAULT NULL COMMENT '用户性别',
  `c_birthday` varchar(255) DEFAULT NULL COMMENT '出生日期',
  `c_create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `c_modify_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`c_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

