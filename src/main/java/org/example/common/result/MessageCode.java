package org.example.common.result;

/**
 * 信息状态
 */
public interface MessageCode{

    /**
     * 用户 00
     */
    interface USER {

        /**
         * 登录 00
         */
        interface LOGIN {
            String SUSSESS = "用户登录成功";// 00
            String LOGIN_ERROR = "登录失败";
            String PHONE_NULL = "手机号码不存在";// 01
            String PHONE_FORMAT = "手机号格式错误";
            String PASSWORD_FORMAT = "密码格式错误";
            String PASSWORD_FAILED = "密码错误";// 02
            String VERTIFY_CODE_FAILED = "验证码错误"; // 04
            String PHONE_PASSWORD_CODE_NULL = "手机号、密码或验证码不能为空";
            String LOGIN_SUCCESS = "用户登录失败！";
            String USERNAME_NULL = "用户名不存在";

        }

        /**
         * 注册 01
         */
        interface REGISTER {
            String SUSSESS = "用户注册成功";// 00
            String ERROR = "用户注册失败";
            String USERNAME_FAILED = "用户名错误";//01
            String PHONE_VERTIFY_FAILED = "手机号码错误";// 02
            String PHONE_VERTIFY_FAILED1 = "手机号码格式不正确";// 03
            String PHONE_VERTIFY_FAILED2 = "手机号已存在";// 04
            String PASSWORD_FAILED = "两次密码输入不一致";// 06
            String VERTIFY_CODE_FAILED = "验证码错误";// 07
            String NULL = "输入参数不能为空";
            String PASSWORD_ERROR = "密码格式错误";
            String USERNAME_EXIST = "用户名已存在";
            String USERREGISTER_FAIL = "用户注册失败";
            String USERNAME_NULL = "用户名为空！";
        }

        /**
         * 忘记密码 02
         */
        interface FORGET_PASSWORD {
            String SUSSESS = "密码重置成功"; // 00
            String INFOMATION_FAILED = "用户信息有误"; // 01
            String PASSWORD_FAILED = "两次密码输入不一致";// 02
            String VERTIFY_CODE_FAILED = "验证码错误"; // 03
            String PASSWORD_FORMAT = "密码格式错误";  //04
            String NAME_OR_IDCARD_NOFOUNT = "姓名或身份证号不存在";  //05
            String IDCARD_FORMAT = "身份证号格式错误";
            String PASSWORD_NULL = "密码不能为空";
            String PHONE_NAME_IDCARD = "用户名、身份证号或手机号错误";
            String USERID_NULL = "用户未登录";
        }

        /**
         * 修改密码 03
         */
        interface UPDATE_PASSWORD {
            String SUSSESS = "密码修改成功"; // 00
            String INFOMATION_FAILED = "用户信息有误"; // 01
            String PASSWORD_FAILED = "两次密码输入不一致";// 02
            String PASSWORD_FORMAT = "密码格式错误";  //03
            String PASSWORD_INCONFORMITY = "原密码错误"; //04
            String VERTIFY_CODE_FAILED = "验证码错误"; //05
            String PASSWORD_NULL = "密码不能为空"; //06
            String USERID_NULL = "用户未登录";
        }
    }

    interface Typelist{

        interface ADDTYPE{
            String SUCESS = "新类型添加成功";
            String ERROR = "类型添加失败";
            String TYPENAME_EXIST = "类型名重复！";

        }
        interface FINDTYPE{
            String SUCCESS = "查找成功！";
            String ERROR = "查找失败！";
            String TYPE_NULL = "无该类型！";
        }

    }

}
