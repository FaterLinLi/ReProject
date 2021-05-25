package org.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.example.common.exception.BankAppException;
import org.example.common.result.MessageCode;
import org.example.common.result.R;
import org.example.common.result.ResultCode;
import org.example.mapper.UserMapper;
import org.example.pojo.Tesk;
import org.example.pojo.User;
import org.example.pojo.parameter.LoginPar;
import org.example.pojo.parameter.RegisterPar;
import org.example.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ChenQ
 * @since 2021-05-22
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    //用户注册
    @RequestMapping("/register")
    public Boolean register(@Valid @RequestBody RegisterPar registerPar){
        if (userService.register(registerPar)){
//            return R.ok().code(e.getCode()).message(e.getMsg());
            throw new BankAppException(ResultCode.SUCCESS,MessageCode.USER.REGISTER.SUSSESS);
        }else {
//            return "注册失败";
           throw new BankAppException(ResultCode.ERROR,MessageCode.USER.REGISTER.ERROR);
        }
    }

    //用户登录
    @RequestMapping("/login")
    public String login(@Valid @RequestBody LoginPar loginPar){
        if (userService.login(loginPar)){
            throw new BankAppException(ResultCode.SUCCESS,MessageCode.USER.LOGIN.SUSSESS);
        }else {
            throw new BankAppException(ResultCode.ERROR,MessageCode.USER.LOGIN.LOGIN_ERROR);
        }
    }

    //用户分页查询
    @GetMapping("/page/{pageNum}")
    public List<User> queryUserList(@PathVariable long pageNum){
        Page<User> page = new Page<>(pageNum, 15);
        userMapper.selectPage(page,null);
        return page.getRecords();
    };


    @Resource
    private UserMapper userMapper;

    @GetMapping("/id/{userId}")
    @ApiOperation(value = "根据用户ID查找用户对象")
    @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "String", paramType = "path")
    public User queryUserById(@PathVariable String userId){
        User user = userMapper.selectById(userId);
        if (user==null)
            throw new BankAppException(ResultCode.ERROR,MessageCode.USER.REGISTER.NULL);
        return user;
    }

    @GetMapping("/name/{uname}")
    @ApiOperation(value = "根据用户名查询用户对象", notes = "用户名称不能为空")
    public User queryUserByName(@PathVariable String uname){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name",uname);
        User user = userMapper.selectOne(wrapper);
        if (user == null)
            throw new BankAppException(ResultCode.ERROR, MessageCode.USER.REGISTER.NULL);
        return user;
    }

    //逻辑删除
    @PutMapping("/delete/{userId}")
    public String deleteUserById(@PathVariable String userId){
        userMapper.deleteById(userId);
        return "Delete Success!";
    }


}
