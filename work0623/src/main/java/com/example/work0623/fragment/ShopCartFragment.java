package com.example.work0623.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.work0623.R;
import com.example.work0623.adapter.ShopAdapter;
import com.example.work0623.bean.GetCartBean;
import com.example.work0623.bean.MerChantBean;
import com.example.work0623.presenter.GetCartPresenterImp;
import com.example.work0623.utils.DialogUtils;
import com.example.work0623.view.GetCartView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by john on 2018/6/23.
 */

public class ShopCartFragment extends Fragment implements GetCartView {

    @BindView(R.id.elv)
    ExpandableListView elv;
    @BindView(R.id.cbAll)
    CheckBox cbAll;
    @BindView(R.id.tvMoney)
    TextView tvMoney;
    @BindView(R.id.tvTotal)
    TextView tvTotal;
    Unbinder unbinder;
    private GetCartPresenterImp presenterImp;
    private int uid = 71;
    private ProgressDialog progressDialog;
    private ShopAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shopcart_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        presenterImp = new GetCartPresenterImp(this);
        presenterImp.getCart(uid + "");
        progressDialog = DialogUtils.getProgressDialog(getActivity());
        presenterImp = new GetCartPresenterImp(this);
        cbAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adapter!=null){
                    progressDialog.show();
                    adapter.changeAllState(cbAll.isChecked());
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        presenterImp.detach();
    }


    @Override
    public void success(final List<MerChantBean> groupList, final List<List<GetCartBean.DataBean.ListBean>> childList) {
getActivity().runOnUiThread(new Runnable() {



    @Override
    public void run() {
        cbAll.setChecked(isAllMerChantCheckAll(groupList));
        adapter = new ShopAdapter(getActivity(),groupList,childList,presenterImp,progressDialog);
        elv.setAdapter(adapter);
        String[] strings = adapter.computeMoneyAndNum();
        tvMoney.setText("总价:"+strings[0]+"￥");
        tvTotal.setText("去结算+"+strings[1]+"个");
        for (int i = 0; i < groupList.size(); i++) {
            elv.expandGroup(i);
        }
        progressDialog.dismiss();
    }
});
    }

    @Override
    public void faile(String msg) {

    }
    private boolean isAllMerChantCheckAll(List<MerChantBean> groupList) {
        for (int i = 0; i < groupList.size(); i++) {
            MerChantBean merChantBean = groupList.get(i);
            if (!merChantBean.isMerChantSelected()) {
                return false;
            }
        }
        return true;
    }
}
