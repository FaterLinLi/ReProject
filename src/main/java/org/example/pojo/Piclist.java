package org.example.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
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
@ApiModel(value="Piclist对象", description="")
public class Piclist implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "图片ID")
    @TableId(value = "pic_id", type = IdType.ID_WORKER)
    private String picId;

    @ApiModelProperty(value = "任务ID")
    private String teskId;

    private Integer inteskid;

    @ApiModelProperty(value = "图片链接")
    private String picUrl;

    @ApiModelProperty(value = "图片标签")
    private Float picTag01;

    private Float picTag02;

    private Float picTag03;

    private Float picTag04;

    private Float picTag05;

    private String pack01;

    private String pack02;

    private String pack03;


}
