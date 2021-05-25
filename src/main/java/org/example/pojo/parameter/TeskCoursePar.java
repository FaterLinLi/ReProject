package org.example.pojo.parameter;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel(value = "Tesk查询对象", description = "课程查询对象封装")
@Data
public class TeskCoursePar {


    @ApiModelProperty(value = "时间筛选方式")
    private String timeType;

    @ApiModelProperty(value = "任务标题/简介相关内容")
    private String teskAbout;

    @ApiModelProperty(value = "任务类型")
    private String teskType;

}
