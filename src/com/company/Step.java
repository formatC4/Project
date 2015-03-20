package com.company;

import java.awt.*;
import java.util.Date;

/**
 * A Step osztályban regisztráljuk az egyes játékosok következő lépését és használni kívánt pályaelemét
 */
public class Step
{
    private Component component;
    private Date time;
    private Player player;
    private Point to;

    public Point getTo() {
        return to;
    }

    public Player getPlayer() {
        return player;
    }

    public Date getTime() {
        return time;
    }

    public Component getComponent() {
        return component;
    }


    public Step(Component component,Date time,Player player,Point to)
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

}
