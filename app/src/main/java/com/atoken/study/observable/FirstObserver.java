package com.atoken.study.observable;

/**
 * Author Aatoken
 * Date 2019/7/3 9:50
 * Description 读者1
 */
public class FirstObserver implements IObserver {

    public static String message;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void update(String s) {
        message="读者1收到的信息:"+s;
    }
}
