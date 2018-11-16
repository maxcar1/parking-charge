package com.maxcar.permission.controller;

import com.maxcar.BaseController;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.pojo.Province;
import com.maxcar.base.service.ProvinceService;
import com.maxcar.base.service.UploadService;
import com.maxcar.base.util.UuidUtils;
import com.maxcar.base.util.file.FilePojo;
import com.maxcar.base.util.file.FileUploadUtil;
import com.maxcar.base.util.file.StringHelper;
import com.maxcar.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


@RestController
public class UploadController extends BaseController {

    @Autowired
    private UploadService uploadService;

    @Autowired
    private ProvinceService provinceService;

    /**
     * param:
     * describe: 上传照片
     * create_date:  lxy   2018/8/17  10:04
     **/
    @RequestMapping("uploadFileStream/{businessPath}")
    public InterfaceResult uploadFileStream(MultipartFile file, @PathVariable("businessPath") String businessPath, HttpServletRequest request) throws Exception {

        User user = super.getCurrentUser(request);
        //绝对目录
        String uid = UuidUtils.generateIdentifier();
        String realPath = request.getSession().getServletContext().getRealPath("/");
        //源文件上传
        FilePojo filePojo = new FilePojo();
        //执行文件上传
        String extension = StringHelper.unqualify(file.getOriginalFilename()).toLowerCase();
        //获取文件后缀
        String filetype = "." + extension;
        //oss服务地址
        String fileName = uid + filetype;
        String ossPath = user.getMarketId() + "/" + businessPath + "/" + uid;
        filePojo.setExtension(extension);
        filePojo.setId(uid);
        filePojo.setPath(realPath);
        filePojo.setName(fileName);
        filePojo.setOssPath(ossPath);
        filePojo = FileUploadUtil.uploadFileCommon(file, filePojo);
        return getInterfaceResult("200", filePojo);
    }

}
