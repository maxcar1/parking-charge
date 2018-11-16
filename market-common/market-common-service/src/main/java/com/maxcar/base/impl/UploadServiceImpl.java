package com.maxcar.base.impl;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.service.UploadService;
import com.maxcar.base.util.oss.OSSManageUtil;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

//import com.maxcar.core.exception.ResultCode;

@Service("uploadService")
public class UploadServiceImpl implements UploadService {

	public UploadServiceImpl(){
		System.out.print(111);
	}

	private static String BACKUP = "1";
	private static String TIMECLEAR = "1";
	
    @Override
    public InterfaceResult uploadFileStream(MultipartFile file, String fileName) {
		InterfaceResult result = new InterfaceResult();
        String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
        // accessKey请登录https://ak-console.aliyun.com/#/查看
        
        
        
        String accessKeyId = "LTAIG2Gjf74uxcYN";
        String accessKeySecret = "7Iqd0TDHEQuvEQWB8oXif2hj3nAoui";
        //创建OSSClient实例
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        try{
            // 上传
            InputStream inputStream = file.getInputStream();
            ossClient.putObject( "maxcar-pic", "market-car/"+fileName, inputStream);
            String url = "https://maxcar-pic.oss-cn-hangzhou.aliyuncs.com/market-car/" + fileName;
            result.InterfaceResult200(url);
//            result.setDatas(url);
//            result.setResultCode(ResultCode.CREATE_SUCCESS.getCode());
            return result;

        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message: " + oe.getErrorCode());
            System.out.println("Error Code:       " + oe.getErrorCode());
            System.out.println("Request ID:      " + oe.getRequestId());
            System.out.println("Host ID:           " + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ce.getMessage());
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            if (ossClient != null) {
            	ossClient.shutdown();
            }
        }

        return result;
    }

	@Override
	public InterfaceResult upload(MultipartFile file) {
		InterfaceResult result = new InterfaceResult();
		 // 流转换 将MultipartFile转换为oss所需的InputStream  
		try {
			CommonsMultipartFile cf = (CommonsMultipartFile)file;  
			DiskFileItem fi = (DiskFileItem) cf.getFileItem(); 
			InputStream fileContent = fi.getInputStream();  
			String fileName = fi.getName();  
			String url = OSSManageUtil.uploadFile(fileContent, "market-car",fileName);
			result.InterfaceResult200(url);
			//System.out.println(result);
			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
	}


	public InterfaceResult download(String url) {
        InterfaceResult result = new InterfaceResult();
		String[] strings = url.split("/");
		//获取文件key值
		String key = strings[strings.length-2]+"/"+strings[strings.length-1];
		String tomcatPath = System.getProperty("catalina.home");
		String filename = tomcatPath+"\\upAndDownFile\\download\\" + strings[strings.length-1];
		File file = new File(tomcatPath+"\\upAndDownFile\\download");
		if(!file .exists()  && !file .isDirectory()){
			file .mkdir();  
		} 
		//System.out.println(key+","+filename);
		try {
			OSSManageUtil.downloadFile(key, filename);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public InterfaceResult delete(String url) {
        InterfaceResult result = new InterfaceResult();
		String[] strings = url.split("/");
		//获取文件key值
		String key = strings[strings.length-2]+"/"+strings[strings.length-1];
		try {
			OSSManageUtil.deleteFile(key);
//			result.setResultCode(ResultCode.DELETE_SUCCESS.getCode());
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public InterfaceResult upload(MultipartFile file, String isBackup, String isTimeClear) {
		System.out.println(isBackup+","+isTimeClear);
		String path = "";
        InterfaceResult result = new InterfaceResult();
		CommonsMultipartFile cf = (CommonsMultipartFile)file;
		DiskFileItem fi = (DiskFileItem) cf.getFileItem();
		String fileName = fi.getName();
		if (BACKUP.equals(isBackup)) {
			//直接上传文件至OSS
			result = upload(file);
		}else{
			if (TIMECLEAR.equals(isTimeClear)) {
				String tomcatPath = System.getProperty("catalina.home");
				path = tomcatPath+"\\upAndDownFile\\notTimeClear\\"+getDate()+"\\"+fileName;
				System.out.println(tomcatPath+","+path);
				//缓存文件至不定时清除文件夹
			}else{
				String tomcatPath = System.getProperty("catalina.home");
				//path = tomcatPath+"\\upAndDownFile\\isTimeClear\\"+fileName;
				path = tomcatPath+"\\upAndDownFile\\isTimeClear\\"+getDate()+"\\"+fileName;
				System.out.println(tomcatPath+","+path);
				//缓存文件至定时清除文件夹
			}
			//执行缓存方法
			multipleSave(file, path);
			//上传文件至OSS
			result = upload(file);
		}
		return result;
//		return null;
	}
	//获得当前日期
	private String getDate() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String day = sdf.format(date);
		return day;
	}

	//复制文件至缓存文件夹
	public void multipleSave(MultipartFile file,String path){
		File desFile = new File(path);
	    try {
			FileUtils.copyInputStreamToFile(file.getInputStream(),desFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
/**
 * sts.access.key.id=LTAIdC42qTdapJIc
sts.access.key.secret=d1C4wc8oM0G5cYzL17PmfIZ87UrklQ
sts.role.arn=acs:ram::1434797575195965:role/maxcar-app
sts.endpoint=http://oss-cn-hangzhou.aliyuncs.com
sts.bucket=maxcar-pic
sts.api.version=2015-04-01
region.cn.hangzhou=cn-hangzhou
keep.path=/usr/local/images
 */
