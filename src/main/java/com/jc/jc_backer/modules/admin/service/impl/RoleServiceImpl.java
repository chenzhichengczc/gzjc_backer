package com.jc.jc_backer.modules.admin.service.impl;

import com.jc.jc_backer.common.exception.JcException;
import com.jc.jc_backer.modules.admin.entity.Role;
import com.jc.jc_backer.modules.admin.mapper.RoleMapper;
import com.jc.jc_backer.modules.admin.service.RoleService;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * @Author: Charles Chan
 * @Date: 2019/3/18 11:54
 * @Version 1.0
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    /**
     * 通过ids 找寻对应的角色
     * @param roleIds
     * @return
     */
    @Override
    public List<Role> findRoles(@NotNull String roleIds) throws JcException {
        String[] roleIdArray = roleIds.split("/");

        List<Integer> list = new ArrayList<Integer>();

        for (int i = 0;i < roleIdArray.length; i++){
            list.add(Integer.parseInt(roleIdArray[i]));
        }

        //获取Role集合对象
        List<Role> roles = roleMapper.selectBatchIds(list);

        return roles;
    }

}
