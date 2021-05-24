package org.example.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID")
    @TableId(value = "user_id")
    private String userId;

    @ApiModelProperty(value = "用户类型（0为管理员，1为普通用户，2为删除用户）")
    private Integer userType;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "用户密码")
    private String userPwd;

    @ApiModelProperty(value = "用户等级")
    private Integer userLevel;

    @ApiModelProperty(value = "用户信用")
    private Integer userCredit;

    @ApiModelProperty(value = "用户是否认证")
    private Integer userSptype;

    @ApiModelProperty(value = "用户认证信息")
    private String userSp;

    @ApiModelProperty(value = "用户积分")
    private Integer userPoints;

    @ApiModelProperty(value = "用户头像")
    private String userUrl;

    @ApiModelProperty(value = "逻辑删除")
    @TableLogic
    private Integer deleted;


}
