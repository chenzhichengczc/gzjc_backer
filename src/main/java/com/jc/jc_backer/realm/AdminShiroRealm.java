package com.jc.jc_backer.realm;

import com.jc.jc_backer.common.exception.JcException;
import com.jc.jc_backer.modules.admin.entity.Admin;
import com.jc.jc_backer.modules.admin.service.AdminService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: Charles Chan
 * @Date: 2019/3/17 14:03
 * @Version 1.0
 */
public class AdminShiroRealm extends AuthorizingRealm {


    @Autowired
    private AdminService adminService;

    /**
     * 权限验证
     * @param principals 用户信息
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 卸载如下代码 ------------
//        String username = ((Admin)principals.getPrimaryPrincipal()).getUsername();
//        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
//        simpleAuthorizationInfo.setRoles(adminService.findRoles(username));
//        simpleAuthorizationInfo.setStringPermissions(adminService.findPermission(username));
        //卸载如上代码 ------------


        return null;
    }


    /**
     * 用户验证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //非空判断
        if(token.getPrincipal() == null) return  null;

        UsernamePasswordToken upt = (UsernamePasswordToken)token;

        //获取用户信息
        String name = token.getPrincipal().toString();
        //凭据名字查询是否有当前用户存在
        Admin admin = adminService.findByName(name);
        if(admin != null){
            if(admin.getPassword().equals(new String(upt.getPassword()))){
                return new SimpleAuthenticationInfo(admin,admin.getPassword().toString(),getName());
            }else {
                throw new JcException("登录信息有误,请确认!");
            }
        }else {
            throw new JcException("用户不存在,请确认!");
        }
    }
}
