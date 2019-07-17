package com.atoken.study.imitate;


/**
 * Author Aatoken
 * Date 2019/7/16 11:48
 * Description 创建新的线程
 */
public class NewThreadSwitcher extends Switcher {
    @Override
    public Worker createWorker() {
        return new NewThreadWorker();
    }
}
