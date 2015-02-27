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
    protected Point prevLocation;
    protected int numStep;

    public abstract Step step(Point newPoint);
    public abstract boolean changeDirection();

    public void  setPrevStep(Point prevLocation) { this.prevLocation = prevLocation; }

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

    public  void setSpeed(int speed)
    {
        this.speed = speed;
    }

    public void setNumSpeed(int numStep){ this.numStep = numStep; }

    public boolean isDead()
    {
        return this.isDead;
    }

    public int getNumOil()
    {
        return this.numOil;
    }

    public void kill()
    {
        this.isDead = true;
    }

}
