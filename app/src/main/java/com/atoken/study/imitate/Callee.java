package com.atoken.study.imitate;




/**
 * Author Aatoken
 * Date 2019/6/28 17:18
 * Description  接电话的人
 */
public interface Callee<T> {


    /**
     * 接通电话
     * @param release
     */
    void onCall(Release release);


    /**
     * 接收信息
     * @param t
     */
    void onReceive(T t);

    /**
     *完成
     */
    void onCompleted();

    /**
     * 错误
     * @param t
     */
    void onError(Throwable t);
}
