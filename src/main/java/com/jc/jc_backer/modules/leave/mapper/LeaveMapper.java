package com.jc.jc_backer.modules.leave.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jc.jc_backer.modules.leave.entity.Leave;

import java.util.List;
import java.util.Map;

/**
 * @Author:sun jw
 * @Date:2019/4/18 0018 14:39
 * @Version 1.0
 */
public interface LeaveMapper extends BaseMapper<Leave> {

    public List<Leave> findByLeaveMap(Map<String,Object> map);
}
