package com.jc.jc_backer.modules.admin.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jc.jc_backer.common.exception.JcException;
import com.jc.jc_backer.common.utils.MD5Hash;
import com.jc.jc_backer.modules.admin.entity.Admin;
import com.jc.jc_backer.modules.admin.mapper.AdminMapper;
import com.jc.jc_backer.modules.admin.service.AdminService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: Charles Chan
 * @Date: 2019/3/17 14:15
 * @Version 1.0
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminMapper adminMapper;

    //注册功能  TODO 到时处理一下权限问题
    @Override
    public boolean registerAdmin(Admin admin) {
        //操作数据,后续谁要zTree控制权限入库
        admin.setSalt(admin.getUsername() + admin.getPassword());
        admin.setRoleId("1");
        admin.setPassword(MD5Hash.getMD5Hash(admin));
        return adminMapper.insert(admin) == 1;
    }

    @Override
    public Admin findByName(String name) {
        return adminMapper.selectList(new EntityWrapper<Admin>().eq("username", name)).get(0);
    }

    @Override
    public Admin loginAdmin(Admin admin) {
        /*@NotEmpty List<Admin> username = adminMapper.selectList(new EntityWrapper<Admin>().eq("username", admin.getUsername()));*/
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(admin.getUsername(),MD5Hash.getMD5Hash(admin)));
        }catch (JcException e){
            //如果验证过程当中出现问题,直接就把结果设置为空,给控制器执行
            return null;
        }
        return (Admin)subject.getPrincipal();
    }


}
