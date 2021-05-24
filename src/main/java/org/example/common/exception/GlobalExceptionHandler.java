package org.example.common.exception;


import org.example.common.result.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends RuntimeException {

//    /**
//     * 全局异常处理
//     * @param e
//     * @return
//     */
//    @ExceptionHandler(Exception.class)
//    @ResponseBody
//    public R error(Exception e){
//        e.printStackTrace();
//        return R.error().message("执行了全局异常处理");
//    }


    /**
     * 自定义异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(BankAppException.class)
    @ResponseBody
    public R error(BankAppException e){
        log.error(e.getMessage());
        e.printStackTrace();
        return R.error().code(e.getCode()).message(e.getMsg());
    }

//    @ExceptionHandler(BankAppException.class)
//    @ResponseBody
//    public R ok(BankAppException e){
//        log.error(e.getMessage());
//        e.printStackTrace();
//        return R.ok().code(e.getCode()).message(e.getMsg());
//    }



}

























