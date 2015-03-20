package com.company;

/**
 * A Lyuk pályaelem oszálya
 */
public class Hole extends Component
{
    /**
     *A konstruktor beállít egy booleant, a lyuk elem léphető.
     */
    public Hole(){ this.stepable = true; }

    /**
     * Növeli a lépészámot, majd "megöli" a játékost.
     * @param p - Step
     */
    public  void steppedOnMe(Step p)
    {
        Human player = (Human)p.getPlayer();
        System.out.println("Lyukba esett: " + player.getName() + p.getTo());
        player.setNumStep(player.getNumStep()+1);
        player.kill();
    }

}
