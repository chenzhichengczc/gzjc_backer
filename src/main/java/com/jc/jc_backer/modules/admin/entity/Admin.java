package com.jc.jc_backer.modules.admin.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.jc.jc_backer.basic.BasicEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Charles Chan
 * @Date: 2019/3/17 16:53
 * @Version 1.0
 */
@Data
@TableName("jc_sys_admin")
@ApiModel("用户主体表")
public class Admin extends BasicEntity {

    //编号
    @ApiModelProperty(value = "自增ID",name = "id")
    @TableId(type = IdType.AUTO)
    private  long id;

    //用户名
    @ApiModelProperty(value = "用户名",name = "username")
    private  String username;

    //密码
    @ApiModelProperty(value = "密码",name = "password")
    private  String password;

    //盐值
    @ApiModelProperty(value = "盐值",name = "salt")
    private  String salt;

    //角色列表
    @ApiModelProperty(value = "角色列表",name = "roleId")
    private  String roleId;

    //是否锁定 0 不锁定 1锁定
    @ApiModelProperty(value = "是否锁定",name = "locked")
    private  int locked = 0;


}
