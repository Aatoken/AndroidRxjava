package com.atoken.study.backpressure;

/**
 * Author Aatoken
 * Date 2019/7/3 12:58
 * Description
 */
public final class TelephonerCreate<T> extends Telephoner<T> {

    private TelephonerOnCall<T> mTelephonerOnCall;

    public TelephonerCreate(TelephonerOnCall<T> telephonerOnCall) {
        this.mTelephonerOnCall = telephonerOnCall;
    }

    /**
     * 核心代码
     * @param receiver
     */
    @Override
    protected void callActual(Receiver receiver) {

        DropEmitter<T> dropEmitter=new DropEmitter<>(receiver);
        receiver.onCall(dropEmitter);
        mTelephonerOnCall.calling(dropEmitter);

    }



}
