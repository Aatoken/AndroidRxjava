package com.atoken.study.imitate;


/**
 * Author Aatoken
 * Date 2019/6/28 17:16
 * Description 实际上打电话的人
 */
public final class CallerCreate<T> extends Caller {
    final CallerOnCall<T> mCallerOnCall;

    public CallerCreate(CallerOnCall<T> callerOnCall) {
        this.mCallerOnCall = callerOnCall;
    }


    @Override
    protected void callActual(Callee observer) {

        //观察者模式 观察者与被观察的联系
        //register，upregister，被观察者 调用 观察者的更新方法进行刷新数据


        //填充数据与如何挂断电话
        CreateEmitter<T> parent = new CreateEmitter<T>(observer);
        observer.onCall(parent);
        mCallerOnCall.calling(parent);

    }


}
