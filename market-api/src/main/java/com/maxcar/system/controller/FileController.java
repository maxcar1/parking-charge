package com.maxcar.system.controller;

import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.service.UploadService;
import com.maxcar.base.util.UuidUtils;
import com.maxcar.base.util.file.FilePojo;
import com.maxcar.base.util.file.FileUploadUtil;
import com.maxcar.base.util.file.StringHelper;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@SuppressWarnings("SpringJavaAutowiringInspection")
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private UploadService uploadService;

	/*
	 * 原来的单独上传文件，无参数方法，放弃
	@ResponseBody
    @RequestMapping(value = "/upload2",method = RequestMethod.POST)
    public Result upload2(@RequestParam("file")MultipartFile file){
    	Result result = uploadService.upload(file);
        return result;
    }
    */

    /**
     * 文件下载
     *
     * @param params 文件url
     * @return
     */
    @PostMapping(value = "/download")
    public InterfaceResult download(@RequestBody JSONObject params) {
        InterfaceResult interfaceResult = new InterfaceResult();
        String url = params.getString("url");
        interfaceResult = uploadService.download(url);
        interfaceResult.InterfaceResult200("成功");
        return interfaceResult;
    }

    /**
     * 文件删除
     *
     * @param params 文件url
     * @return
     */
    @PostMapping(value = "/delete")
    public InterfaceResult delete(@RequestBody JSONObject params) {
        InterfaceResult interfaceResult = new InterfaceResult();
        String url = params.getString("url");
        interfaceResult = uploadService.delete(url);
        interfaceResult.InterfaceResult200("成功");
        return interfaceResult;
    }


    /**
     * @param file
     * @param
     * @param request
     * @return
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public InterfaceResult upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        InterfaceResult interfaceResult = new InterfaceResult();
        try {
            //绝对目录
            String uid = UuidUtils.getUUID();
            String realPath = request.getSession().getServletContext().getRealPath("/");
            //源文件上传
            FilePojo filePojo = new FilePojo();
            //执行文件上传
            String extension = StringHelper.unqualify(file.getOriginalFilename()).toLowerCase();
            //获取文件后缀
            String filetype = ".jpg";
            //oss服务地址
            String fileName = uid + filetype;
            String ossPath = "carAppPic" + "/" + uid + StringHelper.FOLDER_SEPARATOR + fileName;
            filePojo.setExtension(extension);
            filePojo.setId(uid);
            filePojo.setPath(realPath);
            filePojo.setName(fileName);
            filePojo.setOssPath(ossPath);
            filePojo = FileUploadUtil.uploadFileCommon(file, filePojo);
            interfaceResult.InterfaceResult200(filePojo);
        } catch (Exception e) {
            e.printStackTrace();
            interfaceResult.InterfaceResult500();
//            return interfaceResult.InterfaceResult500();
        }
        return interfaceResult;
    }
}
