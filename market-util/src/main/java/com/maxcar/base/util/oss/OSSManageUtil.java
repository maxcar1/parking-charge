package com.maxcar.base.util.oss;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.maxcar.base.util.file.FilePojo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/** 
 * 对OSS服务器进行上传删除等的处理 
 *  
 * @ClassName: OSSManageUtil 
 * @Description: 
 * @author
 * @date
 *  
 */  
public class OSSManageUtil {
    Logger logger = LoggerFactory.getLogger(OSSManageUtil.class);
   /**
    * 
    * @param fileContent   流
    * @param remotePath	   二级目录
    * @param fileName
    * @return
    * @throws Exception
    */
    public static String uploadFile(InputStream fileContent, String remotePath,String fileName) throws Exception {
        //随机名处理  
        fileName = "maxcar_" + System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf("."));
        // 加载配置文件，初始化OSSClient  
        OSSConfigure ossConfigure = new OSSConfigure("oos.properties");
        //noinspection AliDeprecation
        /*OSSClient ossClient = new OSSClient(ossConfigure.getEndpoint(), ossConfigure.getAccessKeyId(),
                ossConfigure.getAccessKeySecret());*/
        OSSClient ossClient = new OSSClient(ossConfigure.getEndpoint(),
                new DefaultCredentialProvider(ossConfigure.getAccessKeyId(),ossConfigure.getAccessKeySecret()),new ClientConfiguration());
        // 定义二级目录  
        String remoteFilePath = remotePath.substring(0, remotePath.length()).replaceAll("\\\\", "/") + "/";  
        // 创建上传Object的Metadata  
        ObjectMetadata objectMetadata = new ObjectMetadata();  
        objectMetadata.setContentLength(fileContent.available());  
        objectMetadata.setContentEncoding("utf-8");  
        objectMetadata.setCacheControl("no-cache");  
        objectMetadata.setHeader("Pragma", "no-cache");  
        objectMetadata.setContentType(contentType(fileName.substring(fileName.lastIndexOf("."))));  
        objectMetadata.setContentDisposition("inline;filename=" + fileName);  
        // 上传文件  
        ossClient.putObject(ossConfigure.getBucketName(), remoteFilePath + fileName, fileContent, objectMetadata);  
        // 关闭OSSClient  
        ossClient.shutdown();  
        // 关闭io流  
        fileContent.close();  
        //return ossConfigure.getAccessUrl() + "/" + remoteFilePath + fileName;  
        //return "https://maxcar-pic.oss-cn-hangzhou.aliyuncs.com/market-car/" + fileName;
        return "https://"+ossConfigure.getBucketName()+".oss-cn-hangzhou.aliyuncs.com/"+remoteFilePath+fileName;
    }

    // 上传文件
    public static FilePojo uploadFile(FilePojo filePojo) throws OSSException, Exception,
            FileNotFoundException {
        File file = new File(filePojo.getPath());

        InputStream input = new FileInputStream(file);
        String ossPath = uploadFile(input,filePojo.getOssPath(),filePojo.getName());
        filePojo.setOssPath(ossPath);
        return filePojo;
    }

    // 下载文件  
    @SuppressWarnings("unused")
    /**
     * @param ossConfigure
     * @param key    url:https://maxcar-pic.oss-cn-hangzhou.aliyuncs.com/market-car/测试.png
     * 				 key:market-car/测试.png
     * @param filename   文件存储路径+文件名
     * @throws OSSException
     * @throws ClientException
     * @throws IOException
     */
    public static void downloadFile(String key, String filename)  
            throws OSSException, ClientException, IOException {
    	OSSConfigure ossConfigure = new OSSConfigure("oos.properties");  
        // 初始化OSSClient  
        //noinspection AliDeprecation
        OSSClient ossClient = new OSSClient(ossConfigure.getEndpoint(),
                new DefaultCredentialProvider(ossConfigure.getAccessKeyId(),ossConfigure.getAccessKeySecret()),new ClientConfiguration());
        /*OSSClient ossClient = new OSSClient(ossConfigure.getEndpoint(), ossConfigure.getAccessKeyId(),
                ossConfigure.getAccessKeySecret()); */
        OSSObject object = ossClient.getObject(ossConfigure.getBucketName(), key);  
        // 获取ObjectMeta  
        ObjectMetadata meta = object.getObjectMetadata();  
  
        // 获取Object的输入流  
        InputStream objectContent = object.getObjectContent();  
  
        ObjectMetadata objectData = ossClient.getObject(new GetObjectRequest(ossConfigure.getBucketName(), key),  
                new File(filename));  
        // 关闭数据流  
        objectContent.close();  
  
    }  
  

    /**
     * 
     * @param key
     * @throws IOException
     */
    public static void deleteFile(String key) throws IOException {  
        // 加载配置文件，初始化OSSClient  
        OSSConfigure ossConfigure = new OSSConfigure("oos.properties");
        //noinspection AliDeprecation
        OSSClient ossClient = new OSSClient(ossConfigure.getEndpoint(),
                new DefaultCredentialProvider(ossConfigure.getAccessKeyId(),ossConfigure.getAccessKeySecret()),new ClientConfiguration());
        /*OSSClient ossClient = new OSSClient(ossConfigure.getEndpoint(), ossConfigure.getAccessKeyId(),
                ossConfigure.getAccessKeySecret()); */
        //filePath=filePath.substring(45);  
        ossClient.deleteObject(ossConfigure.getBucketName(), key);  
  
    }  
  
    /** 
     * Description: 判断OSS服务文件上传时文件的contentType @Version1.0
     *
     * @param FilenameExtension 
     *            文件后缀 
     * @return String 
     */  
    public static String contentType(String FilenameExtension) {  
        if (FilenameExtension.equals(".BMP") || FilenameExtension.equals(".bmp")) {  
            return "image/bmp";  
        }  
        if (FilenameExtension.equals(".GIF") || FilenameExtension.equals(".gif")) {  
            return "image/gif";  
        }  
        if (FilenameExtension.equals(".JPEG") || FilenameExtension.equals(".jpeg") || FilenameExtension.equals(".JPG")  
                || FilenameExtension.equals(".jpg") || FilenameExtension.equals(".PNG")  
                || FilenameExtension.equals(".png")) {  
            return "image/jpeg";  
        }  
        if (FilenameExtension.equals(".HTML") || FilenameExtension.equals(".html")) {  
            return "text/html";  
        }  
        if (FilenameExtension.equals(".TXT") || FilenameExtension.equals(".txt")) {  
            return "text/plain";  
        }  
        if (FilenameExtension.equals(".VSD") || FilenameExtension.equals(".vsd")) {  
            return "application/vnd.visio";  
        }  
        if (FilenameExtension.equals(".PPTX") || FilenameExtension.equals(".pptx") || FilenameExtension.equals(".PPT")  
                || FilenameExtension.equals(".ppt")) {  
            return "application/vnd.ms-powerpoint";  
        }  
        if (FilenameExtension.equals(".DOCX") || FilenameExtension.equals(".docx") || FilenameExtension.equals(".DOC")  
                || FilenameExtension.equals(".doc")) {  
            return "application/msword";  
        }  
        if (FilenameExtension.equals(".XML") || FilenameExtension.equals(".xml")) {  
            return "text/xml";  
        }  
        if (FilenameExtension.equals(".apk") || FilenameExtension.equals(".APK")) {  
            return "application/octet-stream";  
        }  
        return "text/html";  
    }  
}  