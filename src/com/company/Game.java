package com.company;

import java.awt.*;
import java.util.ArrayList;


public class Game
{
    private Map map;
    private ArrayList<Player> players;
    private ArrayList<Step> steps;

    private boolean isRunning;



    public Game()
    {
        init();
    }

    public void init()
    {
        players = new ArrayList<Player>();
        steps = new ArrayList<Step>();
        map = new Map();
        isRunning = true;
    }

    public  void update()
    {
        while (isRunning)
        {
            for(Player player : players)
            {
                Step currentStep = player.step();
                if(currentStep == null && player.isDead())
                {
                    System.out.println(((Human)player).getName()+" veszített");
                    isRunning = false;
                    break;
                }
                Component currentCmp = map.getComponent(currentStep.getTo());
                Point whereToPutComponent = new Point(currentStep.getPlayer().getLocation());
                if (currentCmp.getStepable())
                    currentCmp.steppedOnMe(currentStep);
                if(currentStep.getComponent() != null)
                    map.setComponent(whereToPutComponent,currentStep.getComponent());
                else
                    map.setComponent(whereToPutComponent,new Ground());
                map.draw(players.get(0).getLocation(),players.get(1).getLocation());
            }
        }
    }

    public  void createGame(int level)
    {
        map.load(level);
        players.add(new Human("Player 1"));
        players.add(new Human("Player 2"));
        update();
    }
}
