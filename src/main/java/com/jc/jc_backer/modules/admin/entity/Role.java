package com.jc.jc_backer.modules.admin.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Charles Chan
 * @Date: 2019/3/18 11:25
 * @Version 1.0
 */
@Data
@TableName("jc_sys_role")
@ApiModel("角色主体表")
public class Role {

    //编号
    @ApiModelProperty(value = "自增ID",name = "id")
    @TableId(type = IdType.AUTO)
    private  long id;

    //角色名称
    @ApiModelProperty(value = "角色名称",name = "role")
    private  String role;

    //角色描述
    @ApiModelProperty(value = "角色描述",name = "description")
    private  String description;

    //授权资源组
    @ApiModelProperty(value = "授权资源组",name = "resourceIds")
    private  String resourceIds;

    //是否锁定 1 不锁定 0锁定
    @ApiModelProperty(value = "是否锁定",name = "available")
    private  int available = 1;

}
