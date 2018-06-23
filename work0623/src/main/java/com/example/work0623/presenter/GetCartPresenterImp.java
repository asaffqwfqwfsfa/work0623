package com.example.work0623.presenter;



import com.example.work0623.bean.GetCartBean;
import com.example.work0623.bean.MerChantBean;
import com.example.work0623.model.GetCartModel;
import com.example.work0623.view.GetCartView;

import java.util.List;

/**
 * Created by john on 2018/6/21.
 */

public class GetCartPresenterImp implements GetCartPresenter{
    private GetCartView view;
    private GetCartModel model;

    public GetCartPresenterImp(GetCartView view) {
        this.view = view;
        model=new GetCartModel();
    }
    public void detach(){
        if (view!=null){
            view=null;
        }
    }
    public void getCart(String uid){
        model.getCart(uid,this);
    }

    @Override
    public void success(List<MerChantBean> groupList, List<List<GetCartBean.DataBean.ListBean>> childList) {
        view.success(groupList,childList);
    }

    @Override
    public void faile(String msg) {

    }
}
