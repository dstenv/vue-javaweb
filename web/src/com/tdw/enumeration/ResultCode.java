package com.tdw.enumeration;

public enum ResultCode {
    SUCCESS(0, "成功"),
    ERROR(-1, "系统异常"),
    PARAM_ERROR(1001, "参数异常"),
    USER_EXIST_ERROR(2001, "用户已存在"),
    USER_ACCOUNT_ERROR(2002, "账号或密码错误"),
    USER_NOT_EXIST_ERROR(2003, "未找到用户"),
    USER_UPDATE_ERROR(100,"修改失败"),
    ;

    public Integer code;
    public String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
