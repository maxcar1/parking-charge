package com.maxcar.stock.pojo;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CarInfo   implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String id;
    private String carNo;//车辆编号 根据所属商户编号加自身id后四位
    private String car_no;
    private Date register_time;//系统录入场时间
    private String register_timeStr;
    private Integer register_day;//已入库天数
    private Integer type;//1-商品车；2-市场员工车辆；3-商户员工车
    private String vin;//车辆vin码
    private String no;//车牌号
    private String driving_licence_url;//行驶证
    private Date operation_time;//操作时间
    private String operation_timeStr;
    private String engine_no;//发动机牌号
    private String tenant;//type=1 or 3为商户id
    private String tenant_name;//商户名称
    private String market_id;//type=2 时为市场id
    private String market_name;//市场名字
    private String market_simplename;//type=2时有效 为市场简称
    private String rfid;//电子编码
    private Integer is_mortgaged;//是否已质押：1-质押；2-未质押
    private String equipid;//绑定的终端id
    private String equipid_no;//绑定的终端编号
    private Integer status;//状态：80-超时未归 1-未入场；2-已入场；3-已出场；4-已售出；5-售出已出场；6-不监管；7-申请出场； 8-允许出场 ； 9-出场超时  0 删除
    private Integer version;//版本号
    private Float evaluate_price;//车辆评估价格
    private Integer check_result;//是否维珍验车过 1验过  0 or null 未验过
    private Integer limit_status;//限制出场状态 1:不限制 2:限制
    private String owner;//车主
    private Date insertTime;//录入时间
    private Date update_time;
    private String insert_timeStr;
    private String update_timeStr;
    private Integer is_publish;//1为未发布 2为发布
    private Date publish_time;//最后一次上架发布时间
    private String publish_timeStr;//最后一次insertTime上架发布时间
    private String sale_time;//售出日期
    private String owner_idcard;
    private String taobao_id;//车辆淘宝id
    private String taobao_url;//车辆淘宝地址
    private String taobaoId;//车辆淘宝id
    private String taobaoUrl;//车辆淘宝地址
    private String equip_id;
private Double marketPrice;


    //扩展属性外加字段
    private String color;
    private Integer seats;
    private String owner_name;
    private String series;
    private String seriesName;//车系名
    private String brand_code;//车型码
    private Float displacement;//排量
    private String model_year;//年款
    private String model_name;//款型名称
    private String modelCode;//车型详细信息
    private Integer is_vendor_certificated;//认证
    private String air_conditioner_control_type;//手动挡
    private String fuel_form;//燃料
    private String environmental_standards;//排放标准
    private String level;//轿车级别
    private Integer mileage;//里程
    private Date initialLicenceTime;//上牌时间
    private Date initial_licence_time;
    private String initial_licence_timeStr;//初次上牌时间
    private String brandName;//品牌
    private String mac;//监控锁的mas地址
    private Date enter_time;//最后一次出场时间
    private String enter_timeStr;
    //条件查询字段
    private Integer seatType;
    private Integer disType;
    private Integer evaType;
    private Float displacement1;
    private Float displacement2;
    private Float evaluate_price1;
    private Float evaluate_price2;
    private Float amount;
    private Integer car_status;// 1 场内 2 场外
    private Integer initial_licence_time_int;//车龄
    private Integer order_type;// 1最新发布 2价格最低 3价格最高 4里程最少 5车龄最短
    private String car_pic;//车辆展示图
    private Integer new_car;//是否准新车 1是 其余为否
    private Integer car_sales;//是否已售出 1 售出车辆
    private String customer;//消费者id
    private Integer favorite;//是否收藏
    private String city_name;//车辆归属地城市名
    private Float new_price;//新车市场价
    private Float market_price;//展厅报价
    private Integer equType;//1 监管中  2 不监管
    private Integer brows_count;//浏览次数
    private Integer market_test;
    private String attribution;
    //统计字段
    private Integer saleCount;//已售出车辆
    private Integer inCount;//场内车辆
    private Integer outCount;//场外车辆
    private Float evaluateSum;//总价值（估价）
    private Float mortgage_price;//抵押价

    //2018-3新增字段
    private  Float salePrice;//售价
    private  Float evaluatePrice;//估价
    private  String ownerName;
    private  Object daSouChe;//返回的是大搜车的数据
    private String series_name;//车系名
    private String brand_name;//品牌
    private Integer transferNumber;//过户次数
    private String stockType;
    private Long stockDay;//库存天数
    private Integer taoBaoStatus;//淘宝状态
    private Integer approvalStatus; //审批状态 0:未申请  1:已同意 2:已拒绝 3:审批中
    private String gearBox;
    private String wbcarId;

    public  void setWbcarId(String wbcarId){
        this.wbcarId = wbcarId;
    }
    public  String getWbcarId(String wbcarId){
        return wbcarId;
    }

    public Double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Integer getTaoBaoStatus() {
        return taoBaoStatus;
    }

    public void setTaoBaoStatus(Integer taoBaoStatus) {
        this.taoBaoStatus = taoBaoStatus;
    }

    public Integer getTransferNumber() {
        return transferNumber;
    }
    public void setTransferNumber(Integer transferNumber) {
        this.transferNumber = transferNumber;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    //操作人
    private String operationUserId;
    private String operationUserName;

    public String getOperationUserId() {
        return operationUserId;
    }

    public void setOperationUserId(String operationUserId) {
        this.operationUserId = operationUserId;
    }

    public String getOperationUserName() {
        return operationUserName;
    }

    public void setOperationUserName(String operationUserName) {
        this.operationUserName = operationUserName;
    }

    public String getSeries_name() {
        return series_name;
    }
    public void setSeries_name(String series_name) {
        this.series_name = series_name;
    }

    public String getEquip_id() {
        return equip_id;
    }
    public void setEquip_id(String equip_id) {
        this.equip_id = equip_id;
    }

    public String getBrand_name() {
        return brand_name;
    }
    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }
    public Object getDaSouChe() {
        return daSouChe;
    }
    public void setDaSouChe(Object daSouChe) {
        this.daSouChe = daSouChe;
    }
    public String getCar_no() {
        return car_no;
    }
    public void setCar_no(String car_no) {
        this.car_no = car_no;
    }
    public String getOwnerName() {
        return ownerName;
    }
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
    public String getOwner_name() {
        return owner_name;
    }
    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }
    public String getModel_name() {
        return model_name;
    }
    public void setModel_name(String model_name) {
        this.model_name = model_name;
    }
    public String getModelCode() {
        return modelCode;
    }
    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }
    public Date getInsertTime() {
        return insertTime;
    }
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }


    public Float getSalePrice() {
        return salePrice;
    }
    public String getBrand_code() {
        return brand_code;
    }
    public void setBrand_code(String brand_code) {
        this.brand_code = brand_code;
    }
    public void setSalePrice(Float salePrice) {
        this.salePrice = salePrice;
    }
    public Float getEvaluatePrice() {
        return evaluatePrice;
    }
    public void setEvaluatePrice(Float evaluatePrice) {
        this.evaluatePrice = evaluatePrice;
    }
    public String getSeriesName() {
        return seriesName;
    }
    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }
    public String getBrandName() {
        return brandName;
    }
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
    public String getCarNo() {
        return carNo;
    }
    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public Float getMortgage_price() {
        return mortgage_price;
    }
    public void setMortgage_price(Float mortgage_price) {
        this.mortgage_price = mortgage_price;
    }
    public Integer getSaleCount() {
        return saleCount;
    }
    public void setSaleCount(Integer saleCount) {
        this.saleCount = saleCount;
    }
    public Integer getInCount() {
        return inCount;
    }
    public void setInCount(Integer inCount) {
        this.inCount = inCount;
    }
    public Integer getOutCount() {
        return outCount;
    }
    public void setOutCount(Integer outCount) {
        this.outCount = outCount;
    }
    public Float getEvaluateSum() {
        return evaluateSum;
    }
    public void setEvaluateSum(Float evaluateSum) {
        this.evaluateSum = evaluateSum;
    }
    public String getOwner_idcard() {
        return owner_idcard;
    }
    public void setOwner_idcard(String owner_idcard) {
        this.owner_idcard = owner_idcard;
    }
    public Integer getMarket_test() {
        return market_test;
    }
    public void setMarket_test(Integer market_test) {
        this.market_test = market_test;
    }
    public String getPublish_timeStr() {
        return publish_timeStr;
    }
    public void setPublish_timeStr(String publish_timeStr) {
        this.publish_timeStr = publish_timeStr;
    }
    public Integer getIs_publish() {
        return is_publish;
    }
    public void setIs_publish(Integer is_publish) {
        this.is_publish = is_publish;
    }
    public Date getPublish_time() {
        return publish_time;
    }
    public void setPublish_time(Date publish_time) {
        this.publish_time = publish_time;

    }
    public Integer getBrows_count() {
        return brows_count;
    }
    public void setBrows_count(Integer brows_count) {
        this.brows_count = brows_count;
    }
    public String getInsert_timeStr() {
        return insert_timeStr;
    }
    public void setInsert_timeStr(String insert_timeStr) {
        this.insert_timeStr = insert_timeStr;
    }

    public Date getUpdate_time() {
        return update_time;
    }
    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
    public String getTenant() {
        return tenant;
    }
    public String getEquipid() {
        return equipid;
    }
    public Date getEnter_time() {
        return enter_time;
    }
    public void setEnter_time(Date enter_time) {
        this.enter_time = enter_time;

    }
    public String getEnter_timeStr() {
        return enter_timeStr;
    }
    public void setEnter_timeStr(String enter_timeStr) {
        this.enter_timeStr = enter_timeStr;
    }
    public Integer getEquType() {
        return equType;
    }
    public void setEquType(Integer equType) {
        this.equType = equType;
    }
    public String getDriving_licence_url() {
        return driving_licence_url;
    }
    public void setDriving_licence_url(String driving_licence_url) {
        this.driving_licence_url = driving_licence_url;
    }
    public Integer getRegister_day() {
        return register_day;
    }
    public void setRegister_day(Integer register_day) {
        this.register_day = register_day;
    }

    public Float getNewPrice() {
        return new_price;
    }
    public void setNewPrice(Float new_price) {
        this.new_price = new_price;
    }
    public Float getMarket_price() {
        return market_price;
    }
    public void setMarket_price(Float market_price) {
        this.market_price = market_price;
    }
    public String getCar_pic() {
        return car_pic;
    }

    public String getSale_time() {
        return sale_time;
    }
    public void setSale_time(String sale_time) {
        this.sale_time = sale_time;
    }
    public void setCar_pic(String car_pic) {
        this.car_pic = car_pic;
    }

    public Integer getOrder_type() {
        return order_type;
    }
    public void setOrder_type(Integer order_type) {
        this.order_type = order_type;
    }
    public Integer getInitial_licence_time_int() {
        return initial_licence_time_int;
    }
    public void setInitial_licence_time_int(Integer initial_licence_time_int) {
        this.initial_licence_time_int = initial_licence_time_int;
    }
    public Integer getCar_status() {
        return car_status;
    }
    public void setCar_status(Integer car_status) {
        this.car_status = car_status;
    }

    public Integer getCar_sales() {
        return car_sales;
    }
    public void setCar_sales(Integer car_sales) {
        this.car_sales = car_sales;
    }
    public String getAir_conditioner_control_type() {
        return air_conditioner_control_type;
    }
    public void setAir_conditioner_control_type(String air_conditioner_control_type) {
        this.air_conditioner_control_type = air_conditioner_control_type;
    }
    public String getFuel_form() {
        return fuel_form;
    }
    public void setFuel_form(String fuel_form) {
        this.fuel_form = fuel_form;
    }
    public String getEnvironmental_standards() {
        return environmental_standards;
    }
    public void setEnvironmental_standards(String environmental_standards) {
        this.environmental_standards = environmental_standards;
    }
    public CarInfo(){}
    //单独的vin码构造方法
    public CarInfo(String vin){
        this.vin = vin;
    }

    public CarInfo(String tenant,Integer type){
        this.tenant = tenant;
        this.type = type;
    }

    public String getCustomer() {
        return customer;
    }
    public void setCustomer(String customer) {
        this.customer = customer;
    }
    public Integer getFavorite() {
        return favorite;
    }
    public void setFavorite(Integer favorite) {
        this.favorite = favorite;
    }
    public String getCity_name() {
        return city_name;
    }
    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }
    public String getInitial_licence_timeStr() {
        return initial_licence_timeStr;
    }
    public void setInitial_licence_timeStr(String initial_licence_timeStr) {
        this.initial_licence_timeStr = initial_licence_timeStr;
    }
    public Integer getNew_car() {
        return new_car;
    }
    public void setNew_car(Integer new_car) {
        this.new_car = new_car;
    }
    public Float getAmount() {
        return amount;
    }
    public void setAmount(Float amount) {
        this.amount = amount;
    }
    public Float getDisplacement1() {
        return displacement1;
    }
    public void setDisplacement1(Float displacement1) {
        this.displacement1 = displacement1;
    }
    public Float getDisplacement2() {
        return displacement2;
    }
    public void setDisplacement2(Float displacement2) {
        this.displacement2 = displacement2;
    }
    public Float getEvaluate_price1() {
        return evaluate_price1;
    }
    public void setEvaluate_price1(Float evaluate_price1) {
        this.evaluate_price1 = evaluate_price1;
    }
    public Float getEvaluate_price2() {
        return evaluate_price2;
    }
    public void setEvaluate_price2(Float evaluate_price2) {
        this.evaluate_price2 = evaluate_price2;
    }
    public Integer getSeatType() {
        return seatType;
    }
    public void setSeatType(Integer seatType) {
        this.seatType = seatType;
    }
    public Integer getDisType() {
        return disType;
    }
    public void setDisType(Integer disType) {
        this.disType = disType;
    }
    public Integer getEvaType() {
        return evaType;
    }
    public void setEvaType(Integer evaType) {
        this.evaType = evaType;
    }
    public String getMac() {
        return mac;
    }
    public void setMac(String mac) {
        this.mac = mac;
    }
    public Date getInitialLicenceTime() {
        return initialLicenceTime;
    }
    public void setInitialLicenceTime(Date initialLicenceTime) {
        this.initialLicenceTime = initialLicenceTime;
    }

    public Integer getIs_vendor_certificated() {
        return is_vendor_certificated;
    }
    public void setIs_vendor_certificated(Integer is_vendor_certificated) {
        this.is_vendor_certificated = is_vendor_certificated;
    }

    public String getLevel() {
        return level;
    }
    public void setLevel(String level) {
        this.level = level;
    }
    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }
    public Float getDisplacement() {
        return displacement;
    }
    public void setDisplacement(Float displacement) {
        this.displacement = displacement;
    }
    public String getModel_year() {
        return model_year;
    }
    public void setModel_year(String model_year) {
        this.model_year = model_year;
    }
    public Integer getCheck_result() {
        return check_result;
    }
    public void setCheck_result(Integer check_result) {
        this.check_result = check_result;
    }
    public Integer getLimit_status() {
        return limit_status;
    }
    public void setLimit_status(Integer limit_status) {
        this.limit_status = limit_status;
    }

    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getSeries() {
        return series;
    }
    public void setSeries(String series) {
        this.series = series;
    }

    public Float getEvaluate_price() {
        return evaluate_price;
    }
    public void setEvaluate_price(Float evaluate_price) {
        this.evaluate_price = evaluate_price;
    }


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setTenant(String tenant) {
        this.tenant = tenant;
    }
    public void setEquipid(String equipid) {
        this.equipid = equipid;
    }
    public Date getRegister_time() {
        return register_time;
    }

    public void setRegister_time(Date register_time) {
        this.register_time = register_time;
        if(null != register_time){
            this.setRegister_timeStr(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(register_time));
        }
    }

    public String getRegister_timeStr() {
        return register_timeStr;
    }

    public void setRegister_timeStr(String register_timeStr) {
        this.register_timeStr = register_timeStr;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Date getOperation_time() {
        return operation_time;
    }

    public void setOperation_time(Date operation_time) {
        this.operation_time = operation_time;
        if(null != operation_time){
            this.setOperation_timeStr(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(operation_time));
        }
    }

    public String getOperation_timeStr() {
        return operation_timeStr;
    }

    public void setOperation_timeStr(String operation_timeStr) {
        this.operation_timeStr = operation_timeStr;
    }

    public String getEngine_no() {
        return engine_no;
    }

    public void setEngine_no(String engine_no) {
        this.engine_no = engine_no;
    }


    public String getTenant_name() {
        return tenant_name;
    }

    public void setTenant_name(String tenant_name) {
        this.tenant_name = tenant_name;
    }


    public String getMarket_name() {
        return market_name;
    }

    public void setMarket(String market_id) {
        this.market_id = market_id;
    }

    public String getMarket_id() {
        return market_id;
    }

    public void setMarket_name(String market_name) {
        this.market_name = market_name;
    }

    public String getMarket_simplename() {
        return market_simplename;
    }

    public void setMarket_simplename(String market_simplename) {
        this.market_simplename = market_simplename;
    }

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid;
    }

    public Integer getIs_mortgaged() {
        return is_mortgaged;
    }

    public void setIs_mortgaged(Integer is_mortgaged) {
        this.is_mortgaged = is_mortgaged;
    }

    public String getEquipid_no() {
        return equipid_no;
    }

    public void setEquipid_no(String equipid_no) {
        this.equipid_no = equipid_no;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public Integer getSeats() {
        return seats;
    }
    public void setSeats(Integer seats) {
        this.seats = seats;
    }
    public String getUpdate_timeStr() {
        return update_timeStr;
    }
    public void setUpdate_timeStr(String update_timeStr) {
        this.update_timeStr = update_timeStr;
    }
    public String getTaobao_id() {
        return taobao_id;
    }
    public void setTaobao_id(String taobao_id) {
        this.taobao_id = taobao_id;
    }
    public String getTaobao_url() {
        return taobao_url;
    }
    public void setTaobao_url(String taobao_url) {
        this.taobao_url = taobao_url;
    }
    public Date getInitial_licence_time() {
        return initial_licence_time;
    }
    public void setInitial_licence_time(Date initial_licence_time) {
        this.initial_licence_time = initial_licence_time;
    }
    public String getTaobaoUrl() {
        return taobaoUrl;
    }
    public void setTaobaoUrl(String taobaoUrl) {
        this.taobaoUrl = taobaoUrl;
    }
    public String getTaobaoId() {
        return taobaoId;
    }
    public void setTaobaoId(String taobaoId) {
        this.taobaoId = taobaoId;
        if(taobaoId != null){
            this.setTaobao_id(taobaoId);
        }
    }
    public String getAttribution() {
        return attribution;
    }
    public void setAttribution(String attribution) {
        this.attribution = attribution;
    }
    public String getStockType() {
        return stockType;
    }
    public void setStockType(String stockType) {
        this.stockType = stockType;
    }
    public Long getStockDay() {
        return stockDay;
    }
    public void setStockDay(Long stockDay) {
        this.stockDay = stockDay;
    }

    public Integer getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(Integer approvalStatus) {
        this.approvalStatus = approvalStatus;
    }
    public String getEnvironmentalStandards() {
        return environmental_standards;
    }
    public void setEnvironmentalStandards(String environmentalStandards) {
        this.environmental_standards = environmentalStandards;
    }

    public String getGearBox() {
        return gearBox;
    }
    public void setGearBox(String gearBox) {
        this.gearBox = gearBox;
    }

}
