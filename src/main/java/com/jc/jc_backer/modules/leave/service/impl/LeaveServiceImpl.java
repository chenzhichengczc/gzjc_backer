package com.jc.jc_backer.modules.leave.service.impl;

import com.jc.jc_backer.common.exception.JcException;
import com.jc.jc_backer.modules.admin.entity.Admin;
import com.jc.jc_backer.modules.admin.mapper.AdminMapper;
import com.jc.jc_backer.modules.leave.entity.Leave;
import com.jc.jc_backer.modules.leave.mapper.LeaveMapper;
import com.jc.jc_backer.modules.leave.service.LeaveService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:sun jw
 * @Date:2019/4/18 0018 14:42
 * @Version 1.0
 */
@Service
public class LeaveServiceImpl implements LeaveService {

    @Resource
    private LeaveMapper leaveMapper;

    @Resource
    private AdminMapper adminMapper;

    /**
     * 添加留言
     * @param leave 留言信息
     */
    @Override
    public void newLeave(Leave leave) {
        if("".equals(leave.getContent())){
            throw new JcException("留言失败，留言内容不可为空");
        }
        Admin admin=adminMapper.selectById(leave.getUid());
        String headPortrait=admin.getHeadPortrait();
        leave.setHeadPortrait(headPortrait);
        insertLeave(leave);//新增
    }

    @Override
    public List<Leave> findByLeaves(HashMap<String, Object> hashMap) {
        Map<String,Object> map = hashMap;
        List<Leave> leaves=leaveMapper.findByLeaveMap(map);
        return leaves;
    }

    private Integer insertLeave(Leave leave){
        Integer rows=leaveMapper.insert(leave);
        if(rows!=1){
            throw new JcException("留言失败，请联系管理员！");
        }
        return rows;
    }
}
