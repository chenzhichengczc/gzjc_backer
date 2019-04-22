package com.jc.jc_backer.modules.leave.service;

import com.jc.jc_backer.modules.leave.entity.Leave;

import java.util.HashMap;
import java.util.List;

/**
 * @Author:sun jw
 * @Date:2019/4/18 0018 14:41
 * @Version 1.0
 */
public interface LeaveService {

    public void newLeave(Leave leave);

    public List<Leave> findByLeaves(HashMap<String,Object> hashMap);

}
