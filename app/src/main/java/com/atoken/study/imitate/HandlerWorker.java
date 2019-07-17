package com.atoken.study.imitate;

import android.os.Handler;
import android.os.Message;



/**
 * Author Aatoken
 * Date 2019/7/16 12:04
 * Description 用于Android的Worker
 */
public class HandlerWorker extends Switcher.Worker {
    private final Handler mHandler;
    private volatile boolean mIsReleased;


    public HandlerWorker(Handler handler) {

        this.mHandler = handler;
    }

    @Override
    public Release switches(Runnable runnable) {

        LooperSwitcher.SwitcherRunnable switcherRunnable=new
                LooperSwitcher.SwitcherRunnable(runnable, mHandler);
        Message message=Message.obtain(mHandler,switcherRunnable);
        message.obj=this;
        mHandler.sendMessage(message);
        return switcherRunnable;
    }

    @Override
    public boolean isReleased() {
        return mIsReleased;
    }

    @Override
    public void release() {
        mIsReleased = true;
        mHandler.removeCallbacksAndMessages(this);
    }





}
