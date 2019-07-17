package com.atoken.study.backpressure;

/**
 * Author Aatoken
 * Date 2019/7/3 12:53
 * Description
 */
public interface TelephonerOnCall<T> {

    /**
     * 打通电话
     * @param emitter
     */
    void calling(TelephonerEmitter<T> emitter);

}
