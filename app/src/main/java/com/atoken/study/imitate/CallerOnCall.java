package com.atoken.study.imitate;


/**
 * Author Aatoken
 * Date 2019/6/28 17:20
 * Description  当打电话的时候
 */
public interface CallerOnCall<T> {

    //正在打电话
    void calling(CallerEmitter<T> callerEmitter);


}
