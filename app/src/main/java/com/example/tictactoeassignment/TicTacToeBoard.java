package com.example.tictactoeassignment;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class TicTacToeBoard extends View {

    private final int boardColor;
    private final int xColor;
    private final int oColor;
    private final int winningLineColor;

    private final Paint paint = new Paint();

    private int cellSize = getWidth() / 3;

    private final GameAlgorithm game;

    private boolean clickable = true;

    public TicTacToeBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        game = new GameAlgorithm();

        TypedArray ta = context.getTheme().obtainStyledAttributes
                (attrs, R.styleable.TicTacToeBoard, 0, 0);

        try {
            boardColor = ta.getInteger(R.styleable.TicTacToeBoard_boardColor, 0);
            xColor = ta.getInteger(R.styleable.TicTacToeBoard_xColor, 0);
            oColor = ta.getInteger(R.styleable.TicTacToeBoard_oColor, 0);
            winningLineColor = ta.getInteger(R.styleable.TicTacToeBoard_winningLineColor, 0);
        } finally {
            ta.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int dim = Math.min(getMeasuredWidth(), getMeasuredHeight());
        //choose smallest value between height and width
        cellSize = dim / 3;

        setMeasuredDimension(dim, dim);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setStyle(Paint.Style.STROKE); //draw lines
        paint.setAntiAlias(true);

        drawGameBoard(canvas); //draws #
        drawMarkers(canvas); //dras X and O based on GameAlgorithm's matrix
        GameAlgorithm.GameResults win = game.getWin();
        if(win!=null) //draws winning line if wins
            drawWinningLine(canvas,win.getType(),win.getStart());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        int action = event.getAction();

        if(action == MotionEvent.ACTION_DOWN) //actually tapped
        {
            if(clickable) {
                //calculate the place where tapped
                int row = (int) Math.ceil(y / cellSize);
                int col = (int) Math.ceil(x / cellSize);

                if (game.play(row - 1, col - 1)) {
                    //view end button - winners page
                    clickable = false;
                }

                invalidate(); //redraw our board
                return true;
            }

        }
        return false;
    }

    protected void drawGameBoard(Canvas canvas) {
        paint.setColor(boardColor); //change color according to xml
        paint.setStrokeWidth(16);

        for (int c = 1; c < 3; c++)
            //draw columns
            canvas.drawLine(cellSize * c, 0, cellSize * c, canvas.getWidth(), paint);

        for (int r = 1; r < 3; r++)
            //draw rows
            canvas.drawLine(0, cellSize * r, canvas.getWidth(), cellSize * r, paint);
    }

    private void drawMarkers(Canvas canvas){
        //draws every item in board
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++)
                if(game.getMat()[r][c]!=0)
                    if(game.getMat()[r][c]==1)//X
                        drawX(canvas,r,c);
                    else//O
                        drawO(canvas,r,c);
        //draw columns
    }

    protected void drawX(Canvas canvas, int row, int col) {
        paint.setColor(xColor); //change color according to xml

        float redact = (float)0.2 * cellSize; //so it wont be tight on a borders

        canvas.drawLine((col + 1) * cellSize - redact,
                row * cellSize + redact,
                col * cellSize + redact,
                (row + 1) * cellSize - redact,
                paint); // draw /

        canvas.drawLine(col * cellSize + redact,
                row * cellSize + redact,
                (col + 1) * cellSize - redact,
                (row + 1) * cellSize - redact,
                paint); //draw \
    }

    protected void drawO(Canvas canvas, int row, int col) {
        paint.setColor(oColor); //change color according to xml

        float redact = (float)0.2 * cellSize; //so it wont be tight on a borders

        canvas.drawOval(col * cellSize + redact,
                row * cellSize + redact,
                (col + 1) * cellSize - redact,
                (row + 1) * cellSize - redact,
                paint); //draw O
    }

    protected void drawWinningLine(Canvas canvas, char type, int start){
        paint.setColor(winningLineColor); //change color according to xml


        switch (type) {
            case 'c'://col = draws |
                canvas.drawLine( start*cellSize + cellSize/2,
                        0,
                        start * cellSize + cellSize/2,
                        cellSize*3,
                        paint);
                break;
            case 'r'://row = draws -
                canvas.drawLine( 0,
                        start*cellSize + cellSize/2,
                        cellSize*3,
                        start * cellSize + cellSize/2,
                        paint);
                break;
            case 'd'://diagonal
                if(start==0) //draws \
                    canvas.drawLine( 0,
                            0,
                            cellSize*3,
                            cellSize*3,
                            paint);
                else //draws /
                    canvas.drawLine( 0,
                            cellSize*3,
                            cellSize*3,
                            0,
                            paint);
                break;
        }
    }

    public void setView(Button btn, TextView tv, String nameP1, String nameP2){
        game.setBtn(btn);
        game.setTv(tv);
        game.setNames(nameP1,nameP2);
    }

    public long getTimer(){
        return game.getTimer();
    }

    public String getWinner() {
        return game.getWinner();
    }
}
