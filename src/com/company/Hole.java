package com.company;


import java.awt.*;

public class Hole extends Component
{
    public Hole(Point location)
    {
        this.location = location;
        this.stepable = true;
    }
    public  void steppedOnMe(Jump p)
    {
        System.out.println("Lyukba esett: " + p.getPlayer().getName() + p.getTo());
        p.getPlayer().kill();
    }

}
