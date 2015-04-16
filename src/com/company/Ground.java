package com.company;


import java.awt.*;

public class Ground extends Component
{
    public Ground(Point location)
    {
        this.location = location;
        this.stepable = true;
    }

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
    }

    public String toString()
    {
        return "Ground";
    }

}
