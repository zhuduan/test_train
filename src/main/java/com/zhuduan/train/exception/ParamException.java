package com.zhuduan.train.exception;

import com.zhuduan.train.constant.ErrorCode;

/**
 * input params exception
 *
 * @author Haifeng.Zhu
 * created at 3/11/19
 */
public class ParamException extends Exception{

    public ParamException(ErrorCode errorCode) {
        super(errorCode.getMessage());
    }
}
