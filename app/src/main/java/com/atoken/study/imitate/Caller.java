package com.atoken.study.imitate;


import com.atoken.study.R;

import io.reactivex.functions.Function;

/**
 * Author Aatoken
 * Date 2019/6/28 17:08
 * Description  打电话的人
 */
public abstract class Caller <T>{


    /**
     * 创建一个打电话的人
     * @param callerOnCall
     * @param <T>
     * @return
     */
    public static <T> Caller<T> create (CallerOnCall<T> callerOnCall)
    {
        return new  CallerCreate<>(callerOnCall);
    }

    /**
     * 打电话
     * @param callee
     */
    public void call(Callee<T> callee)
    {
        callActual(callee);
    }

    /**
     * 创建打电话的具体实现(子类中实现)
     * @param callee
     */
    protected abstract void callActual(Callee<T> callee);



    public <R> Caller<R> map(Function<T,R> function)
    {
        return new CallerMap<>(this,function);
    }

    public Caller<T> callOn(Switcher switcher)
    {
        return  new CallerCallOn<>(this,switcher);
    }

    public Caller<T> callbackOn(Switcher switcher)
    {
        return new CallerCallbackOn(this,switcher);
    }

    public <R> Caller<R> unify(CallerConverter<T,R> callerConverter)
    {
        return callerConverter.convert(this);
    }

}
