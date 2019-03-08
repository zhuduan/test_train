package com.zhuduan.train.exception;

import com.zhuduan.train.constant.ErrorCode;

/**
 * the Data Exception
 *
 * @author Haifeng.Zhu
 * created at 3/8/19
 */
public class DataException extends Exception {

    public DataException(ErrorCode errorCode) {
        super(errorCode.getMessage());
    }
}
