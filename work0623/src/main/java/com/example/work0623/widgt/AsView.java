package com.example.work0623.widgt;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.work0623.R;

/**
 * Created by john on 2018/5/2.
 */

public class AsView extends LinearLayout{

    private TextView sub;
    private TextView num;
    private TextView add;

    public AsView(Context context) {
        this(context, null);
    }

    public AsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.add_subtrace, this);
        sub = findViewById(R.id.tv_subtract);
        num = findViewById(R.id.tv_num);
        add = findViewById(R.id.tv_add);
    }

    public void setNum(String str) {
        num.setText(str);
    }

    public String getNum() {
        return num.getText().toString();
    }

    public void setAddOnclickListener(OnClickListener onclickListener) {
        add.setOnClickListener(onclickListener);
    }

    public void setSubOnclickListener(OnClickListener onclickListener) {
        sub.setOnClickListener(onclickListener);
    }
}
