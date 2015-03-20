package com.company;
import java.awt.*;
import java.util.Date;
import java.util.Scanner;

/**
 * A játékos leszármazott osztálya
 */
public class Human extends Player {

    private String name;
    private static int numberOfPlayers = 0;

    /**
     * A Konstruktor inicializálja a játékos tulajdonságait
     * @param name - String
     */
    public Human(String name)
    {
        this.name = name +" #"+ this.hashCode();
        this.setNumOil(3);
        this.setNumGlue(3);
        this.setSpeed(4);
        this.setNumStep(0);
        if(numberOfPlayers == 0)
            this.setLocation(new Point(0,0));
        else
            this.setLocation(new Point(30,30));
        numberOfPlayers++;
    }


    /**
     * Ez a metódust felel a játékos következő pozíciójának a kiszámolásáért a felhasználói input függvényében, illetve itt regisztrálódik a használni kívánt pályaelem.
     * A metódus visszaad egy teljesen beállított Step objektumot.
     * @return Step
     */
    public  Step step(){
        if(slideCount > 0){
            slideCount--;
            int x = location.x - prevLocation.x;
            int y = location.y - prevLocation.y;
            if( location.x+x < 0 ||location.y+y < 0 || location.x+x > 30 || location.y+y > 30 )
            {
                this.kill();
                return null;
            }
            return new Step(null,new Date(),this,new Point(location.x+x,location.y+y));
        }

        Scanner sc = new Scanner(System.in);
        System.out.println(name+" ("+location+") Merre lépnél? (le-S,fel-W,jobbra-D,balra-A");
        char dir = sc.next().toUpperCase().toCharArray()[0];
        System.out.println("Ha szeretnél letenni valamit: (olaj-O,ragacs-R,semmi-Bármi)");
        char cmp = sc.next().toUpperCase().toCharArray()[0];
        Point to = new Point(location);


        switch (dir){
            case 'W': if(location.y > 0)
                        to.y--;
                break;
            case 'S': if(location.y < 30)
                        to.y++;
                break;
            case 'A': if(location.x > 0)
                        to.x--;
                break;
            case 'D': if(location.x < 30)
                        to.x++;
                break;
        }
        Component comp = null;
        switch (cmp)
        {
            case 'O': if(numOil > 0) {comp = new Oil(); this.numOil--;}
                break;
            case 'R': if(numGlue > 0) {comp = new Glue(); this.numGlue--;}
                break;
        }
        return new Step(comp,new Date(),this,to);
    }

    public String getName()
    {
        return this.name;
    }

}
