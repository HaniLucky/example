/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/12/6 22:35:11                           */
/*==============================================================*/


drop table if exists DEP;

drop table if exists EMP;

drop table if exists EMP_ROLE;

drop table if exists GOODS;

drop table if exists GOODSTYPE;

drop table if exists MENU;

drop table if exists ORDERDETAIL;

drop table if exists ORDERS;

drop table if exists RETURNORDERDETAIL;

drop table if exists RETURNORDERS;

drop table if exists ROLE;

drop table if exists ROLE_MENU;

drop table if exists STORE;

drop table if exists STOREDETAIL;

drop table if exists STOREOPER;

drop table if exists SUPPLIER;

/*==============================================================*/
/* Table: DEP                                                   */
/*==============================================================*/
create table DEP
(
   UUID                 numeric(8,0) not null comment '编号',
   NAME                 varchar(30) comment '名称',
   TELE                 varchar(30) comment '电话'
);

alter table DEP comment '部门';

/*==============================================================*/
/* Table: EMP                                                   */
/*==============================================================*/
create table EMP
(
   UUID                 varchar(32) not null comment '编号',
   USERNAME             varchar(15) comment '登陆名',
   PWD                  varchar(32) comment '密码',
   NAME                 varchar(28) comment '真实姓名',
   GENDER               numeric(8,0) comment '性别',
   EMAIL                varchar(255) comment 'EMAIL',
   TELE                 varchar(30) comment '电话',
   ADDRESS              varchar(255) comment '地址',
   BIRTHDAY             datetime comment '出生年月日',
   DEPUUID              numeric(8,0) comment '部门编号'
);

alter table EMP comment '员工';

/*==============================================================*/
/* Table: EMP_ROLE                                              */
/*==============================================================*/
create table EMP_ROLE
(
   EMPUUID              numeric(8,0) not null,
   ROLEUUID             numeric(8,0) not null,
   primary key (EMPUUID, ROLEUUID)
);

/*==============================================================*/
/* Table: GOODS                                                 */
/*==============================================================*/
create table GOODS
(
   UUID                 numeric(8,0) not null comment '编号',
   NAME                 varchar(30) comment '名称',
   ORIGIN               varchar(30) comment '产地',
   PRODUCER             varchar(30) comment '厂家',
   UNIT                 varchar(30) comment '计量单位',
   INPRICE              numeric(8,2) comment '进货价格',
   OUTPRICE             numeric(8,2) comment '销售价格',
   GOODSTYPEUUID        numeric(8,0) comment '商品类型'
);

alter table GOODS comment '商品';

/*==============================================================*/
/* Table: GOODSTYPE                                             */
/*==============================================================*/
create table GOODSTYPE
(
   UUID                 numeric(8,0) not null comment '编号',
   NAME                 varchar(30) comment '名称'
);

alter table GOODSTYPE comment '商品分类';

/*==============================================================*/
/* Table: MENU                                                  */
/*==============================================================*/
create table MENU
(
   MENUID               varchar(20) not null comment '菜单ID',
   MENUNAME             varchar(30) comment '菜单名称',
   ICON                 varchar(20) comment '图标',
   URL                  varchar(255) comment 'URL',
   PID                  varchar(20) comment '上级菜单ID'
);

alter table MENU comment '菜单';

/*==============================================================*/
/* Table: ORDERDETAIL                                           */
/*==============================================================*/
create table ORDERDETAIL
(
   UUID                 numeric(8,0) not null comment '编号',
   GOODSUUID            numeric(8,0) comment '商品编号',
   GOODSNAME            varchar(50) comment '商品名称',
   PRICE                numeric(10,2) comment '价格',
   NUM                  numeric(8,0) comment '数量',
   MONEY                numeric(10,2) comment '金额',
   ENDTIME              datetime comment '结束日期',
   ENDER                numeric(8,0) comment '库管员',
   STOREUUID            numeric(8,0) comment '仓库编号',
   STATE                char(1) comment '状态',
   ORDERSUUID           numeric(8,0) comment '订单编号'
);

alter table ORDERDETAIL comment '订单明细';

/*==============================================================*/
/* Table: ORDERS                                                */
/*==============================================================*/
create table ORDERS
(
   UUID                 numeric(8,0) not null comment '编号',
   CREATETIME           datetime comment '生成日期',
   CHECKTIME            datetime comment '检查日期',
   STARTTIME            datetime comment '开始日期',
   ENDTIME              datetime comment '结束日期',
   TYPE                 char(1) comment '订单类型',
   CREATER              numeric(8,0) comment '下单员',
   CHECKER              numeric(8,0) comment '审查员',
   STARTER              numeric(8,0) comment '采购员',
   ENDER                numeric(8,0) comment '库管员',
   SUPPLIERUUID         numeric(8,0) comment '供应商ID',
   TOTALMONEY           numeric(10,2) comment '总金额',
   STATE                char(1) comment '订单状态',
   WAYBILLSN            numeric(8,0) comment '运单号'
);

alter table ORDERS comment '订单';

/*==============================================================*/
/* Table: RETURNORDERDETAIL                                     */
/*==============================================================*/
create table RETURNORDERDETAIL
(
   UUID                 numeric(8,0) not null comment '编号',
   GOODSUUID            numeric(8,0) comment '商品编号',
   GOODSNAME            varchar(50) comment '商品名称',
   PRICE                numeric(10,2) comment '价格',
   NUM                  numeric(8,0) comment '数量',
   MONEY                numeric(10,2) comment '金额',
   ENDTIME              datetime comment '结束日期',
   ENDER                numeric(8,0) comment '库管员',
   STOREUUID            numeric(8,0) comment '仓库编号',
   STATE                char(1) comment '状态',
   ORDERSUUID           numeric(8,0) comment '订单编号'
);

alter table RETURNORDERDETAIL comment '退货订单明细';

/*==============================================================*/
/* Table: RETURNORDERS                                          */
/*==============================================================*/
create table RETURNORDERS
(
   UUID                 numeric(8,0) not null comment '编号',
   CREATETIME           datetime comment '生成日期',
   CHECKTIME            datetime comment '检查日期',
   ENDTIME              datetime comment '结束日期',
   TYPE                 char(1) comment '订单类型',
   CREATER              numeric(8,0) comment '下单员',
   CHECKER              numeric(8,0) comment '审查员',
   ENDER                numeric(8,0) comment '库管员',
   SUPPLIERUUID         numeric(8,0) comment '供应商ID',
   TOTALMONEY           numeric(10,2) comment '总金额',
   STATE                char(1) comment '订单状态',
   WAYBILLSN            numeric(8,0) comment '运单号',
   ORDERSUUID           numeric(8,0) comment '原订单编号'
);

alter table RETURNORDERS comment '退货订单';

/*==============================================================*/
/* Table: ROLE                                                  */
/*==============================================================*/
create table ROLE
(
   UUID                 numeric(8,0) not null comment '编号',
   NAME                 varchar(30) comment '名称'
);

alter table ROLE comment '角色';

/*==============================================================*/
/* Table: ROLE_MENU                                             */
/*==============================================================*/
create table ROLE_MENU
(
   ROLEUUID             numeric(8,0) not null,
   MENUUUID             varchar(20) not null,
   primary key (ROLEUUID, MENUUUID)
);

/*==============================================================*/
/* Table: STORE                                                 */
/*==============================================================*/
create table STORE
(
   UUID                 numeric(8,0) not null comment '编号',
   NAME                 varchar(30) comment '名称',
   EMPUUID              numeric(8,0) comment '员工编号'
);

alter table STORE comment '仓库';

/*==============================================================*/
/* Table: STOREDETAIL                                           */
/*==============================================================*/
create table STOREDETAIL
(
   UUID                 numeric(8,0) not null comment '编号',
   STOREUUID            numeric(8,0) comment '仓库编号',
   GOODSUUID            numeric(8,0) comment '商品编号',
   NUM                  numeric(8,0) comment '数量'
);

alter table STOREDETAIL comment '仓库库存';

/*==============================================================*/
/* Table: STOREOPER                                             */
/*==============================================================*/
create table STOREOPER
(
   UUID                 numeric(8,0) not null comment '编号',
   EMPUUID              numeric(8,0) comment '员工编号',
   OPERTIME             datetime comment '操作日期',
   STOREUUID            numeric(8,0) comment '仓库编号',
   GOODSUUID            numeric(8,0) comment '商品编号',
   NUM                  numeric(8,0) comment '数量',
   TYPE                 char(1) comment '类型'
);

alter table STOREOPER comment '仓库操作记录';

/*==============================================================*/
/* Table: SUPPLIER                                              */
/*==============================================================*/
create table SUPPLIER
(
   UUID                 numeric(8,0) not null comment '编号',
   NAME                 varchar(30) comment '名称',
   ADDRESS              varchar(100) comment '地址',
   CONTACT              varchar(30) comment '联系人',
   TELE                 varchar(30) comment '电话',
   EMAIL                varchar(100) comment 'EMAIL',
   TYPE                 char(1) comment '类型'
);

alter table SUPPLIER comment '供应商';

alter table EMP add constraint FK_Reference_1 foreign key (DEPUUID)
      references DEP (UUID) on delete restrict on update restrict;

alter table GOODS add constraint FK_Reference_2 foreign key (GOODSTYPEUUID)
      references GOODSTYPE (UUID) on delete restrict on update restrict;

