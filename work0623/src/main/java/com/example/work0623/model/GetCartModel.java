package com.example.work0623.model;


import com.example.john.jd_demo.utils.RetrofitUtils;
import com.example.work0623.bean.GetCartBean;
import com.example.work0623.bean.MerChantBean;
import com.example.work0623.presenter.GetCartPresenterImp;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by john on 2018/6/21.
 */

public class GetCartModel {
    public void getCart(String uid, final GetCartPresenterImp presenterImp){
        RetrofitUtils.getInstance().getCart(uid).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetCartBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GetCartBean bean) {

                        List<MerChantBean> groupList = new ArrayList<>();
                        List<List<GetCartBean.DataBean.ListBean>> childList = new ArrayList<>();


                        List<GetCartBean.DataBean> data = bean.getData();

                        for (int i = 0; i < data.size(); i++) {
                            GetCartBean.DataBean dataBean = data.get(i);
                            MerChantBean merChantBean = new MerChantBean();
                            merChantBean.setMerChantName(dataBean.getSellerName());
                            merChantBean.setMerChantId(dataBean.getSellerid());
                            merChantBean.setMerChantSelected(isMerChatCheckAll(dataBean));
                            groupList.add(merChantBean);

                            List<GetCartBean.DataBean.ListBean> list = dataBean.getList();
                            childList.add(list);
                        }

                        presenterImp.success(groupList,childList);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    private boolean isMerChatCheckAll(GetCartBean.DataBean dataBean) {
        List<GetCartBean.DataBean.ListBean> list = dataBean.getList();
        for (int i = 0; i < list.size(); i++) {
            GetCartBean.DataBean.ListBean listBean = list.get(i);
            if (0 == listBean.getSelected()) {
                return false;
            }
        }
        return true;

    }
}
