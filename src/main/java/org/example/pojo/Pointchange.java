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
@ApiModel(value="Pointchange对象", description="")
public class Pointchange implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "记录ID")
    @TableId(value = "point_id", type = IdType.AUTO)
    private Integer pointId;

    @ApiModelProperty(value = "用户ID")
    private String userId;

    @ApiModelProperty(value = "变动类型（1为发布任务，2为接受任务，3为完成任务，4为兑换奖品）")
    private Integer changeType;

    @ApiModelProperty(value = "记录时间")
    private Date changeTime;

    @ApiModelProperty(value = "变动数值")
    private Double change;

    @ApiModelProperty(value = "变动内容")
    private String changeDisc;


}
