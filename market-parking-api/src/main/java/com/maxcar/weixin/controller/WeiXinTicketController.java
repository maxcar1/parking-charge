package com.maxcar.weixin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.util.StringUtils;
import com.maxcar.base.util.wechat.WeiXinUtils;
import com.maxcar.market.service.ParkingFeeService;
import com.maxcar.websocket.server.WebSocketServer;
import com.maxcar.weixin.service.WeiXinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 微信二维码生成控制器，包含岗亭进场二维码和支付二维码
 * yangshujun
 */
@RestController
@RequestMapping("/api-p/wx")
public class WeiXinTicketController {
    @Autowired
    private WeiXinService weiXinService;
    @Autowired
    private ParkingFeeService parkingFeeService;

    /**
     * 获取商管家二维码
     * @param codeType 1: 临时二维码  2: 永久二维码
     * @param sceneId 场景值ID  32位非0整数
     * @param publicNum 公众号名称
     * @return
     */
    @GetMapping("/ticket/{codeType}/{sceneId}/{publicNum}")
    public Object generorTicket(@PathVariable("codeType")Integer codeType,
                                @PathVariable("sceneId")String sceneId,
                                @PathVariable("publicNum")String publicNum){
        InterfaceResult interfaceResult = new InterfaceResult();
        String msg = "";
        if (codeType != 1 && codeType != 2){
            msg = "codeType传值错误";
            interfaceResult.InterfaceResult600(msg);
            return interfaceResult;
        }
        try {
            String result = weiXinService.getTicket(codeType,sceneId,publicNum);
            if(StringUtils.equals(result,WeiXinUtils.WeiXinStatus.INVALID_TOKEN.getCode())
                    || StringUtils.equals(result,WeiXinUtils.WeiXinStatus.TICKET_FAIL.getCode())
                    || StringUtils.equals(result,WeiXinUtils.WeiXinStatus.TICKET_JSON_FAIL.getCode())){
                interfaceResult.InterfaceResult500(WeiXinUtils.WeiXinStatus.getValue(result));
            }else {
                interfaceResult.InterfaceResult200(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
            interfaceResult.InterfaceResult500();
        }
        return interfaceResult;
    }

    @GetMapping("/pay/{productId}")
    public Object getPayQRCode(@PathVariable("productId") String productId){
        InterfaceResult interfaceResult = new InterfaceResult();
        try {
            interfaceResult = weiXinService.getPayQRCode(productId);
        }catch (Exception e){
            e.printStackTrace();
            interfaceResult.InterfaceResult500();
        }
        return interfaceResult;
    }

    /**
     * 刷卡进场，unionId上行通知
     * post请求兼容get，post读取imageUrl
     * @param marketId
     * @param cardNo
     * @return
     * @throws Exception
     */
    @PostMapping("/in/{marketId}/{cardNo}/{barrierId}/{type}")
    public Object saveInParking(@PathVariable("marketId") String marketId,
                                @PathVariable("cardNo") String cardNo,
                                @PathVariable("barrierId") String barrierId,
                                @PathVariable("type") Integer type,
                                @RequestBody JSONObject params){
        try {
            params.put("marketId",marketId);
            params.put("cardNo",cardNo);
            params.put("barrierId",barrierId);
            params.put("type",type);
            InterfaceResult result = parkingFeeService.saveInParking(params);
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 刷卡出场，扫码上行
     * @param marketId
     * @param key
     * @param barrierId
     * @param type
     * @return
     * @throws Exception
     */
    @GetMapping("/out/{cardNo}/{barrierId}/{type}/{marketId}")
    public Object updateParkingDetail(@PathVariable("marketId") String marketId,
                                @PathVariable("key") String key,
                                @PathVariable("barrierId") String barrierId,
                                @PathVariable("type") Integer type) throws Exception{
        InterfaceResult result = parkingFeeService.updateParkingDetail(marketId,key,barrierId,type);
        if (StringUtils.equals(result.getCode(),"200")){
            JSONObject json = (JSONObject)JSONObject.toJSON(result.getData());
            //推送刷卡出场信息

            WebSocketServer.sendInfo(JSON.toJSONString(json),barrierId);
        }
        return result;
    }

}
