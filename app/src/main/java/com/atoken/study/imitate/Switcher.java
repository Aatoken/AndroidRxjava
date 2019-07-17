package com.atoken.study.imitate;


/**
 * Author Aatoken
 * Date 2019/7/16 10:51
 * Description 用于线程切换的抽象类
 */
public abstract class Switcher {


    public static abstract class Worker implements Release {
        public abstract Release switches(Runnable runnable);
    }


    public abstract Worker createWorker();

    public Release switches(final Runnable runnable) {
        Worker tWorker = createWorker();
        tWorker.switches(new Runnable() {
            @Override
            public void run() {
                runnable.run();
            }
        });
        return tWorker;
    }

}
