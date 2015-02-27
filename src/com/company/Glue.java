package com.company;


public class Glue extends Component
{
    public Glue()
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
