package com.tomer.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tomer.quiz.logic.Game;

public class MainActivity extends AppCompatActivity {
    private Game game = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.gameButtom);
        if (this.game == null) {
            this.game = new Game();
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                intent.putExtra("country", game.getName());
                startActivity(intent);
            }
        });
    }
}