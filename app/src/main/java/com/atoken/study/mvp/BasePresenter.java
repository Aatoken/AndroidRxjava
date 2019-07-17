package com.atoken.study.mvp;

/**
 * Author Aatoken
 * Date 2019/7/16 17:57
 * Description
 */
public abstract class BasePresenter <T extends BaseView>{

    protected final T mView;

    protected BasePresenter(T mView) {
        this.mView = mView;
    }
}
