package com.example.hp.connect;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    int activePlayer=0;//0=cross 1=not
    int[] gameState={2,2,2,2,2,2,2,2,2};//2 means game is unplayed.
    int[][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameIsActive=true;
    public void dropIn(View view)
    {
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter]==2&&gameIsActive)
        {
            counter.setTranslationX(-1000f);
            counter.setTranslationY(-1000f);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.cross);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.circle);
                activePlayer = 0;
            }
            counter.animate().translationXBy(1000f).translationYBy(1000f).setDuration(50);
            gameState[tappedCounter]=activePlayer;
            for(int[] winningPosition:winningPositions)
            {
                if(gameState[winningPosition[0]]==gameState[winningPosition[1]]&&gameState[winningPosition[1]]==gameState[winningPosition[2]]&&gameState[winningPosition[0]]!=2)
                {
                    gameIsActive=false;
                    String winner="CROSS";
                    if(gameState[winningPosition[0]]==0)
                    {
                        winner = "NOT";

                    }
                    Toast.makeText(getApplicationContext(), winner+" has won!", Toast.LENGTH_LONG).show();
                    Button playAgain=(Button) findViewById(R.id.playAgain);
                    playAgain.setVisibility(View.VISIBLE);
                }
                else
                {
                    boolean gameIsOver=true;
                    for(int counterState:gameState)
                    {
                        if(counterState==2)
                        {
                            gameIsOver = false;
                        }
                    }
                    if(gameIsOver)
                    {
                        Toast.makeText(getApplicationContext(),"It's a draw!",Toast.LENGTH_LONG).show();
                        Button playAgain=(Button) findViewById(R.id.playAgain);
                        playAgain.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }
    public void playAgain(View view)
    {
        gameIsActive=true;
        Button playAgain=(Button) findViewById(R.id.playAgain);
        playAgain.setVisibility(View.INVISIBLE);
        activePlayer=0;//0=cross 1=not
        int i;
        for(i=0;i<9;i++)
        {
            gameState[i]=2;
        }
        ConstraintLayout constraintLayout=(ConstraintLayout)findViewById(R.id.constraintLayout);
        for(i=0;i<constraintLayout.getChildCount();i++)
        {
            constraintLayout.getChildAt(i);
        }
        GridLayout gridLayout=(GridLayout)findViewById(R.id.gridLayout);
        for(i=0;i<gridLayout.getChildCount();i++)
        {
            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
