package com.company;

public class Wall extends Component
{
    public Wall()
    {
        this.stepable = false;
    }

    public  void steppedOnMe(Player p)
    {

    }

    public  boolean getStepable()
    {
        return this.stepable;
    }
}
