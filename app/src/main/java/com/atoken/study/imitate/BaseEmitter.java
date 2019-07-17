package com.atoken.study.imitate;

/**
 * Author Aatoken
 * Date 2019/6/28 19:00
 * Description 用于发射数据
 */
public interface BaseEmitter<T> {

    /**
     * 当接收到电话
     * @param t
     */
    void onReceive(T t);

    /**
     * 完成电话
     */
    void onCompleted();

    /**
     * 错误
     * @param t
     */
    void onError(Throwable t);
}
