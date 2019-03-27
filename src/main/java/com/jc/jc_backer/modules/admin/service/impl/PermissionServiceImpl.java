package com.jc.jc_backer.modules.admin.service.impl;

import com.jc.jc_backer.modules.admin.entity.Permission;
import com.jc.jc_backer.modules.admin.entity.Role;
import com.jc.jc_backer.modules.admin.mapper.PermissionMapper;
import com.jc.jc_backer.modules.admin.service.PermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: Charles Chan
 * @Date: 2019/3/27 10:25
 * @Version 1.0
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Resource
    private PermissionMapper permissionMapper;

    //通过当前用户的角色集合,获取到有多少权限列表可以被访问
    @Override
    public Set<String> findPermission(List<Role> roles) {

        //角色表封装资源ID
        List<Integer> resIds = new ArrayList<Integer>();

        for(int i=0; i<roles.size(); i++){
            String[] split = roles.get(i).getResourceIds().split("/");
            //获取每一个Role的ResourceId 然后拆分"/",最终封装到resIds中   [1][2]
            for (int j=0; j<split.length; j++){
                resIds.add(Integer.parseInt(split[j]));
            }
        }

        //去重资源ID,并且传入到资源层查询资源,返回资源列表
        List<Permission> permissions = permissionMapper.selectBatchIds(new ArrayList<Integer>(new HashSet<Integer>(resIds)));

        Set<String> permissionNames = new HashSet<String>();

        for(Permission permission: permissions){
            permissionNames.add(permission.getPermission());
        }

        return permissionNames;
    }
}
