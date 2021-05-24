package org.example.common.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.common.result.MessageCode;
import org.example.common.result.ResultCode;

@Data
@AllArgsConstructor //有参构造
@NoArgsConstructor //无参构造
public class BankAppException extends RuntimeException {

    /**
     * 异常状态码
     */
    private Integer code;

    /**
     * 异常信息
     */
    private String msg;

    public static void main(String[] args) {
        throw new BankAppException(ResultCode.SUCCESS, MessageCode.USER.REGISTER.USERNAME_FAILED);
    }

}
