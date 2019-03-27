package com.jc.jc_backer.modules.admin.service;

import com.jc.jc_backer.modules.admin.entity.Admin;

import java.util.Set;

/**
 * @Author: Charles Chan
 * @Date: 2019/3/17 14:15
 * @Version 1.0
 */
public interface AdminService{

    public boolean registerAdmin(Admin admin);

    public Admin findByName(String name);

    public Admin loginAdmin(Admin admin);

    Set<String> findRoles(String username);

    Set<String> findPermission(String username);
}
