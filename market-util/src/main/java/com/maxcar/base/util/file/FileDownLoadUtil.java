package com.maxcar.base.util.file;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileDownLoadUtil {

	/**
	 * 下载远程文件并保存到本地
	 * @param remoteFilePath 远程文件路径
	 * @param localFilePath 本地文件路径
	 */
	public static void downloadFile(String remoteFilePath, String localFilePath,String fileName) {
		URL urlfile = null;
		HttpURLConnection httpUrl = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			File f = new File(localFilePath);
			if (!f.exists()) {
				FileUtils.forceMkdir(f);
			}
			urlfile = new URL(remoteFilePath);
			httpUrl = (HttpURLConnection)urlfile.openConnection();
			httpUrl.connect();
			bis = new BufferedInputStream(httpUrl.getInputStream());
			bos = new BufferedOutputStream(new FileOutputStream(localFilePath+"/"+fileName));
			int len = 2048;
			byte[] b = new byte[len];
			while ((len = bis.read(b)) != -1)
			{
				bos.write(b, 0, len);
			}
			bos.flush();
			bis.close();
			httpUrl.disconnect();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				bis.close();
				bos.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	public static void main(String []args){
//		downloadFile("http://localhost:9995/upload/cv/100/1492157280385/17041416080038595035.doc","d:/temp/a.doc");
	}

}
