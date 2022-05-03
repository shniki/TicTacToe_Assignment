package com.example.tictactoeassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class GameActivity extends AppCompatActivity {

    private String strPlayer1;
    private String strPlayer2;

    private TicTacToeBoard ticTacToeBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        TextView tv = findViewById(R.id.vsTitleTV);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            strPlayer1 = extras.getString("player1_name");
            strPlayer2 = extras.getString("player2_name");

            tv.setText(strPlayer1+" VS "+strPlayer2);
            //tv2.setText("It's "+strPlayer1+" turn");
            //tv2.setText("It's "+strPlayer2+" turn");
        }

        ticTacToeBoard = findViewById(R.id.ticTacToeBoard);

        Button btn = findViewById(R.id.btnWinners); //get btn by id/name
        btn.setVisibility(View.INVISIBLE);
        btn.setOnClickListener(view -> {
            Intent switchActivityIntent = new Intent(this, WinnersActivity.class)
                            .putExtra("player1_name", strPlayer1)
                            .putExtra("player2_name", strPlayer2)
                            .putExtra("time",ticTacToeBoard.getTimer())
                            .putExtra("winner_name",ticTacToeBoard.getWinner())
                            // send parameters
                            .addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                            // every time we push return we go back to MAIN activity
                            // the only thing left in the Stack is the MAIN activity
                    startActivity(switchActivityIntent);
                }
        );
        ticTacToeBoard.setView(btn, tv, strPlayer1, strPlayer2); //send view parameters

    }
}