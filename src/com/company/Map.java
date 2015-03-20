package com.company;



import java.awt.*;
import java.util.Date;

/**
 * Az osztály biztosítja a pályakirajzolás és tárolás logikáját
 */
public class Map
{
    private java.util.Map<Point,Component> map;
    private Date time;

    /**
     * Konstruktor inicializálja a map adatstruktúrát
     */
    public Map()
    {
        map = new java.util.HashMap<Point, Component>();
    }

    /**
     * A függvény betölti a pályát a paraméterben kapott nehézség alapján
     * @param level - int
     */
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

    /**
     * Visszaad egy adott ponton lévő pályaelem
     * @param p - Point
     * @return Component
     */
    public Component getComponent(Point p)
    {
        return map.get(p);
    }

    /**
     * A prototípushoz kirajzolja a pályát
     * @param p1 - Point
     * @param p2 - Point
     */
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
