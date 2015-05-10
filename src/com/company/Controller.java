package com.company;

import java.util.*;

/**
 * A Controller idő biztosítja a kommmunikációt a játéklogika (Game) és a megjelenítés között (View)
 * Az osztály felelős a megfelelő ütemezés/időzítésért, illetve a billentyűzetről érkező parancsok továbbításáért
 */
public class Controller {


    private Timer time;
    boolean paused = false;


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


    /**
     * Globális időzítő szüneteltetése vagy éppen újraindítása, a játék megállításához szükséges
     */
    public void Pause()
    {
        if(!paused) {
            time.cancel();
            paused = true;
        }else
        {
            time = new Timer();
            time.schedule(new TimerTask() {
                @Override
                public void run() {
                    Tick();
                }
            },100,100);
            paused = false;
        }
    }

    /**
     * A legfontossab függvények (GUI újrarajzolás, takarító robotok beküldése) indítása megfelelő időközönként
     */
    public void Tick()
    {
        if(Game.getInstance().getPlayers().size() < 2)
            EndGame();

        Game.getInstance().setGlobalTime(Game.getInstance().getTime()+1000);
        Game.getInstance().update();
        View.getInstance().Refresh();

        if(Game.getInstance().getTime() % 10000 ==0)
            View.getInstance().setGlobaTime("Global time: "+Integer.toString(Game.getInstance().getTime()/10000));
        if(Game.getInstance().getTime() % 50000 == 0 && !Game.getInstance().getOilList().isEmpty() && Game.getInstance().getPlayers().size() == 2)
            Game.getInstance().WallELauncher();

    }

    /** Játék vége:
     * A játék megállítása és view értesítése, hogy a játékos is értesítve legyen az eseményről
     */
    public void EndGame()
    {
        gameOver = true;
        Pause();
        View.getInstance().GameOver();
    }


    /**
     *
     * A KeyListenertől kapott bemenet továbbirányítása a pufferből
     */
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
