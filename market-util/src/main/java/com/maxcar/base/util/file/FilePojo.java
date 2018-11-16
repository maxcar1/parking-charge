package com.maxcar.base.util.file;

import java.io.Serializable;

/**
 * Created by sunny on 2017/4/6.
 */
public class FilePojo implements Serializable {

    private static final long serialVersionUID = -350107007955355007L;

    public String getBusinessPath() {
        return businessPath;
    }

    public void setBusinessPath(String businessPath) {
        this.businessPath = businessPath;
    }

    private String businessPath;//阿里云业务目录

    private String id;

    private String name;

    private String path;//本地绝对地址

    private String extension;//文件后缀

    private String thumbnail;

    private String type;

    private String ossPath;//oss服务器上传地址。只作为中间转换使用

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getOssPath() {
        return ossPath;
    }

    public void setOssPath(String ossPath) {
        this.ossPath = ossPath;
    }



    public String getExtension() {
        return extension;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
