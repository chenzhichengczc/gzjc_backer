package com.jc.jc_backer.modules.task.controller;

import com.jc.jc_backer.common.utils.ResponseUtil;
import com.jc.jc_backer.modules.admin.entity.Admin;
import com.jc.jc_backer.modules.task.entity.Task;
import com.jc.jc_backer.modules.task.service.TaskService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Resource
    private TaskService taskService;

    @PostMapping("/newTask")
    public ResponseUtil newTask(HttpSession session,String content,String title,String executor,String deadline,Integer level){
        Admin admin=(Admin) session.getAttribute("admin");
        Long uid=admin.getId();
        String name=admin.getName();
        taskService.newTask(uid,name,content,title,executor,deadline,level);
        return ResponseUtil.success("添加成功！");
    }

    @GetMapping("/findTask")
    public ResponseUtil findTask(HttpSession session){
        Admin admin = (Admin)session.getAttribute("admin");
        if(!admin.getUsername().equals("admin")){
            return null;
        }
        List<Task> tasklist = taskService.findTask();
        return ResponseUtil.success(tasklist);
    }

    @GetMapping("/findAdmin")
    public ResponseUtil findAdmin(){
        List<Admin> adminList = taskService.findAdmin();
        return ResponseUtil.success(adminList);
    }

    @GetMapping("/findById")
    public ResponseUtil findById(@RequestParam("tid") Long tid){
        Task task=taskService.findById(tid);
        return ResponseUtil.success(task);
    }

    @GetMapping("/removeTask")
    public ResponseUtil removeTask(@RequestParam("tid") Long tid, HttpSession session){
        Admin admin = (Admin) session.getAttribute("admin");
        String name = admin.getName();
        taskService.removeByTid(tid,name);
        return ResponseUtil.success("删除成功!");
    }

    @PostMapping("/updateByTask")
    public ResponseUtil updateByTask(Task task){
        taskService.updateByTask(task);
        return ResponseUtil.success("修改成功!") ;
    }

    @GetMapping("/selectTaskMap")
    public ResponseUtil selectTaskMap(HttpSession session,String time,String content){
        Admin admin = (Admin)session.getAttribute("admin");
        HashMap<String,Object> hashMap =new HashMap<>();
        hashMap.put("email",admin.getEmail());
        hashMap.put("time",time);
        hashMap.put("content",content);
        List<Task> tasks=taskService.selectTaskMap(hashMap);
        return ResponseUtil.success(tasks);
    }

}
