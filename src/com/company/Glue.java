package com.company;

/**
 * Ragacs pályaelem osztálya
 */
public class Glue extends Component
{
    /**
     * A konstruktor beállít egy boolean-t, a ragacs elem léphető
     */
    public Glue()
    {
        this.stepable = true;
    }

    /**
     * Beállítja a játékos pozícióját, majd megfelezi a sebességét, továbbá menedzseli a játékos ragacs tartalékát és növeli a lépésszámot
     * @param p - Step
     */
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
