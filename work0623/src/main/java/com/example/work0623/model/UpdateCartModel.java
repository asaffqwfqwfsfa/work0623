package com.example.work0623.model;

import com.example.john.jd_demo.utils.RetrofitUtils;
import com.example.work0623.bean.UpdateCartBean;
import com.example.work0623.presenter.UpdateCartPresenterImp;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by john on 2018/6/23.
 */

public class UpdateCartModel {
    public void getUpdate(String uid,String sellerid,String pid,String sellected,String num,final UpdateCartPresenterImp presenterImp){
        RetrofitUtils.getInstance().getUpdate(uid,sellerid,pid,sellected,num).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UpdateCartBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UpdateCartBean updateCartBean) {
                    presenterImp.updatesuccess();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
