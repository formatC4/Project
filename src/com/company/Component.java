package com.company;

import java.awt.*;

/**
 * 
 */
public abstract class Component
{
    protected boolean stepable;
    protected Image icon;
    protected Point location;


    public abstract void steppedOnMe(Jump step);

    public  boolean getStepable(){
        return this.stepable;
    }

    public String toString()
    {
        return "";
    }
}
