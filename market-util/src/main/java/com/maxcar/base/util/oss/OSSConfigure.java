package com.maxcar.base.util.oss;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class OSSConfigure {    
    
    private String endpoint;    
    private String accessKeyId;    
    private String accessKeySecret;    
    private String bucketName;    
    private String accessUrl;    
    
    public OSSConfigure() {    
    
    }    
    
    /**  
     * 通过配置文件.properties文件获取，这几项内容。  
     *   
     * @param storageConfName  
     * @throws IOException  
     */    
    public OSSConfigure(String storageConfName) throws IOException {    
    
        Properties prop = new Properties();   
        InputStream is= this.getClass().getClassLoader().getResourceAsStream(storageConfName);  
        prop.load(is);    
    
        endpoint = prop.getProperty("Endpoint").trim();    
        accessKeyId = prop.getProperty("AccessKey").trim();    
        accessKeySecret = prop.getProperty("AccessKeySecret").trim();    
        bucketName = prop.getProperty("BucketName").trim();    
        accessUrl = prop.getProperty("accessUrl").trim();    
    
    }    
    
    public OSSConfigure(String endpoint, String accessKeyId,
                        String accessKeySecret, String bucketName, String accessUrl) {
    
        this.endpoint = endpoint;    
        this.accessKeyId = accessKeyId;    
        this.accessKeySecret = accessKeySecret;    
        this.bucketName = bucketName;    
        this.accessUrl = accessUrl;    
    }    
    
    public String getEndpoint() {    
        return endpoint;    
    }    
    
    public void setEndpoint(String endpoint) {    
        this.endpoint = endpoint;    
    }    
    
    public String getAccessKeyId() {    
        return accessKeyId;    
    }    
    
    public void setAccessKeyId(String accessKeyId) {    
        this.accessKeyId = accessKeyId;    
    }    
    
    public String getAccessKeySecret() {    
        return accessKeySecret;    
    }    
    
    public void setAccessKeySecret(String accessKeySecret) {    
        this.accessKeySecret = accessKeySecret;    
    }    
    
    public String getBucketName() {    
        return bucketName;    
    }    
    
    public void setBucketName(String bucketName) {    
        this.bucketName = bucketName;    
    }    
    
    public String getAccessUrl() {    
        return accessUrl;    
    }    
    
    public void setAccessUrl(String accessUrl) {    
        this.accessUrl = accessUrl;    
    }
}