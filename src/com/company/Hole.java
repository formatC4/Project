package com.company;


public class Hole extends Component
{
    public Hole()
    {
        this.stepable = true;
    }

    public  void steppedOnMe(Step p)
    {
        Human player = (Human)p.getPlayer();
        System.out.println("Lyukba esett: " + player.getName() + p.getTo());
        player.setNumStep(player.getNumStep()+1);
        player.kill();
    }

}
