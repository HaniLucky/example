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
   UUID                 numeric(8,0) not null comment '���',
   NAME                 varchar(30) comment '����',
   TELE                 varchar(30) comment '�绰'
);

alter table DEP comment '����';

/*==============================================================*/
/* Table: EMP                                                   */
/*==============================================================*/
create table EMP
(
   UUID                 varchar(32) not null comment '���',
   USERNAME             varchar(15) comment '��½��',
   PWD                  varchar(32) comment '����',
   NAME                 varchar(28) comment '��ʵ����',
   GENDER               numeric(8,0) comment '�Ա�',
   EMAIL                varchar(255) comment 'EMAIL',
   TELE                 varchar(30) comment '�绰',
   ADDRESS              varchar(255) comment '��ַ',
   BIRTHDAY             datetime comment '����������',
   DEPUUID              numeric(8,0) comment '���ű��'
);

alter table EMP comment 'Ա��';

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
   UUID                 numeric(8,0) not null comment '���',
   NAME                 varchar(30) comment '����',
   ORIGIN               varchar(30) comment '����',
   PRODUCER             varchar(30) comment '����',
   UNIT                 varchar(30) comment '������λ',
   INPRICE              numeric(8,2) comment '�����۸�',
   OUTPRICE             numeric(8,2) comment '���ۼ۸�',
   GOODSTYPEUUID        numeric(8,0) comment '��Ʒ����'
);

alter table GOODS comment '��Ʒ';

/*==============================================================*/
/* Table: GOODSTYPE                                             */
/*==============================================================*/
create table GOODSTYPE
(
   UUID                 numeric(8,0) not null comment '���',
   NAME                 varchar(30) comment '����'
);

alter table GOODSTYPE comment '��Ʒ����';

/*==============================================================*/
/* Table: MENU                                                  */
/*==============================================================*/
create table MENU
(
   MENUID               varchar(20) not null comment '�˵�ID',
   MENUNAME             varchar(30) comment '�˵�����',
   ICON                 varchar(20) comment 'ͼ��',
   URL                  varchar(255) comment 'URL',
   PID                  varchar(20) comment '�ϼ��˵�ID'
);

alter table MENU comment '�˵�';

/*==============================================================*/
/* Table: ORDERDETAIL                                           */
/*==============================================================*/
create table ORDERDETAIL
(
   UUID                 numeric(8,0) not null comment '���',
   GOODSUUID            numeric(8,0) comment '��Ʒ���',
   GOODSNAME            varchar(50) comment '��Ʒ����',
   PRICE                numeric(10,2) comment '�۸�',
   NUM                  numeric(8,0) comment '����',
   MONEY                numeric(10,2) comment '���',
   ENDTIME              datetime comment '��������',
   ENDER                numeric(8,0) comment '���Ա',
   STOREUUID            numeric(8,0) comment '�ֿ���',
   STATE                char(1) comment '״̬',
   ORDERSUUID           numeric(8,0) comment '�������'
);

alter table ORDERDETAIL comment '������ϸ';

/*==============================================================*/
/* Table: ORDERS                                                */
/*==============================================================*/
create table ORDERS
(
   UUID                 numeric(8,0) not null comment '���',
   CREATETIME           datetime comment '��������',
   CHECKTIME            datetime comment '�������',
   STARTTIME            datetime comment '��ʼ����',
   ENDTIME              datetime comment '��������',
   TYPE                 char(1) comment '��������',
   CREATER              numeric(8,0) comment '�µ�Ա',
   CHECKER              numeric(8,0) comment '���Ա',
   STARTER              numeric(8,0) comment '�ɹ�Ա',
   ENDER                numeric(8,0) comment '���Ա',
   SUPPLIERUUID         numeric(8,0) comment '��Ӧ��ID',
   TOTALMONEY           numeric(10,2) comment '�ܽ��',
   STATE                char(1) comment '����״̬',
   WAYBILLSN            numeric(8,0) comment '�˵���'
);

alter table ORDERS comment '����';

/*==============================================================*/
/* Table: RETURNORDERDETAIL                                     */
/*==============================================================*/
create table RETURNORDERDETAIL
(
   UUID                 numeric(8,0) not null comment '���',
   GOODSUUID            numeric(8,0) comment '��Ʒ���',
   GOODSNAME            varchar(50) comment '��Ʒ����',
   PRICE                numeric(10,2) comment '�۸�',
   NUM                  numeric(8,0) comment '����',
   MONEY                numeric(10,2) comment '���',
   ENDTIME              datetime comment '��������',
   ENDER                numeric(8,0) comment '���Ա',
   STOREUUID            numeric(8,0) comment '�ֿ���',
   STATE                char(1) comment '״̬',
   ORDERSUUID           numeric(8,0) comment '�������'
);

alter table RETURNORDERDETAIL comment '�˻�������ϸ';

/*==============================================================*/
/* Table: RETURNORDERS                                          */
/*==============================================================*/
create table RETURNORDERS
(
   UUID                 numeric(8,0) not null comment '���',
   CREATETIME           datetime comment '��������',
   CHECKTIME            datetime comment '�������',
   ENDTIME              datetime comment '��������',
   TYPE                 char(1) comment '��������',
   CREATER              numeric(8,0) comment '�µ�Ա',
   CHECKER              numeric(8,0) comment '���Ա',
   ENDER                numeric(8,0) comment '���Ա',
   SUPPLIERUUID         numeric(8,0) comment '��Ӧ��ID',
   TOTALMONEY           numeric(10,2) comment '�ܽ��',
   STATE                char(1) comment '����״̬',
   WAYBILLSN            numeric(8,0) comment '�˵���',
   ORDERSUUID           numeric(8,0) comment 'ԭ�������'
);

alter table RETURNORDERS comment '�˻�����';

/*==============================================================*/
/* Table: ROLE                                                  */
/*==============================================================*/
create table ROLE
(
   UUID                 numeric(8,0) not null comment '���',
   NAME                 varchar(30) comment '����'
);

alter table ROLE comment '��ɫ';

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
   UUID                 numeric(8,0) not null comment '���',
   NAME                 varchar(30) comment '����',
   EMPUUID              numeric(8,0) comment 'Ա�����'
);

alter table STORE comment '�ֿ�';

/*==============================================================*/
/* Table: STOREDETAIL                                           */
/*==============================================================*/
create table STOREDETAIL
(
   UUID                 numeric(8,0) not null comment '���',
   STOREUUID            numeric(8,0) comment '�ֿ���',
   GOODSUUID            numeric(8,0) comment '��Ʒ���',
   NUM                  numeric(8,0) comment '����'
);

alter table STOREDETAIL comment '�ֿ���';

/*==============================================================*/
/* Table: STOREOPER                                             */
/*==============================================================*/
create table STOREOPER
(
   UUID                 numeric(8,0) not null comment '���',
   EMPUUID              numeric(8,0) comment 'Ա�����',
   OPERTIME             datetime comment '��������',
   STOREUUID            numeric(8,0) comment '�ֿ���',
   GOODSUUID            numeric(8,0) comment '��Ʒ���',
   NUM                  numeric(8,0) comment '����',
   TYPE                 char(1) comment '����'
);

alter table STOREOPER comment '�ֿ������¼';

/*==============================================================*/
/* Table: SUPPLIER                                              */
/*==============================================================*/
create table SUPPLIER
(
   UUID                 numeric(8,0) not null comment '���',
   NAME                 varchar(30) comment '����',
   ADDRESS              varchar(100) comment '��ַ',
   CONTACT              varchar(30) comment '��ϵ��',
   TELE                 varchar(30) comment '�绰',
   EMAIL                varchar(100) comment 'EMAIL',
   TYPE                 char(1) comment '����'
);

alter table SUPPLIER comment '��Ӧ��';

alter table EMP add constraint FK_Reference_1 foreign key (DEPUUID)
      references DEP (UUID) on delete restrict on update restrict;

alter table GOODS add constraint FK_Reference_2 foreign key (GOODSTYPEUUID)
      references GOODSTYPE (UUID) on delete restrict on update restrict;

