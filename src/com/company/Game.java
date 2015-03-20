package com.company;

import java.awt.*;
import java.util.ArrayList;

/**
 * A Game osztály a játék "karmestere", ő hozza létre a szükséges objektumokat, illetve vezényli le a köztük lévő interakciókat
 */
public class Game
{
    private Map map;
    private ArrayList<Player> players;
    private ArrayList<Step> steps;

    private boolean isRunning;


    /**
     * A Game osztály konstruktora, meghívja az inicializáló metódust
     */
    public Game()
    {
        init();
    }


    /**
     * Az init metódus inicializálja a szükséges adatstruktúrákat, alapértelmezett értéket állít be a használt ciklusváltozóknak
     */
    public void init()
    {
        players = new ArrayList<Player>();
        steps = new ArrayList<Step>();
        map = new Map();
        isRunning = true;
    }

    /**
     * Az update metódus ciklikusan halad végig a játékosokon és dolgozza fel a felhasználói input-ot és azok alapján koordinálja a játékot
     */
    public  void update()
    {
        while (isRunning)
        {
            for(Player player : players)
            {
                Step currentStep = player.step();
                if(currentStep == null && player.isDead())
                {
                    System.out.println(((Human)player).getName()+" veszített");
                    isRunning = false;
                    break;
                }
                Component currentCmp = map.getComponent(currentStep.getTo());
                Point whereToPutComponent = new Point(currentStep.getPlayer().getLocation());
                if (currentCmp.getStepable())
                    currentCmp.steppedOnMe(currentStep);
                if(currentStep.getComponent() != null)
                    map.setComponent(whereToPutComponent,currentStep.getComponent());
                else
                    map.setComponent(whereToPutComponent,new Ground());
                map.draw(players.get(0).getLocation(),players.get(1).getLocation());
            }
        }
    }

    /**
     * A függvény a felhasználó által kért játéknehézséget továbpasszolja a map load() metódusának, hogy kezdeményezze a kért nehézségű pálya betöltését.
     * Továbbá létrehozza a játékosokat, majd elindítja a játékciklust.
     * @param level - int
     */
    public  void createGame(int level)
    {
        map.load(level);
        players.add(new Human("Player 1"));
        players.add(new Human("Player 2"));
        update();
    }
}
