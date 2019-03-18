package com.jc.jc_backer.common.basic;

import com.baomidou.mybatisplus.annotations.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Charles Chan
 * @Date: 2019/3/11 16:58
 * @Version 1.0
 */
@Data
@ApiModel("基础实体类")
public class BasicEntity implements Serializable {

    @ApiModelProperty(value = "创建时间",name = "createTime")
    @TableField(value = "create_time")
    private Date createTime = new Date();

    @ApiModelProperty(value = "创建人",name = "createBy")
    @TableField(value = "create_by")
    private  String createBy = "Charles";

    @ApiModelProperty(value = "修改时间",name = "proUpdateTime")
    @TableField(value = "pro_update_time")
    private  Date proUpdateTime = new Date();

    @ApiModelProperty(value = "修改人",name = "proUpdateBy")
    @TableField(value = "pro_update_by")
    private  String proUpdateBy = "Charles";
}
