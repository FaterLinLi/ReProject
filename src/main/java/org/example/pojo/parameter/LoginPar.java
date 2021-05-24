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
public class LoginPar {

    @ApiModelProperty(value = "用户名")
    @NotBlank(message = "用户名称不能为空！")
    @Length(min = 5, max = 20, message = "用户名长度不得多于20位,最少为5位")
    private String userName;
    @ApiModelProperty(value = "用户密码")
    @NotBlank(message = "请输入密码!")
    @Length(min = 6, max = 15, message = "用户密码的长度至少6位，至多15位！")
    private String userPwd;
    //private String imageCode;

}
