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

    public boolean isAlive(){ return true;}


    public abstract void steppedOnMe(Jump step);

    public  boolean getStepable(){
        return this.stepable;
    }
}
