package org.example.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@ApiModel(value="Teskpack对象", description="")
public class Teskpack implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "任务包ID")
    @TableId(value = "taskpack_id", type = IdType.ID_WORKER)
    private String taskpackId;

    @ApiModelProperty(value = "任务ID")
    private String taskId;

    @ApiModelProperty(value = "承接用户ID")
    private String userId;

    @ApiModelProperty(value = "承接时间")
    private Date getTime;

    @ApiModelProperty(value = "任务包含有图片")
    private Integer teskpackGet;

    @ApiModelProperty(value = "任务包完成图片")
    private Integer teskpackEnd;

    @ApiModelProperty(value = "任务包报酬")
    private Integer teskpackPay;

    @ApiModelProperty(value = "任务包权重")
    private Float teskpackWeight;

    @ApiModelProperty(value = "任务状态：0为未承接；1为未开始；2为进行中；3为已完成；4为已失败")
    private Integer teskpackState;

    @ApiModelProperty(value = "乐观锁")
    @Version
    private Integer version;


}
