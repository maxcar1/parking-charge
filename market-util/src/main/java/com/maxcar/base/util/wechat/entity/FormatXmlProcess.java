package com.maxcar.base.util.wechat.entity;

import java.util.Date;

/**
 * 封装最终的xml格式结果
 *
 * @author ldc
 */
public class FormatXmlProcess {
    /**
     * 封装文字类的返回消息
     *
     * @param to
     * @param from
     * @param content
     * @return
     */
    public String formatXmlAnswer(String to, String from, String content) {
        StringBuffer sb = new StringBuffer();
        Date date = new Date();
        sb.append("<xml><ToUserName><![CDATA[");
        sb.append(to);
        sb.append("]]></ToUserName><FromUserName><![CDATA[");
        sb.append(from);
        sb.append("]]></FromUserName><CreateTime>");
        sb.append(date.getTime());
        sb.append("</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[");
        sb.append(content);
        sb.append("]]></Content><FuncFlag>0</FuncFlag></xml>");
        return sb.toString();
    }

    /**
     * 事件推送
     *
     * @param to
     * @param from
     * @param content
     * @return
     */
    public String formatXmlAnswerEvent(String to, String from, String event, String EventKey, String menuId) {
        StringBuffer sb = new StringBuffer();
        Date date = new Date();
        sb.append("<xml><ToUserName><![CDATA[");
        sb.append(to);
        sb.append("]]></ToUserName><FromUserName><![CDATA[");
        sb.append(from);
        sb.append("]]></FromUserName><CreateTime>");
        sb.append(date.getTime());
        sb.append("</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[");
        sb.append(event);
        sb.append("]]></Event><EventKey><![CDATA[" + EventKey + "]]></EventKey><MenuId>" + menuId + "</MenuId></xml>");
        return sb.toString();
    }


}