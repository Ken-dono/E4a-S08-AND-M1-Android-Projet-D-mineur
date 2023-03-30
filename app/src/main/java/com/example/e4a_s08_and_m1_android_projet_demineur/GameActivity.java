package com.example.e4a_s08_and_m1_android_projet_demineur;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameActivity extends AppCompatActivity implements OnCellClickListener{

    public static final long TIMER_LENGTH = 999000L;
    private static final int TAILLE_EASY = 5;
    private static final int TAILLE_MEDIUM = 8;
    private static final int TAILLE_HARD = 10;

    private static final int BOMB_EASY = 5;
    private static final int BOMB_MEDIUM = 8;
    private static final int BOMB_HARD = 10;


    private Cellule[][] plateau;

    private RecyclerView grid;
    private Switch flagSwitch = null;
    private TextView restart, timer, flag, flagsLeft;
    private Button returnM;
    private MineGridRecyclerAdapter mineGridRecyclerAdapter;
    private boolean timerStarted;
    private int secondsElapsed, taille, bombCount;
    private CountDownTimer countDownTimer;
    private MineSweeperGame mineSweeperGame;
    String difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();

        difficulty = intent.getStringExtra("difficulty");

        switch(difficulty){

            case "easy":
                taille = TAILLE_EASY;
                bombCount = BOMB_EASY;
                break;
            case "medium":
                taille = TAILLE_MEDIUM;
                bombCount = BOMB_MEDIUM;
                break;
            case "hard":
                taille = TAILLE_HARD;
                bombCount = BOMB_HARD;
                break;
        }

        flagSwitch = findViewById(R.id.flag_Switch);
        flagsLeft = findViewById(R.id.flagsleft);
        grid = findViewById(R.id.grid);
        grid.setLayoutManager(new GridLayoutManager(this, taille));

        timer = findViewById(R.id.timer);
        timerStarted = false;

        countDownTimer = new CountDownTimer(TIMER_LENGTH, 1000) {
            public void onTick(long millisUntilFinished) {
                secondsElapsed += 1;
                timer.setText(String.format("%03d", secondsElapsed));
            }

            public void onFinish() {
                Toast.makeText(getApplicationContext(), "Game Over: Timer Expired", Toast.LENGTH_SHORT).show();
            }
        };

        mineSweeperGame = new MineSweeperGame(taille, bombCount);
        flagsLeft.setText(String.format("%03d", mineSweeperGame.getNumberBombs() - mineSweeperGame.getFlagCount()));
        mineGridRecyclerAdapter = new MineGridRecyclerAdapter(mineSweeperGame.getMineGrid().getCells(), this);
        grid.setAdapter(mineGridRecyclerAdapter);

        restart = findViewById(R.id.restart);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mineSweeperGame = new MineSweeperGame(taille, bombCount);
                mineGridRecyclerAdapter.setCells(mineSweeperGame.getMineGrid().getCells());
                timerStarted = false;
                countDownTimer.cancel();
                secondsElapsed = 0;
                timer.setText(R.string.default_count);
                flagsLeft.setText(String.format("%03d", mineSweeperGame.getNumberBombs() - mineSweeperGame.getFlagCount()));
            }
        });
        returnM = findViewById(R.id.ReturnMenu);
        returnM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        flagSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                mineSweeperGame.toggleMode();
                if(isChecked){

                    GradientDrawable border = new GradientDrawable();
                    border.setColor(0xFFFFFFFF);
                    border.setStroke(1, 0xFF000000);
                }else{
                    GradientDrawable border = new GradientDrawable();
                    border.setColor(0xFFFFFFFF);
                }
            }
        });



        }

    @Override
    public void cellClick(Cell cell) {
        mineSweeperGame.handleCellClick(cell);

        flagsLeft.setText(String.format("%03d", mineSweeperGame.getNumberBombs() - mineSweeperGame.getFlagCount()));

        if (!timerStarted) {
            countDownTimer.start();
            timerStarted = true;
        }

        if (mineSweeperGame.isGameOver()) {
            countDownTimer.cancel();
            Toast.makeText(getApplicationContext(), "Game Over", Toast.LENGTH_SHORT).show();
            mineSweeperGame.getMineGrid().revealAllBombs();
        }

        if (mineSweeperGame.isGameWon()) {
            countDownTimer.cancel();
            Toast.makeText(getApplicationContext(), "Game Won", Toast.LENGTH_SHORT).show();
            mineSweeperGame.getMineGrid().revealAllBombs();
        }

        mineGridRecyclerAdapter.setCells(mineSweeperGame.getMineGrid().getCells());
    }
}