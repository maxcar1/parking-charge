package com.maxcar.base.pojo;

/**
 * describe:  魔法值
 * create_date: lxy 2018/8/16  18:23
 **/
public interface Magic {

    //物业未租
    Integer AREA_SHOP_RENT_STATUS_NOTRENT = 0;
    //物业已租
    Integer AREA_SHOP_RENT_STATUS_RENT = 1;

    // 合同缴费状态为未缴纳
    Integer CONTRACT_PAY_NOT = 0;
    // 合同缴费状态为未缴清
    Integer CONTRACT_PAY_NOTCOST = 1;
    // 合同缴费状态为已缴清
    Integer CONTRACT_PAY_COST = 2;
    // 合同缴费状态为终止
    Integer CONTRACT_PAY_END = 3;

    // 合同为正常状态
    Integer CONTRACT_STATUS_NORMAL = 1;
    // 合同为终止状态
    Integer CONTRACT_STATUS_END = 2;
    // 合同为撤销状态 废弃
    Integer CONTRACT_STATUS_UNDO = 3;
    // 合同为撤销状态 到期_
    Integer CONTRACT_STATUS_END_TIME_LIMIT = 4;

    // 物业类型为 车位的
    Integer CONTRACT_CATEGORY_CAR_SPACE = 1;
    // 物业类型为 展厅的
    Integer CONTRACT_CATEGORY_EXHIBITION = 0;
    // 物业类型为 办公室的
    Integer CONTRACT_CATEGORY_OFFICE = 2;
    // 物业类型为 仓库
    Integer CONTRACT_CATEGORY_WAREHOUSE = 3;
    // 物业类型为 临时车位
    Integer CONTRACT_CATEGORY_TEMPORARY_CAR_SPACE = 4;
    // 物业类型为 其他
    Integer CONTRACT_CATEGORY_OTHER = 5;


    // 物业付费方式 月付
    Integer CONTRACT_DETAIL_TYPE_MONTH = 1;
    // 物业付费方式 季付
    Integer CONTRACT_DETAIL_TYPE_SEASON = 2;
    // 物业付费方式 年付
    Integer CONTRACT_DETAIL_TYPE_YEARS = 3;

    //字典表标示 合同状态
    String PROPERTY_CONTRACT_STATUS = "property_contract_status";

    // 字典表标示 商户类型
    String TENANT_TYPE = "tenant_type";

    //null 现在为暂无
    String NUll = "暂无";

    // 车辆类型  商品车
    Integer CAR_TYPE_MERCHANDISE_CAR = 1;
    // 市场车
    Integer CAR_TYPE_MARKET_CAR = 2;
    // 商户车
    Integer CAR_TYPE_TENANT_CAR = 3;

    // 库存状态 在场
    Integer STOCK_STATUS_1 = 1;
    // 库存状态 在内场
    Integer STOCK_STATUS_2 = 2;
    // 库存状态 出场
    Integer STOCK_STATUS_3 = 3;


    //车位占地面积字典key
    String EXHIBITION_AREA = "exhibition_area";

    // 商户类型
    //  1  经纪人2 经纪公司  3 经销公司4 新车经销商  5 代理人 6 餐饮店  7 金融公司 8 其他
    String TENANT_TYPE_1 = "1";
    String TENANT_TYPE_2 = "2";
    String TENANT_TYPE_3 = "3";
    String TENANT_TYPE_4 = "4";
    String TENANT_TYPE_5 = "5";
    String TENANT_TYPE_6 = "6";
    String TENANT_TYPE_7 = "7";


//   过户相关===============

    //车商员工类型
    Integer CAR_STAFF_TYPE = 2;

    // 数据无效
    Integer IS_VALID_FAIL = 0;

    // 车辆类型为库存车 (过户)
    Byte CAR_TYPE_INVENTORY = 1;

    // 车辆类型为挂靠车 (过户)
    Byte CAR_TYPE_AFFILIATED = 2;

    // 过户记录标示 填写车辆信息 (过户)
    Byte TRANSFER_CAR_STATUS_1 = 1;

    // 过户记录标示 填写卖家信息 (过户)
    Byte TRANSFER_CAR_STATUS_2 = 2;

    // 过户记录标示 填写买家信息 (过户)
    Byte TRANSFER_CAR_STATUS_3 = 3;

    // 过户记录标示 完善交易信息 (过户)
    Byte TRANSFER_CAR_STATUS_4 = 4;


    // 过户状态  (过户)
    Integer TRANSFER_CAR_TYPE = 1;

    //过户车辆 卖方为机构
    Integer SELLER_TYPE_2 = 2;

    //过户车辆 买方为机构
    Byte BUYER_TYPE_2 = 2;

}
