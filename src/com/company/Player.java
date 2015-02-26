package com.company;

import java.awt.*;

public abstract class Player
{
    protected Point location;
    protected Image icon; //majd példány contructorban setteljük
    protected int numGlue;
    protected int numOil;
    protected boolean isDead;
    protected int speed;
    protected int slideCount;
    protected Step prevStep;

    public abstract Step step();
    public abstract boolean changeDirection();

    public void  setPrevStep(Step s)
    {
        this.prevStep = s;
    }

    public void setLocation(Point p)
    {
        this.location = p;
    }

    public int getNumGlue()
    {
        return this.numGlue;
    }

    public int getSlideCount()
    {
        return this.slideCount;
    }

    public  int getSpeed()
    {
        return this.speed;
    }

    public  int setSpeed(int speed)
    {
        this.speed = speed;
    }


    public boolean isDead()
    {
        return this.isDead;
    }

    public int getNumOil()
    {
        return this.numOil;
    }

}
