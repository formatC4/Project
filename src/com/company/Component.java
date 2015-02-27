package com.company;

import java.awt.*;


public abstract class Component
{
    protected boolean stepable;
    protected Image icon;

    public abstract void steppedOnMe(Player p);
    public abstract boolean getStepable();
}
