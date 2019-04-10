package com.jc.jc_backer.modules.task.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.jc.jc_backer.common.exception.JcException;
import com.jc.jc_backer.modules.admin.entity.Admin;
import com.jc.jc_backer.modules.admin.mapper.AdminMapper;
import com.jc.jc_backer.modules.task.entity.Task;
import com.jc.jc_backer.modules.task.mapper.TaskMapper;
import com.jc.jc_backer.modules.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Resource
    private TaskMapper taskMapper;

    @Resource
    private AdminMapper adminMapper;

    /**
     * 添加任务信息
     * @param uid 用户id（自动）
     * @param name 用户名（自动）
     * @param content 发布内容
     * @param title 发布标题
     */
    @Override
    public void newTask(Long uid, String name, String content, String title) {
        //对一些可能出现的异常进行处理
        if(uid==null){throw new JcException("新增任务失败！尝试访问的用户ID不存在！");}
        if(name==null){throw new JcException("新增任务失败！尝试访问的用户名不存在！");}
        Admin admin=adminMapper.selectById(uid);
        if(!name.equals(admin.getName())){throw new JcException("新增任务失败！非法访问！");}
        //创建任务实体类
        Task task = new Task();
        task.setUid(uid);
        task.setName(name);
        task.setContent(content);
        task.setTitle(title);
        task.setCreateBy(name);
        //执行添加到数据库
        insertByTask(task);
    }

    /**
     * 查询所有的任务信息
     * @return 返回所有的任务信息list集合
     */
    @Override
    public List<Task> findTask() {
        Wrapper<Task> wrapper=new Wrapper<Task>() {
            @Override
            public String getSqlSegment() {
                return null;
            }
        };
        List<Task> tasklist=taskMapper.selectList(wrapper);
        for (int i=tasklist.size()-1;i>0;i--) {
            Task task=tasklist.get(i);
            if(task.getRemove()==0){
                tasklist.remove(i);
            }
        }
        return tasklist;
    }

    @Override
    public void removeByTid(Long tid,String name) {
        Task task=new Task();
        Date time = new Date();
        task.setTid(tid);
        task.setRemove(0);
        task.setProUpdateBy(name);
        task.setProUpdateTime(time);
        taskMapper.updateById(task);
    }

    /**
     * 根据Task实体类新增任务信息
     * @param task
     * @return
     */
    private Integer insertByTask(Task task) {
        Integer rows = taskMapper.insert(task);
        if (rows == 0) { throw new JcException("新增任务失败！出现未知错误请联系管理员！"); }
        return rows;
    }

}