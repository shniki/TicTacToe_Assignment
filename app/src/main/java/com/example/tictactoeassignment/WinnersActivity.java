package com.example.tictactoeassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class WinnersActivity extends AppCompatActivity {

    static List<Winner> wins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winners);

        if(wins == null)
            wins = new ArrayList<>();


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String strWinner = extras.getString("winner_name");
            long time = extras.getLong("time");
            if(time!=0) //TIE
                addWinner(new Winner(strWinner,time));

        }

        RecyclerView rvFeed = findViewById(R.id.winsView); //get recycler-view by id
        WinnersAdapter adapter = new WinnersAdapter(wins); //create adapter
        rvFeed.setAdapter(adapter); //set adapter
        //choose type of layout: linear, horological or staggered
        rvFeed.setLayoutManager(new LinearLayoutManager(this));

        Button btnHome = findViewById(R.id.btnRestart2); //get btn by id/name
        btnHome.setOnClickListener(view -> {
                    // instead of:
                    // Intent switchActivityIntent = new Intent(this, MainActivity.class);
                    // switchActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

                    // startActivity(switchActivityIntent);

                    // NOTICE: the only thing in stack is MAIN activity
                    // so we just end this activity & pull the latest activity from the stack
                    finish();
                }
        );
        Button btnPlay = findViewById(R.id.btnPlay2); //get btn by id/name
        btnPlay.setOnClickListener(view -> {
                    if (extras != null) {
                        Intent switchActivityIntent = new Intent(this, GameActivity.class)
                                .putExtra("player1_name", extras.getString("player1_name"))
                                .putExtra("player2_name", extras.getString("player2_name"))
                                // send parameters
                                .addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        // every time we push return we go back to MAIN activity
                        // the only thing left in the Stack is the MAIN activity
                        startActivity(switchActivityIntent);
                    }
                }
        );
    }

    private void addWinner(Winner winner)
    {
        //make sure list contains 10 items tops, make sure it is sorted

        wins.add(winner); //tries to add to wins list
        wins.sort((Winner w1,Winner w2)-> (int) (w1.getTime()-w2.getTime())); //make sure it is sorted, use compare
        if (wins.size()>10)
            wins=wins.subList(0,10); //make sure list contains 10 items tops
        //if adds print (maybe)
    }
}