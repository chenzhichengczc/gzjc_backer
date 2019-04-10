package com.jc.jc_backer.modules.admin.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.jc.jc_backer.common.basic.BasicEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author: Charles Chan
 * @Date: 2019/3/17 16:53
 * @Version 1.0
 */
@Data
@TableName("jc_sys_user")
@ApiModel("用户主体表")
public class Admin extends BasicEntity {

    //编号
    @ApiModelProperty(value = "自增ID",name = "id")
    @TableId(type = IdType.AUTO)
    private  long id;

    //组织ID
    @ApiModelProperty(value = "组织ID",name = "organizationId")
    @TableId(type = IdType.AUTO)
    private  long organizationId = 1;

    //用户名
    @ApiModelProperty(value = "用户名",name = "username")
    @NotBlank(message = "用户名不能为空")
    private  String username;

    //密码
    @ApiModelProperty(value = "密码",name = "password")
    @NotBlank(message = "密码不能为空")
    private  String password;

    //盐值
    @ApiModelProperty(value = "盐值",name = "salt")
    private  String salt;

    //角色列表
    @ApiModelProperty(value = "角色列表",name = "roleIds")
    private String roleIds;

    //是否锁定 1 不锁定 0锁定
    @ApiModelProperty(value = "是否锁定",name = "locked")
    private  int locked = 1;

    //姓名
    @ApiModelProperty(value = "姓名",name = "name")
    private  String name ;

    //邮箱
    @ApiModelProperty(value = "邮箱",name = "email")
    private  String email ;

    //电话
    @ApiModelProperty(value = "电话",name = "telephone")
    private  String telephone ;

    //QQ
    @ApiModelProperty(value = "QQ",name = "qq")
    private  String qq ;

    //权限--(0-管理员,1-vip员工,2-普通员工)
    @ApiModelProperty(value = "权限",name = "authority")
    private  Integer authority;

    //简介
    @ApiModelProperty(value = "简介",name = "intro")
    private String intro;

    //头像
    @ApiModelProperty(value = "头像",name = "headPortrait")
    private  String headPortrait ;

}
