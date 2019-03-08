package com.zhuduan.test.train.constant;

/**
 * purpose of this class
 *
 * @author Haifeng.Zhu
 * created at 3/8/19
 */
public enum ErrorCode {
    
    NO_SUCH_ROUTE(1, "NO SUCH ROUTE"),
    ILLAGEL_TRAIN_PLAN(2, "Illegal train plan."),
    UNSUPPORTED_TRAIN_PLAN(3, "Not support this train plan.");
    
    
    private Integer id;
    private String message;

    ErrorCode(Integer id, String message) {
        this.id = id;
        this.message = message;
    }

    public Integer getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }
}
