package com.example.e4a_s08_and_m1_android_projet_demineur.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e4a_s08_and_m1_android_projet_demineur.adapters.MineGridRecyclerAdapter;
import com.example.e4a_s08_and_m1_android_projet_demineur.listeners.OnCellClickListener;
import com.example.e4a_s08_and_m1_android_projet_demineur.R;
import com.example.e4a_s08_and_m1_android_projet_demineur.objects.Cell;
import com.example.e4a_s08_and_m1_android_projet_demineur.objects.MineSweeperGame;
import com.example.e4a_s08_and_m1_android_projet_demineur.writeToDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GameActivity extends AppCompatActivity implements OnCellClickListener {

    public static final long TIMER_LENGTH = 999000L;
    private static final int TAILLE_EASY = 5;
    private static final int TAILLE_MEDIUM = 8;
    private static final int TAILLE_HARD = 10;

    private static final int BOMB_EASY = 3;
    private static final int BOMB_MEDIUM = 10;
    private static final int BOMB_HARD = 16;

    private RecyclerView grid;
    private TextView restart, timer, flagsLeft;
    private Button returnM;
    private MineGridRecyclerAdapter mineGridRecyclerAdapter;
    private boolean timerStarted;
    private int secondsElapsed, taille, bombCount;
    private CountDownTimer countDownTimer;
    private MineSweeperGame mineSweeperGame;
    String difficulty;

    Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

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

            //Show Popup Dialog
            showEndGameDialog(false);


            mineSweeperGame.getMineGrid().revealAllBombs();
        }

        if (mineSweeperGame.isGameWon()) {
            countDownTimer.cancel();

            //Show Popup Dialog
            showEndGameDialog(true);

            mineSweeperGame.getMineGrid().revealAllBombs();
        }

        mineGridRecyclerAdapter.setCells(mineSweeperGame.getMineGrid().getCells());
    }


    public void cellLongClick(Cell cell) {
        mineSweeperGame.handleCellLongClick(cell);
        flagsLeft.setText(String.format("%03d", mineSweeperGame.getNumberBombs() - mineSweeperGame.getFlagCount()));

        if (!timerStarted) {
            countDownTimer.start();
            timerStarted = true;
        }

        if (mineSweeperGame.isGameOver()) {
            countDownTimer.cancel();

            //Show Popup Dialog
            showEndGameDialog(false);
            mineSweeperGame.getMineGrid().revealAllBombs();
        }

        if (mineSweeperGame.isGameWon()) {
            countDownTimer.cancel();

            //Show Popup Dialog
            showEndGameDialog(true);
            mineSweeperGame.getMineGrid().revealAllBombs();
        }
        mineGridRecyclerAdapter.setCells(mineSweeperGame.getMineGrid().getCells());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            vibrator.vibrate(50);
        }
    }

    private void showEndGameDialog(boolean isVictory) {

        Dialog dialog;

        dialog = new Dialog(GameActivity.this);
        dialog.setCancelable(false);

        if (isVictory){

            EditText playerName;
            Button validate;

            dialog.setContentView(R.layout.victory_popup);

            playerName = dialog.findViewById(R.id.playerNameEditText);

            validate = dialog.findViewById(R.id.ValidateButton);

            validate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String playerNameString = playerName.getText().toString();

                    if (playerNameString.isEmpty()){

                        Toast.makeText(getApplicationContext(), "Le pseudo n'est pas Valide", Toast.LENGTH_LONG).show();
                    }else{
                        writeToDatabase.newScore(difficulty, playerNameString, secondsElapsed);
                        Toast.makeText(getApplicationContext(), "Votre Score a bien été enregistré !", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(GameActivity.this, MainActivity.class);
                        startActivity(intent);

                        dialog.dismiss();
                    }
                }
            });
        }else{

            Button restartButton, backToMenu;

            dialog.setContentView(R.layout.lose_popup);

            restartButton = dialog.findViewById(R.id.restartButton);
            backToMenu = dialog.findViewById(R.id.backToMenuButton);

            restartButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mineSweeperGame = new MineSweeperGame(taille, bombCount);
                    mineGridRecyclerAdapter.setCells(mineSweeperGame.getMineGrid().getCells());
                    timerStarted = false;
                    countDownTimer.cancel();
                    secondsElapsed = 0;
                    timer.setText(R.string.default_count);
                    flagsLeft.setText(String.format("%03d", mineSweeperGame.getNumberBombs() - mineSweeperGame.getFlagCount()));
                    dialog.dismiss();
                }
            });

            backToMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(GameActivity.this, MainActivity.class);
                    startActivity(intent);

                    dialog.dismiss();
                }
            });
        }

        dialog.show();
    }
}