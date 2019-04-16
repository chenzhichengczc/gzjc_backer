package com.jc.jc_backer.modules.task.service;

import com.jc.jc_backer.modules.admin.entity.Admin;
import com.jc.jc_backer.modules.task.entity.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: sunjw
 * @Date 2019/4/2 16:05
 * @Version 1.0
 */
public interface TaskService {

    public void newTask(Long uid,String name,String content,String title,String executor,Integer level);

    public List<Task> findTask();

    public List<Admin> findAdmin();

    public Task findById(Long tid);

    public void removeByTid(Long tid,String name);

    public void updateByTask(Task task);

    public List<Task> selectTaskMap(HashMap<String,Object> hashMap);

}
