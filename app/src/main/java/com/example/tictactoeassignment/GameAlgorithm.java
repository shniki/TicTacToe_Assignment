package com.example.tictactoeassignment;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class GameAlgorithm {
    //We need a class which stores the data
    //and knows who's turn is it?
    //who wins?
    //it also checks how long it takes?
    private final int[][] mat;//0=empty, 1='X', 2='O'
    private int turn;
    private GameResults win;
    private Date startTimer;
    private long timer;

    private Button btn;
    private TextView tv;
    private String[] names = new String[2];

    //CTOR
    public GameAlgorithm() {
        mat = new int[3][3];
        turn = 0;
        win = null;
    }

    //getBoard() = returns matrix
    public int[][] getMat() {
        return mat;
    }

    //player() = who's turn is it? 'X' or 'O'
    public char player() {
        if(turn%2==0)
            return 'X';
        return 'O';
    }

    //tryplace() = checks if possible to place in spot (checks if taken), returns true/false
    public boolean tryplace(int row, int col){
        return mat[row][col]==0;
    }

    class GameResults{
        private final char winner;
        private final char type;
        private final int start;

        public GameResults(char winner, char type, int start) {
            this.winner = winner;
            this.type = type;
            this.start = start;
        }

        public char getWinner() {
            return winner;
        }

        public char getType() {
            return type;
        }

        public int getStart() {
            return start;
        }
    }

    //play() = places in spot, returns true/false if game ends
    public boolean play(int row, int col){
        if(turn==0)
            startTimer=new Date();
        if(!tryplace(row,col))
            return false;
        mat[row][col]=(turn%2)+1;
        turn++;

        tv.setText(names[turn%2]+"'s turn");//X = player1

        if(validate(row,col)) {
            Date end = new Date();
            long diff = end.getTime() - startTimer.getTime();
            TimeUnit time = TimeUnit.SECONDS;
            timer = time.convert(diff, TimeUnit.MILLISECONDS);

            tv.setText(names[(turn-1)%2]+" WINS!!");//X = player1

            btn.setVisibility(View.VISIBLE);

            return true; //WIN
        }
        if(turn==9) {
            btn.setVisibility(View.VISIBLE);

            tv.setText(R.string.tie_txt);

            return true; //TIE
        }
        return false;
    }

    private boolean validate(int row, int col){
        if(mat[0][col]==mat[1][col]&&mat[1][col]==mat[2][col]) {
            win = new GameResults(player(),'c',col);
            return true;
        }
        if(mat[row][0]==mat[row][1]&&mat[row][1]==mat[row][2]) {
            win = new GameResults(player(),'r',row);
            return true;
        }
        if(mat[0][0]==mat[1][1]&&mat[1][1]==mat[2][2]&&mat[1][1]!=0) {
            win = new GameResults(player(),'d',0);
            return true;
        }
        if(mat[0][2]==mat[1][1]&&mat[1][1]==mat[2][0]&&mat[1][1]!=0) {
            win = new GameResults(player(),'d',1);
            return true;
        }
        return false;
    }

    //timer() = checks how long it took for game to end
    public long getTimer() {
        return timer;
    }

    public String getWinner(){
        return names[(turn-1)%2];
    }

    //over() = checks if over or not, returns true/false

    //winInfo() = type='d','c','r' and starting-point
    public GameResults getWin() {
        return win;
    }

    //also add: Button winners, when game ends - make viewable using .
    //setter of view items
    public void setBtn(Button btn) {
        this.btn = btn;
    }

    public void setTv(TextView tv) {
        this.tv = tv;
    }

    public void setNames(String nameP1, String nameP2){
        names[0]=nameP1;
        names[1]=nameP2;
    }



//README:

    //HOW TO USE:
    //create new game using:
    //GameAlgorithm ga = new GameAlgorithm();

    //each turn use
    //char player=ga.player()
    //to know if to place 'X' or 'O' - place player


    //if(ga.tryplace(row,col))
        //place in (row,col)

    //then use
    //if(ga.play())
        //if(ga.over())
            //print "TIE"
        //else //win
            //if(player=='X')
                //print "X won"
            //else //player=='Y'
                //print "Y won"
            //send intent to activity3 with info: who won? how long it took (in seconds)?

    //in activity3
    //use adapter to add to list of WinsItems (String winner, String loser, long secs)
    //in adapter.add(WinsItems) check if faster
    //if faster than everyone in table
        //place correctly in table
        //and return/print "NEW RECORD"
}
