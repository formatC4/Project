package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 
 */
public abstract class Component
{
    protected boolean stepable;



    protected BufferedImage icon;
    protected Point location;


    public abstract void steppedOnMe(Jump step);

    public  boolean getStepable(){
        return this.stepable;
    }

    public BufferedImage getIcon() {
        return icon;
    }

    public Point getLocation()
    {
        return location;
    }

    public String toString()
    {
        return "";
    }
}
