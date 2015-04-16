package com.company;


import javafx.geometry.Pos;

import java.awt.*;
import java.util.ArrayList;

public class Map
{
    private java.util.Map<Point,Component> map;

    public Map()
    {
        map = new java.util.HashMap<Point, Component>();
    }

    public void load(int level)
    {
        for (int i = 0;i<=30;i++)
            for(int j = 0;j<=30;j++)
                map.put(new Point(i,j),new Ground(new Point(i,j)));

    }
    public void setComponent(Point p,Component c)
    {
        map.put(p,c);
    }


    public Component getComponent(Point p)
    {
        if (p.x < 0 || p.y < 0)
            return new Hole(p);
        return map.get(p);
    }

    public void draw(ArrayList<Player> players)
    {
        boolean foundP = false;
        for (int i = 0;i<=30;i++) {
            for (int j = 0; j <= 30; j++) {
                for (Player p : players)
                {
                    if(p.location.x == j && p.location.y == i && !p.isDead())
                    {
                        if(p.isRobot())
                        {
                            System.out.print(((Human)p).getID());
                        }
                        else
                        {
                            System.out.print("T");
                        }
                        foundP = true;
                    }
                }
                if(!foundP)
                {
                    String name = getComponent(new Point(j,i)).getClass().getName();
                    if(name.equals(Ground.class.getName()))
                        System.out.print("_");
                    else if(name.equals(Glue.class.getName()))
                        System.out.print("G");
                    else if(name.equals(Oil.class.getName()))
                        System.out.print("O");
                }
                foundP = false;

            }
            System.out.println();
        }
        System.out.println();
    }
}
