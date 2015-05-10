package com.company;


import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;


/**
 * Ragacs pályaelem
 */
public class Glue extends Component
{
    private int life;

    public Glue(Point location)
    {
        this.location = location;
        this.stepable = true;
        life = 2;
    }

    /**
     * Ragacsra lépés
     * Játékos sebességének felezése
     * Jelzés a View-nek a GUI frissítésről
     */
    public  void steppedOnMe(Jump p)
    {
        System.out.println("Ragacsra léptek: " + p.getPlayer().getName() + p.getTo());
        p.getPlayer().setPrevLocation(p.getPlayer().getLocation());
        p.getPlayer().setLocation(p.getTo());
        if (p.getPlayer().isRobot)
        {
            Human player = (Human)p.getPlayer();
            System.out.println("Feleződött a speedje");
            if(player.getSpeed()<8)
                player.setSpeed(player.getSpeed()*2);
            player.setNumStep(player.getNumStep()+1);
            if(player.getNumGlue() <3 )
            player.setNumGlue(player.getNumGlue()+1);

            if(player.getID() == 0)
            {
                View.getInstance().setGlue1("Glue: "+player.getNumGlue());
                View.getInstance().setSpeed1("Speed: "+player.getSpeed());
            }
            else
            {
                View.getInstance().setGlue2("Glue: "+player.getNumGlue());
                View.getInstance().setSpeed2("Speed: "+player.getSpeed());
            }

        }
        life--;
        if(life == 0) Game.getInstance().terminateObject(this);

        try {

            this.icon = ImageIO.read(new File("kepek/glue.png"));
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }

    public String toString()
    {
        return "Glue";
    }

}
