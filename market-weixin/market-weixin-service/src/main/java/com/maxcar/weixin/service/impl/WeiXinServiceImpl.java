package com.maxcar.weixin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.maxcar.barrier.pojo.Barrier;
import com.maxcar.barrier.service.BarrierService;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.service.TopicService;
import com.maxcar.base.util.*;
import com.maxcar.base.util.dasouche.Result;
import com.maxcar.base.util.kafka.PostParam;
import com.maxcar.base.util.wechat.*;
import com.maxcar.kafka.service.MessageProducerService;
import com.maxcar.market.model.request.AddParkingFeeDetailRequest;
import com.maxcar.market.pojo.OpenBarrierRecord;
import com.maxcar.market.pojo.ParkingFeeDetail;
import com.maxcar.market.service.OpenBarrierRecordService;
import com.maxcar.market.service.ParkingFeeRuleService;
import com.maxcar.market.service.ParkingFeeService;
import com.maxcar.redis.service.RedisService;
import com.maxcar.redis.service.SsoService;
import com.maxcar.weixin.model.AccessToken;
import com.maxcar.weixin.model.UserInfo;
import com.maxcar.weixin.service.WeiXinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;

/**
 * 停车收费微信核心处理服务
 * yangshujun
 */
@Service("weiXinService")
public class WeiXinServiceImpl implements WeiXinService {
    private static final Logger logger = LoggerFactory.getLogger(WeiXinServiceImpl.class);

    @Value("${access_token_url}")
    private String accessTokenUrl;

    @Value("${paibo_app_id}")
    private String paiboAppId;

    @Value("${paibo_app_secret_weixin}")
    private String paiboAppSecret;

    @Value("${icar_app_id}")
    private String icarAppId;

    @Value("${icar_app_secret_weixin}")
    private String icarAppSecret;

    @Value("${grant_type}")
    private String grantType;

    @Value("${ticket_url}")
    private String ticketUrl;

    @Value("${expire_seconds}")
    private String expireSeconds;

    @Value("${action_name}")
    private String actionName;

    @Value("${action_name_limit}")
    private String actionNameLimit;

    @Value("${action_name_str}")
    private String actionNameStr;

    @Value("${action_name_limit_str}")
    private String actionNameLimitStr;

    @Value("${show_ticket_url}")
    private String showTicketUrl;

    @Value("${user_info_url}")
    private String userInfoUrl;

    @Value("${lang}")
    private String lang;

    @Value("${welcome_icar}")
    private String welcomeIcar;

    @Value("${welcome_paibo}")
    private String welcomePaibo;

    @Value("${pay_tenant_num}")
    private String payTenantNum;

    @Value("${pay_account_app_id}")
    private String payAccountAppId;

    @Value("${pay_QR_url}")
    private String payQRUrl;

    @Value("${weixin_pay_app_key}")
    private String weixinPayAppkey;

    @Value("${save_img_file_path}")
    private String saveImgFilePath;

    @Value("${down_order_url}")
    private String downOrderUrl;

    @Value("${pay_notify_url}")
    private String payNotifyUrl;

    @Value("${trade_type}")
    private String tradeType;

    @Value("${spbill_create_ip}")
    private String spbillCreateIp;

    @Value("${weixin_pay_order_body}")
    private String weixinPayOrderBody;

    @Value("${weixin_js_api_url}")
    private String weixinJsApiUrl;

    @Value("${weixin_pay_js_url}")
    private String weixinPayJsUrl;

    @Value("${pay_account_app_secret}")
    private String payAccountAppSecret;

    private static final String KEY_PAIBO = "paibo";
    private static final String KEY_ICAR = "icar";
    private static final String KEY_TENANT = "tenant";


    @Autowired
    private ParkingFeeService parkingFeeService;

    @Autowired
    private BarrierService barrierService;

    @Autowired
    private MessageProducerService messageProducerService;

    @Autowired
    private ParkingFeeRuleService parkingFeeRuleService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private OpenBarrierRecordService openBarrierRecordService;

    @Autowired
    private TopicService topicService;


    private static final String SLASH = "/";
    private static final String SUCCESS = "SUCCESS";
    private static final String FAIL = "FAIL";
    private static final String OK = "OK";
    private static final String SIGN_ERROR = "签名错误";

    private static Map<String, String> transformationMap = new HashMap<>();

    @Override
    public InterfaceResult getPayQRCode(String productId) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        //生成支付二维码
        String timeStamp = WeiXinUtils.getCurrentTimeStamp();
        String urlStr = generorQRUrl(productId, timeStamp);
        logger.info("微信二维码拼接参数:{}", urlStr);

        StringBuilder url = new StringBuilder(saveImgFilePath);
        File path = new File(saveImgFilePath);
        if (!path.exists()) {
            path.mkdirs();
        }
        String picType = ".png";
        url.append(SLASH).append(timeStamp).append("_").append(productId).append(picType);
        //设置二维码宽高，保存的url及图片类型
        Boolean encode = ZxingKitUtil.encode(urlStr,
                BarcodeFormat.QR_CODE, 3, ErrorCorrectionLevel.H,
                picType.substring(1, picType.length()), 430, 430,
                url.toString());
        if (encode) {
            com.google.zxing.Result result = ZxingKitUtil.decode(url.toString());
            //微信校验二维码内容
            if (StringUtils.equals(result.getText(), urlStr)) {
                /*FilePojo filePojo = new FilePojo();
                filePojo.setPath(url.toString());
                filePojo.setType(picType);
                filePojo.setName(url.toString().split(SLASH)[url.toString().split(SLASH).length - 1]);
                filePojo.setOssPath(url.toString().split("\\.")[0]);
                FilePojo resultFile = OSSManageUtil.uploadFile(filePojo);*/
                interfaceResult.InterfaceResult200(url);
            } else {
                interfaceResult.InterfaceResult500("二维码内容校验失败!");
            }
        } else {
            interfaceResult.InterfaceResult500("二维码生成失败!");
        }
        return interfaceResult;
    }

    @Override
    public InterfaceResult doOrder(JSONObject params) throws Exception {
        InterfaceResult result = new InterfaceResult();
        //验证签名是否正确，微信提供的验证工具类
        logger.info("下单回调的参数==>{}", params);
        String nonceStr = params.getString("nonceStr");
        String openid = params.getString("openId");
        String orderNo = params.getString("orderNo");
        Integer price = params.getInteger("price");
        String barrierId = params.getString("barrierId");
        String marketId = params.getString("marketId");
        String afterTime = params.getString("afterTime");
        String ip = params.getString("ip");
        //value设为Object，便于后续扩展
        //封装下单参数
        SortedMap<String, Object> packageParams = new TreeMap<String, Object>();
        packageParams.put("appid", payAccountAppId);
        packageParams.put("openid", openid);
        packageParams.put("mch_id", payTenantNum);
        packageParams.put("nonce_str", nonceStr);
        packageParams.put("device_info", "WEB");
        packageParams.put("body", weixinPayOrderBody);
        packageParams.put("out_trade_no", orderNo);
        //正式
        packageParams.put("total_fee", price * 100);
        //测试  1分钱
        /*packageParams.put("total_fee", "1");*/
        packageParams.put("spbill_create_ip", spbillCreateIp);
        packageParams.put("trade_type", tradeType);
        packageParams.put("attach", marketId + "-" + barrierId + "-" + afterTime);
        //支付后返回结果
        packageParams.put("notify_url", payNotifyUrl);
        //生成sign校验，并封装xml
        String xmlRequest = doSimpleSignAndXml(packageParams);
        logger.info("下单请求==>{}", xmlRequest);
        //请求下单
        String orderResponse = HttpClientUtils.sendPostXml(downOrderUrl, xmlRequest);
        //校验签名
        logger.info("下单发起的响应==>{}", orderResponse);
        if (WeiXinUtils.isSignatureValid(orderResponse, weixinPayAppkey)) {
            Map<String, String> xmlOrderMap = WeiXinUtils.xmlToMap(orderResponse);
            if (StringUtils.equals(xmlOrderMap.get("result_code"), SUCCESS)) {
                // 统一下单返回的参数
                // 交易会话标识 2小时内有效
                String prepay_id = xmlOrderMap.get("prepay_id");
                SortedMap<String, Object> resParams = new TreeMap<String, Object>();
                resParams.put("appId", payAccountAppId); // 必须
                resParams.put("nonceStr", nonceStr); // 必须
                resParams.put("package", "prepay_id=" + prepay_id); // 必须
                resParams.put("timeStamp", WeiXinUtils.getCurrentTimeStamp());
                resParams.put("signType", "MD5");
                //生成sign校验，并封装xml
                if (!transformationMap.isEmpty()) {
                    transformationMap.clear();
                }
                StringBuilder splitParams = new StringBuilder();
                for (SortedMap.Entry<String, Object> entryMap : resParams.entrySet()) {
                    splitParams.append("&").append(entryMap.getKey()).append("=").append(entryMap.getValue());
                    transformationMap.put(entryMap.getKey(), String.valueOf(entryMap.getValue()));
                }
                logger.info("拼接生成sign的字符串==>{}", splitParams);
                //生成sign签名
                String sign = WeiXinUtils.createSign(splitParams, weixinPayAppkey);
                transformationMap.put("paySign", sign);
                result.InterfaceResult200(transformationMap);
            } else {
                result.InterfaceResult600("微信下单失败!");
            }
        } else {
            result.InterfaceResult600("微信签名校验失败!");
        }

        return result;
    }

    @Override
    public JSONObject payCallBack(String xml) throws Exception {
        JSONObject json = new JSONObject();
        String resXml = "";
        //验证签名是否正确，微信提供的验证工具类
        if (WeiXinUtils.isSignatureValid(xml, weixinPayAppkey)) {
            Map<String, String> xmlMap = WeiXinUtils.xmlToMap(xml);
            if (StringUtils.equals(SUCCESS, xmlMap.get("result_code"))) {
                String mchId = xmlMap.get("mch_id");
                String openId = xmlMap.get("openid");
                String isSubscribe = xmlMap.get("is_subscribe");
                //订单号
                String orderNo = xmlMap.get("out_trade_no");
                String totalFee = xmlMap.get("total_fee");
                //读取道闸id
                String attach = xmlMap.get("attach");
                //支付成功更新数据，根据订单号查询数据
                //根据订单号查找该条记录
                String[] info = attach.split("-");
                ParkingFeeDetail detail = new ParkingFeeDetail();
                detail.setCardNo(orderNo);
                detail.setMarketId(info[0]);
                Calendar c = Calendar.getInstance();
                c.setTimeInMillis(Long.valueOf(info[2]));
                ParkingFeeDetail parkingFeeDetail = parkingFeeService.selectDetail(detail);
                parkingFeeDetail.setPrice(Integer.valueOf(totalFee)/100);
                parkingFeeDetail.setChargePrice(Integer.valueOf(totalFee)/100);
                parkingFeeDetail.setAfterTime(c.getTime());
                parkingFeeService.updateAfterTime(parkingFeeDetail);
                StringBuilder sb = new StringBuilder();
                Map map1 = DateUtils.getHMS(parkingFeeDetail.getAfterTime(), parkingFeeDetail.getBeforeTime());
                sb.append(map1.get("hour"));
                sb.append("时");
                sb.append(map1.get("minute"));
                sb.append("分");
                sb.append(map1.get("second"));
                sb.append("秒");
                parkingFeeDetail.setParkingTime(sb.toString());
                json.put("barrierId", info[1]);
                if (null != parkingFeeDetail) {
                    json.put("parking", parkingFeeDetail);
                }
                //下发开闸 微信下发参数-1  一路顺风
                openDZ(info[0], info[1], -1);
                resXml = FormatXmlProcess.formatXmlResponse(SUCCESS, OK);
            } else {
                resXml = FormatXmlProcess.formatXmlResponse(FAIL, "报文为空");
            }
        } else {
            resXml = FormatXmlProcess.formatXmlResponse(FAIL, SIGN_ERROR);
        }
        json.put("result", resXml);
        return json;
    }

    @Override
    public InterfaceResult getJsApi() throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        StringBuilder sb = new StringBuilder(weixinJsApiUrl);
        sb.append("?type=jsapi&access_token=");
        sb.append(cacheTokenInRedis(payAccountAppId, payAccountAppSecret, KEY_PAIBO));
        String response = HttpClientUtils.sendGet(sb.toString());
        logger.info("jsapi返回==>{}", response);
        if (StringUtils.isNotBlank(response)) {
            JSONObject json = JSONObject.parseObject(response);
            String noncestr = WeiXinUtils.getNonceStr();
            String timestamp = WeiXinUtils.getCurrentTimeStamp();
            //weixinPayJsUrl
            SortedMap<String, Object> sortMap = new TreeMap<>();
            sortMap.put("noncestr", noncestr);
            sortMap.put("jsapi_ticket", json.getString("ticket"));
            sortMap.put("timestamp", timestamp);
            sortMap.put("url", weixinPayJsUrl);
            StringBuilder split = new StringBuilder();
            for (SortedMap.Entry<String, Object> map : sortMap.entrySet()) {
                split.append("&").append(map.getKey()).append("=").append(map.getValue());
            }
            split.deleteCharAt(0);
            logger.info("jsapi拼接==>{}", split);
            String signature = SHAUtil.getSha1(split.toString());
            JSONObject result = new JSONObject();
            result.put("signature", signature);
            result.put("noncestr", noncestr);
            result.put("timestamp", timestamp);
            result.put("appId", payAccountAppId);
            interfaceResult.InterfaceResult200(result);
        } else {
            interfaceResult.InterfaceResult500("调用微信jsapi异常!");
        }
        return interfaceResult;
    }

    private String doSimpleSignAndXml(SortedMap<String, Object> packageParams) throws Exception {
        if (!transformationMap.isEmpty()) {
            transformationMap.clear();
        }
        StringBuilder splitParams = new StringBuilder();
        for (SortedMap.Entry<String, Object> entryMap : packageParams.entrySet()) {
            splitParams.append("&").append(entryMap.getKey()).append("=").append(entryMap.getValue());
            transformationMap.put(entryMap.getKey(), String.valueOf(entryMap.getValue()));
        }
        logger.info("拼接生成sign的字符串==>{}", splitParams);
        //生成sign签名
        String sign = WeiXinUtils.createSign(splitParams, weixinPayAppkey);
        transformationMap.put(WeiXinUtils.SIGN, sign);
        //封装xml
        return WeiXinUtils.mapToXml(transformationMap);
    }

    private String generorQRUrl(String productId, String timeStamp) {
        StringBuilder splitParam = new StringBuilder();
        Map<String, String> sortMap = new HashMap<>();
        String nonceStr = WeiXinUtils.getNonceStr();
        sortMap.put("appid", payAccountAppId);
        sortMap.put("mch_id", payTenantNum);
        sortMap.put("product_id", productId);
        sortMap.put("time_stamp", timeStamp);
        sortMap.put("nonce_str", nonceStr);
        Collection<String> sortKey = sortMap.keySet();
        List<String> keyList = new ArrayList<>(sortKey);
        Collections.sort(keyList);
        keyList.forEach(key -> {
            //value为null,不参与拼接
            if (StringUtils.isNotBlank(sortMap.get(key))) {
                splitParam.append("&").append(key).append("=").append(sortMap.get(key));
            }
        });

        //生成sign签名
        StringBuilder deleteKey = new StringBuilder(splitParam);
        String sign = WeiXinUtils.createSign(splitParam, weixinPayAppkey);
        StringBuilder urlStr = new StringBuilder("sign=");
        //sign拼接
        urlStr.append(sign);
        //参数追加签名
        deleteKey.insert(0, urlStr);
        //删除元素
        urlStr.delete(0, urlStr.length());
        //组装url
        urlStr.append(payQRUrl).append(deleteKey);
        return urlStr.toString();
    }

    public String cacheTokenInRedis(String appId, String appSecret, String key) throws Exception {
        String aToken = "";
        String accessToken = redisService.get(appId + "_" + key);
        logger.info("accessToken==>{}", accessToken);
        if (StringUtils.isBlank(accessToken)) {
            AccessToken token = getAccessToken(appId, appSecret);
            if (null != token) {
                redisService.del(appId + "_" + key);
                long l = System.currentTimeMillis();
                redisService.set(appId + "_" + key, token.getAccess_token() + "#" + l, 7200);
                aToken = token.getAccess_token();
            }
        } else {
            String[] tokens = accessToken.split("#");
            if (tokens.length == 2) {
                //判断是否过期
                long l = System.currentTimeMillis();
                long c = Long.parseLong(tokens[1]) + 7200 * 1000;
                if (l > c) {
                    //过期
                    AccessToken token = getAccessToken(appId, appSecret);
                    if (null != token) {
                        redisService.del(appId + "_" + key);
                        redisService.set(appId + "_" + key, token.getAccess_token() + "#" + l, 7200);
                        aToken = token.getAccess_token();
                    }
                } else {
                    aToken = tokens[0];
                }
            }
        }
        return aToken;
    }

    /**
     * 获取AccessToken并缓存
     */
    private String cacheToken(String appId, String appSecret, String key) throws Exception {
        logger.info("tokenMap为:{}", WeiXinUtils.tokenMap);
        String accessToken = "";
        AccessToken token = null;

        if (!WeiXinUtils.tokenMap.isEmpty()) {
            token = (AccessToken) WeiXinUtils.tokenMap.get("access_token_" + key);
            if (null == token || StringUtils.isBlank(token.getAccess_token())) {
                token = getAccessToken(appId, appSecret);
                token.setApplyTime(new Date());
                WeiXinUtils.tokenMap.put("access_token_" + key, token);
                accessToken = token.getAccess_token();
                return accessToken;
            }
            // 当前时间
            Calendar nowTime = Calendar.getInstance();
            nowTime.setTime(new Date());
            // 通用接口凭证申请时间加过期时间
            // 特殊处理：过期时间减60秒
            Calendar applyTime = Calendar.getInstance();
            applyTime.setTime(token.getApplyTime());
            applyTime.add(Calendar.SECOND, token.getExpires_in() - 60);
            // 比较时间
            if (nowTime.compareTo(applyTime) >= 0) {
                // Token失效,删除内存中的数据
                WeiXinUtils.tokenMap.remove("access_token_" + key);
                token = getAccessToken(appId, appSecret);
                token.setApplyTime(new Date());
                WeiXinUtils.tokenMap.put("access_token_" + key, token);
                accessToken = token.getAccess_token();
            } else {
                // Token有效
                accessToken = token.getAccess_token();
            }
        } else {
            token = getAccessToken(appId, appSecret);
            token.setApplyTime(new Date());
            WeiXinUtils.tokenMap.put("access_token_" + key, token);
            accessToken = token.getAccess_token();
        }

        return accessToken;

    }

    private AccessToken getAccessToken(String appId, String appSecret) throws Exception {
        StringBuilder tokenUrl = new StringBuilder(accessTokenUrl);
        tokenUrl.append("?grant_type=").append(grantType)
                .append("&appid=").append(appId).append("&secret=").append(appSecret);
        String response = HttpClientUtils.sendGet(tokenUrl.toString());
        JSONObject json = JSONObject.parseObject(response);
        json.put("ApplyTime", new Date());
        logger.info("微信获取的access_token:{}", json.toJSONString());
        AccessToken accessToken = JSONObject.toJavaObject(json, AccessToken.class);
        return accessToken;
    }

    /**
     * 获取微信公众号二维码
     *
     * @param codeType 二维码类型 1: 临时二维码  2: 永久二维码
     * @param sceneStr 场景值ID
     */
    @Override
    public String getTicket(Integer codeType, String sceneStr, String publicNum) throws Exception {
        Map<String, Object> map = new HashMap<>();
        StringBuilder ticketurl = new StringBuilder(ticketUrl);
        StringBuilder showUrl = new StringBuilder(showTicketUrl);
        String response = "";
        String token = "";
        switch (publicNum) {
            case "paibo":
                AccessToken accessToken = getAccessToken(paiboAppId, paiboAppSecret);
                token = accessToken.getAccess_token();
                break;
            case "icar":
                AccessToken accessToken1 = getAccessToken(icarAppId, icarAppSecret);
                token = accessToken1.getAccess_token();
                break;
            default:
                return WeiXinUtils.WeiXinStatus.TICKET_FAIL.getCode();
        }
        if (StringUtils.isNotBlank(token)) {
            if (codeType == 1) {
                // 临时二维码
                map.put("expire_seconds", expireSeconds);
                map.put("action_name", actionNameStr);
                Map<String, Object> sceneMap = new HashMap<>();
                Map<String, Object> sceneIdMap = new HashMap<>();
                sceneIdMap.put("scene_str", sceneStr);
                sceneMap.put("scene", sceneIdMap);
                map.put("action_info", sceneMap);
            } else if (codeType == 2) {
                // 永久二维码
                map.put("action_name", actionNameLimitStr);
                Map<String, Object> sceneMap = new HashMap<>();
                Map<String, Object> sceneIdMap = new HashMap<>();
                sceneIdMap.put("scene_str", sceneStr);
                sceneMap.put("scene", sceneIdMap);
                map.put("action_info", sceneMap);
            }
            ticketurl.append(token);
            response = HttpClientUtils.sendPost(ticketurl.toString(), JSON.toJSONString(map));
            JSONObject json = JSONObject.parseObject(response);
            if (null != json && !json.isEmpty()) {
                if (json.containsKey("ticket")) {
                    String ticket = json.getString("ticket");
                    showUrl.append(ticket);
                    return showUrl.toString();
                } else {
                    return WeiXinUtils.WeiXinStatus.TICKET_FAIL.getCode();
                }
            } else {
                return WeiXinUtils.WeiXinStatus.TICKET_JSON_FAIL.getCode();
            }
        } else {
            return WeiXinUtils.WeiXinStatus.INVALID_TOKEN.getCode();
        }
    }

    @Override
    public UserInfo getUserInfo(String openId, String appId, String appSecret, String key) throws Exception {
        String token = cacheTokenInRedis(appId, appSecret, key);
        StringBuilder userUrl = new StringBuilder(userInfoUrl);
        if (StringUtils.isNotBlank(token)) {
            userUrl.append(token).append("&openid=").append(openId).append("&lang=").append(lang);
            String response = HttpClientUtils.sendGet(userUrl.toString());
            UserInfo userInfo = JSONObject.toJavaObject(JSONObject.parseObject(response), UserInfo.class);
            if (null == userInfo.getSubscribe()) {
                AccessToken aToken = getAccessToken(appId, appSecret);
                redisService.del(appId + "_" + key);
                long l = System.currentTimeMillis();
                redisService.set(appId + "_" + key, aToken.getAccess_token() + "#" + l, 7200);
                StringBuilder userUrl1 = new StringBuilder(userInfoUrl);
                userUrl1.append(token).append("&openid=").append(openId).append("&lang=").append(lang);
                String response1 = HttpClientUtils.sendGet(userUrl.toString());
                UserInfo userInfo1 = JSONObject.toJavaObject(JSONObject.parseObject(response1), UserInfo.class);
                return userInfo1;
            }
            return userInfo;
        }
        return null;
    }

    /**
     * 处理微信公众号请求信息
     *
     * @param returnXml
     * @return
     */
    @Override
    public JSONObject handlePublicMsg(String returnXml, WeiXinUtils.WeiXinPublicNum type) throws Exception {
        logger.info("===>进入处理公共方法类，{}", returnXml);
        JSONObject result = new JSONObject();
        // 将xml数据转换为map
        ReceiveXmlEntity receiveXmlEntity = ReceiveXmlProcess.getMsgEntity(returnXml);
        // 区分消息类型
        String msgType = receiveXmlEntity.getMsgType();
        switch (msgType) {
            case "text":
                // 文本消息
                break;
            case "image":
                // 图片消息
                break;
            case "voice":
                //语音消息
                break;
            case "video":
                // 视频消息
                break;
            case "shortvideo":
                // 小视频消息
                break;
            case "location":
                // 地理位置消息
                break;
            case "link":
                // 链接消息
                break;
            case "event":
                // 事件推送---事件消息
                result = doEvent(receiveXmlEntity, type,returnXml);
                break;
            default:
                break;
        }
        return result;
    }


    private void doSaveAdmission(UserInfo userInfo, String eventKey) {
        logger.info("eventKey==>{}", eventKey);
        try {
            if (null != userInfo) {
                String[] market = eventKey.split("-");
                AddParkingFeeDetailRequest request = new AddParkingFeeDetailRequest();
                if (null == userInfo.getUnionid()) {
                    Thread.sleep(3000);
                    userInfo = getUserInfo(userInfo.getOpenid(), icarAppId, icarAppSecret, KEY_ICAR);
                }
                //根据id和marketId查真正的道闸id
                Barrier barrier = new Barrier();
                barrier.setId(Integer.parseInt(market[1]));
                barrier.setMarketId(market[0]);
                Barrier ba = barrierService.selectBarrierByIdAndMarketId(barrier);
                if (market.length > 0) {
                    logger.info("{}==>车辆入场成功", WeiXinUtils.getSystemDate());
                    //根据场景id判断开启哪个道闸
                    // kafka下发数据到道闸系统
                    //下划线区分,下标0为市场id,下标1为道闸id
                    ParkingFeeDetail feeDetail = new ParkingFeeDetail();
                    feeDetail.setMarketId(market[0]);
                    feeDetail.setUnionId(userInfo.getUnionid());
                    List<ParkingFeeDetail> feeDetails = parkingFeeService.selectParkingFee(feeDetail);
                    if (null != ba) {
                        if (null == feeDetails || feeDetails.size() == 0) {
                            checkGroundSense(market[0], ba, userInfo.getUnionid());
                            /*request.setMaxketId(market[0]);
                            request.setUnionId(userInfo.getUnionid());
                            request.setBarrierId(ba.getBarrierId());
                            //微信扫码进入的,订单号存入cardNo里面
                            request.setCardNo(WeiXinUtils.createOrderNo());
                            parkingFeeService.addParkingFeeDetail(request);*/
                        } else {
                            openDZ(market[0], ba.getBarrierId(), -3);
                        }
                    }
                } else {
                    logger.error("{}==>车辆入场失败!", WeiXinUtils.getSystemDate());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("微信获取用户信息失败", e);
        }
    }

    private JSONObject doEvent(ReceiveXmlEntity receiveXmlEntity, WeiXinUtils.WeiXinPublicNum type,String returnXml) throws Exception {
        logger.info("===>处理事件开始,参数：{},类型:{}", receiveXmlEntity.toString(), type);
        // 区分事件推送
        JSONObject result = new JSONObject();
        String event = receiveXmlEntity.getEvent();
        String openId = receiveXmlEntity.getFromUserName();
        String eventKey = receiveXmlEntity.getEventKey();
        logger.info("请求的openId为:{}", openId);
        UserInfo userInfo = null;
        if (StringUtils.isNotBlank(openId)) {
            if (WeiXinUtils.WeiXinPublicNum.ICAR.equals(type)) {
                userInfo = getUserInfo(openId, icarAppId, icarAppSecret, KEY_ICAR);
            } else if (WeiXinUtils.WeiXinPublicNum.PAIBO.equals(type)) {
                userInfo = getUserInfo(openId, paiboAppId, paiboAppSecret, KEY_PAIBO);
            }
            logger.info("用户信息为==>{}", userInfo.toString());
        }
        switch (event) {
            case "subscribe":
                // 订阅事件 或 未关注扫描二维码事件
                // 返回消息时ToUserName的值与FromUserName的互换
                String mark = eventKey.replace("qrscene_", "").trim();
                if (WeiXinUtils.WeiXinPublicNum.ICAR.equals(type)) {
                    result.put("publicNum", WeiXinUtils.WeiXinPublicNum.ICAR);
                    //添加入场记录
                    doSaveAdmission(userInfo, mark);
                    result.put("result", doResponseByIcar(receiveXmlEntity, 1));
                } else if (WeiXinUtils.WeiXinPublicNum.PAIBO.equals(type)) {
                    result.put("publicNum", WeiXinUtils.WeiXinPublicNum.PAIBO);
                    //关注派勃公众号,返回关注语,显示出入场时间
                    //查询入场记录,更新出场时间，计算停车金额
                    doResponseByPaibo(mark, receiveXmlEntity, result, 1,returnXml);
                }
                break;
            case "unsubscribe":
                // 取消订阅事件
                break;
            case "SCAN":
                // 已关注扫描二维码事件
                // 处理入场
                if (WeiXinUtils.WeiXinPublicNum.ICAR.equals(type)) {
                    result.put("publicNum", WeiXinUtils.WeiXinPublicNum.ICAR);
                    doSaveAdmission(userInfo, eventKey);
                    result.put("result", doResponseByIcar(receiveXmlEntity, 2));
                    //添加入场记录
                } else if (WeiXinUtils.WeiXinPublicNum.PAIBO.equals(type)) {
                    //派勃已关注
                    result.put("publicNum", WeiXinUtils.WeiXinPublicNum.PAIBO);
                    doResponseByPaibo(eventKey, receiveXmlEntity, result, 2,returnXml);
                }
                break;
            case "LOCATION":
                // 上报地理位置事件
                break;
            case "CLICK":
                // 点击菜单拉取消息时的事件推送事件
                if (WeiXinUtils.WeiXinPublicNum.PAIBO.equals(type)) {
                    result.put("publicNum", WeiXinUtils.WeiXinPublicNum.PAIBO);
                    if (receiveXmlEntity.getEventKey().equals("csd")) {
                        String mediaId = "amP7tCi9fdPygahpdJCvt16GHKFUoG-UbNtlwHj09XM";
                        result.put("result", FormatXmlProcess.formatXmlImage(receiveXmlEntity.getFromUserName(), receiveXmlEntity.getToUserName(), mediaId));
                        return result;
                    } else if (receiveXmlEntity.getEventKey().equals("gywm")) {
                        NewsInfo info = new NewsInfo();
                        info.setTitle("玉林派渤及“车派宝”简介");
                        info.setDescription("玉林派渤二手车交易市场有限公司成立于2017年6月，是以经营二手车交易市场、汽车金融、物业管理为核心业务，整车销售、维修服务、客户服务、广告、电商平台为辅的专业汽车运营服务商。");
                        info.setPicUrl("http://mmbiz.qpic.cn/mmbiz_jpg/Iz7Q9eRSCFbprEuMhFD23AWd5tlMicUelGBzN1fp0MUt1c9gHx6KdebQAeOz65X22Gpib3KstUlhYGraMibbBtqmw/0");
                        info.setUrl("https://mp.weixin.qq.com/s/ljqlGe6QBK7817ORE7bC5g");
                        List<NewsInfo> newsInfos = new ArrayList<>(1);
                        newsInfos.add(info);
                        result.put("result", FormatXmlProcess.formatXmlNews(receiveXmlEntity.getFromUserName(), receiveXmlEntity.getToUserName(), newsInfos));
                        return result;
                    }
                } else if (WeiXinUtils.WeiXinPublicNum.ICAR.equals(type)) {
                    if (receiveXmlEntity.getEventKey().equals("gywm")) {
                        String mediaId = "Y4Chx5ZetVR6_rgItZoAaBAM-t-j0wzi5hbdDEPGlcw";
                        result.put("result", FormatXmlProcess.formatXmlImage(receiveXmlEntity.getFromUserName(), receiveXmlEntity.getToUserName(), mediaId));
                        return result;
                    }
                }
                break;
            case "VIEW":
                // 点击菜单跳转链接时的事件推送
                break;
            default:
                break;
        }
        return result;
    }

    private void doResponseByPaibo(String mark,ReceiveXmlEntity receiveXmlEntity,
                                   JSONObject result, int type,String returnXml) throws Exception {
        if (StringUtils.isBlank(receiveXmlEntity.getEventKey())) {
            result.put("result", doResponseByPaiBo(receiveXmlEntity, type, null));
        } else {

            //存redis缓存，并下行检测地感，缓存微信信息2分钟
            String key = UuidUtils.getUUID();
            redisService.set(key,returnXml,120);
            String[] market = mark.split("-");
            Barrier barrier = new Barrier();
            barrier.setId(Integer.parseInt(market[1]));
            barrier.setMarketId(market[0]);
            Barrier ba = barrierService.selectBarrierByIdAndMarketId(barrier);
            checkGroundSense(market[0],ba,key);

        }
    }

    private String doResponseByIcar(ReceiveXmlEntity receiveXmlEntity, int type) throws Exception {
        StringBuilder word = new StringBuilder();
        //进场,推送欢迎标语
        if (type == 1) {
            word.append(welcomeIcar.replace("{FOLLOW}", "关注"));
        } else if (type == 2) {
            word.append(welcomeIcar.replace("{FOLLOW}", "进入"));
        }
        String result = FormatXmlProcess.formatXmlAnswer(receiveXmlEntity.getFromUserName(),
                receiveXmlEntity.getToUserName(), word.toString());
        logger.info("对象转xml字符串,==>{}", result);
        return result;
    }

    @Override
    public String doResponseByPaiBo(ReceiveXmlEntity receiveXmlEntity, int type, JSONObject parking) throws Exception {
        StringBuilder word = new StringBuilder();
        //派勃公众号出场,文本消息
        if (null != parking) {
            if (StringUtils.equals(parking.getString("price"), "0")) {
                if (type == 1) {
                    word.append("您好，欢迎关注派勃汽车公众号。本次停车收费0元。");
                } else if (type == 2) {
                    word.append("您好，欢迎进入派勃汽车公众号。本次停车收费0元。");
                }
                //0元直接下发开闸
                openDZ(parking.getString("marketId"), parking.getString("barrierId"), -1);
            } else {
                StringBuilder url = new StringBuilder();
                url.append("<a href=\"");
                url.append(weixinPayJsUrl);
                url.append("?");
                url.append("marketId=");
                url.append(parking.getString("marketId"));
                url.append("&beforeTime=");
                url.append(parking.getString("beforeTime"));
                url.append("&afterTime=");
                url.append(parking.getString("afterTime"));
                url.append("&hour=");
                url.append(parking.getString("hour"));
                url.append("&minute=");
                url.append(parking.getString("minute"));
                url.append("&second=");
                url.append(parking.getString("second"));
                url.append("&barrierId=");
                url.append(parking.getString("barrierId"));
                url.append("&orderNo=");
                url.append(parking.getString("cardNo"));
                url.append("&price=");
                url.append(parking.getString("price"));
                //url.append("0.01");
                url.append("&accessToken=");
                url.append(cacheTokenInRedis(payAccountAppId, payAccountAppSecret, KEY_PAIBO));
                url.append("&openId=");
                url.append(receiveXmlEntity.getFromUserName());
                url.append("\">点击这里</a>");
                String sendUrl = welcomePaibo.replace("{URL}", url.toString());
                if (type == 1) {
                    word.append(sendUrl.replace("{FOLLOW}", "关注"));
                } else if (type == 2) {
                    word.append(sendUrl.replace("{FOLLOW}", "进入"));
                }
                //客服消息接口触发,直接返回内容
                return word.toString();
            }
        } else {
            if (type == 1) {
                word.append("您好，欢迎关注派勃汽车公众号");
            } else if (type == 2) {
                word.append("您好，欢迎进入派勃汽车公众号。");
            }
        }
        String result = FormatXmlProcess.formatXmlAnswer(receiveXmlEntity.getFromUserName(),
                receiveXmlEntity.getToUserName(), word.toString());
        logger.info("对象转xml字符串,==>{}", result);
        return result;
    }

    @Override
    public InterfaceResult escapeHatch(JSONObject params) throws Exception {
        InterfaceResult result = new InterfaceResult();
        String marketId = params.getString("marketId");
        String remoteAddr = params.getString("remoteAddr");
        Barrier barrier = barrierService.selectBarrierByRemoteHost(remoteAddr,marketId);
        if (StringUtils.isBlank(params.getString("reason"))) {
            result.InterfaceResult600("请输入开闸原因！");
        } else {
            String reason = params.getString("reason");
            OpenBarrierRecord record = new OpenBarrierRecord();
            record.setId(UuidUtils.generateIdentifier());
            record.setBarrierId(barrier.getBarrierId());
            record.setIsValid(1);
            record.setMarketId(marketId);
            record.setReason(reason);
            if (params.containsKey("detailId")) {
                record.setParkingDetailId(params.getString("detailId"));
            }
            int code = openBarrierRecordService.addRecord(record);
            if (code == 1) {
                if (barrier.getInOutType() == 0) {
                    openDZ(marketId, barrier.getBarrierId(), -2);
                } else if (barrier.getInOutType() == 1) {
                    openDZ(marketId, barrier.getBarrierId(), -1);
                }
            }
        }

        return result;
    }

    private void openDZ(String marketId, String barrierId, int type) throws Exception {
        Barrier barrier = barrierService.selectByBarrierId(barrierId);
        String topic = topicService.getTopic(marketId);
        PostParam postParam = new PostParam();
        StringBuilder url = new StringBuilder();
        url.append("/barrier/open/");
        url.append(barrier.getMqttTopic());
        url.append("/");
        url.append(barrierId);
        url.append("/");
        //-1 应急出场 其它为金额
        url.append(type);
        postParam.setUrl(url.toString());
        postParam.setMarket(marketId);
        postParam.setOnlySend(false);
        postParam.setMethod("get");
        postParam.setMessageTime(Constants.dateformat.format(new Date()));
        logger.info("下发开闸信息开始==>{}", JsonTools.toJson(postParam));
        messageProducerService.sendMessage(topic, JsonTools.toJson(postParam), false, 0, Constants.KAFKA_SASS);
    }

    private void checkGroundSense(String marketId, Barrier barrier, String id) {
        String topic = topicService.getTopic(marketId);
        PostParam postParam = new PostParam();
        StringBuilder url = new StringBuilder();
        url.append("/barrier/check/");
        url.append(barrier.getMqttTopic());
        url.append("/");
        url.append(barrier.getBarrierId());
        url.append("/");
        //-4标识下发检测是否压地感
        url.append(-4);
        url.append("/");
        url.append(id);
        postParam.setUrl(url.toString());
        postParam.setMarket(marketId);
        postParam.setOnlySend(false);
        postParam.setMethod("get");
        postParam.setMessageTime(Constants.dateformat.format(new Date()));
        logger.info("下发开闸信息开始==>{}", JsonTools.toJson(postParam));
        messageProducerService.sendMessage(topic, JsonTools.toJson(postParam), false, 0, Constants.KAFKA_SASS);
    }
}
