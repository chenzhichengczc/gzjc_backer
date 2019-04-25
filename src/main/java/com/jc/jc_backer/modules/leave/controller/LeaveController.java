package com.jc.jc_backer.modules.leave.controller;

import com.jc.jc_backer.common.utils.ResponseUtil;
import com.jc.jc_backer.modules.admin.entity.Admin;
import com.jc.jc_backer.modules.leave.entity.Leave;
import com.jc.jc_backer.modules.leave.service.LeaveService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

/**
 * @Author:sun jw
 * @Date:2019/4/18 0018 15:26
 * @Version 1.0
 */
@RestController
@RequestMapping("/leave")
public class LeaveController {

    @Resource
    private LeaveService leaveService;

    /**
     * 添加留言
     * @param session 获取session
     * @param content 获取留言内容
     * @return 成功信息
     */
    @PostMapping("/newLeave")
    public ResponseUtil newLeave(HttpSession session,String content){
        Admin admin=(Admin) session.getAttribute("admin");//获取session中的当前用户
        Leave leave=new Leave();//创建留言实体类
        leave.setUid(admin.getId());
        leave.setLname(admin.getName());
//        leave.setHeadPortrait(admin.getHeadPortrait());
        leave.setContent(content);
        leave.setCreateBy(admin.getName());
        leaveService.newLeave(leave);//添加进数据库
        return ResponseUtil.success("添加成功");
    }

    /**
     * 显示留言
     * @param time 根据时间查找
     * @param content 根据内容查找
     * @return 查询的留言信息
     */
    @GetMapping("/findLeaves")
    public ResponseUtil findLeaves(String time,String content){
        HashMap<String,Object> hashMap = new HashMap<>();//创建hashMap进行绑定数据
        hashMap.put("time",time);//绑定时间
        hashMap.put("content",content);//绑定内容
        List<Leave> leaves = leaveService.findByLeaves(hashMap);//从数据库获取查询的数据
        return ResponseUtil.success(leaves);
    }

}
