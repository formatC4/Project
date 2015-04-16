package com.company;

import java.awt.*;

public class Wall extends Component
{
    public Wall(Point location)
    {
        this.location = location;
        this.stepable = false;
    }
    public  void steppedOnMe(Jump p)
    {
        System.out.println("Falra lépnék, de nem tudok: " + p.getPlayer().getName() + p.getTo());
    }

    public String toString()
    {
        return "Wall";
    }
}
