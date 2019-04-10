package com.jc.jc_backer.modules.task.controller;

import com.jc.jc_backer.common.utils.ResponseUtil;
import com.jc.jc_backer.modules.admin.entity.Admin;
import com.jc.jc_backer.modules.task.entity.Task;
import com.jc.jc_backer.modules.task.service.TaskService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Resource
    private TaskService taskService;

    @PostMapping("/newTask")
    public ResponseUtil newTask(HttpSession session,String content,String title){
        Admin admin=(Admin) session.getAttribute("admin");
        Long uid=admin.getId();
        String name=admin.getName();
        taskService.newTask(uid,name,content,title);
        return ResponseUtil.success("添加成功！");
    }

    @GetMapping("/findTask")
    public ResponseUtil findTask(){
        List<Task> tasklist = taskService.findTask();
        return ResponseUtil.success(tasklist);
    }

    @GetMapping("/removeTask")
    public ResponseUtil removeTask(@RequestParam("tid") Long tid, HttpSession session){
        Admin admin = (Admin) session.getAttribute("admin");
        String name = admin.getName();
        taskService.removeByTid(tid,name);
        return ResponseUtil.success("删除成功!");
    }

}
