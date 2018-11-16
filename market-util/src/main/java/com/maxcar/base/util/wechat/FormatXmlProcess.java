package com.maxcar.base.util.wechat;
  
import java.util.Calendar;
import java.util.List;

/**
 * 封装最终的xml格式结果
 * xml一定要连贯,不要出现空格,zz腾讯示例有误，要仔细看
 * @author yangshujun
 * 
 */  
public class FormatXmlProcess {  
    /** 
     * 封装文字类的返回消息 
     * @param to 
     * @param from 
     * @param content 
     * @return 
     */  
    public static String formatXmlAnswer(String to, String from, String content) {
        StringBuffer sb = new StringBuffer();  
        sb.append("<xml><ToUserName><![CDATA[");
        sb.append(to);  
        sb.append("]]></ToUserName><FromUserName><![CDATA[");  
        sb.append(from);  
        sb.append("]]></FromUserName><CreateTime>");  
        sb.append(Calendar.getInstance().getTimeInMillis());
        sb.append("</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[");  
        sb.append(content);  
        sb.append("]]></Content><FuncFlag>0</FuncFlag></xml>");  
        return sb.toString();  
    }  
    /**
     * 事件推送
     * @param to
     * @param from
     * @param event
     * @return
     */
    public static String formatXmlAnswerEvent(String to, String from,String event,String EventKey,String menuId) {
        StringBuffer sb = new StringBuffer();  
        sb.append("<xml><ToUserName><![CDATA[");
        sb.append(to);  
        sb.append("]]></ToUserName><FromUserName><![CDATA[");  
        sb.append(from);  
        sb.append("]]></FromUserName><CreateTime>");  
        sb.append(Calendar.getInstance().getTimeInMillis());
        sb.append("</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[");  
        sb.append(event);  
        sb.append("]]></Event><EventKey><![CDATA["+EventKey+"]]></EventKey><MenuId>"+menuId+"</MenuId></xml>");  
        return sb.toString();  
    }

    /**
     * 下单返回参数统一设定
     * @param resultCode
     * @param resultMsg
     * @return
     */
    public static String formatXmlResponse(String resultCode,String resultMsg){
        StringBuilder sb = new StringBuilder();
        sb.append("<xml><return_code><![CDATA[")
                .append(resultCode).append("]]></return_code><return_msg><![CDATA[")
                .append(resultMsg).append("]]></return_msg></xml>");
        return sb.toString();
    }

    /**
     * 微信被动回复之图片消息
     * @param to
     * @param from
     * @param mediaId
     * @return
     */

    public static String formatXmlImage(String to,String from,String mediaId){
        StringBuilder sb = new StringBuilder();
        sb.append("<xml><ToUserName><![CDATA[");
        sb.append(to);
        sb.append("]]></ToUserName><FromUserName><![CDATA[");
        sb.append(from);
        sb.append("]]></FromUserName><CreateTime>");
        sb.append(Calendar.getInstance().getTimeInMillis());
        sb.append("</CreateTime><MsgType>image</MsgType><Image><MediaId><![CDATA[");
        sb.append(mediaId);
        sb.append("]]></MediaId></Image></xml>");
        System.out.println(sb);
        return  sb.toString();
    }

    /**
     * 微信图文消息推送
     * @param toUser
     * @param fromUser
     * @param newsInfo
     * @return
     */
    public static String formatXmlNews(String toUser, String fromUser, List<NewsInfo> newsInfo){
        StringBuilder sb = new StringBuilder();
        sb.append("<xml><ToUserName><![CDATA[");
        sb.append(toUser);
        sb.append("]]></ToUserName><FromUserName><![CDATA[");
        sb.append(fromUser);
        sb.append("]]></FromUserName><CreateTime>");
        sb.append(Calendar.getInstance().getTimeInMillis());
        sb.append("</CreateTime><MsgType><![CDATA[news]]></MsgType><ArticleCount>");
        sb.append(newsInfo.size());
        sb.append("</ArticleCount><Articles>");
        for (int i=0;i<newsInfo.size();i++){
            NewsInfo info = newsInfo.get(i);
            sb.append("<item><Title><![CDATA[");
            sb.append(info.getTitle());
            sb.append("]]></Title><Description><![CDATA[");
            sb.append(info.getDescription());
            sb.append("]]></Description><PicUrl><![CDATA[");
            sb.append(info.getPicUrl());
            sb.append("]]></PicUrl><Url><![CDATA[");
            sb.append(info.getUrl());
            sb.append("]]></Url></item>");
        }
        sb.append("</Articles></xml>");
        return sb.toString();
    }
}