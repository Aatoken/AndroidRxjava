package com.atoken.study.backpressure;

/**
 * Author Aatoken
 * Date 2019/7/3 12:45
 * Description 接电话的人
 */
public interface Receiver<T> {

    /**
     * 接通电话
     * @param d
     */
    void onCall(Drop d);

    /**
     * 接受电话信息
     * @param t
     */
    void onReceive(T t);

    /**
     * 错误
     * @param t
     */
    void onError(Throwable t);

    /**
     * 完成通话
     */
    void onCompleted();
}
