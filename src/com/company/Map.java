package com.company;


import javafx.geometry.Pos;

import java.awt.*;
import java.util.Date;

public class Map
{
    private java.util.Map<Point,Component> map;
    private Date time;

    public Map()
    {
        map = new java.util.HashMap<Point, Component>();
    }

    public void load(int level)
    {
        for (int i = 0;i<=30;i++)
            for(int j = 0;j<=30;j++)
                map.put(new Point(i,j),new Ground());

    }
    public void setComponent(Point p,Component c)
    {
        map.put(p,c);
    }

    public void setTime(Date time)
    {
        this.time = time;
    }

    public Date getTime()
    {
        return this.time;
    }

    public Component getComponent(Point p)
    {
        return map.get(p);
    }

    public void draw(Point p1,Point p2)
    {
        for (int i = 0;i<=30;i++) {
            for (int j = 0; j <= 30; j++) {
                if(p1.x == j && p1.y == i)
                    System.out.print("1");
                else if(p2.x == j && p2.y == i)
                    System.out.print("2");
                else{

                    String name = getComponent(new Point(j,i)).getClass().getName();
                    if(name.equals(Ground.class.getName()))
                        System.out.print("_");
                    else if(name.equals(Glue.class.getName()))
                        System.out.print("G");
                    else if(name.equals(Oil.class.getName()))
                        System.out.print("O");
                }
            }
            System.out.println();
        }
    }
}
