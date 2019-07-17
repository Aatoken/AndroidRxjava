package com.atoken.study.imitate;

/**
 * Author Aatoken
 * Date 2019/7/16 16:40
 * Description 用于整体变换
 */
public interface CallerConverter<T, R> {

    Caller<R> convert(Caller<T> caller);
}
