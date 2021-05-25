package org.example.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 
 * </p>
 *
 * @author ChenQ
 * @since 2021-05-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Tesk对象", description="")
public class Tesk implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "任务ID")
    @TableId(value = "tesk_id", type = IdType.ID_WORKER)
    private String teskId;

    @ApiModelProperty(value = "发布者ID")
    private String userId;

    @ApiModelProperty(value = "发布时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    @ApiModelProperty(value = "任务标题")
    private String teskTitle;

    @ApiModelProperty(value = "任务简介")
    private String teskDisc;

    @ApiModelProperty(value = "任务标注提示")
    private String teskNeed;

    @ApiModelProperty(value = "任务类型")
    private String teskType;

    @ApiModelProperty(value = "任务状态：0为未开始；1为进行中；2为已完成；3为失败")
    private String state;

    @ApiModelProperty(value = "用户等级限制，0为无限制")
    private Integer limitLevel;

    @ApiModelProperty(value = "用户信用限制，0为无限制")
    private Integer limitCredit;

    @ApiModelProperty(value = "图片数量")
    @NotBlank(message = "至少上传一张图片！")
    private Integer picNum;

    @ApiModelProperty(value = "任务花费")
    private Integer payment;

    @ApiModelProperty(value = "所需人数")
    private Integer needNum;

    @ApiModelProperty(value = "已有人数")
    private Integer haveNum;

    @ApiModelProperty(value = "已完成人数")
    private Integer finishNum;

    @ApiModelProperty(value = "标签数（至少两个）")
    private Integer tagNum;

    @ApiModelProperty(value = "标签1")
    private String tag01;

    @ApiModelProperty(value = "标签2")
    private String tag02;

    @ApiModelProperty(value = "标签3")
    private String tag03;

    @ApiModelProperty(value = "标签4")
    private String tag04;

    @ApiModelProperty(value = "标签5")
    private String tag05;

    @ApiModelProperty(value = "逻辑删除")
    @TableLogic
    private Integer deleted;

}
