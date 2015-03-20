package com.company;

/**
 * A fal pályaelem oszálya
 */
public class Wall extends Component
{
    /**
     *A konstruktor beállít booleant, a fal nem elem léphető.
     */
    public Wall()
    {
        this.stepable = false;
    }

    /**
     *A lépés hatására nem történik semmi
     * @param p - Step
     */
    public  void steppedOnMe(Step p)
    {
        Human player = (Human)p.getPlayer();
        System.out.println("Falra lépnék, de nem tudok: " + player.getName() + p.getTo());
    }

}
