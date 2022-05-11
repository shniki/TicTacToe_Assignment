package com.example.tictactoeassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.btnPlay1); //get btn by id/name
        btn.setOnClickListener(view -> {
            EditText edtPlayer1 = findViewById(R.id.player1ET); //get input line (edit text) by id
            EditText edtPlayer2 = findViewById(R.id.player2ET); //get input line (edit text) by id

            boolean exists=false;
            if(edtPlayer1!=null && edtPlayer2!=null) {
                String strPlayer1 = edtPlayer1.getText().toString();
                String strPlayer2 = edtPlayer2.getText().toString();

                if (!TextUtils.isEmpty(strPlayer1) && !TextUtils.isEmpty(strPlayer2)) //start playing
                {
                    if(!strPlayer2.equals(strPlayer1)) {
                        Intent switchActivityIntent = new Intent(this, GameActivity.class)
                                .putExtra("player1_name", strPlayer1)
                                .putExtra("player2_name", strPlayer2)
                                // send parameters
                                .addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        // every time we push return we go back to MAIN activity
                        // the only thing left in the Stack is the MAIN activity
                        startActivity(switchActivityIntent);
                    }
                    else
                    {
                        Snackbar.make(view, R.string.diff_input, Snackbar.LENGTH_LONG).show();
                        exists=true;
                    }
                }
            }

            if(!exists)
                Snackbar.make(view, R.string.wrong_input, Snackbar.LENGTH_LONG).show();
        });
    }
}