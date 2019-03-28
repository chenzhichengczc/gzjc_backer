package com.jc.jc_backer.modules.statisticVisit.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.jc.jc_backer.common.basic.BasicEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Charles Chan
 * @Date: 2019/3/14 15:54
 * @Version 1.0
 */
@TableName("jc_statistic")
@Data
@ApiModel("访问主体信息")
public class Statistic extends BasicEntity {

    @ApiModelProperty(value = "自增id",name = "id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "IP地址",name = "addressIp")
    @TableField("address_ip")
    private String addressIp;

    @ApiModelProperty(value = "用户引擎",name = "addressUserAgent")
    @TableField("address_userAgent")
    private String addressUserAgent;

}
