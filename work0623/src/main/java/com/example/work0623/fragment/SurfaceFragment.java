package com.example.work0623.fragment;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.example.work0623.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by john on 2018/6/23.
 */

public class SurfaceFragment extends Fragment {
    @BindView(R.id.surface1)
    SurfaceView surface1;
    @BindView(R.id.rl)
    RelativeLayout rl;
    @BindView(R.id.ib_pause)
    ImageButton ibPause;
    @BindView(R.id.ib_start)
    ImageButton ibStart;
    Unbinder unbinder;
    private int postion ;
    private MediaPlayer mediaPlayer=null;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.surface_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        mediaPlayer = MediaPlayer.create(getActivity(), R.raw.oppo2);
        ibStart.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                try {
                    mediaPlayer.setDisplay(surface1.getHolder());
                    mediaPlayer.start();
                    ibStart.setEnabled(false);
                    ibPause.setEnabled(true);



                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });
        ibPause.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                } else {
                    mediaPlayer.start();
                }
                ibStart.setEnabled(true);
                ibPause.setEnabled(false);



            }
        });




        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer arg0) {
                surface1.setBackgroundResource(R.drawable.ic_launcher_background);


            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();


        }
        mediaPlayer.release();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
