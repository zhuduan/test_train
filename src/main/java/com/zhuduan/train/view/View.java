package com.zhuduan.train.view;

import com.zhuduan.train.bo.suggestion.Suggestion;

/**
 * render the content
 *
 * @author Haifeng.Zhu
 * created at 3/8/19
 */
public interface View {
    
    void render(Suggestion suggestion);
}
