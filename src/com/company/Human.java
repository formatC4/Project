package com.company;

import java.awt.*;
import java.util.Date;


public class Human extends Player {

    public Human()
    {

    }

    public  Step step(Point newPoint){
        return new Step(null,new Date(),this,newPoint);
    }
    public  boolean changeDirection(){
        return  true;
    }


}
