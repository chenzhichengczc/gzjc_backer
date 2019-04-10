package com.jc.jc_backer.modules.task.service;

import com.jc.jc_backer.modules.task.entity.Task;

import java.util.List;

/**
 * @Author: sunjw
 * @Date 2019/4/2 16:05
 * @Version 1.0
 */
public interface TaskService {

    public void newTask(Long uid,String name,String content,String title);

    public List<Task> findTask();

    public void removeByTid(Long tid,String name);

}
