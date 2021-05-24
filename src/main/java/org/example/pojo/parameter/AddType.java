package org.example.pojo.parameter;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddType {

    @ApiModelProperty(value = "类型名")
    @NotBlank(message = "类型名不能为空！")
    @Length(max = 15, message = "类型名最长15位！")
    private String typeName;

}
