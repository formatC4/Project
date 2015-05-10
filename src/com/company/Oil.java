package com.company;


import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class Oil extends Component
{

    private  int timeLeft;

    public Oil(Point location)
    {
        this.location = location;
        this.stepable = true;
        timeLeft = 100;
        try {
            this.icon = ImageIO.read(new File("kepek/oil.png"));
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void tick()
    {
        timeLeft--;
        if(timeLeft == 0)
            Game.getInstance().terminateObject(this);
    }


    public  void steppedOnMe(Jump p)
    {
        System.out.println("Olajba léptek: " + p.getPlayer().getName() + p.getTo());
        p.getPlayer().setPrevLocation(p.getPlayer().getLocation());
        p.getPlayer().setLocation(p.getTo());
        if(p.getPlayer().isRobot)
        {
            Human player = (Human)p.getPlayer();
            System.out.println("Duplázódott a speedje");
            if(player.getSpeed() > 8)
                player.setSpeed(player.getSpeed() / 2);
            player.setNumStep(player.getNumStep()+1);
            player.setSlideCount(player.getSpeed());
            player.setNumOil(player.getNumOil()+1);
        }
        else
        {
            timeLeft = 1;
        }


    }

    public String toString()
    {
        return "Oil "+timeLeft;
    }

}
