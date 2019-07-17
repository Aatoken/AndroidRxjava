package com.atoken.study.observable;

import android.util.Log;

/**
 * Author Aatoken
 * Date 2019/7/3 9:50
 * Description 读者2
 */
public class SecondObserver implements IObserver {
    public static String message;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void update(String s) {
        message = "读者2收到的信息:" + s;
    }
}
