package com.jc.jc_backer.modules.task.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jc.jc_backer.modules.task.entity.Task;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author: sunjw
 * @Date 2019/4/2 16:01
 * @Version 1.0
 */
public interface TaskMapper extends BaseMapper<Task> {

    public List<Task> selectTaskMap(Map<String,Object> map);
}
