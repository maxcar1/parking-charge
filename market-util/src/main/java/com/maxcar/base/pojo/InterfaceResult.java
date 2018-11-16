package com.maxcar.base.pojo;

import java.io.Serializable;

/**
 * @program: maxcar-util
 * @description: 接口返回结果对象
 * @author: 罗顺锋
 * @create: 2018-04-25 17:48
 **/
public class InterfaceResult implements Serializable{
    private static String CODE200 = "200";
    private static String CODE401 = "401";
    private static String CODE403 = "403";
    private static String CODE404 = "404";
    private static String CODE406 = "406";
    private static String CODE500 = "500";
    private static String CODE600 = "600";
    private String code;//返回码
    private String msg;//返回说明
    private Integer total = 0;//返回说明

    private Double avg = 0d;//返回平均数

    public Double getAvg() {
        return avg;
    }

    public void setAvg(Double avg) {
        this.avg = avg;
    }
    private String path;//返回数据
    private Object data;//返回数据
    public InterfaceResult(){
        this.code = "200";
        this.msg = "请求成功";
    }
    InterfaceResult(String code,String msg,Object data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public void InterfaceResult200(Object data){
        this.code = "200";
        this.msg = "请求成功";
        this.data = data;
    }
    public void  InterfaceResult401(){
        this.code = "401";
        this.msg = "没有权限";
    }
    public void  InterfaceResult403(){
        this.code = "403";
        this.msg = "身份验证失败";
    }
    public void  InterfaceResult404(){
        this.code = "404";
        this.msg = "资源请求不存在";
    }

    public void  InterfaceResult406(){
        this.code = "406";
        this.msg = "参数格式错误";
    }

    public void  InterfaceResult500(){
        this.code = "500";
        this.msg = "服务器发生错误";
    }
    public void  InterfaceResult500(String msg){
        this.code = "500";
        this.msg = msg;
    }
    public void  InterfaceResult600(String msg){
        this.code = "600";
        this.msg = msg;//未知错误参数校验使用
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
