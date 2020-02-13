package com.nzh.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {

    QUESTION_NOT_FOUND(2001, "你找的问题不在了，要不换个试试？"),
    TARGET_PARAM_NOT_FOUND(2002, "未选中任何问题或评论进行回复"),
    NOT_LOGIN(2003, "当前操作需要登录，请登录后重试"),
    SYSTEM_ERROR(2004, "服务器冒烟了，要不然你稍后再试试！"),
    TYPE_PARAM_WRONG(2005, "评论类型错误不存在"),
    COMMENT_NOT_FOUND(2006, "当前操作评论不存在"),
    CONTENT_IS_EMPTY(2007, "输入内容不能为空"),
    READ_NOTIFICATION_FAIL(2008, "兄弟你这是读别人的信息呢"),
    NOTIFICATION_NOT_FOUND(2009, "消息不见了"),
    FILE_UPLOAD_FAIL(20010, "获取图片URL失败"),
    FAIL_TO_UPLOAD(20011, "加载图片"),
    ;

    private Integer code;
    private String message;

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }
}
