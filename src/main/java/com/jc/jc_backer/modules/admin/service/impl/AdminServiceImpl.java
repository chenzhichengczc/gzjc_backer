package com.jc.jc_backer.modules.admin.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jc.jc_backer.common.exception.JcException;
import com.jc.jc_backer.common.utils.MD5Hash;
import com.jc.jc_backer.modules.admin.entity.Admin;
import com.jc.jc_backer.modules.admin.mapper.AdminMapper;
import com.jc.jc_backer.modules.admin.service.AdminService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
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
        admin.setPassword(MD5Hash.getMD5Hash(admin));
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

        subject.hasRole("admin");

        return (Admin)subject.getPrincipal();
    }

    /**
     * 修改个人资料
     * @param admin
     * @return
     */
    @Override
    public Integer updateAdmin(Admin admin){
        if(admin.getLocked()==0){
            throw new JcException("该用户被锁定##请联系管理员！");
        }
        if (!admin.getUsername().equals(adminMapper.selectById(admin.getId()).getUsername())){
            throw new JcException("修改失败！您尝试访问的用户不存在！");
        }
        Integer rows=updateById(admin);

        return rows;
    }

    /**
     * 修改头像
     * @param id 根据id查询用户
     * @param avatar 根据头像地址存储
     */
    @Override
    public void changeAvatar(Long id,String avatar){
        Admin admin=adminMapper.selectById(id);
        admin.setHeadPortrait(avatar);
        updateById(admin);
    }

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    @Override
    public Admin findByAdmin(Long id){
        Admin admin=adminMapper.selectById(id);
        admin.setLocked(-1);
        admin.setSalt("");
        admin.setPassword("");
        admin.setId(-1);
        return admin;
    }

    /**
     * 根据用户实体类的ID修改当前用户信息
     * @param admin 用户实体类
     * @return 受影响的行数
     */
    private Integer updateById(Admin admin){
        Integer rows=adminMapper.updateById(admin);
        if(rows!=1){
            throw new JcException("修改失败！出现未知错误##请联系管理员！");
        }
        return rows;
    }

    /**
     * 根据用户名找到对应的用户信息
     * @param name 用户名
     * @return 用户信息
     */
    @Override
    public Admin findByName(String name) {
        return adminMapper.selectList(new EntityWrapper<Admin>().eq("email", name)).get(0);
    }

}
