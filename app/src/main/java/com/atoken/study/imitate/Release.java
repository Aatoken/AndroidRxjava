package com.atoken.study.imitate;

/**
 * Author Aatoken
 * Date 2019/6/28 17:25
 * Description  挂断电话
 */
public interface Release {

    /**
     * 判断是否挂断
     * @return
     */
    boolean isReleased();

    /**
     * 挂断电话
     */
    void release();
}
