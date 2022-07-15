package com.tomer.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class PlayVideo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);
        Intent intent = getIntent();
        int video = intent.getIntExtra("video",-1);
        String country = intent.getStringExtra("country");
        if (video == -1) {
            Intent intent2 = new Intent(this, GameActivity.class);
            intent2.putExtra("country", country);
            startActivity(intent2);
        }
        VideoView videoView = findViewById(R.id.videoEsterEgg);
        String path = "android.resource://" + getPackageName() + "/";
        videoView.setVideoPath(path + video);
        Button reStart = findViewById(R.id.reStartVideoButton);
        reStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoView.stopPlayback();
                videoView.start();
            }
        });
        Button back = findViewById(R.id.goBackToGameButtom);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getApplicationContext(), GameActivity.class);
                intent2.putExtra("country", country);
                startActivity(intent2);
            }
        });
        videoView.start();

    }
}