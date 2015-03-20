package com.company;

import java.awt.*;

/**
 * Játékos ősosztály biztosítja a megfelelő tulajdonságot és setter/gettereket a Human örökös számára
 */
public abstract class Player
{
    protected Point location;
    protected Image icon;
    protected int numGlue;
    protected int numOil;
    protected boolean isDead;
    protected int speed;
    protected int slideCount;
    protected Point prevLocation;
    protected int numStep;

    public abstract Step step();

    public int getNumGlue() {
        return numGlue;
    }

    public void setNumGlue(int numGlue) {
        this.numGlue = numGlue;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public Image getIcon() {
        return icon;
    }

    public void setIcon(Image icon) {
        this.icon = icon;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSlideCount() {
        return slideCount;
    }

    public void setSlideCount(int slideCount) {
        this.slideCount = slideCount;
    }

    public Point getPrevLocation() {
        return prevLocation;
    }

    public void setPrevLocation(Point prevLocation) {
        this.prevLocation = prevLocation;
    }

    public int getNumStep() {
        return numStep;
    }

    public void setNumStep(int numStep) {
        this.numStep = numStep;
    }

    public int getNumOil() {
        return numOil;
    }

    public void setNumOil(int numOil) {
        this.numOil = numOil;
    }

    public boolean isDead()
    {
        return this.isDead;
    }


    public void kill()
    {
        this.isDead = true;
    }

}
