package com.company;

import java.awt.*;

/**
 * 
 */
public abstract class Component
{
    protected boolean stepable;
    protected Image icon;

    public abstract void steppedOnMe(Step step);

    public  boolean getStepable(){
        return this.stepable;
    }
}
