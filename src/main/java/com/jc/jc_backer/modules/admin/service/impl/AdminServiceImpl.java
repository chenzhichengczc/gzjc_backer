package com.jc.jc_backer.modules.admin.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jc.jc_backer.common.exception.JcException;
import com.jc.jc_backer.common.utils.MD5Hash;
import com.jc.jc_backer.modules.admin.entity.Admin;
import com.jc.jc_backer.modules.admin.entity.Role;
import com.jc.jc_backer.modules.admin.mapper.AdminMapper;
import com.jc.jc_backer.modules.admin.service.AdminService;
import com.jc.jc_backer.modules.admin.service.PermissionService;
import com.jc.jc_backer.modules.admin.service.RoleService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: Charles Chan
 * @Date: 2019/3/17 14:15
 * @Version 1.0
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminMapper adminMapper;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    //注册功能  TODO 到时处理一下权限问题
    @Override
    public boolean registerAdmin(Admin admin) {
        //操作数据,后续谁要zTree控制权限入库
        admin.setSalt(admin.getUsername() + admin.getPassword());
        admin.setPassword(MD5Hash.getMD5Hash(admin));
        admin.setRoleIds("1/");
        return adminMapper.insert(admin) == 1;
    }

    @Override
    public Admin loginAdmin(Admin admin) {
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(admin.getUsername(),MD5Hash.getMD5Hash(admin)));
        }catch (JcException e){
            //如果验证过程当中出现问题,直接就把结果设置为空,给控制器执行
            return null;
        }

        //绑定通过用户登录信息绑定到session中
        Session session = subject.getSession();
        session.setAttribute("admin",subject.getPrincipal());

        return (Admin)subject.getPrincipal();
    }

    /**
     * 根据用户名找到对应的用户信息
     * @param name
     * @return
     */
    @Override
    public Admin findByName(String name) {
        return adminMapper.selectList(new EntityWrapper<Admin>().eq("username", name)).get(0);
    }

    /**
     * 获取角色表的信息
     * @param username
     * @return
     */
    @Override
    public Set<String> findRoles(String username) {
        Admin admin = findByName(username);
        //如果找不到则返回一个空集合
        if(ObjectUtils.isEmpty(admin)){
            return Collections.EMPTY_SET;
        }

        //Role集合,遍历封装Role集合,获取RoleName角色返回给Shiro容器装载
        List<Role> roles = roleService.findRoles(admin.getRoleIds());

        //装载容器
        HashSet<String> rolesName = new HashSet<>();

        for (int i = 0; i<roles.size(); i++){
            rolesName.add(roles.get(i).getRole());
        }
        return rolesName;
    }

    @Override
    public Set<String> findPermission(String username) {
        Admin admin = findByName(username);
        //如果找不到则返回一个空集合
        if(ObjectUtils.isEmpty(admin)){
            return Collections.EMPTY_SET;
        }
        return permissionService.findPermission(roleService.findRoles(admin.getRoleIds()));
    }

}
