/*
Navicat MySQL Data Transfer

Source Server         : 144.34.172.1252
Source Server Version : 50557
Source Host           : 144.34.172.125:3306
Source Database       : erp2

Target Server Type    : MYSQL
Target Server Version : 50557
File Encoding         : 65001

Date: 2019-01-11 15:36:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dep
-- ----------------------------
DROP TABLE IF EXISTS `dep`;
CREATE TABLE `dep` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `NAME` varchar(30) DEFAULT NULL COMMENT '名称',
  `TELE` varchar(30) DEFAULT NULL COMMENT '电话',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='部门';

-- ----------------------------
-- Records of dep
-- ----------------------------
INSERT INTO `dep` VALUES ('1', '研发部', '10086');

-- ----------------------------
-- Table structure for emp
-- ----------------------------
DROP TABLE IF EXISTS `emp`;
CREATE TABLE `emp` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `USERNAME` varchar(15) DEFAULT NULL COMMENT '登陆名',
  `PWD` varchar(32) DEFAULT NULL COMMENT '密码',
  `NAME` varchar(28) DEFAULT NULL COMMENT '真实姓名',
  `GENDER` decimal(8,0) DEFAULT NULL COMMENT '性别',
  `EMAIL` varchar(255) DEFAULT NULL COMMENT 'EMAIL',
  `TELE` varchar(30) DEFAULT NULL COMMENT '电话',
  `ADDRESS` varchar(255) DEFAULT NULL COMMENT '地址',
  `BIRTHDAY` datetime DEFAULT NULL COMMENT '出生年月日',
  `DEPUUID` decimal(8,0) DEFAULT NULL COMMENT '部门编号',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='员工';

-- ----------------------------
-- Records of emp
-- ----------------------------
INSERT INTO `emp` VALUES ('1', 'admin', '928bfd2577490322a6e19b793691467e', 'Admin', '1', '1178391126@qq.com', '10086', '杭州', '2018-12-31 08:00:00', '1');
INSERT INTO `emp` VALUES ('2', 'root', '88aec6f4d07b756329e42eb50c4688dd', 'Root', '1', 'root@163.com', '10000', '北京', '2018-11-28 08:00:00', '1');

-- ----------------------------
-- Table structure for emp_role
-- ----------------------------
DROP TABLE IF EXISTS `emp_role`;
CREATE TABLE `emp_role` (
  `EMPUUID` int(11) NOT NULL,
  `ROLEUUID` int(11) NOT NULL,
  PRIMARY KEY (`EMPUUID`,`ROLEUUID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of emp_role
-- ----------------------------
INSERT INTO `emp_role` VALUES ('1', '1');
INSERT INTO `emp_role` VALUES ('2', '2');

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `NAME` varchar(30) DEFAULT NULL COMMENT '名称',
  `ORIGIN` varchar(30) DEFAULT NULL COMMENT '产地',
  `PRODUCER` varchar(30) DEFAULT NULL COMMENT '厂家',
  `UNIT` varchar(30) DEFAULT NULL COMMENT '计量单位',
  `INPRICE` decimal(8,2) DEFAULT NULL COMMENT '进货价格',
  `OUTPRICE` decimal(8,2) DEFAULT NULL COMMENT '销售价格',
  `GOODSTYPEUUID` decimal(8,0) DEFAULT NULL COMMENT '商品类型',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='商品';

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('2', 'java', '杭州', '杭州', '吨', '1.00', '15.00', '1');

-- ----------------------------
-- Table structure for goodstype
-- ----------------------------
DROP TABLE IF EXISTS `goodstype`;
CREATE TABLE `goodstype` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `NAME` varchar(30) DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='商品分类';

-- ----------------------------
-- Records of goodstype
-- ----------------------------
INSERT INTO `goodstype` VALUES ('1', '商品');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `MENUID` varchar(20) NOT NULL COMMENT '菜单ID',
  `MENUNAME` varchar(30) DEFAULT NULL COMMENT '菜单名称',
  `ICON` varchar(20) DEFAULT NULL COMMENT '图标',
  `URL` varchar(255) DEFAULT NULL COMMENT 'URL',
  `PID` varchar(20) DEFAULT NULL COMMENT '上级菜单ID',
  PRIMARY KEY (`MENUID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单';

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('0', '系统菜单', 'icon-sys', '-', '-1');
INSERT INTO `menu` VALUES ('100', '基础数据', 'icon-sys', '-', '0');
INSERT INTO `menu` VALUES ('101', '商品类型', 'icon-sys', 'goodstype.html', '100');
INSERT INTO `menu` VALUES ('102', '商品', 'icon-sys', 'goods.html', '100');
INSERT INTO `menu` VALUES ('103', '供应商', 'icon-sys', 'supplier.html?type=1', '100');
INSERT INTO `menu` VALUES ('104', '客户', 'icon-sys', 'supplier.html?type=2', '100');
INSERT INTO `menu` VALUES ('105', '仓库', 'icon-sys', 'store.html', '100');
INSERT INTO `menu` VALUES ('106', '供应商及客户管理', 'icon-sys', 'supplier.html', '100');
INSERT INTO `menu` VALUES ('200', '人事管理', 'icon-sys', '-', '0');
INSERT INTO `menu` VALUES ('201', '部门', 'icon-sys', 'dep.html', '200');
INSERT INTO `menu` VALUES ('202', '员工', 'icon-sys', 'emp.html', '200');
INSERT INTO `menu` VALUES ('300', '采购管理', 'icon-sys', '-', '0');
INSERT INTO `menu` VALUES ('301', '采购订单查询', 'icon-sys', 'orders.html?operation=order&type=1', '300');
INSERT INTO `menu` VALUES ('302', '采购申请', 'icon-sys', 'orders.html?operation=myorders&type=1', '300');
INSERT INTO `menu` VALUES ('303', '采购审核', 'icon-sys', 'orders.html?operation=check&type=1', '300');
INSERT INTO `menu` VALUES ('304', '采购确认', 'icon-sys', 'orders.html?operation=start&type=1', '300');
INSERT INTO `menu` VALUES ('305', '采购入库', 'icon-sys', 'orders.html?operation=instore&type=1', '300');
INSERT INTO `menu` VALUES ('400', '权限设置', 'icon-sys', '-', '0');
INSERT INTO `menu` VALUES ('401', '菜单设置', 'icon-sys', 'menu.html', '400');
INSERT INTO `menu` VALUES ('402', '角色设置', 'icon-sys', 'role.html', '400');
INSERT INTO `menu` VALUES ('403', '员工角色设置', 'icon-sys', 'empRoleSet.html', '400');
INSERT INTO `menu` VALUES ('404', '角色菜单设置', 'icon-sys', 'roleMenuSet.html', '400');
INSERT INTO `menu` VALUES ('405', '管理员重置密码', 'icon-sys', 'pwd.html', '400');
INSERT INTO `menu` VALUES ('500', '销售管理', 'icon-sys', '-', '0');
INSERT INTO `menu` VALUES ('501', '销售订单查询', 'icon-sys', 'orders.html?operation=order&type=2', '500');
INSERT INTO `menu` VALUES ('502', '销售订单录入', 'icon-sys', 'orders.html?operation=myorders&type=2', '500');

-- ----------------------------
-- Table structure for orderdetail
-- ----------------------------
DROP TABLE IF EXISTS `orderdetail`;
CREATE TABLE `orderdetail` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `GOODSUUID` decimal(8,0) DEFAULT NULL COMMENT '商品编号',
  `GOODSNAME` varchar(50) DEFAULT NULL COMMENT '商品名称',
  `PRICE` decimal(10,2) DEFAULT NULL COMMENT '价格',
  `NUM` decimal(8,0) DEFAULT NULL COMMENT '数量',
  `MONEY` decimal(10,2) DEFAULT NULL COMMENT '金额',
  `ENDTIME` datetime DEFAULT NULL COMMENT '结束日期',
  `ENDER` decimal(8,0) DEFAULT NULL COMMENT '库管员',
  `STOREUUID` decimal(8,0) DEFAULT NULL COMMENT '仓库编号',
  `STATE` char(1) DEFAULT NULL COMMENT '状态',
  `ORDERSUUID` decimal(8,0) DEFAULT NULL COMMENT '订单编号',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='订单明细';

-- ----------------------------
-- Records of orderdetail
-- ----------------------------
INSERT INTO `orderdetail` VALUES ('1', null, 'q', null, null, null, null, null, null, null, null);
INSERT INTO `orderdetail` VALUES ('2', '2', 'java', '13.00', '112', '1456.00', null, null, null, '0', '8');
INSERT INTO `orderdetail` VALUES ('3', '2', 'java', '13.00', '112', '1456.00', null, null, null, '0', '8');
INSERT INTO `orderdetail` VALUES ('4', '2', 'java', '12.00', '12', '144.00', null, null, null, '0', '9');
INSERT INTO `orderdetail` VALUES ('5', '2', 'java', '123.00', '23', '2829.00', null, null, null, '0', '9');
INSERT INTO `orderdetail` VALUES ('6', '2', 'java', '1.00', '12', '12.00', null, null, null, '0', '10');
INSERT INTO `orderdetail` VALUES ('7', '2', 'java', '1.00', '23', '23.00', null, null, null, '0', '10');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `CREATETIME` datetime DEFAULT NULL COMMENT '生成日期',
  `CHECKTIME` datetime DEFAULT NULL COMMENT '检查日期',
  `STARTTIME` datetime DEFAULT NULL COMMENT '开始日期',
  `ENDTIME` datetime DEFAULT NULL COMMENT '结束日期',
  `TYPE` char(1) DEFAULT NULL COMMENT '订单类型',
  `CREATER` decimal(8,0) DEFAULT NULL COMMENT '下单员',
  `CHECKER` decimal(8,0) DEFAULT NULL COMMENT '审查员',
  `STARTER` decimal(8,0) DEFAULT NULL COMMENT '采购员',
  `ENDER` decimal(8,0) DEFAULT NULL COMMENT '库管员',
  `SUPPLIERUUID` decimal(8,0) DEFAULT NULL COMMENT '供应商ID',
  `TOTALMONEY` decimal(10,2) DEFAULT NULL COMMENT '总金额',
  `STATE` char(1) DEFAULT NULL COMMENT '订单状态',
  `WAYBILLSN` decimal(8,0) DEFAULT NULL COMMENT '运单号',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='订单';

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('2', '2018-12-23 21:03:51', null, null, null, '1', '1', null, null, null, null, '35.00', '0', null);
INSERT INTO `orders` VALUES ('3', '2018-12-23 18:41:09', null, null, null, '1', '1', null, null, null, null, '264.00', '0', null);
INSERT INTO `orders` VALUES ('4', '2018-12-23 18:44:49', null, null, null, '1', '1', null, null, null, null, '1599.00', '0', null);
INSERT INTO `orders` VALUES ('9', '2018-12-23 19:22:35', null, null, null, '1', '1', null, null, null, null, '2973.00', '0', null);

-- ----------------------------
-- Table structure for returnorderdetail
-- ----------------------------
DROP TABLE IF EXISTS `returnorderdetail`;
CREATE TABLE `returnorderdetail` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `GOODSUUID` decimal(8,0) DEFAULT NULL COMMENT '商品编号',
  `GOODSNAME` varchar(50) DEFAULT NULL COMMENT '商品名称',
  `PRICE` decimal(10,2) DEFAULT NULL COMMENT '价格',
  `NUM` decimal(8,0) DEFAULT NULL COMMENT '数量',
  `MONEY` decimal(10,2) DEFAULT NULL COMMENT '金额',
  `ENDTIME` datetime DEFAULT NULL COMMENT '结束日期',
  `ENDER` decimal(8,0) DEFAULT NULL COMMENT '库管员',
  `STOREUUID` decimal(8,0) DEFAULT NULL COMMENT '仓库编号',
  `STATE` char(1) DEFAULT NULL COMMENT '状态',
  `ORDERSUUID` decimal(8,0) DEFAULT NULL COMMENT '订单编号',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='退货订单明细';

-- ----------------------------
-- Records of returnorderdetail
-- ----------------------------

-- ----------------------------
-- Table structure for returnorders
-- ----------------------------
DROP TABLE IF EXISTS `returnorders`;
CREATE TABLE `returnorders` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `CREATETIME` datetime DEFAULT NULL COMMENT '生成日期',
  `CHECKTIME` datetime DEFAULT NULL COMMENT '检查日期',
  `ENDTIME` datetime DEFAULT NULL COMMENT '结束日期',
  `TYPE` char(1) DEFAULT NULL COMMENT '订单类型',
  `CREATER` decimal(8,0) DEFAULT NULL COMMENT '下单员',
  `CHECKER` decimal(8,0) DEFAULT NULL COMMENT '审查员',
  `ENDER` decimal(8,0) DEFAULT NULL COMMENT '库管员',
  `SUPPLIERUUID` decimal(8,0) DEFAULT NULL COMMENT '供应商ID',
  `TOTALMONEY` decimal(10,2) DEFAULT NULL COMMENT '总金额',
  `STATE` char(1) DEFAULT NULL COMMENT '订单状态',
  `WAYBILLSN` decimal(8,0) DEFAULT NULL COMMENT '运单号',
  `ORDERSUUID` decimal(8,0) DEFAULT NULL COMMENT '原订单编号',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='退货订单';

-- ----------------------------
-- Records of returnorders
-- ----------------------------

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `NAME` varchar(30) DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '超级管理员');
INSERT INTO `role` VALUES ('2', '测试');

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
  `ROLEUUID` int(11) NOT NULL,
  `MENUUUID` varchar(20) NOT NULL,
  PRIMARY KEY (`ROLEUUID`,`MENUUUID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES ('1', '100');
INSERT INTO `role_menu` VALUES ('1', '101');
INSERT INTO `role_menu` VALUES ('1', '102');
INSERT INTO `role_menu` VALUES ('1', '103');
INSERT INTO `role_menu` VALUES ('1', '104');
INSERT INTO `role_menu` VALUES ('1', '105');
INSERT INTO `role_menu` VALUES ('1', '106');
INSERT INTO `role_menu` VALUES ('1', '200');
INSERT INTO `role_menu` VALUES ('1', '201');
INSERT INTO `role_menu` VALUES ('1', '202');
INSERT INTO `role_menu` VALUES ('1', '300');
INSERT INTO `role_menu` VALUES ('1', '301');
INSERT INTO `role_menu` VALUES ('1', '302');
INSERT INTO `role_menu` VALUES ('1', '303');
INSERT INTO `role_menu` VALUES ('1', '304');
INSERT INTO `role_menu` VALUES ('1', '305');
INSERT INTO `role_menu` VALUES ('1', '400');
INSERT INTO `role_menu` VALUES ('1', '401');
INSERT INTO `role_menu` VALUES ('1', '402');
INSERT INTO `role_menu` VALUES ('1', '403');
INSERT INTO `role_menu` VALUES ('1', '404');
INSERT INTO `role_menu` VALUES ('1', '405');
INSERT INTO `role_menu` VALUES ('1', '500');
INSERT INTO `role_menu` VALUES ('1', '501');
INSERT INTO `role_menu` VALUES ('1', '502');
INSERT INTO `role_menu` VALUES ('2', '100');
INSERT INTO `role_menu` VALUES ('2', '101');
INSERT INTO `role_menu` VALUES ('2', '102');
INSERT INTO `role_menu` VALUES ('2', '103');
INSERT INTO `role_menu` VALUES ('2', '104');
INSERT INTO `role_menu` VALUES ('2', '105');
INSERT INTO `role_menu` VALUES ('2', '200');
INSERT INTO `role_menu` VALUES ('2', '201');
INSERT INTO `role_menu` VALUES ('2', '202');

-- ----------------------------
-- Table structure for store
-- ----------------------------
DROP TABLE IF EXISTS `store`;
CREATE TABLE `store` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `NAME` varchar(30) DEFAULT NULL COMMENT '名称',
  `EMPUUID` decimal(8,0) DEFAULT NULL COMMENT '员工编号',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='仓库';

-- ----------------------------
-- Records of store
-- ----------------------------
INSERT INTO `store` VALUES ('2', '2号库', '2');

-- ----------------------------
-- Table structure for storedetail
-- ----------------------------
DROP TABLE IF EXISTS `storedetail`;
CREATE TABLE `storedetail` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `STOREUUID` decimal(8,0) DEFAULT NULL COMMENT '仓库编号',
  `GOODSUUID` decimal(8,0) DEFAULT NULL COMMENT '商品编号',
  `NUM` decimal(8,0) DEFAULT NULL COMMENT '数量',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='仓库库存';

-- ----------------------------
-- Records of storedetail
-- ----------------------------

-- ----------------------------
-- Table structure for storeoper
-- ----------------------------
DROP TABLE IF EXISTS `storeoper`;
CREATE TABLE `storeoper` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `EMPUUID` decimal(8,0) DEFAULT NULL COMMENT '员工编号',
  `OPERTIME` datetime DEFAULT NULL COMMENT '操作日期',
  `STOREUUID` decimal(8,0) DEFAULT NULL COMMENT '仓库编号',
  `GOODSUUID` decimal(8,0) DEFAULT NULL COMMENT '商品编号',
  `NUM` decimal(8,0) DEFAULT NULL COMMENT '数量',
  `TYPE` char(1) DEFAULT NULL COMMENT '类型',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='仓库操作记录';

-- ----------------------------
-- Records of storeoper
-- ----------------------------

-- ----------------------------
-- Table structure for supplier
-- ----------------------------
DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `NAME` varchar(30) DEFAULT NULL COMMENT '名称',
  `ADDRESS` varchar(100) DEFAULT NULL COMMENT '地址',
  `CONTACT` varchar(30) DEFAULT NULL COMMENT '联系人',
  `TELE` varchar(30) DEFAULT NULL COMMENT '电话',
  `EMAIL` varchar(100) DEFAULT NULL COMMENT 'EMAIL',
  `TYPE` char(1) DEFAULT NULL COMMENT '类型',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='供应商';

-- ----------------------------
-- Records of supplier
-- ----------------------------
INSERT INTO `supplier` VALUES ('1', '安能物流', '杭州', '胡良俊', '1', '1178391126@qq.com', '1');
INSERT INTO `supplier` VALUES ('2', '胡良俊', '杭州', '10086', '2', '10086@qq.com', '2');
