package com.maxcar.base.util.wechat;

import com.maxcar.base.util.RedisUtil;
import com.maxcar.base.util.SerializeUtil;
import com.maxcar.base.util.StringUtils;
import com.maxcar.base.util.dasouche.TimeStampUtils;
import com.maxcar.base.util.wechat.entity.ExtAccessToken;
import com.maxcar.base.util.wechat.entity.ResponseContent;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

/**
 * 微信jsApi签名服务
 *
 * @author ldc
 */

public class JsApiService extends BaseService {

    /**
     * 微信端请求jsapi签名 商管家版
     *
     * @param params
     * @return
     * @throws Exception
     */
    public Object getJsApi(JSONObject params) throws Exception {
        //Logger.getRootLogger().info("getJsApi--params》"+params.toString());
        ResponseContent content = new ResponseContent();
        String url = null;
        String market = null;
        try {
            url = params.getString("url");
            url = url.trim();
        } catch (Exception e) {
            content.setMessage("url地址缺失!");
            content.setResultCode(1);
            return content;
        }
        try {
            market = params.getString("market");
        } catch (Exception e) {
            content.setMessage("market值缺失!");
            content.setResultCode(1);
            return content;
        }
        String weToken = "we_tenant_" + market;
        byte[] weTokenByte = SerializeUtil.serialize(weToken);
        if (weTokenByte == null) {
            return ResponseContent.error(1, "序列化异常");
        }
        String timestamp = TimeStampUtils.getTimeM();
        //自定义随机字符串
        String noncestr = "suiJiZiFuChuanMaxCar";
        StringBuffer sb = new StringBuffer();
        sb.append("jsapi_ticket=");

        Logger.getRootLogger().info("getjsapi:market->" + market);
        byte[] extByte = RedisUtil.getInstance().strings().get(weTokenByte);
        if (extByte == null) {
            content.setMessage("当前市场尚未配置jsapi授权");
            content.setResultCode(2);
            return content;
        }

        ExtAccessToken ext = (ExtAccessToken) SerializeUtil.unserialize(extByte);
        if (ext == null) {
            return ResponseContent.error(1, "反序列化异常");
        }
        if (StringUtils.isNotBlank(ext.getJsapi_ticket())) {
            Logger.getRootLogger().info("jsapi: " + ext.getJsapi_ticket());
            sb.append(ext.getJsapi_ticket());
        } else {
            sb.append(TokenThread.jsapi_ticket_tenant);
        }

        sb.append("&noncestr=");
        sb.append(noncestr);
        sb.append("&timestamp=");
        sb.append(timestamp);
        sb.append("&url=");
        sb.append(url);
        Logger.getRootLogger().info("加密前的组装：" + sb.toString());
        String signature = SHAUtil.getSha1(sb.toString());
        Logger.getRootLogger().info("加密后的签名：" + signature);
        JSONObject obj = new JSONObject();
        obj.put("noncestr", noncestr);
        obj.put("signature", signature);
        obj.put("timestamp", timestamp);
        if (StringUtils.isNotBlank(ext.getAppid())) {
            obj.put("appId", ext.getAppid());
        } else {
            obj.put("appId", TokenThread.appIdTenant);
        }
        Logger.getRootLogger().info("商户版签完名的数据-->" + obj.toString());
        content.setDatas(obj);
        return content;
    }

}
