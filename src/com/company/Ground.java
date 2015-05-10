package com.company;


import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

/**
 * Talaj pályaelem
 */
public class Ground extends Component
{
    public Ground(Point location)
    {
        this.location = location;
        this.stepable = true;
    }

    /**
     *
     * Lépészám növelése
     */
    public  void steppedOnMe(Jump p)
    {
        System.out.println("Új pozíció: " + p.getPlayer().getName() + p.getTo());
        p.getPlayer().setPrevLocation(p.getPlayer().getLocation());
        p.getPlayer().setLocation(p.getTo());
        if(p.getPlayer().isRobot())
        {
            Human player = (Human)p.getPlayer();
            player.setNumStep(player.getNumStep()+1);
        }

        try {

            this.icon = ImageIO.read(new File("kepek/ground.png"));
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public String toString()
    {
        return "Ground";
    }

}
