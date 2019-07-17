package com.atoken.study.backpressure;


/**
 * Author Aatoken
 * Date 2019/7/3 12:43
 * Description 打电话的人
 */
public abstract class Telephoner<T> {


    /**
     * 创建真正打电话的人
     * @param telephonerOnCall
     * @param <T>
     * @return
     */
    public static <T> Telephoner<T> create(TelephonerOnCall<T> telephonerOnCall) {
        return new TelephonerCreate<>(telephonerOnCall);
    }


    /**
     * 拨通电话
     * @param receiver
     */
    public void call(Receiver<? super T> receiver) {
        callActual(receiver);
    }

    //子类中实现
    protected abstract void callActual(Receiver<? super T> receiver);


}
