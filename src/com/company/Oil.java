package com.company;


public class Oil extends Component
{

    public Oil()
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
