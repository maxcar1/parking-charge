drop table if exists integral_datil;
create table integral_datil
(
   id                   varchar(32) not null,
   tenant_no            varchar(32) comment '商户号',
   tenant_name          varchar(100) comment '商户名称',
   check_score          double DEFAULT 0 comment '考核分数',
   check_items          varchar(200) comment '考核事项',
   check_time           date comment '考核时间',
   market_id            varchar(32) comment '市场',
   region               varchar(20) comment '所属区域',
   check_id             varchar(32),
   type                 int(1),
   base_score           int(11),
   status               varchar(5),
   remark               varchar(255) comment '备注',
   isvalid              int(1) default 1  comment  '1-有效，0-失效，不能再使用，只作为历史信息保存',
   insert_time          timestamp DEFAULT CURRENT_TIMESTAMP,
   insert_operator      varchar(32),
   update_time          datetime,
   update_operator      varchar(32),
   primary key (id)
);

alter table integral_datil comment '积分详情表';


drop table if exists integral_evaluation;
create table integral_evaluation
(
   id                   varchar(32) not null,
   check_items          varchar(200) comment '具体标准',
   scores_add_subtract  int(1),
   scope_up             varchar(20) comment '分数范围',
   scope_down           varchar(20),
   market_id            varchar(32),
   status               int(1) comment '状态，预留' ,
   remark               varchar(255) comment '备注',
   isvalid              int(1) default 1  comment  '1-有效，0-失效，不能再使用，只作为历史信息保存',
   insert_time          timestamp DEFAULT CURRENT_TIMESTAMP,
   insert_operator      varchar(32),
   update_time          datetime,
   update_operator      varchar(32),
   primary key (id)
);

alter table integral_evaluation comment '积分规则表';


drop table if exists integral_statistics;

create table integral_statistics
(
   id                   varchar(32) not null,
   tenant_no            varchar(20),
   tenant_name          varchar(50),
   time                 date,
   count_score          int(11),
   area                 varchar(20),
   level                varchar(15),
   market_id            varchar(32),
   isvalid              int(1) default 1  comment  '1-有效，0-失效，不能再使用，只作为历史信息保存',
   remark               varchar(255) comment '备注',
   insert_time          timestamp DEFAULT CURRENT_TIMESTAMP,
   insert_operator      varchar(32),
   update_time          datetime,
   update_operator      varchar(32),
   primary key (id)
);

alter table integral_statistics comment '积分统计表';



drop table if exists tenant_shop;

create table tenant_shop
(
   id                   varchar(32) not null,
   tenant_no            varchar(32),
   tenant_name          varchar(20),
   shop_no              varchar(32),
   shop_name            varchar(20),
   shop_area            varchar(20),
   property_type        varchar(20),
   area_area            varchar(20),
   isvalid              int(1) default 1  comment  '1-有效，0-失效，不能再使用，只作为历史信息保存',
   remark               varchar(255) comment '备注',
   insert_time          timestamp DEFAULT CURRENT_TIMESTAMP,
   insert_operator      varchar(32),
   update_time          datetime,
   update_operator      varchar(32),
   primary key (id)
);
alter table tenant_shop comment '商铺对应商户表';

drop table if exists user_tenant;

create table user_tenant
(
   id                   varchar(32) not null comment '主键UUID',
   tenant_no            varchar(32) comment '商户编号',
   tenant_name          varchar(100) comment '商户名称',
   tenant_type          varchar(10) comment '''商户类型  1 经济公司  2  经纪人   3 经营公司'',',
   register_time        datetime comment '商户注册时间',
   enter_system_time    datetime comment '录入我们系统的时间',
   corporate_name       varchar(50) comment '商户法人姓名',
   corporate_id_card    varchar(20) comment '商户法人身份证',
   licence_no           varchar(20) comment '营业执照编号，或组织机构代码证',
   tenant_address       varchar(40) comment '商户注册地址',
   tenant_phone         varchar(20) comment '商户联系电话',
   contact_name         varchar(36) comment '商户联系人',
   contact_mobile       varchar(15) comment '商户联系人手机号码',
   contact_duty         varchar(50) comment '职务',
   contact_email        varchar(36) comment '邮箱',
   copy_business_license varchar(200) comment '营业执照复印件   type= 2  经纪人类型不传',
   company              varchar(50),
   market_id            varchar(10) comment '市场',
   Integral             varchar(10) comment '积分',
   tenant_shop_id       varchar(32) comment '商铺编号',
   check_in_time        datetime comment '初次入住时间',
   isvalid              int(1) default 1  comment  '1-有效，0-失效，不能再使用，只作为历史信息保存',
   remark               varchar(255),
   insert_time          timestamp DEFAULT CURRENT_TIMESTAMP,
   insert_operator      varchar(32),
   update_time          datetime,
   update_operator      varchar(32),
   primary key (id)
);

alter table user_tenant comment '商户表';