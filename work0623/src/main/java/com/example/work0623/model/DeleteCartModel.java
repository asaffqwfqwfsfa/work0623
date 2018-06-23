package com.example.work0623.model;

import com.example.john.jd_demo.utils.RetrofitUtils;
import com.example.work0623.bean.DeleteCartBean;
import com.example.work0623.presenter.DeleteCartPresenterImp;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by john on 2018/6/23.
 */

public class DeleteCartModel {
    public void getDelete(String uid,String pid,final DeleteCartPresenterImp presenterImp){
        RetrofitUtils.getInstance().getDelete(uid,pid).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DeleteCartBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DeleteCartBean bean) {
                        presenterImp.success(bean);
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
