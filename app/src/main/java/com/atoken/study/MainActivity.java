package com.atoken.study;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.atoken.study.backpressure.Drop;
import com.atoken.study.backpressure.Receiver;
import com.atoken.study.backpressure.Telephoner;
import com.atoken.study.backpressure.TelephonerEmitter;
import com.atoken.study.backpressure.TelephonerOnCall;
import com.atoken.study.imitate.Callee;
import com.atoken.study.imitate.Caller;
import com.atoken.study.imitate.CallerConverter;
import com.atoken.study.imitate.CallerEmitter;
import com.atoken.study.imitate.CallerOnCall;
import com.atoken.study.imitate.LooperSwitcher;
import com.atoken.study.imitate.NewThreadSwitcher;
import com.atoken.study.imitate.Release;
import com.atoken.study.observable.FirstObserver;
import com.atoken.study.observable.NewsPaperOffice;
import com.atoken.study.observable.SecondObserver;
import com.atoken.study.retrofit.NetworkService;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    private Button btn_update;
    private Button btn_first;
    private TextView tv_first;
    private Button btn_second;
    private TextView tv_second;

    private NewsPaperOffice mNewsPaperOffice;
    private FirstObserver mFirstObserver;
    private SecondObserver mSecondObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initview();

        //init();
        //test();
        //call();

        //backcall();

        //map();

        //switchOn();

        //unify();


    }



    private void unify() {
        Caller
                .create(new CallerOnCall<String>() {
                    @Override
                    public void calling(CallerEmitter<String> callerEmitter) {

                        callerEmitter.onReceive("11");
                        Log.d(TAG, "caller current thread:" + Thread.currentThread());
                        callerEmitter.onCompleted();
                    }
                })
                .unify(new CallerConverter<String, String>() {
                    @Override
                    public Caller<String> convert(Caller<String> caller) {
                        return caller.callOn(new NewThreadSwitcher())
                                .callbackOn(new LooperSwitcher(getMainLooper()));
                    }
                })
                .call(new Callee<String>() {
                    @Override
                    public void onCall(Release release) {
                        Log.d(TAG, "onCall");
                    }

                    @Override
                    public void onReceive(String s) {
                        Log.d(TAG, "current value:" + s);
                        Log.d(TAG, "onReceive current thread:" + Thread.currentThread());
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable t) {

                    }
                });

    }

    private void switchOn() {
        Caller
                .create(new CallerOnCall<String>() {
                    @Override
                    public void calling(CallerEmitter<String> callerEmitter) {

                        callerEmitter.onReceive("11");
                        Log.d(TAG, "caller current thread:" + Thread.currentThread());
                        callerEmitter.onCompleted();

                    }
                })
                .callOn(new LooperSwitcher(getMainLooper()))
                .callbackOn(new NewThreadSwitcher())
                .call(new Callee<String>() {
                    @Override
                    public void onCall(Release release) {
                        Log.d(TAG, "onCall");
                    }

                    @Override
                    public void onReceive(String s) {
                        Log.d(TAG, "current value:" + s);
                        Log.d(TAG, "callee current thread:" + Thread.currentThread());
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable t) {

                    }
                });
    }


    private void map() {
        Caller.create(new CallerOnCall<String>() {
            @Override
            public void calling(CallerEmitter<String> callerEmitter) {
                callerEmitter.onReceive("1");
                callerEmitter.onCompleted();
            }
        }).map(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) throws Exception {
                return Integer.parseInt(s);
            }
        }).call(new Callee<Integer>() {
            @Override
            public void onCall(Release release) {
                Log.d(TAG, "onCall");
            }

            @Override
            public void onReceive(Integer integer) {
                Log.d(TAG, "onReceive---" + integer);
            }

            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable t) {
                Log.d(TAG, "onError");
            }
        });
    }

    private void initview() {
        btn_update = (Button) findViewById(R.id.btn_update);
        btn_first = (Button) findViewById(R.id.btn_first);
        tv_first = (TextView) findViewById(R.id.tv_first);
        btn_second = (Button) findViewById(R.id.btn_second);
        tv_second = (TextView) findViewById(R.id.tv_second);

        btn_update.setOnClickListener(this);
        btn_first.setOnClickListener(this);
        btn_second.setOnClickListener(this);
    }


    private void init() {

        //被观察者，create 一个被观察者对象，subscribe 注册一个观察者
        //
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) {
                Log.d(TAG, "currentThread name: " + Thread.currentThread().getName());

                e.onNext("test");
                e.onComplete();

            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread()).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "==onSubscribe");
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "==onNext " + s);
            }


            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "==onError");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "==onComplete");
            }
        });


    }


    private void test() {
        Flowable.create(new FlowableOnSubscribe<Integer>() {
                            @Override
                            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                                Log.d(TAG, "发送事件 1");
                                emitter.onNext(1);
                                Log.d(TAG, "发送事件 2");
                                emitter.onNext(2);
                                Log.d(TAG, "发送事件 3");
                                emitter.onNext(3);
                                Log.d(TAG, "发送完成");
                                emitter.onComplete();
                            }
                        },
                BackpressureStrategy.ERROR

        ).subscribe(new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
                Log.d(TAG, "onSubscribe");
                s.request(3);
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "接收到了事件" + integer);
            }

            @Override
            public void onError(Throwable t) {
                Log.w(TAG, "onError: ", t);
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete");
            }


        });

    }


    private void call() {


        //创建一个打电话的人
        Caller.create(new CallerOnCall<String>() {
            //正在打电话
            @Override
            public void calling(CallerEmitter<String> e) {

                Log.d(TAG, "开始打电话");
                // e.onReceive("打出第一个电话");
                //e.onCompleted();

            }
            //接电话
        }).call(new Callee<String>() {
            //接通电话
            @Override
            public void onCall(Release release) {
                Log.d(TAG, "打通电话");

            }

            //接受打电话的信息
            @Override
            public void onReceive(String s) {
                Log.d(TAG, "接收到第一个电话：" + s);
            }

            @Override
            public void onCompleted() {
                Log.d(TAG, "打完电话！");
            }

            @Override
            public void onError(Throwable t) {

            }
        });
    }


    private void backcall() {
        Telephoner.create(new TelephonerOnCall<String>() {
            @Override
            public void calling(TelephonerEmitter<String> emitter) {
                Log.d(TAG, "正在通话");
                emitter.onReceive("1---");
                emitter.onReceive("2---");
                emitter.onReceive("3---");
                emitter.onCompleted();
            }
        }).call(new Receiver<String>() {
            @Override
            public void onCall(Drop d) {
                Log.d(TAG, "开通电话");
                d.request(2);

            }

            @Override
            public void onReceive(String s) {
                Log.d(TAG, "接受电话:" + s);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted");
            }
        });
    }


    @Override
    public void onClick(View v) {
        if (mNewsPaperOffice == null) {
            mNewsPaperOffice = new NewsPaperOffice();
        }
        switch (v.getId()) {
            case R.id.btn_update:
                mNewsPaperOffice.notifyObserver();
                if (mFirstObserver != null) {
                    tv_first.setText(mFirstObserver.getMessage());
                }
                if (mSecondObserver != null) {
                    tv_second.setText(mSecondObserver.getMessage());
                }
                break;
            case R.id.btn_first:
                if (mFirstObserver == null) {
                    mFirstObserver = new FirstObserver();
                }
                if (btn_first.getText().equals("订阅报纸")) {
                    btn_first.setText("取消订阅");
                    mNewsPaperOffice.registerObserver(mFirstObserver);
                } else {
                    btn_first.setText("订阅报纸");
                    mNewsPaperOffice.removeObserver(mFirstObserver);
                }

                break;
            case R.id.btn_second:

                if (mSecondObserver == null) {
                    mSecondObserver = new SecondObserver();
                }
                if (btn_second.getText().equals("订阅报纸")) {
                    btn_second.setText("取消订阅");
                    mNewsPaperOffice.registerObserver(mSecondObserver);
                } else {
                    btn_second.setText("订阅报纸");
                    mNewsPaperOffice.removeObserver(mSecondObserver);
                }

                break;
            default:
                break;
        }
    }
}
