package com.example.work0623;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.work0623.fragment.ShopCartFragment;
import com.example.work0623.fragment.SurfaceFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.rv_Home)
    RadioButton rvHome;
    @BindView(R.id.rb_shopCart)
    RadioButton rbShopCart;
    @BindView(R.id.rg)
    RadioGroup rg;
    @BindView(R.id.flContent)
    FrameLayout flContent;
    private FragmentManager manager;
    private SurfaceFragment surfaceFragment;
    private ShopCartFragment shopCartFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setListener();
        initData();
    }

    private void initData() {
        surfaceFragment = new SurfaceFragment();
        shopCartFragment = new ShopCartFragment();
        manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.flContent, surfaceFragment).commit();

    }

    private void setListener() {
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rv_Home:

                       manager .beginTransaction().replace(R.id.flContent,surfaceFragment ).commit();
                        break;
                    case R.id.rb_shopCart:
                        manager.beginTransaction().replace(R.id.flContent, shopCartFragment).commit();
                        break;



                }
            }
        });
    }


}



