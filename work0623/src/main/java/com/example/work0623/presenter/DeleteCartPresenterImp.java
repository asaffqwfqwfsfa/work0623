package com.example.work0623.presenter;

import com.example.work0623.bean.DeleteCartBean;
import com.example.work0623.model.DeleteCartModel;
import com.example.work0623.view.DeleteCartView;

/**
 * Created by john on 2018/6/23.
 */

public class DeleteCartPresenterImp implements DeleteCartPresenter{
    private DeleteCartView view;
    private DeleteCartModel model;

    public DeleteCartPresenterImp(DeleteCartView view) {
        this.view = view;
        model=new DeleteCartModel();
    }
public void getDelete(String uid,String pid){
        model.getDelete(uid,pid,this);
}
    @Override
    public void success(DeleteCartBean bean) {
view.DeleteSuccess(bean);
    }
}
