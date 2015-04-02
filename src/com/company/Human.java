package com.company;
import java.awt.*;
import java.util.Date;
import java.util.Scanner;


public class Human extends Player {

    private static int numberOfPlayers = 0;
    protected int numStep;
    protected int slideCount;
    protected int numGlue;
    protected int numOil;
    private int ID;

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
        ID = numberOfPlayers;
        numberOfPlayers++;
        this.isRobot = true;
    }
    public int getID(){return this.ID;}

    public int getNumStep() {
        return numStep;
    }

    public void setNumStep(int numStep) {
        this.numStep = numStep;
    }

    public int getNumOil() {
        return numOil;
    }

    public void setNumOil(int numOil) {
        this.numOil = numOil;
    }

    public int getNumGlue() {
        return numGlue;
    }

    public void setNumGlue(int numGlue) {
        this.numGlue = numGlue;
    }

    public int getSlideCount() {
        return slideCount;
    }

    public void setSlideCount(int slideCount) {
        this.slideCount = slideCount;
    }

    public  Jump step(){
        if(slideCount > 0){
            slideCount--;
            int x = location.x - prevLocation.x;
            int y = location.y - prevLocation.y;
            if( location.x+x < 0 ||location.y+y < 0 || location.x+x > 30 || location.y+y > 30 ) /*majd ha körberaktunk lyukkal a mapot törölhetjük*/
            {
                this.kill();
                return null;
            }
            return new Jump(null,Game.getInstance().getTime(),this,new Point(location.x+x,location.y+y));
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
            case 'O':
                if(numOil > 0) {comp = new Oil(to); this.numOil--; Game.getInstance().addOil((Oil)comp);
                }
                break;
            case 'R': if(numGlue > 0) {comp = new Glue(to); this.numGlue--;}
                break;
        }
        return new Jump(comp,Game.getInstance().getTime(),this,to);
    }



}
