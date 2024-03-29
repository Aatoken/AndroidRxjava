package com.atoken.study.imitate;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Author Aatoken
 * Date 2019/7/16 11:02
 * Description
 */
public class CallerCallbackOn<T> extends CallerWithUpstream<T,T> {

    private final Switcher mSwitcher;

    public CallerCallbackOn(Caller<T> source,Switcher switcher) {
        super(source);
        this.mSwitcher=switcher;
    }

    @Override
    protected void callActual(Callee<T> callee) {
        source.call(new CallbackOnCallee<>(callee,mSwitcher));
    }


    private static final class CallbackOnCallee<T> implements Callee<T>,Runnable {

        private final Callee<T> mCallee;
        private final  Switcher.Worker mWorker;
        private final Queue<T> mQueue = new LinkedList<>();
        public CallbackOnCallee(Callee<T> callee, Switcher switcher) {
            mCallee=callee;
            mWorker=switcher.createWorker();
        }

        @Override
        public void onCall(Release release) {

        }

        @Override
        public void onReceive(T t) {
            mQueue.offer(t);
            switches();
        }

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable t) {

        }

        @Override
        public void run() {
            T t = mQueue.poll();
            mCallee.onReceive(t);
        }

        private void switches() {
            mWorker.switches(this);
        }
    }
}
