package com.jc.jc_backer.modules.admin.service;

import com.jc.jc_backer.modules.admin.entity.Role;

import java.util.List;
import java.util.Set;

/**
 * @Author: Charles Chan
 * @Date: 2019/3/27 10:25
 * @Version 1.0
 */
public interface PermissionService {

    Set<String> findPermission(List<Role> roles);

}
