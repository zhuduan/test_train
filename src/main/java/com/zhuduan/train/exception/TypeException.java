package com.zhuduan.train.exception;

import com.zhuduan.train.constant.ErrorCode;

/**
 * the exception of type
 *
 * @author Haifeng.Zhu
 * created at 3/10/19
 */
public class TypeException extends Exception {

    public TypeException(ErrorCode errorCode) {
        super(errorCode.getMessage());
    }
}
