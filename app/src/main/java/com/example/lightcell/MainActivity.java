package com.example.lightcell;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    View view; //Generic view to being able to call some functions from code.

    public          int num_colors = 8;  // Options 6,8 TODO Possibilitat de escollir-ho in-game
    public  final   int max_colors = 8;
    public          int max_rows = 20;   // Options 8,12,15,20

    // List of all the "PickAColor" buttons, and the accept button
    public MaterialButton[] color_buttons;
    public ImageButton accept_button, restart_button;
    public TextView error_message, congrats_message;

    // List of all rows, score-image of each row, buttons (for playing), and the color on them.
    public LinearLayout[] rows;
    public ImageView[] row_score;
    public Button[][] game_buttons;
    public byte[][] game_buttons_color_index;

    // Variables for following the activity of each game.
    public ColorStateList active_color;
    public int active_color_index;
    public int active_row;
    public int[] solution;

    // Variables for statistics
    public int          won_games;
    public int          played_games;
    public int          fastest_game;
    public int          all_time_rows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // We initialize all the buttons.
        initialize_right_buttons();
        initialize_rows();
        initialize_game_buttons();
        initialize_score_imageview();

        // We initialize all the text_views.
        initialize_text_views();

        // We initialize the values of the current statistics.
        initialize_statistics();

        reset_game(view);
    }


    @SuppressLint("WrongViewCast")
    private void initialize_right_buttons() {
        accept_button = findViewById(R.id.accept_button);
        restart_button = findViewById(R.id.restart_button);
        color_buttons = new MaterialButton[max_colors];
        color_buttons[0] = findViewById(R.id.red_button);
        color_buttons[1] = findViewById(R.id.orange_button);
        color_buttons[2] = findViewById(R.id.yellow_button);
        color_buttons[3] = findViewById(R.id.green_button);
        color_buttons[4] = findViewById(R.id.cyan_button);
        color_buttons[5] = findViewById(R.id.blue_button);
        color_buttons[6] = findViewById(R.id.purple_button);
        color_buttons[7] = findViewById(R.id.pink_button);
        for(int i=num_colors; i<max_colors; i++){
            color_buttons[i].setVisibility(View.GONE);
        }
    }

    private void initialize_rows() {
        rows = new LinearLayout[max_rows];
        rows[0] = findViewById(R.id.row1);
        rows[1] = findViewById(R.id.row2);
        rows[2] = findViewById(R.id.row3);
        rows[3] = findViewById(R.id.row4);
        rows[4] = findViewById(R.id.row5);
        rows[5] = findViewById(R.id.row6);
        rows[6] = findViewById(R.id.row7);
        rows[7] = findViewById(R.id.row8);
        if (max_rows > 8) {
            rows[8] = findViewById(R.id.row9);
            rows[9] = findViewById(R.id.row10);
            rows[10] = findViewById(R.id.row11);
            rows[11] = findViewById(R.id.row12);
        }
        if (max_rows > 12) {
            rows[12] = findViewById(R.id.row13);
            rows[13] = findViewById(R.id.row14);
            rows[14] = findViewById(R.id.row15);
        }
        if (max_rows > 15) {
            rows[15] = findViewById(R.id.row16);
            rows[16] = findViewById(R.id.row17);
            rows[17] = findViewById(R.id.row18);
            rows[18] = findViewById(R.id.row19);
            rows[19] = findViewById(R.id.row20);
        }
    }

    private void initialize_game_buttons() {
        // Create the game buttons matrix
        game_buttons = new Button[max_rows][4];

        // Create and initialize a boolean matrix for if buttons got painted
        game_buttons_color_index = new byte[max_rows][4];
        for (int i = 0; i < max_rows; i++) {
            Arrays.fill(game_buttons_color_index[i], (byte) -1);
        }

        // Initialize all the buttons matrix
        game_buttons[0][0] = findViewById(R.id.r1b1);
        game_buttons[0][1] = findViewById(R.id.r1b2);
        game_buttons[0][2] = findViewById(R.id.r1b3);
        game_buttons[0][3] = findViewById(R.id.r1b4);

        game_buttons[1][0] = findViewById(R.id.r2b1);
        game_buttons[1][1] = findViewById(R.id.r2b2);
        game_buttons[1][2] = findViewById(R.id.r2b3);
        game_buttons[1][3] = findViewById(R.id.r2b4);

        game_buttons[2][0] = findViewById(R.id.r3b1);
        game_buttons[2][1] = findViewById(R.id.r3b2);
        game_buttons[2][2] = findViewById(R.id.r3b3);
        game_buttons[2][3] = findViewById(R.id.r3b4);

        game_buttons[3][0] = findViewById(R.id.r4b1);
        game_buttons[3][1] = findViewById(R.id.r4b2);
        game_buttons[3][2] = findViewById(R.id.r4b3);
        game_buttons[3][3] = findViewById(R.id.r4b4);

        game_buttons[4][0] = findViewById(R.id.r5b1);
        game_buttons[4][1] = findViewById(R.id.r5b2);
        game_buttons[4][2] = findViewById(R.id.r5b3);
        game_buttons[4][3] = findViewById(R.id.r5b4);

        game_buttons[5][0] = findViewById(R.id.r6b1);
        game_buttons[5][1] = findViewById(R.id.r6b2);
        game_buttons[5][2] = findViewById(R.id.r6b3);
        game_buttons[5][3] = findViewById(R.id.r6b4);

        game_buttons[6][0] = findViewById(R.id.r7b1);
        game_buttons[6][1] = findViewById(R.id.r7b2);
        game_buttons[6][2] = findViewById(R.id.r7b3);
        game_buttons[6][3] = findViewById(R.id.r7b4);

        game_buttons[7][0] = findViewById(R.id.r8b1);
        game_buttons[7][1] = findViewById(R.id.r8b2);
        game_buttons[7][2] = findViewById(R.id.r8b3);
        game_buttons[7][3] = findViewById(R.id.r8b4);

        if (max_rows > 8) {
            game_buttons[8][0] = findViewById(R.id.r9b1);
            game_buttons[8][1] = findViewById(R.id.r9b2);
            game_buttons[8][2] = findViewById(R.id.r9b3);
            game_buttons[8][3] = findViewById(R.id.r9b4);

            game_buttons[9][0] = findViewById(R.id.r10b1);
            game_buttons[9][1] = findViewById(R.id.r10b2);
            game_buttons[9][2] = findViewById(R.id.r10b3);
            game_buttons[9][3] = findViewById(R.id.r10b4);

            game_buttons[10][0] = findViewById(R.id.r11b1);
            game_buttons[10][1] = findViewById(R.id.r11b2);
            game_buttons[10][2] = findViewById(R.id.r11b3);
            game_buttons[10][3] = findViewById(R.id.r11b4);

            game_buttons[11][0] = findViewById(R.id.r12b1);
            game_buttons[11][1] = findViewById(R.id.r12b2);
            game_buttons[11][2] = findViewById(R.id.r12b3);
            game_buttons[11][3] = findViewById(R.id.r12b4);
        }

        if (max_rows > 12) {
            game_buttons[12][0] = findViewById(R.id.r13b1);
            game_buttons[12][1] = findViewById(R.id.r13b2);
            game_buttons[12][2] = findViewById(R.id.r13b3);
            game_buttons[12][3] = findViewById(R.id.r13b4);

            game_buttons[13][0] = findViewById(R.id.r14b1);
            game_buttons[13][1] = findViewById(R.id.r14b2);
            game_buttons[13][2] = findViewById(R.id.r14b3);
            game_buttons[13][3] = findViewById(R.id.r14b4);

            game_buttons[14][0] = findViewById(R.id.r15b1);
            game_buttons[14][1] = findViewById(R.id.r15b2);
            game_buttons[14][2] = findViewById(R.id.r15b3);
            game_buttons[14][3] = findViewById(R.id.r15b4);
        }

        if (max_rows > 15) {
            game_buttons[15][0] = findViewById(R.id.r16b1);
            game_buttons[15][1] = findViewById(R.id.r16b2);
            game_buttons[15][2] = findViewById(R.id.r16b3);
            game_buttons[15][3] = findViewById(R.id.r16b4);

            game_buttons[16][0] = findViewById(R.id.r17b1);
            game_buttons[16][1] = findViewById(R.id.r17b2);
            game_buttons[16][2] = findViewById(R.id.r17b3);
            game_buttons[16][3] = findViewById(R.id.r17b4);

            game_buttons[17][0] = findViewById(R.id.r18b1);
            game_buttons[17][1] = findViewById(R.id.r18b2);
            game_buttons[17][2] = findViewById(R.id.r18b3);
            game_buttons[17][3] = findViewById(R.id.r18b4);

            game_buttons[18][0] = findViewById(R.id.r19b1);
            game_buttons[18][1] = findViewById(R.id.r19b2);
            game_buttons[18][2] = findViewById(R.id.r19b3);
            game_buttons[18][3] = findViewById(R.id.r19b4);

            game_buttons[19][0] = findViewById(R.id.r20b1);
            game_buttons[19][1] = findViewById(R.id.r20b2);
            game_buttons[19][2] = findViewById(R.id.r20b3);
            game_buttons[19][3] = findViewById(R.id.r20b4);
        }

    }

    private void initialize_score_imageview() {
        row_score = new ImageView[max_rows];
        row_score[0] = findViewById(R.id.score1);
        row_score[1] = findViewById(R.id.score2);
        row_score[2] = findViewById(R.id.score3);
        row_score[3] = findViewById(R.id.score4);
        row_score[4] = findViewById(R.id.score5);
        row_score[5] = findViewById(R.id.score6);
        row_score[6] = findViewById(R.id.score7);
        row_score[7] = findViewById(R.id.score8);
        if (max_rows > 8) {
            row_score[8] = findViewById(R.id.score9);
            row_score[9] = findViewById(R.id.score10);
            row_score[10] = findViewById(R.id.score11);
            row_score[11] = findViewById(R.id.score12);
        }
        if (max_rows > 12) {
            row_score[12] = findViewById(R.id.score13);
            row_score[13] = findViewById(R.id.score14);
            row_score[14] = findViewById(R.id.score15);
        }
        if (max_rows > 15) {
            row_score[15] = findViewById(R.id.score16);
            row_score[16] = findViewById(R.id.score17);
            row_score[17] = findViewById(R.id.score18);
            row_score[18] = findViewById(R.id.score19);
            row_score[19] = findViewById(R.id.score20);
        }
    }

    private void initialize_text_views() {
        error_message = findViewById(R.id.textview_error_message);
        congrats_message = findViewById(R.id.textview_congrats_message);
    }

    private void initialize_statistics() { // TODO Save them on another file
        won_games = 0;
        played_games = 0;
        fastest_game = 20;
        all_time_rows = 0;
    }

    public void reset_game(View view) {
        // We set the active row at 0
        active_row = 0;

        // We enable all the buttons, paint them gray, and take out all the rows...
        active_color = findViewById(R.id.grey_button).getBackgroundTintList(); // We set the active color at grey
        for (int i = 0; i < max_rows; i++) {
            for (int j = 0; j < 4; j++) {
                game_buttons[i][j].setEnabled(true);
                SetColor(game_buttons[i][j]);
            }
            Arrays.fill(game_buttons_color_index[i], (byte) -1);
            rows[i].setVisibility(View.GONE);
            row_score[i].setBackgroundResource(R.drawable._00);
        }

        accept_button.setVisibility(View.VISIBLE);
        restart_button.setVisibility(View.GONE);
        congrats_message.setVisibility(View.GONE);

        for (int i=0; i<num_colors; i++){
            color_buttons[i].setStrokeWidth(0);
            color_buttons[i].setVisibility(View.VISIBLE);
        }
        for (int i=num_colors; i<max_colors; i++){
            color_buttons[i].setVisibility(View.GONE);
        }

        // We pick a random color to start the game
        PickColor(color_buttons[new Random().nextInt(8)]);

        // ... except the first one.
        rows[0].setVisibility(View.VISIBLE);
        rows[0].setBackgroundResource(R.color.light_green);

        // We generate a new solution to start playing.
        solution = generateSolution();

        randomizeAnimation();
    }

    private int[] generateSolution() {
        Random rn = new Random();
        int[] options = {0, 1, 2, 3, 4, 5, 6, 7};
        int tmp;
        for (int i = 0; i < 4; i++) {
            int num = rn.nextInt(num_colors - i);
            tmp = options[i];
            options[i] = options[num + i];
            options[num + i] = tmp;
        }
        int[] solutions = Arrays.copyOf(options, 4);
//        int[] solutions = {0, 1, 2, 3};
        return solutions;
    }

    private void randomizeAnimation() { // TODO Create an animation for the beggining of the game.
        // TODO Colors fading
    }

    @SuppressLint("ResourceAsColor")
    public void PickColor(View view) {
        Button button = (Button) view;
        for (int i = 0; i < num_colors; i++) {
            if (color_buttons[i].getBackgroundTintList() == active_color) {
                color_buttons[i].setStrokeWidth(0);
            }
        }
        for (int i = 0; i < num_colors; i++) {
            if (button.getId() == color_buttons[i].getId()) {
                active_color = color_buttons[i].getBackgroundTintList();
                active_color_index = i;
                color_buttons[i].setStrokeColor(ColorStateList.valueOf(R.color.black));
                color_buttons[i].setStrokeWidth(5);
            }
        }
    }

    public void SetColor(View view) {
        view.setBackgroundTintList(active_color);
        for (int i = 0; i < 4; i++) {
            if (game_buttons[active_row][i].getId() == view.getId()) {
                game_buttons_color_index[active_row][i] = (byte) active_color_index;
            }
        }
    }


    @SuppressLint("ResourceAsColor")
    public void tryGuess(View view) {
        if (everything_ok()) {
            // We check the punctuation of the active_row and store it in "result"
            int result = checkRowResult();
            paintResult(result, row_score[active_row]);
            // If we have won, we have to exit the game
            if (result == 20) {
                winGame();
            }
            if (result < 20) {
                prepare_next_row();
            }
        }

    }

    @SuppressLint("ResourceAsColor")
    private boolean everything_ok() {
        error_message.setText("");
        for (int j = 0; j < 4; j++) {
            if (game_buttons_color_index[active_row][j] == (byte) -1) {
                error_message.setText(R.string.error_message_blank_colors);
                error_message.setVisibility(View.VISIBLE);
                return false;
            }
            for(int i=j+1; i<4; i++){
                if (game_buttons_color_index[active_row][i] == game_buttons_color_index[active_row][j]) {
                    error_message.setText(R.string.error_message_equal_colors);
                    error_message.setVisibility(View.VISIBLE);
                    return false;
                }
            }
        }
        error_message.setVisibility(View.GONE);
        error_message.setText("");
        return true;
    }

    private int checkRowResult() {
        int value = 0;
        for (int j = 0; j < 4; j++) {
            // 1 Get color index from button j
            int index_color = game_buttons_color_index[active_row][j];

            // 2 Compare color to solution j. It's +5
            // [In fact, if solution is in j, then we go +4+1, instead of +5+0]
            if (index_color == solution[j]) {
                value += 4;
            }

            // 3 Compare color to solution {1234}\{j}. It's +1
            for (int i = 0; i < 4; i++) {
                if (index_color == solution[i]) {
                    value += 1;
                }
            }

        }

        // 4 Return final result
        return value;
    }

    private void paintResult(int result, ImageView view) {
        switch(result){
            case 0: view.setBackgroundResource(R.drawable._00); break;
            case 1: view.setBackgroundResource(R.drawable._01); break;
            case 2: view.setBackgroundResource(R.drawable._02); break;
            case 3: view.setBackgroundResource(R.drawable._03); break;
            case 4: view.setBackgroundResource(R.drawable._04); break;
            case 5: view.setBackgroundResource(R.drawable._10); break;
            case 6: view.setBackgroundResource(R.drawable._11); break;
            case 7: view.setBackgroundResource(R.drawable._12); break;
            case 8: view.setBackgroundResource(R.drawable._13); break;
            case 10: view.setBackgroundResource(R.drawable._20); break;
            case 11: view.setBackgroundResource(R.drawable._21); break;
            case 12: view.setBackgroundResource(R.drawable._22); break;
            case 15: view.setBackgroundResource(R.drawable._30); break;
            case 16: view.setBackgroundResource(R.drawable._31); break;
            case 20: view.setBackgroundResource(R.drawable._40); break;
        }
    }

    @SuppressLint("ResourceAsColor")
    private void prepare_next_row() {
        for (int j = 0; j < 4; j++) {
            game_buttons[active_row][j].setEnabled(false);
        }
        rows[active_row].setBackgroundResource(R.color.light_red);

        // Setup for new round
        active_row++;

        if (active_row < max_rows) {
            rows[active_row].setVisibility(View.VISIBLE);
            rows[active_row].setBackgroundResource(R.color.light_green);
        }
        else loseGame();
    }

    @SuppressLint("DefaultLocale")
    private void winGame() {
        // We update all the statistics
        won_games++;
        played_games++;
        all_time_rows += active_row+1;
        if(fastest_game > active_row+1){fastest_game = active_row+1;}

        // We setup the congratulations for the endGameScreen
        congrats_message.setText(R.string.win_message);
        if(active_row==0) {congrats_message.append("You used 1 round");}
        else {congrats_message.append(String.format("You used %d rounds",active_row+1));}
        endGameScreen();
    }

    private void loseGame(){
        played_games++;
        all_time_rows += active_row+1;
        congrats_message.setText(R.string.lose_message);
        // And we go to the end-game-screen
        endGameScreen();
    }

    @SuppressLint("DefaultLocale")
    private void endGameScreen() {
        accept_button.setVisibility(View.GONE);
        restart_button.setVisibility(View.VISIBLE);
        congrats_message.setVisibility(View.VISIBLE);

//        Intent intent = new Intent(this, WinActivity.class);
//        intent.putExtra("score", active_row + 1);
//        intent.putExtra("won_games", won_games);
//        intent.putExtra("played_games", played_games);
//        intent.putExtra("fastest_game", fastest_game);
//        intent.putExtra("all_time_rows", all_time_rows);
//        startActivity(intent);
    }

}