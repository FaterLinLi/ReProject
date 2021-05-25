package org.example.pojo.parameter;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassTeskPar {

    @ApiModelProperty(value = "任务ID")
    private String teskId;

    @ApiModelProperty(value = "任务状态：0为未开始；1为进行中；2为已完成；3为失败")
    private String state;

}
