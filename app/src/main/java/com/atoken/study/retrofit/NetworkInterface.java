package com.atoken.study.retrofit;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Author Aatoken
 * Date 2019/7/16 17:26
 * Description 接口
 */
public interface NetworkInterface {

    @GET("user_profile.php")
    Observable<String> json();

}
