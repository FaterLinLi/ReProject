package org.example.pojo.parameter;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description="任务上传功能类")
public class TeskUploadPar {

    @ApiModelProperty(value = "发布者ID")
    private String userId;

    @ApiModelProperty(value = "任务标题")
    @NotBlank(message = "任务标题不能为空！")
    private String teskTitle;

    @ApiModelProperty(value = "任务简介")
    private String teskDisc;

    @ApiModelProperty(value = "任务标注提示")
    @NotBlank(message = "任务标注提示不能为空！")
    private String teskNeed;

    @ApiModelProperty(value = "任务类型")
    private Integer teskType;

    @ApiModelProperty(value = "用户等级限制，0为无限制")
    private Integer limitLevel;

    @ApiModelProperty(value = "用户信用限制，0为无限制")
    private Integer limitCredit;

    @ApiModelProperty(value = "任务花费")
    private Integer payment;

    @ApiModelProperty(value = "所需人数")
    private Integer needNum;

    @ApiModelProperty(value = "标签数（至少两个）")
    private Integer tagNum;

    @ApiModelProperty(value = "标签1")
    @NotBlank(message = "任务标签不能为空！")
    private String tag01;

    @ApiModelProperty(value = "标签2")
    @NotBlank(message = "任务标签不能为空！")
    private String tag02;

    @ApiModelProperty(value = "标签3")
    private String tag03;

    @ApiModelProperty(value = "标签4")
    private String tag04;

    @ApiModelProperty(value = "标签5")
    private String tag05;


}
