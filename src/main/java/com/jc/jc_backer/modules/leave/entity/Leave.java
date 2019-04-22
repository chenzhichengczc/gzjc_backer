package com.jc.jc_backer.modules.leave.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.jc.jc_backer.common.basic.BasicEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author:sun jw
 * @Date:2019/4/18 0018 14:32
 * @Version 1.0
 */
@Data
@TableName("jc_sys_leave")
@ApiModel("留言表")
public class Leave extends BasicEntity {

    @ApiModelProperty(value = "自增ID",name = "lid")
    @TableId(type = IdType.AUTO)
    private Integer lid;

    @ApiModelProperty(value = "用户ID",name = "uid")
    @TableId(type = IdType.AUTO)
    private Long uid;

    @ApiModelProperty(value = "用户名",name = "lname")
    @NotBlank(message = "用户名不能为空")
    private String lname;

    @ApiModelProperty(value = "用户头像",name = "headPortrait")
    private String headPortrait;

    @ApiModelProperty(value = "留言内容",name = "content")
    private String content;

}
