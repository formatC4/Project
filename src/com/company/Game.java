package com.company;


import java.awt.*;
import java.util.ArrayList;

/**
 * A Game osztály a játék fő logikája, felel az ütközésekért, a játékosok és tereptárgyak nyilvántartásáért és életciklusáért
 */
public class Game
{
    private Map map;
    private ArrayList<Player> players;
    private ArrayList<Jump> jumps;
    private ArrayList<Oil> oilList;
    private static Game instance = null;
    private int globalTime;
    private final int maxSpeed = 8;

    public static Game getInstance()
    {
        if(instance == null)
            instance = new Game();
        return instance;
    }


    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Map getMap() {
        return map;
    }

    public ArrayList<Jump> getJumps() {
        return jumps;
    }


    public Game()
    {
        init();
    }

    public void init()
    {
        players = new ArrayList<Player>();
        jumps = new ArrayList<Jump>();
        oilList = new ArrayList<Oil>();
        map = new Map();
    }
    //TODO véglegesből kivenni
    public void debug()
    {
        System.out.println("--------------------------------------------------");
        System.out.println("gt: " + globalTime);
        System.out.println("LÉPÉSEK SZÁMA: " + jumps.size());
        for (Jump j : jumps)
            System.out.println(j);
        System.out.println("PLAYEREK SZAMA: " + players.size());
        for (Player p:players)
            System.out.println(p);

        System.out.println("OILOK SZAMA: " + oilList.size());
        for (Oil o:oilList)
            System.out.println(o);
        System.out.println("--------------------------------------------------");

    }

    /**
     * A függvény lépteti a robotokat "körönként", illetve menedzseli az olajok életciklusát
     */
    public  void update()
    {
            //debug();
            for(int i=0;i<players.size();i++)
            {
                Player player = players.get(i);
                boolean hasJump = false;
                for (Jump j : jumps)
                    if(player == j.getPlayer())
                    {
                        hasJump = true;
                        break;
                    }
                if(!hasJump)
                {
                    Jump currentJump = player.step();
                    if(currentJump != null)
                        jumps.add(currentJump);
                }
            }
            //debug();
            collisionTest();
           // debug();
            //map.draw(players);
            oilUpdate();


    }

    /**
     * Elhelyez a pályán két takarító robotot
     */
    public void WallELauncher()
    {
            WallE w1 = new WallE("Takker1");
            WallE w2 = new WallE("Takker2");
            w1.setLocation(new Point(6,6));
            w2.setLocation(new Point(23,23));
            players.add(w1);
            players.add(w2);
    }

    public int getTime()
    {
        return globalTime;
    }

    public void setGlobalTime(int t){  if(this.globalTime < t) this.globalTime = t;}

    /**
     * A metódus felel az összes előforduló robot ütközés lebononyolításáért
     * Robot - Robot | Robot - Kisrobot | Kisrobot - Robot | Kisrobot - Kisrobot
     */
    private void collisionTest()
    {
        ArrayList<Jump> actualJumps = new ArrayList<Jump>();
        for (Jump jump : jumps)
            if(jump.getTime()+jump.getPlayer().getSpeed() <  globalTime)
                actualJumps.add(jump);
        if(actualJumps.isEmpty())
            return;

        for(int i = 0; i<actualJumps.size();i++)
            for(int j = i;j<actualJumps.size();j++)
                if(i != j && actualJumps.get(i).getTo().equals(actualJumps.get(j).getTo()))
                {
                    if(actualJumps.get(i).getPlayer().isRobot() && actualJumps.get(j).getPlayer().isRobot())
                    {
                        if(maxSpeed-actualJumps.get(i).getPlayer().getSpeed() < maxSpeed-actualJumps.get(j).getPlayer().getSpeed())
                        {
                            System.out.println("Robot-robottal ütközés "+actualJumps.get(i).getPlayer().getName()+"-ra "+actualJumps.get(j).getPlayer().getName());
                            actualJumps.get(i).getPlayer().kill();
                            jumps.remove(actualJumps.get(i));
                            actualJumps.remove(i);
                            if(isAnybodyThere(actualJumps.get(j).getTo()) == null)
                                executeJump(actualJumps.get(j));
                        }
                        else
                        {
                            System.out.println("Robot-robottal ütközés "+actualJumps.get(j).getPlayer().getName()+"-ra "+actualJumps.get(i).getPlayer().getName());

                            actualJumps.get(j).getPlayer().kill();
                            jumps.remove(actualJumps.get(j));
                            actualJumps.remove(j);
                            if(isAnybodyThere(actualJumps.get(i).getTo()) == null)
                                executeJump(actualJumps.get(i));
                        }
                    }
                    else if((!actualJumps.get(i).getPlayer().isRobot() && actualJumps.get(j).getPlayer().isRobot()))
                    {
                        System.out.println("Takker-robottal ütközés "+actualJumps.get(i).getPlayer().getName()+"-ra "+actualJumps.get(j).getPlayer().getName());

                        if(isAnybodyThere(actualJumps.get(j).getTo()) == null)
                            executeJump(actualJumps.get(j));
                        actualJumps.get(i).getPlayer().kill();
                        jumps.remove(actualJumps.get(i));
                        actualJumps.remove(i);
                    }
                    else if((actualJumps.get(i).getPlayer().isRobot() && !actualJumps.get(j).getPlayer().isRobot()))
                    {
                        System.out.println("Takker-robottal ütközés "+actualJumps.get(j).getPlayer().getName()+"-ra "+actualJumps.get(i).getPlayer().getName());

                        if(isAnybodyThere(actualJumps.get(i).getTo()) == null)
                            executeJump(actualJumps.get(i));
                        actualJumps.get(j).getPlayer().kill();
                        map.setComponent(actualJumps.get(j).getPlayer().getLocation(),new Oil(actualJumps.get(j).getPlayer().getLocation()));
                        jumps.remove(actualJumps.get(j));
                        actualJumps.remove(j);
                    }
                    else if((!actualJumps.get(i).getPlayer().isRobot() && !actualJumps.get(j).getPlayer().isRobot()))
                    {

                        if(jumps.size() != 0) {
                            System.out.println("----"+jumps.toString());
                            System.out.println("----"+i+" "+j);
                            System.out.println("Takker-takkerral ütközés "+actualJumps.get(i).getPlayer().getName()+"-ra "+actualJumps.get(j).getPlayer().getName());
                            Jump jmp = jumps.get(j);
                            if (isAnybodyThere(actualJumps.get(i).getTo()) == null)
                                executeJump(jumps.get(i));
                            Point to = new Point(jmp.getPlayer().getPrevLocation());
                            Jump tempJump = new Jump(jmp.getComponent(), Game.getInstance().getTime(), jmp.getPlayer(), to);

                            if (isAnybodyThere(tempJump.getTo()) == null)
                                executeJump(tempJump);
                            jumps.remove(actualJumps.get(j));
                            actualJumps.remove(j);
                        }
                    }

                }
        for(int i=0;i<players.size();i++)
        {
            Player p = players.get(i);
            for (int l=0;l<jumps.size();l++)
            {
                Jump j = jumps.get(l);
                if(p.location.equals(j.getTo()))
                {
                    if(p.isRobot() && j.getPlayer().isRobot())
                    {
                        System.out.println("robot-robotra ugrik " + players.get(i).getName() + "-ra " + jumps.get(l).getPlayer().getName());
                        p.kill();
                        executeJump(j);
                        break;
                    }
                    else if(p.isRobot() && !j.getPlayer().isRobot())
                    {
                        System.out.println("takker-robotra ugrik "+players.get(i).getName()+"-ra "+jumps.get(l).getPlayer().getName());

                        Point to = new Point(j.getPlayer().getPrevLocation());
                        Jump tempJump = new Jump(j.getComponent(),Game.getInstance().getTime(),j.getPlayer(),to);
                        executeJump(tempJump);
                        jumps.remove(j);
                        break;
                    }
                    else if(!p.isRobot() && j.getPlayer().isRobot())
                    {
                        System.out.println("robot-takkerre ugrik "+players.get(i).getName()+"-ra "+jumps.get(l).getPlayer().getName());

                        p.kill();
                        executeJump(j);
                        Oil o = new Oil(p.getLocation());
                        this.oilList.add(o);
                        map.setComponent(p.getLocation(),o);
                        break;
                    }
                    else
                    {
                        System.out.println("takker-takkerre ugrik "+players.get(i).getName()+"-ra "+jumps.get(l).getPlayer().getName());
                        jumps.remove(j);
                    }
                }
            }
        }
        for (Jump j : actualJumps)
            executeJump(j);
       // System.out.println("#######################################################");
    }

    /**
     *
     * Megnézzük, hogy az adott pozíción tartózkodik-e valaki
     */
    private Player isAnybodyThere(Point location)
    {
        for (Player p : players)
        {
            if(p.location.equals(location))
                return p;
        }
        return null;
    }

    /**
     * Csökkenti az oldaj életciklusát
     */
    public void oilUpdate()
    {
        if(!oilList.isEmpty())
            for(int i = 0;i<oilList.size();i++)
                 oilList.get(i).tick();
    }

    /**
     * Törli az adott robotot a pályáról
     */
    public void terminatePlayer(Player p)
    {
        if(!players.isEmpty()) {
            for(int i = 0;i<players.size();i++)
                if (players.get(i).getName().equals(p.getName())) {
                    players.remove(players.get(i));
                    break;
                }
        }
    }

    /**
     * Törli az adott pályaelemet a pályáról
     */
    public void terminateObject(Component obj)
    {
        map.setComponent(obj.location, new Ground(obj.location));
        if(!oilList.isEmpty()) {
            for(int i = 0;i<oilList.size();i++)
                if (oilList.get(i).location.equals(obj.location)) {
                    oilList.remove(oilList.get(i));
                    break;
                }
        }
    }

    /**
     * Játék inicializálása, Játékos robotok elhelyezése a pályán
     * @param level: nehézség paramétere
     */
    public  void createGame(int level)
    {
        map.load(level);
        players.add(new Human("Player 1"));
        players.add(new Human("Player 2"));
        globalTime = 0;
    }

    /**
     * Ugrás végrehajtása, vele járó adminisztrációk elvégzése
     */
    private void executeJump(Jump j)
    {
        Component currentCmp = map.getComponent(j.getTo());
        Point whereToPutComponent = new Point(j.getPlayer().getLocation());
        if (currentCmp.getStepable())
            currentCmp.steppedOnMe(j);
        if(j.getComponent() != null)
            map.setComponent(whereToPutComponent,j.getComponent());

        jumps.remove(j);
    }

    public ArrayList<Oil> getOilList() { return oilList;}
    public void addOil(Oil o) {this.oilList.add(o);}
}
