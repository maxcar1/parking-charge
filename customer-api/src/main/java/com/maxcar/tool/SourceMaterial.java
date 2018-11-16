package com.maxcar.tool;

import com.alibaba.fastjson.JSONObject;
import com.maxcar.base.util.HttpClientUtils;
import com.maxcar.weixin.model.AccessToken;

import java.io.File;

/**
 * 素材相关
 */
public class SourceMaterial {

    private static final String APP_ID = "wx880aa7250a756969";
    private static final String APP_SECRET = "f0103f4f490096af95ecb766a27b3077";

    private static String access_toen_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";

    private static String upload_url = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN&type=image";

    private static String uploadimg_url = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=ACCESS_TOKEN";

    private static String getAccessToken(){
        StringBuilder tokenUrl = new StringBuilder(access_toen_url);
        tokenUrl.append("&appid=").append(APP_ID).append("&secret=").append(APP_SECRET);
        String response = HttpClientUtils.sendGet(tokenUrl.toString());
        System.out.println(response);
        JSONObject json = JSONObject.parseObject(response);
        AccessToken accessToken = JSONObject.toJavaObject(json, AccessToken.class);
        System.out.println(accessToken.getAccess_token());
        return accessToken.getAccess_token();
    }

    /**
     * 图片上传微信
     * @param type 1为新增其他类型永久素材(url和mediaId);2为上传图文消息内的图片获取URL(只返回url)
     */
    private static void addPic(int type){
        String aToken = getAccessToken();
        String res1 = "";
        if (type == 1){
            //图片（image）、语音（voice）、视频（video）和缩略图（thumb）
            String newUrl1 = upload_url.replace("ACCESS_TOKEN",aToken);
            //工具类生成media和url
            //{"media_id":"amP7tCi9fdPygahpdJCvt16GHKFUoG-UbNtlwHj09XM","url":"http://mmbiz.qpic.cn/mmbiz_jpg/Iz7Q9eRSCFbprEuMhFD23AWd5tlMicUelw4bW9frojc2cxvWDia65kFFYdkBp1mgaI6CagUBPZMH7O3STCd4jEdg/0?wx_fmt=jpeg"}
            File file1 = new File("C:\\Users\\Administrator\\Desktop\\0.jpg");
            res1 = HttpClientUtils.sendPost(newUrl1,file1);
        }else if (type == 2){
            String newUrl1 = uploadimg_url.replace("ACCESS_TOKEN",aToken);
            //工具类生成url
            //http://mmbiz.qpic.cn/mmbiz_jpg/Iz7Q9eRSCFbprEuMhFD23AWd5tlMicUelGBzN1fp0MUt1c9gHx6KdebQAeOz65X22Gpib3KstUlhYGraMibbBtqmw/0
            File file1 = new File("C:\\Users\\Administrator\\Desktop\\2.jpg");
            res1 = HttpClientUtils.sendPost(newUrl1,file1);
        }

        System.out.println(res1);
    }

    public static void main(String[] args){
        addPic(2);
    }

}
