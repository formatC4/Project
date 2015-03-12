package com.company;


public class Glue extends Component
{
    public Glue()
    {
        this.stepable = true;
    }

    public  void steppedOnMe(Step p)
    {
        Human player = (Human)p.getPlayer();
        System.out.println("Ragacsra léptek: " + player.getName() + p.getTo());
        player.setPrevLocation(player.getLocation());
        player.setLocation(p.getTo());
        System.out.println("Feleződött a speedje");
        player.setSpeed(player.getSpeed()/2);
        player.setNumStep(player.getNumStep()+1);
        player.setNumGlue(player.getNumGlue()+1);
    }

}
