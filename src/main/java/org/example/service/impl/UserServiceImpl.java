package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.example.common.exception.BankAppException;
import org.example.common.result.MessageCode;
import org.example.common.result.ResultCode;
import org.example.pojo.User;
import org.example.mapper.UserMapper;
import org.example.pojo.parameter.LoginPar;
import org.example.pojo.parameter.RegisterPar;
import org.example.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.util.GetOneId;
import org.example.util.MD5;
import org.example.util.SnowFlakeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.regex.Pattern;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ChenQ
 * @since 2021-05-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public boolean register(RegisterPar registerPar) {

        //8-16位密码正则表达式
        String format = "^(?=.*\\d)(?!^[0-9]+$)(?!^[A-z]+$)(?!^[^A-z0-9]+$)^[^\\s\\u4e00-\\u9fa5]{8,16}";

//        //用户名是否为空
//        if (StringUtils.isBlank(registerPar.getUserName()))
//            throw new BankAppException(ResultCode.ERROR,MessageCode.USER.REGISTER.USERNAME_NULL);

        //两次密码是否一致
        if (!registerPar.getFirstPassword().equals(registerPar.getSecondPassword())){
            throw new BankAppException(ResultCode.ERROR, MessageCode.USER.REGISTER.PASSWORD_FAILED);
        }

        //密码格式是否正确
        if (!Pattern.matches(format,registerPar.getFirstPassword())){
            throw new BankAppException(ResultCode.ERROR,MessageCode.USER.REGISTER.PASSWORD_ERROR);
        }

        //用户名是否存在
        QueryWrapper<User> wrapper_name = new QueryWrapper<>();
        wrapper_name.eq("user_name",registerPar.getUserName());
        User user_name = userMapper.selectOne(wrapper_name);
        if (user_name != null){
            throw new BankAppException(ResultCode.ERROR,MessageCode.USER.REGISTER.USERNAME_EXIST);
        }

        //生成唯一ID
        GetOneId getOneId = new GetOneId("user",8);
        String onlyId = getOneId.GetOnlyId();
        QueryWrapper<User> wrapper_id = new QueryWrapper<>();
        wrapper_id.eq("user_id",onlyId);
        User user_id = userMapper.selectOne(wrapper_id);
        if (user_id != null){
            onlyId = getOneId.GetOnlyId();
            user_id = userMapper.selectOne(wrapper_id);
        }
//        try{
//            //注册成功，插入数据
//            User user = new User();
//            user.setUserId(onlyId);
//            user.setUserName(registerPar.getUserName());
//            user.setUserPwd(MD5.encrypt(registerPar.getFirstPassword()));
//            int result = userMapper.insert(user);
//        }
//        catch (Exception e){
//            throw new BankAppException(ResultCode.ERROR,MessageCode.USER.REGISTER.ERROR);
//        }
//        return true;

        //注册成功，插入数据
        User user = new User();
        user.setUserId(onlyId);
        user.setUserName(registerPar.getUserName());
        user.setUserPwd(MD5.encrypt(registerPar.getFirstPassword()));
        int result = userMapper.insert(user);
        if (result < 1){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public boolean login(LoginPar loginPar) {

        //8-16位密码正则表达式
        String format = "^(?=.*\\d)(?!^[0-9]+$)(?!^[A-z]+$)(?!^[^A-z0-9]+$)^[^\\s\\u4e00-\\u9fa5]{8,16}";

        //密码格式错误
        if (! Pattern.matches(format,loginPar.getUserPwd())){
            throw new BankAppException(ResultCode.ERROR,MessageCode.USER.LOGIN.PASSWORD_FORMAT);
        }

        //查询用户信息
        QueryWrapper<User> queryWrapper_username = new QueryWrapper<>();
        queryWrapper_username.eq("user_name",loginPar.getUserName());
        User user = userMapper.selectOne(queryWrapper_username);
        if (user == null){
            throw new BankAppException(ResultCode.ERROR,MessageCode.USER.LOGIN.USERNAME_NULL);
        }

        //密码错误
        if (! user.getUserPwd().equals(MD5.encrypt(loginPar.getUserPwd()))){
            throw new BankAppException(ResultCode.ERROR,MessageCode.USER.LOGIN.PASSWORD_FAILED);
        }

        //登录成功
        QueryWrapper<User> queryWrapper_login = new QueryWrapper<>();
        queryWrapper_login.eq("user_name",loginPar.getUserName())
                .eq("user_pwd",MD5.encrypt(loginPar.getUserPwd()));
        User user1 = userMapper.selectOne(queryWrapper_login);
        if (user1 != null){
            return true;
        }else {
            return false;
        }
    }
}
