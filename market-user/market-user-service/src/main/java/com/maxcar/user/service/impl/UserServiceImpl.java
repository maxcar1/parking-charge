package com.maxcar.user.service.impl;

import com.maxcar.base.dao.BaseDao;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.service.impl.BaseServiceImpl;
import com.maxcar.base.util.MD5Util;
import com.maxcar.base.util.UuidUtils;
import com.maxcar.redis.service.SsoService;
import com.maxcar.user.dao.*;
import com.maxcar.user.entity.*;
import com.maxcar.user.service.UserService;
import com.maxcar.user.vo.ChangeUser;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User,String> implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private LoginLogMapper loginLogMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private OrganizationsMapper organizationsMapper;
    @Autowired
    private StaffMapper staffMapper;
    @Autowired
    private SsoService ssoService;
    @Override
    public BaseDao<User, String> getBaseMapper() {
        return userMapper;
    }

    @Value("${REDIS_USER_SESSION_KEY}")
    private String REDIS_USER_SESSION_KEY;


    @Override
    public InterfaceResult login(User user)throws Exception {
        String userName = user.getUserName();
        String password = user.getUserPwd();
        InterfaceResult interfaceResult = new InterfaceResult();
        UserExample example = new UserExample();
//        String pwd = MD5Util.MD5(userName + password);
//        example.createCriteria().andUserPwdEqualTo(pwd);
        example.createCriteria().andUserNameEqualTo(userName).andIsvalidEqualTo(1);;//只取可用
        List<User> users = userMapper.selectByExample(example);
        //获取用户集合，通过集合便利
        if(users!=null && users.size()>0){
            for(User trueUser:users) {
                String pwd = MD5Util.MD5(userName + password);
                if (pwd.equals(trueUser.getUserPwd())) {
                    if (trueUser.getIsvalid() == 0){
                        interfaceResult.InterfaceResult600("该账号已弃用");
                    }else {
                        Organizations organizations = organizationsMapper.selectByPrimaryKey(trueUser.getOrgId());
                        if(organizations.getOrgCode()!=null ){
                            trueUser.setOrgCode(organizations.getOrgCode());
                        }
                        InterfaceResult result = ssoService.login(trueUser.getUserId(),(organizations.getOrgCode()!=null && organizations.getOrgCode().equals("000"))?-1:0);
                        if (result.getCode().equals("200")) {
                            //根据登录的员工id查询员工姓名
                            StaffExample staffExample = new StaffExample();
                            staffExample.createCriteria().andIdEqualTo(trueUser.getStaffId()).andIsValidEqualTo(1);
                            List<Staff> staffList = staffMapper.selectByExample(staffExample);
//                            trueUser.setStaffName(staffList.get(0).getStaffName());
                            if (null != staffList && !staffList.isEmpty()) {
                                trueUser.setStaffName(staffList.get(0).getStaffName());
                            }
                            //更新用户登录状态
                            trueUser.setLastLoginIp(user.getLastLoginIp());
                            trueUser.setTokenId(result.getData().toString());
                            updateLoginTime(trueUser);
                            //传递参数返回前台
                            Map map = new HashMap<>();
//                            map.put("tokenId",result.getRes());
                            map.put("userInfo",trueUser);
                            trueUser.setUserPwd(null);
                            trueUser.setRoles(userRoleMapper.selectRoleByUserId(trueUser.getUserId()));
                            interfaceResult.InterfaceResult200(map);
                        } else {
                            interfaceResult.InterfaceResult600("密码缓存失败");
                        }
                    }
                }else {
                    interfaceResult.InterfaceResult600("密码错误");
                }
                return interfaceResult;
            }
        }
        interfaceResult.InterfaceResult600("用户名不存在");
        return interfaceResult;
    }

    @Override
    public List<User> searchUser(User user)throws Exception {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        //criteria.andIsvalidEqualTo(1);
        if(user.getManagerFlag()==1) {
            criteria.andMarketIdEqualTo(user.getMarketId());
        }
        List<User> users = new ArrayList<>();
        boolean flag = false;
        if(StringUtils.isNotBlank(user.getUserName())){
            user.setUserName("%" + user.getUserName() + "%");
            criteria.andUserNameLike(user.getUserName());
        }
        if(StringUtils.isNotBlank(user.getOrgId())){
            criteria.andOrgIdEqualTo(user.getOrgId());
        }

        if(StringUtils.isNotBlank(user.getStaffName())){
            flag = true;
            StaffExample staffExample = new StaffExample();
            StaffExample.Criteria criteria1=staffExample.createCriteria();
            criteria1.andStaffNameLike("%"+user.getStaffName()+"%").andIsValidEqualTo(1);
            if(user.getManagerFlag()==1) {
                criteria1.andMarketIdEqualTo(user.getMarketId());
            }
            List<Staff> staffList = staffMapper.selectByExample(staffExample);
            for (Staff staff:staffList){
                criteria.andStaffIdEqualTo(staff.getId()).andIsvalidEqualTo(1);
                example.setOrderByClause(" register_time desc");
                List<User> userList = userMapper.selectByExample(example);
                users.addAll(userList);
            }
        }

        if (!flag){
            example.setOrderByClause(" register_time desc");
            users = userMapper.selectByExample(example);
        }

        for(User newUser:users){
            newUser.setOrganizations(organizationsMapper.selectByPrimaryKey(newUser.getOrgId()));
            List<Role> roles = userRoleMapper.selectRoleByUserId(newUser.getUserId());
            newUser.setRoles(roles);
            //查询员工信息
            Staff staff = staffMapper.selectByPrimaryKey(newUser.getStaffId());
            newUser.setStaffName(staff!=null?staff.getStaffName():null);
        }

        return users;
    }

    @Override
    public InterfaceResult saveOrUpdateUser(User user)throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        int count = 0;
        String userId = user.getUserId();

        // 姓名没有用用户名代替
        if (null==user.getTrueName()){
            user.setTrueName(user.getUserName());
        }

        //新增
        if(StringUtils.isBlank(userId)){
            // 插入之前验证用户名是否已存在
            UserExample example = new UserExample();
            example.createCriteria().andUserNameEqualTo(user.getUserName()).andIsvalidEqualTo(1);;//只取可用
            List<User> users = userMapper.selectByExample(example);
            //如果用户名存在，返回
            if(users!=null && users.size()>0) {
                interfaceResult.InterfaceResult600("用户名已存在");
                return interfaceResult;
            }
            else {
                userId = UuidUtils.generateIdentifier();
                user.setUserId(userId);
                String pwd = MD5Util.MD5(user.getUserName()+user.getUserPwd());
                user.setUserPwd(pwd);
                //设置用户编号，根据市场编号算
                example.createCriteria().andOrgIdEqualTo(user.getOrgId());;//只取可用
                Integer userCount = userMapper.countByExample(example);
                Organizations organizations = organizationsMapper.selectByPrimaryKey(user.getOrgId());
                if(organizations==null) {
                    interfaceResult.InterfaceResult600("部门存在");
                    return interfaceResult;
                }
                user.setUserCode(organizations.getOrgCode() + (userCount+1));
                count = userMapper.insertSelective(user);
            }
        }else{//修改
            user.setUserPwd(null);//不允许修改密码
            count = userMapper.updateByPrimaryKeySelective(user);
            UserRoleExample userRoleExample = new UserRoleExample();
            userRoleExample.createCriteria().andUserIdEqualTo(userId);
            userRoleMapper.deleteByExample(userRoleExample);//删除已存在角色
        }
        //新增角色
        List<Role> roles = user.getRoles();
        for(Role role:roles) {
            UserRole userRole = new UserRole();
            userRole.setRoleId(role.getRoleId());
            userRole.setUserId(userId);
            userRole.setUserRoleId(UuidUtils.generateIdentifier());
            count += userRoleMapper.insertSelective(userRole);
        }
        return interfaceResult;
//        return count==2?interfaceResult:interfaceResult.InterfaceResult600("操作失败");
    }

    @Override
    public boolean resetPassword(String userId) throws Exception{
        User user = userMapper.selectByPrimaryKey(userId);
        if(user!=null) {
            String pwd = MD5Util.MD5(user.getUserName() + "12345678");
            user.setUserPwd(pwd);
            return userMapper.updateByPrimaryKeySelective(user)==1;
        }
        return false;
    }

    @Override
    public boolean updateUserStatus(String ids,Integer status) throws Exception{
        int count1 = 0;
        int count2= 0 ;
        for(String id:ids.split(",")) {
            if (status == -1) {
                count1 = userMapper.deleteByPrimaryKey(id);
                //删除用户同时删除用户对应角色关联表信息
                UserRoleExample example = new UserRoleExample();
                UserRoleExample.Criteria criteria = example.createCriteria();
                criteria.andUserIdEqualTo(id);
                count2 = userRoleMapper.deleteByExample(example);
            } else {
                User user = userMapper.selectByPrimaryKey(id);
                if (user != null) {
                    user.setIsvalid(status);
                    count1 = userMapper.updateByPrimaryKeySelective(user);
                }
            }
        }
        if(count1==1&&count2>=1){
            return true;
        }
        return false;
    }

    @Override
    public boolean updateLoginTime(User user) throws Exception {
        LoginLog loginLog = new LoginLog();
        loginLog.setLoginDate(new Date());
        loginLog.setLoginId(UuidUtils.generateIdentifier());
        loginLog.setLoginIp(user.getLastLoginIp());
        loginLog.setLoginType(0);
        loginLog.setUserToken(user.getTokenId());
        user.setLastLoginTime(new Date());
        loginLogMapper.insertSelective(loginLog);
        return userMapper.updateByPrimaryKeySelective(user)==1;
        //新增登录日志
    }

    @Override
    public boolean updateOutsideLoginTime(User user) throws Exception {
        LoginLog loginLog = new LoginLog();
        loginLog.setLoginDate(new Date());
        loginLog.setLoginId(UuidUtils.generateIdentifier());
        loginLog.setLoginIp(user.getLastLoginIp());
        loginLog.setLoginType(2);//爱步登录状态
        loginLog.setUserToken(user.getTokenId());
        user.setLastLoginTime(new Date());
        loginLogMapper.insertSelective(loginLog);
        return userMapper.updateByPrimaryKeySelective(user)==1;
        //新增登录日志
    }

    @Override
    public InterfaceResult changePassword(ChangeUser user) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        User oldUser = userMapper.selectByPrimaryKey(user.getUserId());
        if(oldUser!=null){
            //验证用户旧密码是否正确
            if(!user.getPassWord().equals(user.getRePassWord())){
                interfaceResult.InterfaceResult600("新密码输入不一致");
                return interfaceResult;
            }
            String oldPwd = MD5Util.MD5(oldUser.getUserName()+user.getOldPassWord());
            if(!oldUser.getUserPwd().equals(oldPwd)){
                interfaceResult.InterfaceResult600("旧密码不正确");
                return interfaceResult;
            }
            String pwd = MD5Util.MD5(oldUser.getUserName()+user.getPassWord());
            oldUser.setUserPwd(pwd);
            userMapper.updateByPrimaryKeySelective(oldUser);
        }
        return interfaceResult;
    }

    @Override
    public List<User> getUserList(User user) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andIsvalidEqualTo(1);
        if(StringUtils.isNotBlank(user.getStaffId())){
            criteria.andStaffIdEqualTo(user.getStaffId());
        }
        if (StringUtils.isNotBlank(user.getMarketId())){
            criteria.andMarketIdEqualTo(user.getMarketId());
        }
        List<User> list = userMapper.selectByExample(example);
        return list;
    }

    @Override
    public List<User> getUserList(String marketId, String orgId) {
        UserExample example = new UserExample();
        example.createCriteria().andMarketIdEqualTo(marketId)
                .andOrgIdEqualTo(orgId)
                .andIsvalidEqualTo(1);
        return userMapper.selectByExample(example);
    }

    @Override
    public InterfaceResult logout(User user) throws Exception {
        InterfaceResult interfaceResult = new InterfaceResult();
        //清空缓存
        InterfaceResult proResult = ssoService.clearUserByToken(user.getTokenId());
        LoginLog loginLog = new LoginLog();
        loginLog.setLoginId(UuidUtils.generateIdentifier());
        loginLog.setLoginDate(new Date());
        loginLog.setLoginIp(user.getLastLoginIp());
        loginLog.setLoginType(1);
        loginLog.setUserToken(user.getTokenId());
        user.setLastLoginTime(new Date());
        //新增登出记录
        loginLogMapper.insertSelective(loginLog);
        interfaceResult.InterfaceResult200(proResult.getData());
        return interfaceResult;
    }


//    public static void main(String[] args){
//        System.out.println(MD5Util.MD5("maxcar12345678"));
//    }
}