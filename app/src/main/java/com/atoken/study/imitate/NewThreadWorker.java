package com.atoken.study.imitate;

import android.support.annotation.NonNull;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;




/**
 * Author Aatoken
 * Date 2019/7/16 11:49
 * Description
 */
public class NewThreadWorker extends Switcher.Worker {
    private volatile boolean mIsReleased;

    private final ExecutorService mExecutor = Executors.newScheduledThreadPool(1, new ThreadFactory() {
        @Override
        public Thread newThread(@NonNull Runnable runnable) {
            return new Thread(runnable, "Async");
        }
    });

    @Override
    public boolean isReleased() {
        return mIsReleased;
    }

    @Override
    public void release() {
        mIsReleased = true;
    }

    @Override
    public Release switches(Runnable runnable) {
        SwitcherAction tSwitcherAction = new SwitcherAction(runnable);
        mExecutor.submit((Callable<Object>) tSwitcherAction);
        return tSwitcherAction;
    }

    private static class SwitcherAction implements Runnable, Callable<Object>, Release {

        private final Runnable mRunnable;

        private volatile boolean mIsReleased;

        public SwitcherAction(Runnable runnable) {
            mRunnable = runnable;
        }

        @Override
        public boolean isReleased() {
            return mIsReleased;
        }

        @Override
        public void release() {
            mIsReleased = true;
        }

        @Override
        public void run() {
            mRunnable.run();
        }

        @Override
        public Object call() throws Exception {
            run();
            return null;
        }
    }
}
