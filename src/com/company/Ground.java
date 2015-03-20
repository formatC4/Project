package com.company;

/**
 * A talan pályaelem osztálya
 */
public class Ground extends Component
{
    /**
     * A konstruktor beállít egy boolean-t, a talaj elem léphető
     */
    public Ground()
    {
        this.stepable = true;
    }

    /**
     * Beállítja a játékos pozícióját, növeli a lépésszámot.
     * @param p - Step
     */
    public  void steppedOnMe(Step p)
    {
        Human player = (Human)p.getPlayer();
        System.out.println("Új position: " + player.getName() + p.getTo());
        player.setPrevLocation(player.getLocation());
        player.setNumStep(player.getNumStep()+1);
        player.setLocation(p.getTo());

    }

}
