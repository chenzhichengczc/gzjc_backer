package com.jc.jc_backer.modules.table.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.jc.jc_backer.common.basic.BasicEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@TableName("jc_sys_table")
@ApiModel("表格")
public class Table extends BasicEntity {
    @ApiModelProperty(value = "自增ID",name = "tid")
    @TableId(type = IdType.AUTO)
    private Long tid;

    @ApiModelProperty(value = "标题",name = "title")
    private String title;

    @ApiModelProperty(value = "类别",name = "category")
    private String category;

    @ApiModelProperty(value = "作者",name = "author")
    private String author;

    @ApiModelProperty(value = "网站",name = "website")
    private String website;

    @ApiModelProperty(value = "二维码",name = "wechatQrCode")
    private String wechatQrCode;

    @ApiModelProperty(value = "审核（0通过1未审核2未通过），默认1未审核",name = "audit")
    private Integer audit=1;

    @ApiModelProperty(value = "是否删除0是1否，默认1否",name = "remove")
    private Integer remove=1;

}
