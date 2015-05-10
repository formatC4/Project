package com.company;

import java.awt.*;

/**
 * Ugrás osztály - tárolja az ugrás részleteti: | Ki ugrott | mit rakna le | mikor ugrik | hova történne az ugrás
 */
public class Jump
{
    private Component component;
    private int time;
    private Player player;
    private Point to;

    public Point getTo() {
        return to;
    }

    public Player getPlayer() {
        return player;
    }

    public int getTime() {
        return time;
    }

    public Component getComponent() {
        return component;
    }


    public Jump(Component component,int time,Player player,Point to)
    {
        this.component = component;
        this.time = time;
        this.player = player;
        this.to = to;
    }

    public void setComponent(Component cmp)
    {
        this.component = cmp;
    }


    public String toString()
    {
        return player.getName()+"from: "+player.getLocation()+" to:"+to+" "+component+" "+time;
    }

}
