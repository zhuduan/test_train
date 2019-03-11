package com.zhuduan.train.bo.suggestion;


/**
 * the suggestion result of the plan
 *
 * @author Haifeng.Zhu
 * created at 3/8/19
 */
public class Suggestion {

    private int planId;
    private String message;     // the calculation result

    public Suggestion() {
    }

    public Suggestion(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }
}
