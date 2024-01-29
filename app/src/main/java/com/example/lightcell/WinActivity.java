package com.example.lightcell;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WinActivity extends AppCompatActivity {

    public int          won_games;
    public TextView won_games_txt;
    public int          played_games;
    public TextView     played_games_txt;
    public int          fastest_game;
    public TextView     fastest_game_txt;
    public int          all_time_rows;
    public TextView     avg_game_txt;
    public int          score;
    public TextView     game_score_txt;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        Bundle statistics = getIntent().getExtras();
        score           = statistics.getInt("score");
        won_games       = statistics.getInt("won_games");
        played_games    = statistics.getInt("played_games");
        fastest_game    = statistics.getInt("fastest_game");
        all_time_rows   = statistics.getInt("all_time_rows");

        update_statistics();
    }


    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    public void update_statistics() {

        game_score_txt = findViewById(R.id.rows_of_game);
        won_games_txt = findViewById(R.id.games_won);
        played_games_txt = findViewById(R.id.games_played);
        fastest_game_txt = findViewById(R.id.best_score);
        avg_game_txt = findViewById(R.id.average_score);

        if(score==1) {game_score_txt.setText("You have won" + System.getProperty("line.separator") + "in 1 round!");}
        else {game_score_txt.setText("You have won" + System.getProperty("line.separator") +
                String.format("in %d rounds!",score));}
        won_games_txt.setText(Integer.toString(won_games));
        played_games_txt.setText(Integer.toString(played_games));
        fastest_game_txt.setText(Integer.toString(fastest_game));
        avg_game_txt.setText(String.format("%.2f",1.*all_time_rows/played_games));

    }
    public void new_game(View view){
        finish();
    }

}