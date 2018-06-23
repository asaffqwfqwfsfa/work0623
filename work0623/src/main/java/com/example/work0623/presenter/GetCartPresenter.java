package com.example.work0623.presenter;

import com.example.work0623.bean.GetCartBean;
import com.example.work0623.bean.MerChantBean;

import java.util.List;

/**
 * Created by john on 2018/6/23.
 */

public interface GetCartPresenter {
    void success(List<MerChantBean> groupList, List<List<GetCartBean.DataBean.ListBean>> childList);
    void faile(String msg);
}
