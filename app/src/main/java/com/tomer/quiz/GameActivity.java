package com.tomer.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.tomer.quiz.logic.Game;

public class GameActivity extends AppCompatActivity {
    private Game game = null;


    private void setGameActivity() {
        ImageView flagView = findViewById(R.id.flagView);
        flagView.setImageResource(game.getCountryFlag());
        TextView info = findViewById(R.id.infoText);
        info.setText("");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        game = new Game();
        Intent intent = getIntent();
        String name = intent.getStringExtra("country");
        game.setIndex(name);
        setGameActivity();
        Button button = findViewById(R.id.gussButton);
        EditText editText = findViewById(R.id.editGuess);
        TextView info = findViewById(R.id.infoText);
        info.setText("");
        TextView count = findViewById(R.id.Text);
        updateLife();

        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if (editText.getText().toString().equals("")) {
                    return;
                }
                String answer = editText.getText().toString();
                int esterEgg = game.getEsterEgg(answer);
                if (esterEgg != -1) {
                    Intent intent = new Intent(getApplicationContext(), PlayVideo.class);
                    intent.putExtra("video", esterEgg);
                    intent.putExtra("country", game.getName());
                    editText.setText("");
                    startActivity(intent);
                    return;
                }
                boolean correct = game.gussCountry(answer);
                updateLife();
                editText.setText("");
                if (!correct) {
                    info.setText("You guested Wrong!\nthe correct answer is : " + game.getName());
                    info.setTextColor(Color.RED);
                } else {
                    info.setTextColor(Color.GREEN);
                    info.setText("Correct!");
                }
                new CountDownTimer(3000, 1000) {
                    @Override
                    public void onTick(long l) {
                        count.setText("seconds remaining: " + l / 1000);
                    }

                    @Override
                    public void onFinish() {
                        game.getNextCountry();
                        count.setText(R.string.EnterString);
                        setGameActivity();
                    }
                }.start();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void updateLife() {
        TextView life = findViewById(R.id.lifeView);
        String lifeText = "Life left : ";
        life.setText(lifeText + this.game.getLife());
        //life.setTextColor(Color.BLACK);

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}