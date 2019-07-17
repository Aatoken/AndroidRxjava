package com.atoken.study.backpressure;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Author Aatoken
 * Date 2019/7/3 13:03
 * Description
 */
public final class DropEmitter<T> extends AtomicLong
        implements TelephonerEmitter<T>, Drop {

    private Receiver<T> mReceiver;

    private volatile boolean mIsDropped;

    public DropEmitter(Receiver<T> receiver) {
        mReceiver = receiver;
    }


    @Override
    public void onReceive(T t) {
        if (get() != 0) {
            mReceiver.onReceive(t);
            BackpressureHelper.produced(this, 1);
        }
    }

    @Override
    public void onCompleted() {
        mReceiver.onCompleted();
    }

    @Override
    public void onError(Throwable t) {
        mReceiver.onError(t);
    }

    @Override
    public void drop() {
        mIsDropped = true;
    }

    @Override
    public void request(long n) {
        BackpressureHelper.add(this, n);
    }


}
