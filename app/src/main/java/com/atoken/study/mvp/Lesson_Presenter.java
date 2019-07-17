package com.atoken.study.mvp;

import android.util.Log;

import com.atoken.study.retrofit.NetworkService;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Author Aatoken
 * Date 2019/7/16 18:51
 * Description
 */
public class Lesson_Presenter extends Contract.Presenter {

     private static final String TAG="currentMvp";

    protected Lesson_Presenter(Contract.View mView) {
        super(mView);
    }

    @Override
    void loadDate() {
        NetworkService.getInstance().json()
                .compose(new NetworkService.NetworkTransfoemer<String>())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG,"onSubscribe");
                    }

                    @Override
                    public void onNext(String s) {
                        mView.update(s + "加载成功!");
                        Log.d(TAG,"onNext"+s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG,"onError"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }
}
