package com.foo.music;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private List<MediaPlayer> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_do).setOnClickListener(this);
        findViewById(R.id.btn_re).setOnClickListener(this);
        findViewById(R.id.btn_mi).setOnClickListener(this);
        findViewById(R.id.btn_fa).setOnClickListener(this);
        findViewById(R.id.btn_sol).setOnClickListener(this);
        findViewById(R.id.btn_la).setOnClickListener(this);
        findViewById(R.id.btn_si).setOnClickListener(this);

        findViewById(R.id.btn_1).setOnClickListener(this);
        findViewById(R.id.btn_2).setOnClickListener(this);
        findViewById(R.id.btn_3).setOnClickListener(this);
        findViewById(R.id.btn_4).setOnClickListener(this);
        findViewById(R.id.btn_5).setOnClickListener(this);

        try {
            String[] notes = {"do", "re", "mi", "fa", "sol", "la", "si"};
            // 创建7个 MediaPlayer
            for (String note : notes) {
                MediaPlayer player = new MediaPlayer();
                AssetFileDescriptor descriptor = getAssets().openFd(note + ".mp3");
                player.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
                player.prepareAsync();
//                player.setVolume(1f, 1f);
                mList.add(player);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        int index;
        switch (v.getId()) {
            case R.id.btn_do:
                index = 0;
                break;
            case R.id.btn_re:
                index = 1;
                break;
            case R.id.btn_mi:
                index = 2;
                break;
            case R.id.btn_fa:
                index = 3;
                break;
            case R.id.btn_sol:
                index = 4;
                break;
            case R.id.btn_la:
                index = 5;
                break;
            case R.id.btn_si:
                index = 6;
                break;
//            case R.id.btn_1:
//                break;
//            case R.id.btn_2:
//                break;
//            case R.id.btn_3:
//                break;
//            case R.id.btn_4:
//                break;
//            case R.id.btn_5:
//                break;
            default:
                index = 0;
                break;
        }


        MediaPlayer player = mList.get(index);
        if (player.isPlaying()) {
            // 重新开始
            player.seekTo(0);
        }
        player.start();
    }

    @Override
    protected void onDestroy() {
        for (MediaPlayer mediaPlayer : mList) {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.release();
            }
        }
        super.onDestroy();
    }
}
