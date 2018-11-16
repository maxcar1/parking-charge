package com.maxcar.kafka.util;

import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/** 
 * HttpClient4.5.1工具类 
 * @author ldc 
 * 
 */  
public class HttpClientUtils {  
      
    /** 
     * post请求传输String参数 
     * 例如：name=Jack&sex=1&type=2 
     * Content-type:application/x-www-form-urlencoded 
     * @param url 
     *            url地址 
     * @param strParam 
     *            参数
     *            不需要返回结果 
     * @return 
     */  
    public static JSONObject httpPost(String url, String strParam) {  
        // post请求返回结果  
        CloseableHttpClient httpClient = HttpClients.createDefault();  
        JSONObject jsonResult = null;  
        HttpPost httpPost = new HttpPost(url);
        // 设置请求和传输超时时间  
        RequestConfig requestConfig = RequestConfig.custom()  
                .setSocketTimeout(2000).setConnectTimeout(2000).build();  
        httpPost.setConfig(requestConfig); 
        try {  
            if (null != strParam) {  
                // 解决中文乱码问题  
                StringEntity entity = new StringEntity(strParam,"utf-8");  
                entity.setContentEncoding("UTF-8");  
                entity.setContentType("application/x-www-form-urlencoded"); 
                httpPost.setEntity(entity);  
            }  
            CloseableHttpResponse result = httpClient.execute(httpPost);  
            //请求发送成功，并得到响应  
            if (result.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {  
                String str = "";  
                try {  
                    //读取服务器返回过来的json字符串数据  
                    str = EntityUtils.toString(result.getEntity(), "utf-8");  
                    //把json字符串转换成json对象  
                    jsonResult = JSONObject.fromObject(str);  
                } catch (Exception e) {  
                	Logger.getRootLogger().error(e); 
                }  
            }  
        } catch (IOException e) {  
        	Logger.getRootLogger().error(e);  
        } finally {  
            httpPost.releaseConnection();  
        }  
        return jsonResult;  
    }  
    
    /** 
     * 发送get请求 
     *  
     * @param url 
     *            路径 
     * @return 
     */  
    public static JSONObject httpGet(String url,String header,String headName) {  
        // get请求返回结果  
        JSONObject jsonResult = null;  
        CloseableHttpClient client = HttpClients.createDefault(); 
        
        // 发送get请求  
        HttpGet request = new HttpGet(url); 
        if(null != header && !("".equals(header))){
        	 request.setHeader(headName, header);
        }
        // 设置请求和传输超时时间  
        RequestConfig requestConfig = RequestConfig.custom()  
                .setSocketTimeout(2000).setConnectTimeout(2000).build();  
        request.setConfig(requestConfig);  
        try {  
            CloseableHttpResponse response = client.execute(request);  
  
            //请求发送成功，并得到响应  
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {  
                //读取服务器返回过来的json字符串数据  
                HttpEntity entity = response.getEntity();  
                String strResult = EntityUtils.toString(entity, "utf-8");  
                //把json字符串转换成json对象  
                jsonResult = JSONObject.fromObject(strResult);  
            } else {  
            	Logger.getRootLogger().error("请求失败!");
            }  
        } catch (IOException e) {  
        	Logger.getRootLogger().error(e);
        } finally {  
            request.releaseConnection();  
        }  
        return jsonResult;  
    } 
    
    /**
     * ajxa  post请求
     */
    public static String sendPostAjax(String url,String requestMethod,String param) { 
		OutputStreamWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
			// 发送POST请求必须设置如下两行
			if(null != requestMethod  && "POST".equals(requestMethod)){
				conn.setDoOutput(true);
				conn.setDoInput(true);
			}
			// 获取URLConnection对象对应的输出流
			out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			// 发送请求参数
			out.write(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				Logger.getRootLogger().error(ex);
			}
		}
		return result;
       } 
    
    
    /**
     * 设置HttpConnection的post请求
     */
    public static String sendAjax(String url,String requestMethod,String param) { 
		OutputStreamWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			HttpURLConnection conn = (HttpURLConnection)realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
			// 发送POST请求必须设置如下两行
			conn.setRequestMethod(requestMethod);// 提交模式
			if(null != requestMethod && ("POST").equals(requestMethod)){
				conn.setDoOutput(true);
				conn.setDoInput(true);
			}
			
			// 获取URLConnection对象对应的输出流
			out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			// 发送请求参数
			out.write(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				Logger.getRootLogger().error(ex);
			}
		}
		return result;
   } 
     

	public static String doPostJson(String url, String json) {
		// 创建Httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		String resultString = "";
		try {
			// 创建Http Post请求
			HttpPost httpPost = new HttpPost(url);
			// 创建请求内容
			StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
			httpPost.setEntity(entity);
			// 执行http请求
			response = httpClient.execute(httpPost);
			resultString = EntityUtils.toString(response.getEntity(), "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

		return resultString;
	}

	/**
	 * PUT基础请求
	 */
	public static String doPutJson(String url, String json) {
		// 创建Httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		String resultString = "";
		try {
			// 创建Http Put请求
			HttpPut httpPut = new HttpPut(url);
			// 创建请求内容
			StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
			httpPut.setEntity(entity);
			httpPut.setHeader("syncKafka", "syncKafka");
			// 执行http请求
			response = httpClient.execute(httpPut);
			resultString = EntityUtils.toString(response.getEntity(), "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return resultString;
	}
      
}  