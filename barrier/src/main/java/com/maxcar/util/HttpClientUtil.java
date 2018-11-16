package com.maxcar.util;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * https连接工具类
 * @author ldc
 *
 */
public class HttpClientUtil { 
	
    // 连接超时时间
    private static final int CONNECTION_TIMEOUT = 3000;//3秒
    // 读数据超时时间
    //private static final int READ_DATA_TIMEOUT = 6000;//6秒
     
    private static PoolingHttpClientConnectionManager connManager = null;
    private static CloseableHttpClient httpclient = null;
    static{
        connManager = new PoolingHttpClientConnectionManager();
        httpclient = HttpClients.custom().setConnectionManager(connManager).build();
    
    }
    
    /**
     * Post请求
     * @param url
     * @param connectTimeout
     * @param readTimeout
     * @param data
     * @param encoding
     * @return
     * @throws IOException 
     * @throws ParseException 
     */
    public static String post(String url, int connectTimeout, int readTimeout,Map<String, Object> data, String encoding) throws IOException{
        HttpPost post = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(readTimeout)
                .setConnectTimeout(connectTimeout)
                .setConnectionRequestTimeout(connectTimeout)
                .setExpectContinueEnabled(false).build();
        post.setConfig(requestConfig);
         
        if (null!=data && !data.isEmpty()) {
             List<NameValuePair> formparams = new ArrayList<NameValuePair>();
             for (String key : data.keySet()){
                 formparams.add(new BasicNameValuePair(key, data.get(key).toString()));
             }
             UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(formparams, encoding);
             post.setEntity(formEntity);
        }
        CloseableHttpResponse response = null;
        if(url.startsWith("https")){//https
            response = createSSLClient().execute(post);
        }else{
            response = httpclient.execute(post);
        }
         
        HttpEntity entity = response.getEntity();
        try {
            if(entity != null){
                 String str = EntityUtils.toString(entity, encoding);
                 return str;
           }
        }finally {
            if(entity != null){
               entity.getContent().close();
           }
           if(response != null){
               response.close();
           }
       }
        return null;
    }
    
    /**
     * get请求（默认超时时间）
     * @param url
     * @param encoding
     * @param encoding
     * @return
     */
    //get请求
    public static String get(String url, String encoding,String sign) throws IOException {
        HttpGet get = new HttpGet(url);
        if(null != sign){
        	get.addHeader("x-izayoi-sign", sign);
        }
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(CONNECTION_TIMEOUT)
                .setConnectTimeout(CONNECTION_TIMEOUT)
                .setConnectionRequestTimeout(CONNECTION_TIMEOUT)
                .setExpectContinueEnabled(false).build();
        get.setConfig(requestConfig);
       /*if (cookies !=null && !cookies.isEmpty()){
            StringBuilder buffer = new StringBuilder(128);
            for (String cookieKey : cookies.keySet()){
                buffer.append(cookieKey).append("=").append(cookies.get(cookieKey)).append("; ");
            }
           // 设置cookie内容
            get.setHeader(new BasicHeader("Cookie",buffer.toString()));
        }*/
       CloseableHttpResponse response = null;
        if(url.startsWith("https")){//https
            response = createSSLClient().execute(get);
        }else{
            response = httpclient.execute(get);
        }
        HttpEntity entity = response.getEntity();
        try {
             if(entity != null){
                  String str = EntityUtils.toString(entity, encoding);
                  return str;
            }
        }finally {
            if(entity != null){
                entity.getContent().close();
            }
            if(response != null){
                response.close();
            }
        }
        return null;
    }
    
    /**获取sslclient
     * 
     * @return
     */
    @SuppressWarnings("deprecation")
	public static CloseableHttpClient createSSLClient() {
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                //信任所有
                public boolean isTrusted(X509Certificate[] chain,String authType) throws CertificateException {
                    return true;
                }
            }).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext ,SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        }catch (Exception e) {
//        	Logger.getRootLogger().error(e, e);
        }
        return  HttpClients.createDefault();
    }
    
    /**
     * map转成queryStr
     * @param paramMap
     * @return
     * @throws UnsupportedEncodingException 
     */
    public static String mapToQueryStr(Map<String,String> paramMap) {
        StringBuffer strBuff = new StringBuffer();
        for (String key : paramMap.keySet()) {
            strBuff.append(key).append("=").append(paramMap.get(key)).append("&");
        }
        return strBuff.substring(0, strBuff.length()-1);
    }
}  