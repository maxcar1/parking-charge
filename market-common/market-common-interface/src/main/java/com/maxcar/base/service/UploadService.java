package com.maxcar.base.service;

import com.maxcar.base.pojo.InterfaceResult;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
	//上传文件流
    InterfaceResult uploadFileStream(MultipartFile file, String fileName);

    InterfaceResult upload(MultipartFile file);

	InterfaceResult download(String url);

	InterfaceResult delete(String url);

	InterfaceResult upload(MultipartFile file, String isBackup, String isTimeClear);
    
}
