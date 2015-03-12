package com.company;

public class Wall extends Component
{
    public Wall()
    {
        this.stepable = false;
    }

    public  void steppedOnMe(Step p)
    {
        Human player = (Human)p.getPlayer();
        System.out.println("Falra lépnék, de nem tudok: " + player.getName() + p.getTo());
    }

}
