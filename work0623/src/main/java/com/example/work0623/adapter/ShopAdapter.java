package com.example.work0623.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.work0623.R;
import com.example.work0623.bean.DeleteCartBean;
import com.example.work0623.bean.GetCartBean;
import com.example.work0623.bean.MerChantBean;
import com.example.work0623.bean.UpdateCartBean;
import com.example.work0623.presenter.DeleteCartPresenterImp;
import com.example.work0623.presenter.GetCartPresenterImp;
import com.example.work0623.presenter.UpdateCartPresenterImp;
import com.example.work0623.view.DeleteCartView;
import com.example.work0623.view.UpdateCartView;
import com.example.work0623.widgt.AsView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by john on 2018/5/2.
 */


public class ShopAdapter extends BaseExpandableListAdapter implements UpdateCartView, DeleteCartView {
    private Context context;
    private List<MerChantBean> groupList;
    private List<List<GetCartBean.DataBean.ListBean>> childList;
    private LayoutInflater inflater;
    private final UpdateCartPresenterImp updatePresenterImp;
    private GetCartPresenterImp getCatPresenterImp;
    private final String uid=71+"";
    private ProgressDialog progressDialog;
    private int productIndex;
    private int groupPosition;
    private boolean checked;
    private static final int GETCARTS = 0;
    private static final int UPDATE_PRODUCT = 1;
    private static final int UPDATE_SELLER = 2;

    private static int state = GETCARTS;
    private boolean allSelected;
    private final DeleteCartPresenterImp deltePresenterImp;

    public ShopAdapter(Context context, List<MerChantBean> groupList, List<List<GetCartBean.DataBean.ListBean>>
            childList, GetCartPresenterImp getCartsPresenterImp, ProgressDialog progressDialog) {
        this.context = context;
        this.groupList = groupList;
        this.childList = childList;
        inflater = LayoutInflater.from(context);
        this.getCatPresenterImp = getCartsPresenterImp;

        updatePresenterImp = new UpdateCartPresenterImp(this);
        deltePresenterImp = new DeleteCartPresenterImp(this);


        this.progressDialog = progressDialog;


    }

    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childList.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childList.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        final GroupViewHolder groupViewHolder;
        if (convertView == null) {
            groupViewHolder = new GroupViewHolder();
            convertView = inflater.inflate(R.layout.rv_item, null);
            groupViewHolder.cbSeller = convertView.findViewById(R.id.cbSeller);
            groupViewHolder.tvSeller = convertView.findViewById(R.id.tvSeller);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }


        MerChantBean merChantBean = groupList.get(groupPosition);
        groupViewHolder.tvSeller.setText(merChantBean.getMerChantName());
        groupViewHolder.cbSeller.setChecked(merChantBean.isMerChantSelected());
        groupViewHolder.cbSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                state = UPDATE_PRODUCT;
                progressDialog.show();
                productIndex = 0;
                ShopAdapter.this.groupPosition = groupPosition;
                checked = groupViewHolder.cbSeller.isChecked();
                updateProductInSeller();
            }

        });
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView,
                             ViewGroup
                                     parent) {

        final ChildViewHolder childViewHolder;
        if (convertView == null) {
            childViewHolder = new ChildViewHolder();
            convertView = inflater.inflate(R.layout.rv_shopcart, null);
            childViewHolder.cbProduct = convertView.findViewById(R.id.cbProduct);
            childViewHolder.iv = convertView.findViewById(R.id.iv);
            childViewHolder.tvTitle = convertView.findViewById(R.id.tvTitle);
            childViewHolder.tvPrice = convertView.findViewById(R.id.tvPrice);
            childViewHolder.tvDel = convertView.findViewById(R.id.tvDel);
            childViewHolder.addSubView = convertView.findViewById(R.id.asCard);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }

        final GetCartBean.DataBean.ListBean listBean = childList.get(groupPosition).get(childPosition);
        childViewHolder.cbProduct.setChecked(listBean.getSelected() == 1 ? true : false);
        childViewHolder.tvTitle.setText(listBean.getTitle());
        childViewHolder.tvPrice.setText(listBean.getPrice() + "");
        String split = listBean.getImages().split("\\|")[0];
        childViewHolder.iv.setImageURI(split);
        childViewHolder.addSubView.setNum(listBean.getNum() + "");
        childViewHolder.cbProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                state = GETCARTS;
                progressDialog.show();
                ShopAdapter.this.groupPosition = groupPosition;
                String sellerid = groupList.get(groupPosition).getMerChantId();
                String pid = listBean.getPid() + "";
                boolean childChecked = childViewHolder.cbProduct.isChecked();
                updatePresenterImp.getUpdate(uid, sellerid, pid, "1", childChecked ? "1" : "0");
            }
        });
        childViewHolder.addSubView.setAddOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                state = GETCARTS;
                String sellerid = groupList.get(groupPosition).getMerChantId();
                int pid = listBean.getPid();
                int num = listBean.getNum();
                num += 1;
                String isChecked = childViewHolder.cbProduct.isChecked() ? "1" : "0";
                updatePresenterImp.getUpdate(uid, sellerid, pid + "", num + "", isChecked);
            }
        });

        childViewHolder.addSubView.setSubOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                state = GETCARTS;
                int num = listBean.getNum();
                if (num <= 1) {
                    progressDialog.dismiss();
                    Toast.makeText(context, "数量不能小于1", Toast.LENGTH_SHORT).show();
                    return;
                }
                num -= 1;
                String sellerid = groupList.get(groupPosition).getMerChantId();
                int pid = listBean.getPid();
                String isChecked = childViewHolder.cbProduct.isChecked() ? "1" : "0";
                updatePresenterImp.getUpdate(uid, sellerid, pid + "", num + "", isChecked);
            }
        });

        childViewHolder.tvDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                state = GETCARTS;
                int pid = listBean.getPid();
                deltePresenterImp.getDelete(uid, pid + "");

            }
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public void Updatesuccess() {
        switch (state) {
            case GETCARTS:
                productIndex = 0;
                groupPosition = 0;
                getCatPresenterImp.getCart(uid);
                break;
            case UPDATE_PRODUCT:
                productIndex++;
                if (productIndex < childList.get(groupPosition).size()) {
                    updateProductInSeller();
                } else {
                    state = GETCARTS;
                    Updatesuccess();
                }
                break;
            case UPDATE_SELLER:
                productIndex++;
                if (productIndex < childList.get(groupPosition).size()) {
                    updateProductInSeller(allSelected);
                } else {
                    productIndex = 0;
                    groupPosition++;
                    if (groupPosition < groupList.size()) {
                        updateProductInSeller(allSelected);
                    } else {
                        state = GETCARTS;
                        Updatesuccess();
                    }
                }
                break;
        }
    }

    @Override
    public void DeleteSuccess(DeleteCartBean bean) {
        getCatPresenterImp.getCart(uid);

    }


    class GroupViewHolder {
        CheckBox cbSeller;
        TextView tvSeller;
    }

    class ChildViewHolder {
        CheckBox cbProduct;
        SimpleDraweeView iv;
        TextView tvTitle;
        TextView tvPrice;
        TextView tvDel;
        AsView addSubView;
    }




    private void updateProductInSeller() {
        MerChantBean bean = groupList.get(groupPosition);
        String merChantId = bean.getMerChantId();
        GetCartBean.DataBean.ListBean listBean = childList.get(groupPosition).get(productIndex);
        int num = listBean.getNum();
        int pid = listBean.getPid();
        updatePresenterImp.getUpdate(uid, merChantId, pid + "", num + "", checked ? "1" : "0");
    }

    private void updateProductInSeller(boolean bool) {
        MerChantBean bean = groupList.get(groupPosition);
        String merChantId = bean.getMerChantId();
        GetCartBean.DataBean.ListBean listBean = childList.get(groupPosition).get(productIndex);
        int pid = listBean.getPid();
        int num = listBean.getNum();
        updatePresenterImp.getUpdate(uid, merChantId, pid + "", num + "", bool ? "1" : "0");
    }


    public String[] computeMoneyAndNum() {
        double sum = 0;
        int num = 0;
        for (int i = 0; i < groupList.size(); i++) {
            for (int j = 0; j < childList.get(i).size(); j++) {

                GetCartBean.DataBean.ListBean listBean = childList.get(i).get(j);
                if (listBean.getSelected() == 1) {
                    sum += listBean.getPrice() * listBean.getNum();
                    num += listBean.getNum();
                }
            }
        }
        return new String[]{sum + "", num + ""};
    }

    public void changeAllState(boolean bool) {
        this.allSelected = bool;
        state = UPDATE_SELLER;
        updateProductInSeller(bool);

    }


}