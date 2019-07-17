package com.atoken.study.mvp;

import java.security.PublicKey;

/**
 * Author Aatoken
 * Date 2019/7/16 17:59
 * Description 契约接口描述MVP结构
 */
public interface Contract {

    interface View extends BaseView<Presenter> {
        void update(String text);
    }

    abstract class Presenter extends BasePresenter<View> {
        protected Presenter(View mView) {
            super(mView);
        }

        abstract void loadDate();
    }

}
