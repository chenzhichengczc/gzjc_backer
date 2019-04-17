package com.jc.jc_backer.modules.admin.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jc.jc_backer.modules.admin.entity.Admin;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

/**
 * @Author: Charles Chan
 * @Date: 2019/3/17 17:21
 * @Version 1.0
 */
public interface AdminMapper extends BaseMapper<Admin> {

    public String findByExecutor(String executor);
}
