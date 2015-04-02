package com.company;


import java.awt.*;

public class Oil extends Component
{

    private  int timeLeft;

    public Oil(Point location)
    {
        this.location = location;
        this.stepable = true;
        timeLeft = 3;
    }

    public void tick()
    {
        timeLeft--;
        if(timeLeft == 0)
            Game.getInstance().terminateObject(this);
    }

    public boolean isAlive()
    {
        return timeLeft > 0;
    }

    public  void steppedOnMe(Jump p)
    {
        if(p.getPlayer().isRobot)
        {
            Human player = (Human)p.getPlayer();
            System.out.println("Olajba léptek: " + player.getName() + p.getTo());
            player.setPrevLocation(player.getLocation());
            player.setLocation(p.getTo());
            System.out.println("Duplázódott a speedje");
            player.setSpeed(player.getSpeed() * 2);
            player.setNumStep(player.getNumStep()+1);
            player.setSlideCount(player.getSpeed());
            player.setNumOil(player.getNumOil()+1);
        }
        else
        {
            timeLeft = 1;
        }


    }

}
