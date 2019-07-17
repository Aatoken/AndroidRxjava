package com.atoken.study.backpressure;

/**
 * Author Aatoken
 * Date 2019/7/3 12:46
 * Description 挂断电话
 */
public interface Drop<T> {

    /**
     * 挂断电话
     */
    void drop();

    /**
     * 请求
     * @param n
     */
    void request(long n);
}
