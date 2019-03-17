package com.jc.jc_backer.modules.admin.controller;

import com.jc.jc_backer.common.utils.ResponseUtil;
import com.jc.jc_backer.modules.admin.entity.Admin;
import com.jc.jc_backer.modules.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Charles Chan
 * @Date: 2019/3/17 16:15
 * @Version 1.0
 */
@RestController
@RequestMapping("/admin")
public class AdminController{

    @Autowired
    private AdminService adminService;

    /**
     * 注册功能
     */
    @RequestMapping("/registerAdmin")
    public ResponseUtil registerAdmin(Admin admin){
        return adminService.registerAdmin(admin) ? ResponseUtil.success() : ResponseUtil.error();
    }

}
