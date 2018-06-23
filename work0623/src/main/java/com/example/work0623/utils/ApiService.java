package com.example.work0623.utils;

import com.example.work0623.bean.DeleteCartBean;
import com.example.work0623.bean.GetCartBean;
import com.example.work0623.bean.UpdateCartBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by john on 2018/6/23.
 */

public interface ApiService {

    String URL = "http://120.27.23.105/";
    @POST("product/getCarts")
    @FormUrlEncoded
  Observable<GetCartBean> getCart(@Field("uid") String uid);
    @POST("product/updateCarts")
    @FormUrlEncoded
    Observable<UpdateCartBean>  getUpdate(@Field("uid") String uid,@Field("sellerid") String sellerid,
     @Field("pid") String pid,@Field("selected") String selected,@Field("num") String num);
    @POST("product/deleteCart")
    @FormUrlEncoded
    Observable<DeleteCartBean> getDelete(@Field("uid") String uid,@Field("pid") String pid);

}
