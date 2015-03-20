package com.company;

/**
 * Az Olaj pályaelem osztálya
 */
public class Oil extends Component
{
    /**
     * A konstruktor beállít egy boolean-t, az olaj elem léphető
     */
    public Oil()
    {
        this.stepable = true;
    }

    /**
     * Beállítja a játékos pozícióját, majd megkétszerezi a sebességét,letiltja adott számú lépéstől a sebesség függvényében (csúszás)
     * Továbbá menedzseli a játékos olaj tartalékát és növeli a lépésszámot
     * @param p - Step
     */
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
