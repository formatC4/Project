package com.company;


public class Ground extends Component
{
    public Ground()
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
