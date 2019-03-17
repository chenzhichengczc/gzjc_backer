package com.jc.jc_backer.modules.admin.service.impl;

import com.jc.jc_backer.common.utils.MD5Hash;
import com.jc.jc_backer.modules.admin.entity.Admin;
import com.jc.jc_backer.modules.admin.mapper.AdminMapper;
import com.jc.jc_backer.modules.admin.service.AdminService;
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

    @Override
    public boolean registerAdmin(Admin admin) {
        admin.setSalt(admin.getUsername() + admin.getPassword());
        admin.setRoleId("21");
        admin.setPassword(MD5Hash.getMD5Hash(admin));
        return adminMapper.insert(admin) == 1;
    }
}
