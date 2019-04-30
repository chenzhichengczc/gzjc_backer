package com.jc.jc_backer.modules.Word.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@TableName("jc_word")
@ApiModel("用户需求说明书")
@Component
public class WordEntity {

    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "自增ID",name = "id")
    private Integer id;

    @ApiModelProperty(value = "日期", name = "dates")
    private String date;

    @ApiModelProperty(value = "制定", name = "enact")
    private String enact;

    @ApiModelProperty(value = "类别", name = "sort")
    private String sort;

    @ApiModelProperty(value = "批准", name = "ratify")
    private String ratify;

    @ApiModelProperty(value = "描述", name = "describes")
    private String describes;

    @ApiModelProperty(value = "术语", name = "term")
    private String term;

    @ApiModelProperty(value = "解释说明", name = "explanation")
    private String explanation;

    @ApiModelProperty(value = "文档名称", name = "docName")
    private String docName;

    @ApiModelProperty(value = "文档标识号", name = "docLabelNo")
    private String docLabelNo;

    @ApiModelProperty(value = "需求编号", name = "reqId")
    private String reqId;

    @ApiModelProperty(value = "优先级", name = "priority")
    private String priority;

    @ApiModelProperty(value = "用例名称", name = "testCaseName")
    private String testCaseName;

    @ApiModelProperty(value = "主要角色", name = "leadRole")
    private String leadRole;

    @ApiModelProperty(value = "前置条件", name = "precondition")
    private String precondition;

    @ApiModelProperty(value = "后置条件", name = "postCondition")
    private String postCondition;

    @ApiModelProperty(value = "功能类别", name = "functionalCategory")
    private String functionalCategory;

    @ApiModelProperty(value = "功能设计描述", name = "functionalDesignDescription")
    private String functionalDesignDescription;

    @ApiModelProperty(value = "补充说明", name = "supplementaryInstruction")
    private String supplementaryInstruction;

    @ApiModelProperty(value = "预计人工", name = "expectedToArtificial")
    private String expectedToArtificial;

    @ApiModelProperty(value = "开发约束", name = "developmentConstraints")
    private String developmentConstraints;

    @ApiModelProperty(value = "进度目标", name = "progressOfTheTarget")
    private String progressOfTheTarget;

    @ApiModelProperty(value = "质量期望", name = "qualityExpectations")
    private String qualityExpectations;

    @ApiModelProperty(value = "人员要求", name = "staffRequirement")
    private String staffRequirement;

    @ApiModelProperty(value = "验收准则", name = "acceptanceCriteria")
    private String acceptanceCriteria;

    @ApiModelProperty(value = "维护要求", name = "maintenanceRequirements")
    private String maintenanceRequirements;

    @ApiModelProperty(value = "保密要求", name = "secretAndConfidential")
    private String secretAndConfidential;

    @ApiModelProperty(value = "客户公司名称", name = "customerCompanyName")
    private String customerCompanyName;

    @ApiModelProperty(value = "联系人", name = "linkman")
    private String linkman;

    @ApiModelProperty(value = "联系方式", name = "contactWay")
    private String contactWay;

    @ApiModelProperty(value = "最终用户", name = "finalUser")
    private String finalUser;



}
