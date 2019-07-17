package com.atoken.study.observable;

/**
 * Author Aatoken
 * Date 2019/7/3 9:44
 * Description 主题接口
 */
public interface ISubject {

    //订阅
    void registerObserver(IObserver iObserver);
    //取消订阅
    void removeObserver(IObserver iObserver);
    //更新
    void notifyObserver();
}
