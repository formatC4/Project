package com.company;


public class Oil extends Component
{

    public Oil()
    {
        this.stepable = true;
    }

    public  void steppedOnMe(Step p)
    {
        Human player = (Human)p.getPlayer();
        System.out.println("Olajba léptek: " + player.getName() + p.getTo());
        player.setPrevLocation(player.getLocation());
        player.setLocation(p.getTo());
        System.out.println("Duplázódott a speedje");
        player.setSpeed(player.getSpeed() * 2);
        player.setNumStep(player.getNumStep()+1);
        player.setSlideCount(player.getSpeed());
        player.setNumOil(player.getNumOil()+1);

    }

}
