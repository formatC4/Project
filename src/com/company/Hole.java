package com.company;


import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class Hole extends Component
{
    public Hole(Point location)
    {
        this.location = location;
        this.stepable = true;

        try {
            this.icon = ImageIO.read(new File("kepek/hole.png"));
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    public  void steppedOnMe(Jump p)
    {
        System.out.println("Lyukba esett: " + p.getPlayer().getName() + p.getTo());
        p.getPlayer().kill();
    }

    public String toString()
    {
        return "Hole";
    }
}
