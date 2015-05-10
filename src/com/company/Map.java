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

        switch (level){
            case 1:
                for (int i = 0;i<=30;i++)
                {
                    if(i == 0 || i==30)
                        for(int j = 0;j<=30;j++)
                        {
                            map.put(new Point(i,j),new Wall(new Point(i,j)));
                        }
                    else
                    {
                        map.put(new Point(i,0),new Wall(new Point(i,0)));
                        map.put(new Point(i,30),new Wall(new Point(i,30)));
                    }
                }
                break;
            case 2:
            {
                for (int i = 13;i<=16;i++)
                {
                    map.put(new Point(i,13),new Hole(new Point(i,13)));
                    map.put(new Point(i,14),new Hole(new Point(i,14)));
                    map.put(new Point(i,15),new Hole(new Point(i,15)));
                    map.put(new Point(i,16),new Hole(new Point(i,16)));
                }
            }
        }



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
                    else if(name.equals(Hole.class.getName()))
                        System.out.print("H");
                    else if(name.equals(Wall.class.getName()))
                        System.out.print("W");
                }
                foundP = false;

            }
            System.out.println();
        }
        System.out.println();
    }
}
