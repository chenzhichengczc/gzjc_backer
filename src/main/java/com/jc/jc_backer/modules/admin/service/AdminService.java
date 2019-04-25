package com.jc.jc_backer.modules.admin.service;

import com.jc.jc_backer.modules.admin.entity.Admin;

import java.util.List;
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

    public Integer updateAdmin(Admin admin);

    public Admin findByAdmin(Long id);

    public void changeAvatar(Long id,String avatar);

    public List<Admin> getAllEmpoyee();

}
