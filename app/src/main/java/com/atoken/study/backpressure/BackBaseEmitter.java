package com.atoken.study.backpressure;

/**
 * Author Aatoken
 * Date 2019/7/3 12:55
 * Description 发射数据
 */
public interface BackBaseEmitter<T> {

    /**
     * 接通电话 传递消息
     * @param t
     */
    void onReceive(T t);

    void onCompleted();

    void onError(Throwable t);


}
