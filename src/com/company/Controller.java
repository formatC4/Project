package com.company;

import java.util.*;

/**
 * Created by nagypeter on 15. 05. 07..
 */
public class Controller {


    private Timer time;
    boolean paused = false;

    public boolean getGameOver() {
        return gameOver;
    }

    boolean gameOver = false;

    private KeyHandler handler;
    private static Controller instance;

    public static Controller getInstance()
    {
        if(instance == null)
            instance = new Controller();
        return instance;
    }



    private  Controller()
    {
        handler = new KeyHandler();
        time = new Timer();
        time.schedule(new TimerTask() {
            @Override
            public void run() {
                Tick();
            }
        },100,100);
    }

    public KeyHandler getHandler() {
        return handler;
    }

    public void Pause()
    {
        if(!paused) {
            time.cancel();
            paused = true;
        }else
        {
            time.schedule(new TimerTask() {
                @Override
                public void run() {
                    Tick();
                }
            },100,100);
            paused = false;
        }
    }


    public void Tick()
    {
        if(Game.getInstance().getPlayers().size() < 2)
            EndGame();

        Game.getInstance().setGlobalTime(Game.getInstance().getTime()+1000);
        Game.getInstance().update();
        View.getInstance().Refresh();

        if(Game.getInstance().getTime() % 50000 == 0 && !Game.getInstance().getOilList().isEmpty() && Game.getInstance().getPlayers().size() == 2)
            Game.getInstance().WallELauncher();

    }

    public void EndGame()
    {
        gameOver = true;
        Pause();
        View.getInstance().GameOver();
    }



    public char getKeyDirection(Human player)
    {
        switch (player.getID())
        {
            case 0:
                return handler.getPuffer(0);

            case 1:
                return handler.getPuffer(2);

        }

        return 'x';
    }

    public char getKeyItem(Human player)
    {
        switch (player.getID())
        {
            case 0:
                return handler.getPuffer(1);

            case 1:
                return handler.getPuffer(3);

        }
        return 'x';
    }

}
