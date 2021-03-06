package com.company;


import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;


/**
 * Takarító robot osztálya, felépítésében hasonlít a Human osztályra, azomban a lépés végrehajtása autonóm
 */
public class WallE extends Player
{

    public WallE(String name)
    {
        this.isRobot = false;
        setSpeed(8);
        this.name = name;
        try {
            this.icon = ImageIO.read(new File("kepek/walle.png"));
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Az útvonalválasztásért és lépésért felelő metődus
     */
    public  Jump step()
    {
        Oil target  = findNearestOil(Game.getInstance().getOilList());
        if (target  == null) {
            kill();
            return null;
        }

        Point to = new Point(location);

        if(target.location == location)
            return null;

        if(target.location.x - location.x != 0){
            to.x += (target.location.x - location.x)/Math.abs(target.location.x - location.x);
        }else  if (target.location.y - location.y != 0) {
                to.y += (target.location.y - location.y) / Math.abs(target.location.y - location.y);
            }


        return new Jump(null,Game.getInstance().getTime(),this,to);
    }

    private Oil findNearestOil(ArrayList<Oil> oils)
    {
        if (oils.isEmpty())
            return null;
        Oil nearest = oils.get(0);
        double dis = distance(nearest.location, location);
        for (Oil o : oils) {
            double distance = distance(o.location, location);
            if (distance < dis) {
                nearest = o;
                dis = distance;
            }
        }
        return nearest;
    }

    private double distance(Point p1,Point p2)
    {
        return Math.sqrt(Math.pow(p2.x-p1.x,2)+Math.pow(p2.y-p1.y,2));
    }

    @Override
    public String toString()
    {
        return this.name+" "+this.isRobot+" "+this.isDead+" loc: "+this.location+" prevLoc: "+this.prevLocation+" speed: "+speed;
    }
}
