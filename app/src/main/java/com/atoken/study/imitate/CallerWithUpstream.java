package com.atoken.study.imitate;

/**
 * Author Aatoken
 * Date 2019/7/10 13:41
 * Description Caller用于操作符的类
 */
public abstract class CallerWithUpstream<T,R> extends Caller<R>
        implements CallerSource<T> {

    protected final Caller<T> source;

    public CallerWithUpstream(Caller<T> source) {
        this.source = source;
    }

    @Override
    public Caller<T> source() {
        return source;
    }
}
