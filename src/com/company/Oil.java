package com.company;


import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

/**
 * Az olaj pályaelem
 */
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

    /**
     * Az olaj életciklusát csökkenti
     */
    public void tick()
    {
        timeLeft--;
        if(timeLeft == 0)
            Game.getInstance().terminateObject(this);
    }

    /**
     * Az olajralépő játékos sebessége duplázódik, és további adminisztratív feladatok
     */
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
            if(player.getNumOil() <3 )
                player.setNumOil(player.getNumOil()+1);

            if(player.getID() == 0)
            {
                View.getInstance().setGlue1("Oil: "+player.getNumOil());
                View.getInstance().setSpeed1("Speed: "+player.getSpeed());
            }
            else
            {
                View.getInstance().setGlue2("Oil: "+player.getNumOil());
                View.getInstance().setSpeed2("Speed: "+player.getSpeed());
            }

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
