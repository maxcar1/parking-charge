package com.maxcar.base.pojo;

import java.io.Serializable;

/**
 * @program: maxcar-util
 * @description: 图片转换实体
 * @author: 罗顺锋
 * @create: 2018-05-04 15:56
 **/
public class FileBean implements Serializable{
    private String name;//名称
    private String path;//路径
    private String extension;//类型
    private String size;//大小

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

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
