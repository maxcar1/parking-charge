DROP TABLE IF EXISTS property_contract;

CREATE TABLE property_contract (
  id varchar(32) NOT NULL COMMENT '商户合同表主键',
  market_id varchar(32) NOT NULL COMMENT '市场id',
  contract_no varchar(40) NOT NULL COMMENT '合同编号',
  tenant_id varchar(32) NOT NULL COMMENT '商户id',
  margin int(10) DEFAULT NULL COMMENT '保证金',
  status int(2) DEFAULT '1' COMMENT '合同状态 1 正常 2 终止 3 撤销',
  contract_remark varchar(50) DEFAULT NULL COMMENT '签订合同备注',
  contract_image varchar(300) DEFAULT NULL COMMENT '租赁合同图片',
  guarantee_image varchar(300) DEFAULT NULL COMMENT '保证书图片',
  commitment_image varchar(300) DEFAULT NULL COMMENT '承诺书图片',
  isvalid int(2) DEFAULT '1' COMMENT '数据有效性 1 有效 0 无效',
  remark varchar(255) DEFAULT NULL COMMENT '备注',
  insert_time timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  update_time datetime DEFAULT NULL COMMENT '最后修改时间',
  insert_operator varchar(32) DEFAULT NULL COMMENT '创建人',
  update_operator varchar(32) DEFAULT NULL COMMENT '最后修改人',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商户合同表';

/*Table structure for table property_contract_detail */

DROP TABLE IF EXISTS property_contract_detail;

CREATE TABLE property_contract_detail (
  id varchar(32) NOT NULL COMMENT '合同详情表数据',
  maxket_id varchar(32) NOT NULL COMMENT '市场id',
  property_contract_id varchar(32) NOT NULL COMMENT '合同主键id',
  tenant_id varchar(32) NOT NULL COMMENT '商户id',
  area_id varchar(32) NOT NULL COMMENT '物业编号',
  contract_category int(2) NOT NULL COMMENT '物业类型 1 车位 2 展厅 3 办公室',
  price int(10) DEFAULT NULL COMMENT '单价',
  preferential_price int(10) DEFAULT NULL COMMENT '优惠后单价',
  type int(2) NOT NULL COMMENT '付款方式  1 月付 2 季付 3 年付',
  status_tme datetime NOT NULL COMMENT '合同开始日期',
  end_time datetime NOT NULL COMMENT '合同结束日期',
  termination_remark varchar(300) DEFAULT NULL COMMENT '终止合同备注',
  termination_message varchar(300) DEFAULT NULL COMMENT '终止合同原因',
  termination_time datetime DEFAULT NULL COMMENT '终止合同时间',
  isvalid int(2) DEFAULT '1' COMMENT '数据有效性 1 有效 0 无效',
  remark varchar(255) DEFAULT NULL COMMENT '备注',
  insert_time timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time datetime DEFAULT NULL COMMENT '最后更新时间',
  insert_operator varchar(32) DEFAULT NULL COMMENT '创建人',
  update_operator varchar(32) DEFAULT NULL COMMENT '最后修改人',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='合同详情';

/*Table structure for table property_contract_pay */

DROP TABLE IF EXISTS property_contract_pay;

CREATE TABLE property_contract_pay (
  id varchar(32) NOT NULL COMMENT '合同缴费列表',
  maxket_id varchar(32) NOT NULL COMMENT '市场ID',
  property_contract_id varchar(32) NOT NULL COMMENT '合同id',
  tenact_id varchar(32) NOT NULL COMMENT '商户id',
  status int(2) NOT NULL DEFAULT '1' COMMENT '缴费状态 1 未缴清 2 已缴清',
  price int(10) NOT NULL COMMENT '待缴纳金额',
  isvalid int(2) DEFAULT '1' COMMENT '数据有效性 1 有效 0 无效',
  remark varchar(255) DEFAULT NULL COMMENT '备注',
  insert_time timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  update_time datetime DEFAULT NULL,
  insert_operator varchar(32) DEFAULT NULL,
  update_operator varchar(32) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='合同缴费表';

/*Table structure for table property_contract_pay_detail */

DROP TABLE IF EXISTS property_contract_pay_detail;

CREATE TABLE property_contract_pay_detail (
  id varchar(32) NOT NULL COMMENT '合同缴费记录id',
  maxket_id varchar(32) NOT NULL COMMENT '市场ID',
  property_contract_id varchar(32) NOT NULL COMMENT '合同ID',
  property_contract_detail varchar(32) NOT NULL COMMENT '合同详情id',
  pay_time datetime DEFAULT NULL COMMENT '缴费日期',
  status varchar(32) DEFAULT NULL COMMENT '缴费状态 1 未缴纳 2 未付清 3 已付清',
  pay_price int(10) DEFAULT NULL COMMENT '已缴纳金额',
  isvalid int(2) DEFAULT '1',
  remark varchar(255) DEFAULT NULL,
  insert_time timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  update_time datetime DEFAULT NULL,
  insert_operator varchar(32) DEFAULT NULL,
  update_operator varchar(32) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='合同缴费记录表';


/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/8/15 14:52:52                           */
/*==============================================================*/


drop table if exists area;
/*==============================================================*/
/* Table: area                                                  */
/*==============================================================*/
create table area
(
   id                   varchar(32) not null,
   name                 varchar(50) comment '区域名称',
   type                 int(2) comment '物业类型(0:展厅  1:车位  2:办公室)',
   area                 double(10,3) comment '区域面积',
   remarks              varchar(255) comment '备注',
   isvalid              int(2) default 1 comment '数据有效性 0 无效 1 有效',
   insert_time          timestamp default CURRENT_TIMESTAMP comment '创建时间',
   update_time          datetime comment '修改时间',
   insert_operator      varchar(32) comment '添加人',
   update_operator      varchar(32) comment '修改人',
   primary key (id)
);

alter table area comment '区域管理';

/*==============================================================*/
/* Table: area_shop                                             */
/*==============================================================*/
drop table if exists area_shop;
create table area_shop
(
   id                   int(32) not null,
   area_id              varchar(32) not null comment '区域id',
   area_no              varchar(50) comment '编号',
   park_space           int(10) comment '车位',
   rent_status          int(2) comment '租赁情况（0 ：未租  1：已租）',
   remarks              varchar(255) comment '备注',
   isvalid              int(2) default 1 comment '数据有效性 0 无效 1 有效',
   insert_time          timestamp default CURRENT_TIMESTAMP comment '创建时间',
   update_time          datetime comment '最后修改时间',
   insert_operator      varchar(32) comment '添加人',
   update_operator      varchar(32) comment '修改人',
   primary key (id)
);

alter table area_shop comment '商铺表';

/*==============================================================*/
/* Table: car_channel                                           */
/*==============================================================*/

drop table if exists car_channel;
create table car_channel
(
   id                   varchar(32) not null,
   channel_no           varchar(50) comment '渠道编号',
   channel_name         varchar(100) comment '渠道名称',
   remarks              varchar(255) comment '备注',
   isvalid              int(2) default 1 comment '数据有效性 1 有效 0 无效',
   insert_time          timestamp default CURRENT_TIMESTAMP comment '创建时间',
   update_time          datetime comment '修改时间',
   insert_operator      varchar(32) comment '添加人',
   update_operator      varchar(32) comment '修改人',
   primary key (id)
);


drop table if exists ele_label;
/*==============================================================*/
/* Table: ele_label                                             */
/*==============================================================*/
create table ele_label
(
   id                   varchar(32) not null comment '主键id',
   use_person           varchar(32) comment '领用人',
   use_time             datetime comment '领用时间',
   use_number           integer comment '领用张数',
   used_number          integer comment '已用张数',
   is_valid             int(1) default 1 comment '是否可用,0不可用;1可用',
   scrap_number         integer comment '报废张数',
   insert_time          timestamp default CURRENT_TIMESTAMP comment '新增时间',
   insert_operator      varchar(32) comment '新增人',
   update_time          datetime comment '更新时间',
   update_operator      varchar(32) comment '更新人',
   remark               varchar(255) comment '备注',
primary key (id)
);
alter table area_shop comment '电子标签';



drop table if exists ele_label_record;
/*==============================================================*/
/* Table: ele_label_record                                      */
/*==============================================================*/
create table ele_label_record
(
   id                 varchar(32) not null comment '主键id',
   car_number           varchar(20) comment '车辆编号',
   vin                  varchar(20) comment '车架号',
   owned_tenant         varchar(32) comment '所属商户',
   old_rfid             varchar(50) comment '原RFID',
   new_rfid             varchar(50) comment '新RFID',
   operation_time       datetime comment '操作时间',
   operator             varchar(32) comment '操作人',
   is_valid             int(1) default 1 comment '是否可用,0不可用;1可用',
   insert_time          timestamp default CURRENT_TIMESTAMP comment '新增时间',
   insert_operator      varchar(32) comment '新增人',
   remark               varchar(255) comment '备注',
	primary key (id)
);
alter table area_shop comment '电子标签记录表';

