package com.atoken.study.imitate;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Author Aatoken
 * Date 2019/6/28 18:51
 * Description 创建一个发射数据与挂断电话的类
 */
public final class CreateEmitter<T>   extends AtomicReference<Release>
        implements CallerEmitter<T>, Release{


    /**
     * 接电话的人
     */
    private Callee<T> mCallee;

    public CreateEmitter(Callee<T> callee) {
        this.mCallee = callee;
    }


    @Override
    public boolean isReleased() {
        return ReleaseHelper.isReleased(get());
    }

    @Override
    public void release() {
        ReleaseHelper.release(this);
    }


    @Override
    public void onReceive(T t) {
        if (!isReleased()) {
            mCallee.onReceive(t);
        }
    }

    @Override
    public void onCompleted() {
        if (!isReleased()) {
            mCallee.onCompleted();
        }
    }

    @Override
    public void onError(Throwable t) {
        if (!isReleased()) {
            mCallee.onError(t);
        }
    }
}
