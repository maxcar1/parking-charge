package com.maxcar.user.vo;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @program: maxcar-util
 * @description: 修改密码使用
 * @author: 罗顺锋
 * @create: 2018-05-03 17:24
 **/
public class ChangeUser implements Serializable {


    private String userId;
    @NotNull(message="旧密码不能为空")
    private String oldPassWord;//旧密码
    @NotNull(message="新密码不能为空")
    private String passWord;//新密码

    @NotNull(message="重复密码不能为空")
    private  String rePassWord;//重复新密码


    public String getOldPassWord() {
        return oldPassWord;
    }

    public void setOldPassWord(String oldPassWord) {
        this.oldPassWord = oldPassWord;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getRePassWord() {
        return rePassWord;
    }

    public void setRePassWord(String rePassWord) {
        this.rePassWord = rePassWord;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
