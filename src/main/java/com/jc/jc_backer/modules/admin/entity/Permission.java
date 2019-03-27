package com.jc.jc_backer.modules.admin.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author: Charles Chan
 * @Date: 2019/3/18 11:00
 * @Version 1.0
 */
@Data
@TableName("jc_sys_resource")
@ApiModel("资源主体表")
public class Permission {


    //编号
    @ApiModelProperty(value = "自增ID",name = "id")
    @TableId(type = IdType.AUTO)
    private  long id;

    //用户名
    @ApiModelProperty(value = "资源名称",name = "name")
    private  String name;

    //密码
    @ApiModelProperty(value = "资源类型",name = "type")
    private  String type;

    //排序
    @ApiModelProperty(value = "路径",name = "url")
    private  String url;

    //父编号
    @ApiModelProperty(value = "父编号",name = "parentId")
    private  Integer parentId;

    //父编号组
    @ApiModelProperty(value = "父编号组",name = "parentIds")
    private  String parentIds;

    //权限字符串
    @ApiModelProperty(value = "权限字符串",name = "permission")
    private  String permission;


    //是否锁定 1 不锁定 0锁定
    @ApiModelProperty(value = "是否锁定",name = "available")
    private  int available = 0;
}
