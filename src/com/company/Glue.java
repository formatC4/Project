package com.company;


import java.awt.*;

public class Glue extends Component
{
    private int life;

    public Glue(Point location)
    {
        this.location = location;
        this.stepable = true;
        life = 4;
    }


    public  void steppedOnMe(Jump p)
    {
        if (p.getPlayer().isRobot)
        {
            Human player = (Human)p.getPlayer();
            System.out.println("Ragacsra léptek: " + player.getName() + p.getTo());
            player.setPrevLocation(player.getLocation());
            player.setLocation(p.getTo());
            System.out.println("Feleződött a speedje");
            player.setSpeed(player.getSpeed()/2);
            player.setNumStep(player.getNumStep()+1);
            player.setNumGlue(player.getNumGlue()+1);
        }
        life--;
        if(life == 0) Game.getInstance().terminateObject(this);
    }

}
