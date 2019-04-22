package com.jc.jc_backer.modules.task.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.jc.jc_backer.common.basic.BasicEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author: sunjw
 * @Date: 2019/4/2 15:43
 * @Version 1.0
 */
@Data
@TableName("jc_sys_task")
@ApiModel("任务表")
public class Task extends BasicEntity {
    @ApiModelProperty(value = "自增ID",name = "tid")
    @TableId(type = IdType.AUTO)
    private Long tid;

    @ApiModelProperty(value = "用户ID",name = "uid")
    @TableId(type = IdType.AUTO)
    private Long uid;

    @ApiModelProperty(value = "发布人",name = "name")
    @NotBlank(message = "发布人不能为空")
    private String name;

    @ApiModelProperty(value = "发布内容",name = "content")
    private String content;

    @ApiModelProperty(value = "发布标题",name = "title")
    private String title;

    @ApiModelProperty(value = "执行人邮箱",name = "executor")
    private String executor;

    @ApiModelProperty(value = "执行人",name = "exename")
    private String exename;

    @ApiModelProperty(value = "最后完成日期",name = "deadline")
    private String deadline;

    @ApiModelProperty(value = "是否完成0是1否，默认1否",name = "complete")
    private Integer complete=1;

    @ApiModelProperty(value = "是否删除0是1否，默认1否",name = "remove")
    private Integer remove=1;

    @ApiModelProperty(value = "级别1高-2中-3低，默认2中",name = "level")
    private Integer level;

}
