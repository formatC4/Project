package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

/**
 * Fal pályaelem
 */
public class Wall extends Component
{
    public Wall(Point location)
    {
        this.location = location;
        this.stepable = false;
        try {
            this.icon = ImageIO.read(new File("kepek/wall.png"));
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    /**
     *
     * A falra nem lehet rálépni, semmi sem törénik
     */
    public  void steppedOnMe(Jump p)
    {
        System.out.println("Falra lépnék, de nem tudok: " + p.getPlayer().getName() + p.getTo());
    }

    public String toString()
    {
        return "Wall";
    }
}
