package com.atoken.study.imitate;

import android.os.Handler;
import android.os.Looper;

/**
 * Author Aatoken
 * Date 2019/7/16 12:01
 * Description 用于Android的Switcher
 */
public class LooperSwitcher extends Switcher {

    private Handler mHandler;

    public LooperSwitcher(Looper looper)
    {
        mHandler=new Handler(looper);
    }
    public Release switches(final Runnable runnable) {
        SwitcherRunnable switcherRunnable=new SwitcherRunnable(runnable,mHandler);
        mHandler.post(switcherRunnable);
        return switcherRunnable;
    }

    @Override
    public Worker createWorker() {
        return new HandlerWorker(mHandler);
    }

    public static class SwitcherRunnable implements Runnable,Release{
        private final Runnable mRunnable;
        private final Handler mHandler;
        private volatile boolean mIsReleased;

        public SwitcherRunnable(Runnable runnable, Handler handler) {
            this.mRunnable = runnable;
            this.mHandler = handler;

        }

        @Override
        public boolean isReleased() {
            return mIsReleased;
        }

        @Override
        public void release() {
            mIsReleased=true;
            mHandler.removeCallbacks(this);
        }

        @Override
        public void run() {
            mRunnable.run();
        }
    }

}
