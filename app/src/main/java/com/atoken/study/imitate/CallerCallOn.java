package com.atoken.study.imitate;

/**
 * Author Aatoken
 * Date 2019/7/16 10:57
 * Description 用于callOn
 */
public class CallerCallOn<T> extends CallerWithUpstream<T, T> {

    private Switcher mSwitcher;

    public CallerCallOn(Caller<T> source, Switcher switcher) {
        super(source);
        mSwitcher = switcher;
    }

    @Override
    protected void callActual(Callee<T> callee) {

        final CallOnCallee<T> tCallerOnCall = new CallOnCallee<>(callee);
        callee.onCall(tCallerOnCall);
        mSwitcher.switches(new Runnable() {
            @Override
            public void run() {
                source.call(tCallerOnCall);
            }
        });
    }


    private static final class CallOnCallee<T> implements Callee<T>, Release {

        private final Callee<T> mCallee;

        public CallOnCallee(Callee<T> callee) {
            mCallee = callee;
        }


        @Override
        public void onCall(Release release) {

        }

        @Override
        public void onReceive(T t) {
            mCallee.onReceive(t);
        }

        @Override
        public void onCompleted() {
            mCallee.onCompleted();
        }

        @Override
        public void onError(Throwable t) {
            mCallee.onError(t);
        }

        @Override
        public boolean isReleased() {
            return false;
        }

        @Override
        public void release() {

        }
    }
}
