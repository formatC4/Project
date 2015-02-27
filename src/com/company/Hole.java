package com.company;


public class Hole extends Component
{
    public Hole()
    {
        this.stepable = true;
    }

    public  void steppedOnMe(Player p)
    {

    }

    public  boolean getStepable()
    {
        return this.stepable;
    }
}
