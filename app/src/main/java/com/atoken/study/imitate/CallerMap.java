package com.atoken.study.imitate;

import io.reactivex.functions.Function;

/**
 * Author Aatoken
 * Date 2019/7/10 13:48
 * Description map操作符
 */
public class CallerMap<T, R> extends CallerWithUpstream<T, R> {

    private Function<T, R> mFunction;

    public CallerMap(Caller<T> source, Function<T, R> function) {
        super(source);
        mFunction = function;
    }

    @Override
    protected void callActual(Callee<R> callee) {
        source.call(new MapCallee<>(callee, mFunction));
    }

    static class MapCallee<T, R> implements Callee<T> {

        private final Callee<R> mCallee;
        private final Function<T, R> mFunction;

        public MapCallee(Callee<R> callee, Function<T, R> function) {
            this.mCallee = callee;
            this.mFunction = function;
        }

        @Override
        public void onCall(Release release) {
            mCallee.onCall(release);
        }

        @Override
        public void onReceive(T t) {
            try {
                R tR=mFunction.apply(t);
                mCallee.onReceive(tR);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onCompleted() {
            mCallee.onCompleted();
        }

        @Override
        public void onError(Throwable t) {
            mCallee.onError(t);
        }
    }

}
