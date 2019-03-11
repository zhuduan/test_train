package com.zhuduan.train.view;

import com.zhuduan.train.bo.suggestion.Suggestion;

/**
 * render the result to console
 *
 * @author Haifeng.Zhu
 * created at 3/8/19
 */
public class ConsoleView implements View{
    
    @Override
    public void render(Suggestion suggestion){
        System.out.println(String.format("Output #%s: %s", suggestion.getPlanId(), suggestion.getMessage()));
    }
}
