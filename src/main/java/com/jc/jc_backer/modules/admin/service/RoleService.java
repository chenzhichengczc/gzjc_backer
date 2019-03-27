package com.jc.jc_backer.modules.admin.service;

import com.jc.jc_backer.modules.admin.entity.Role;

import java.util.List;

/**
 * @Author: Charles Chan
 * @Date: 2019/3/18 11:28
 * @Version 1.0
 */
public interface RoleService {

    List<Role> findRoles(String roleIds);

}
