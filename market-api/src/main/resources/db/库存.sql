
DROP TABLE IF EXISTS car;
CREATE TABLE car  (
  id varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  vin varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  rfid varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  tenant varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商户id',
  is_new_car int(1) NULL DEFAULT NULL COMMENT '是否新车 0：新车 1：旧车',
  stock_status int(1) NULL DEFAULT NULL COMMENT '库存状态',
  car_status int(1) NULL DEFAULT NULL COMMENT '车辆状态',
  register_time datetime(0) NULL DEFAULT NULL COMMENT '录入时间',
  isvalid              int(2) default 1 comment '数据有效性 1 有效 0 无效',
  market_id varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '市场id',
  update_operator varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  inster_operator varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  insert_time timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  update_time datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (id) USING BTREE,
  UNIQUE INDEX index_id(id) USING BTREE,
  INDEX index_vin(vin) USING BTREE,
  INDEX index_rfid(rfid) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '车辆基础表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for car_base
-- ----------------------------
DROP TABLE IF EXISTS car_base;
CREATE TABLE car_base  (
  id varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  brand_name varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '品牌名称',
  brand_code varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '品牌code',
  series_name varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车系名称',
  series_code varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车系code',
  model_year varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '年款',
  model_name varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车型名称',
  model_code varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车型code',
  mileage int(7) NULL DEFAULT NULL COMMENT '公里数',
  evaluate_price decimal(9, 2) NULL DEFAULT NULL COMMENT '估价',
  new_price decimal(9, 2) NULL DEFAULT NULL COMMENT '新车价',
  market_price decimal(9, 2) NULL DEFAULT NULL COMMENT '市场价',
  color varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '颜色',
  engine_volume_unitl double NULL DEFAULT NULL COMMENT '排量',
  environmental_standards int(1) NULL DEFAULT NULL COMMENT '排放标准',
  gear_box int(1) NULL DEFAULT NULL COMMENT '变速箱',
  seat_number int(1) NULL DEFAULT NULL COMMENT '座椅数',
  fuel_form int(1) NULL DEFAULT NULL COMMENT '燃油方式 ',
  fuel_number int(1) NULL DEFAULT NULL COMMENT '油标',
  model_data text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '车型数据json',
  remark text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  check_company_id varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '检测公司',
  inster_operator varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  update_operator varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  insert_time timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  update_time datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (id) USING BTREE,
  UNIQUE INDEX index_id(id) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '车辆详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for car_check
-- ----------------------------
DROP TABLE IF EXISTS car_check;
CREATE TABLE car_check  (
  id varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  company_id varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司code',
  data text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '检测数据',
  describes int(1) NULL DEFAULT NULL COMMENT '描述',
  car_id varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  vin varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  insert_time datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (id) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '车辆检测信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for car_pic
-- ----------------------------
DROP TABLE IF EXISTS car_pic;
CREATE TABLE car_pic  (
  id varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  car varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车id',
  src varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片的地址',
  type int(11) NULL DEFAULT NULL COMMENT '图片的类型',
  inster_operator varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  update_operator varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  insert_time timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  update_time datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (id) USING BTREE,
  INDEX index_id(car, type) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '车辆图片' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for car_record
-- ----------------------------
DROP TABLE IF EXISTS car_record;
CREATE TABLE car_record  (
  id varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  rfid varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'rfid',
  channel varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '道闸的编号',
  type int(11) NULL DEFAULT NULL COMMENT '车辆出入类型 1 入场 0 出场',
  pic varchar(400) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '道闸照片id',
  insert_time timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '时间',
  channel_name varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '道闸名称',
  PRIMARY KEY (id) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '出入场记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for check_company
-- ----------------------------
DROP TABLE IF EXISTS check_company;
CREATE TABLE check_company  (
  id varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  company_id varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司编号',
  company_name varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司名称',
  PRIMARY KEY (id) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '检测公司表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;



alter table car_channel comment '车辆渠道';
/*==============================================================*/
/* Table: car_channel_rel                                       */
/*==============================================================*/
drop table if exists car_channel_rel;
create table car_channel_rel
(
   id                   varchar(32) not null,
   channel_id           varchar(32) not null comment '车辆渠道id',
   car_id               varchar(32) not null comment '车辆id',
   shelf_status         int(2) comment '上/下架状态(1:已上架 0:已下架)',
   remarks              varchar(255) comment '备注',
   isvalid              int(2) default 1 comment '数据有效性 1 有效 0 无效',
   insert_time          timestamp default CURRENT_TIMESTAMP comment '创建时间',
   update_time          datetime comment '最后修改时间',
   insert_operator      varchar(32) comment '添加人',
   update_operator      varchar(32) comment '最后修改人',
   primary key (id)
);

alter table car_channel_rel comment '车辆渠道关系表';
