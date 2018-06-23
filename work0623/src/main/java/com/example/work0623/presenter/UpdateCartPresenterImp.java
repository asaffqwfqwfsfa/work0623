package com.example.work0623.presenter;

import com.example.work0623.model.UpdateCartModel;
import com.example.work0623.view.UpdateCartView;

/**
 * Created by john on 2018/6/23.
 */

public class UpdateCartPresenterImp implements UpdateCartPresenter{
private UpdateCartView view;
private UpdateCartModel model;

    public UpdateCartPresenterImp(UpdateCartView view) {
        this.view = view;
        model=new UpdateCartModel();
    }
public void getUpdate(String uid,String sellerid,String pid,String selected,String num){
        model.getUpdate(uid,sellerid,pid,selected,num,this);
}
    @Override
    public void updatesuccess() {
        view.Updatesuccess();
    }
}
