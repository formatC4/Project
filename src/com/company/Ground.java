package com.company;


public class Ground extends Component
{
    public Ground()
    {
        this.stepable = true;
    }

    public  void steppedOnMe(Step p)
    {
        Human player = (Human)p.getPlayer();
        System.out.println("Új position: " + player.getName() + p.getTo());
        player.setPrevLocation(player.getLocation());
        player.setNumStep(player.getNumStep()+1);
        player.setLocation(p.getTo());

    }

}
