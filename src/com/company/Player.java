package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Játékos ősosztály biztosítja a megfelelő tulajdonságot és setter/gettereket a Human örökös számára
 */
public abstract class Player
{
    protected Point location;
    protected BufferedImage icon;
    protected boolean isDead;
    protected int speed;
    protected boolean isRobot;
    protected Point prevLocation;
    protected String name;


    public abstract Jump step();



    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public Image getIcon() {
        return icon;
    }

    public void setIcon(BufferedImage icon) {
        this.icon = icon;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }



    public Point getPrevLocation() {
        return prevLocation;
    }

    public void setPrevLocation(Point prevLocation) {
        this.prevLocation = prevLocation;
    }



    public boolean isDead()
    {
        return this.isDead;
    }
    public boolean isRobot() {return this.isRobot;}

    public void kill()
    {
        this.isDead = true;
        Game.getInstance().terminatePlayer(this);
    }

    public String getName()
    {
        return this.name;
    }
}
