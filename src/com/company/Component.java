package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * A komponens osztályból származnak az egyes pályaelemek
 * Ő felel azokért a metódusokért,tulajdonságokért melyek mindegyik szármaoztt osztályban közösek
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

    public Image getIcon() {
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
