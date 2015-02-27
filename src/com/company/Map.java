package com.company;


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

    private void load()
    {

    }
    private void setComponent(Component c)
    {
        
    }

    private Component getComponent(Point p)
    {
        return map.get(p);
    }
}
