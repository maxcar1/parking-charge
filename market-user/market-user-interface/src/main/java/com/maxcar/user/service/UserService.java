package com.maxcar.user.service;

import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.service.BaseService;
import com.maxcar.user.entity.LoginLog;
import com.maxcar.user.entity.User;
import com.maxcar.user.vo.ChangeUser;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface UserService extends BaseService<User,String> {
    /**
    * @Description: 登录验证
     * 先拿用户名查询，如果存在记录，循环遍历拿出密码，和登录密码进行md5匹配
    * @Param: [userName, password]
    * @return: com.maxcar.permission.pojo.User
    * @Author: 罗顺锋
    * @Date: 2018/4/26
    */
    public InterfaceResult login(User user)throws Exception;

    /**
    * @Description: 用户搜索接口
    * @Param: [user]
    * @return: java.util.List<com.maxcar.permission.pojo.User>
    * @Author: 罗顺锋
    * @Date: 2018/5/3
    */
    public List<User> searchUser(User user)throws Exception;

    /**
    * @Description: 新增或者修改用户，同时关联角色
    * @Param: [user]
    * @return: boolean
    * @Author: 罗顺锋
    * @Date: 2018/4/25
    */
    public InterfaceResult saveOrUpdateUser(User user)throws Exception;;

    /**
    * @Description: 密码重置
    * @Param: [userId]
    * @return: boolean
    * @Author: 罗顺锋
    * @Date: 2018/4/26
    */
    public boolean resetPassword(String userId)throws Exception;
    /**
    * @Description: 修改用户状态，删除用户，启用，弃用用户，-1物理删除，0弃用，1启用
    * @Param: [status,id]
    * @return: boolean
    * @Author: 罗顺锋
    * @Date: 2018/4/25
    */
    public boolean updateUserStatus(String id, Integer status)throws Exception;
    /**
    * @Description: 更新用户登录时间
    * @Param: [userId]
    * @return: boolean
    * @Author: 罗顺锋
    * @Date: 2018/4/28
    */
    public boolean updateLoginTime(User userId)throws Exception;

    /**
     * 更新外部登录时间
     * @param user
     * @return
     * @throws Exception
     */
    public boolean updateOutsideLoginTime(User user) throws Exception;
    /**
     * 登出
     * @param user
     * @return
     * @throws Exception
     */
    public InterfaceResult logout(User user)throws Exception;
    /**
    * @Description: 修改密码
    * @Param: [oldPassWord, passWord]
    * @return: com.maxcar.base.pojo.InterfaceResult
    * @Author: 罗顺锋
    * @Date: 2018/5/3
    */
    public InterfaceResult changePassword(ChangeUser changeUser)throws Exception;

    public List<User> getUserList(User user);

    List<User> getUserList(String marketId, String orgId);

    /*getUser(User user)throws Exception;*/
}