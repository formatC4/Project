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
        Human player = (Human)p.getPlayer();
        System.out.println("Új position: " + player.getName() + p.getTo());
        player.setPrevLocation(player.getLocation());
        player.setNumStep(player.getNumStep()+1);
        player.setLocation(p.getTo());

    }

}
