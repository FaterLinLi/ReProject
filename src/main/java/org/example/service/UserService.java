package org.example.service;

import org.example.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.pojo.parameter.LoginPar;
import org.example.pojo.parameter.RegisterPar;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ChenQ
 * @since 2021-05-22
 */
public interface UserService extends IService<User> {

    //用户注册
    boolean register(RegisterPar registerPar);

    //用户登录
    boolean login(LoginPar loginPar);
}
