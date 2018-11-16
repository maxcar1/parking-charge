package com.maxcar.base.util.file;


import com.maxcar.base.util.oss.OSSManageUtil;
import org.apache.commons.io.FileUtils;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class FileUploadUtil {
	private static Logger logger = LoggerFactory.getLogger(FileUploadUtil.class);
	/**
	 * 普通
	 * @param mfile
	 * @param filePojo
	 * @return
	 */
	public static FilePojo uploadFileCommon(MultipartFile  mfile,FilePojo filePojo) throws Exception{
		String type = filePojo.getExtension().toLowerCase();
		File rootFile = new File(filePojo.getPath());//验证源文件夹是否存在
		if (!rootFile.exists()) {
			FileUtils.forceMkdir(rootFile);
		}
		filePojo.setPath(filePojo.getPath()+filePojo.getName());
		File uploadFilePath = new File(filePojo.getPath());
		mfile.transferTo(uploadFilePath);
		filePojo.setType("." + type);
		//上传oss
        filePojo = OSSManageUtil.uploadFile(filePojo);
		return filePojo;
	}

	public static FilePojo uploadFileCommon(InputStream  inputStream,FilePojo filePojo) throws Exception{
		String type = filePojo.getExtension().toLowerCase();
		File rootFile = new File(filePojo.getPath());//验证源文件夹是否存在
		if (!rootFile.exists()) {
			FileUtils.forceMkdir(rootFile);
		}
		File uploadFilePath = new File(filePojo.getPath());
		OutputStream  outputStream = new FileOutputStream(uploadFilePath);
		int bytesWritten = 0;
		int byteCount = 0;
		byte[] bytes = new byte[1024];
		while ((byteCount = inputStream.read(bytes)) != -1){
			outputStream.write(bytes, bytesWritten, byteCount);
			bytesWritten += byteCount;
		}
		inputStream.close();
		outputStream.close();
		//在本地生产一个原始路径
//		mfile.transferTo(uploadFilePath);
		//转换pdf
		filePojo.setType("." + type);
		return filePojo;
	}

	public static String createThumb(String fileId, String realPath,
			String picPath, String rootPath,boolean isMore){
		int UPLOAD_SMALL_PICTURE_MAX_WIDTH = 120;
		int UPLOAD_SMALL_PICTURE_MAX_HEIGHT = 120;
		try{
			String smallPictureFileName = fileId + "_thumbnail.jpg";
			String smallPictureFilePath = realPath + rootPath + "/"
					+ smallPictureFileName;
			File smallPictureFile = new File(smallPictureFilePath);
			if (smallPictureFile.exists()) {
				FileUtils.forceDeleteOnExit(smallPictureFile);
			}
			BufferedImage image = ImageHelper.resizeImage(realPath + picPath,
					ImageHelper.IMAGE_JPEG, UPLOAD_SMALL_PICTURE_MAX_WIDTH,
					UPLOAD_SMALL_PICTURE_MAX_HEIGHT);
			ImageHelper.saveImage(image, smallPictureFilePath,
					ImageHelper.IMAGE_JPEG);

			return rootPath + "/" + smallPictureFileName;
		}catch (Exception e) {
			// TODO: handle exception
		}
		return null;

	}

	public static File zipFiles(List<File> files) throws IOException{
		File zipFile = new File("temp.zip");
		OutputStream os = new BufferedOutputStream(new FileOutputStream(zipFile));
        ZipOutputStream zos = new ZipOutputStream( os );
        byte[] buf = new byte[8192];
        int len;
        for (int i=0;i<files.size();i++) {
            File file = files.get(i);
            if ( !file.isFile() ) continue;
            ZipEntry ze = new ZipEntry( file.getName() );
            zos.putNextEntry( ze );
            FileInputStream fis = new FileInputStream( file );
            BufferedInputStream bis = new BufferedInputStream( fis );
            while ( ( len = bis.read( buf ) ) > 0 ) {
                zos.write( buf, 0, len );
            }
            zos.closeEntry();
            bis.close();
            fis.close();
        }
        zos.setEncoding("GBK");
        zos.closeEntry();
        zos.close();

		return zipFile;
	}

	/**
	 * 从远程服务器读取图片
	* @date 2016-9-6 下午02:38:27
	 */
	public static File saveImageFromURL(String url,String newsRootPath, String filename){
        try {
            URL picUrl;
            HttpURLConnection conn = null;
            InputStream is = null;
                picUrl = new URL(url);
                conn = (HttpURLConnection) picUrl.openConnection();
                conn.setConnectTimeout(20000);
                conn.setReadTimeout(20000);
                conn.connect();
                // 获取图片大小
                int picSize = conn.getContentLength();
                is = conn.getInputStream();
                    //String fileName  = url.substring(url.lastIndexOf("/")+1);
                    File folder = new File(newsRootPath);
                    if(!folder.exists()){
                        folder.mkdir();
                    }
                    File file = new File(newsRootPath+"/"+filename);
                    OutputStream os = new FileOutputStream(file);

                    final int buffer_size = 1024;
                    byte[] bytes = new byte[buffer_size];
                    for (;;) {
                        int count = is.read(bytes, 0, buffer_size);
                        if (count == -1)
                            break;
                        os.write(bytes, 0, count);
                    }
                    os.close();
             return file;
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

	// 保存文件
    public static void saveFile(String newsRootPath, String filename,
            File picFile) {
        try {
            File newsFileRoot = new File(newsRootPath);
            if (!newsFileRoot.exists()) {
                newsFileRoot.mkdirs();
            }

            FileOutputStream fos = new FileOutputStream(newsRootPath + filename);
            FileInputStream fis = new FileInputStream(picFile);
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = fis.read(buf)) > 0) {
                fos.write(buf, 0, len);
            }
            if (fis != null)
                fis.close();
            if (fos != null)
                fos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
