package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

//<>
public class Game
{
    private Map map;
    private ArrayList<Player> players;
    private ArrayList<Jump> jumps;
    private ArrayList<Oil> oilList;
    private static Game instance = null;
    private boolean isRunning;
    private int globalTime;
    private final int maxSpeed = 8;

    public static Game getInstance()
    {
        if(instance == null)
            instance = new Game();
        return instance;
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
        isRunning = true;
    }

    public  void update()
    {
        while (isRunning)
        {

            for(Player player : players)
            {
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
            collisionTest();
            map.draw(players);
            for(Player p : players)
            {
                if(p.isDead() && p.isRobot()) {
                    isRunning = false;
                    break;
                }
            }
            globalTime++;/* új kör #yolo*/
            oilUpdate();
        }
        for (Player p : players)
            if(!p.isDead() && p.isRobot)
                System.out.println(p.getName() +" nyert! lépésszáma: "+((Human)p).getNumStep());
    }

    public void WallELauncher()
    {


    }

    public int getTime()
    {
        return globalTime;
    }

    private void collisionTest()
    {
        ArrayList<Jump> actualJumps = new ArrayList<Jump>();
        for (Jump jump : jumps)
            if(jump.getTime()+jump.getPlayer().getSpeed() == globalTime)
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
                            actualJumps.get(i).getPlayer().kill();
                            jumps.remove(actualJumps.get(i));
                            actualJumps.remove(i);
                            if(isAnybodyThere(actualJumps.get(j).getTo()) == null)
                                executeJump(actualJumps.get(j));
                        }
                        else
                        {
                            actualJumps.get(j).getPlayer().kill();
                            jumps.remove(actualJumps.get(j));
                            actualJumps.remove(j);
                            if(isAnybodyThere(actualJumps.get(i).getTo()) == null)
                                executeJump(actualJumps.get(i));
                        }
                    }
                    else if((!actualJumps.get(i).getPlayer().isRobot() && actualJumps.get(j).getPlayer().isRobot()))
                    {
                        if(isAnybodyThere(actualJumps.get(j).getTo()) == null)
                            executeJump(actualJumps.get(j));
                        actualJumps.get(i).getPlayer().kill();
                        jumps.remove(actualJumps.get(i));
                        actualJumps.remove(i);
                    }
                    else if((actualJumps.get(i).getPlayer().isRobot() && !actualJumps.get(j).getPlayer().isRobot()))
                    {
                        if(isAnybodyThere(actualJumps.get(i).getTo()) == null)
                            executeJump(actualJumps.get(i));
                        actualJumps.get(j).getPlayer().kill();
                        jumps.remove(actualJumps.get(j));
                        actualJumps.remove(j);
                    }
                    else
                    {
                        if(isAnybodyThere(actualJumps.get(i).getTo()) == null)
                            executeJump(jumps.get(i));
                        Jump jmp = jumps.get(j);
                        Point to = new Point(jmp.getPlayer().getPrevLocation());
                        Jump tempJump = new Jump(jmp.getComponent(),Game.getInstance().getTime(),jmp.getPlayer(),to);

                        if(isAnybodyThere(tempJump.getTo()) == null)
                            executeJump(tempJump);
                        jumps.remove(actualJumps.get(j));
                        actualJumps.remove(j);
                    }

                }
        for(Player p : players)
        {
            for (Jump j : actualJumps)
            {
                if(p.location.equals(j.getTo()))
                {
                    if(p.isRobot() && j.getPlayer().isRobot())
                    {
                        p.kill();
                        executeJump(j);
                    }
                    else if(p.isRobot() && !j.getPlayer().isRobot())
                    {
                        Point to = new Point(j.getPlayer().getPrevLocation());
                        Jump tempJump = new Jump(j.getComponent(),Game.getInstance().getTime(),j.getPlayer(),to);
                        executeJump(tempJump);
                        jumps.remove(j);
                    }
                    else if(!p.isRobot() && j.getPlayer().isRobot())
                    {
                        p.kill();
                        executeJump(j);
                    }
                    else
                    {
                        Point to = new Point(j.getPlayer().getPrevLocation());
                        Jump tempJump = new Jump(j.getComponent(),Game.getInstance().getTime(),j.getPlayer(),to);
                        executeJump(tempJump);
                        jumps.remove(j);
                    }
                }
            }
        }
        for (Jump j : actualJumps)
            executeJump(j);
    }

    private void removeDeadWallEs()
    {
        for(Player p : players)
            if(!p.isRobot() && p.isDead())
                players.remove(p);
    }

    private Player isAnybodyThere(Point location)
    {
        for (Player p : players)
        {
            if(p.location.equals(location))
                return p;
        }
        return null;
    }

    public void oilUpdate()
    {
        for (Oil o : oilList)
            o.tick();
    }

    public void terminateObject(Component obj)
    {
        map.setComponent(obj.location,new Ground(obj.location));
        for (Oil o : oilList)
            if(o.location.equals(obj.location))
                oilList.remove(o);
    }

    public  void createGame(int level)
    {
        map.load(level);
        players.add(new Human("Player 1"));
        players.add(new Human("Player 2"));
        globalTime = 0;
        update();
    }

    private void executeJump(Jump j)
    {
        Component currentCmp = map.getComponent(j.getTo());
        Point whereToPutComponent = new Point(j.getPlayer().getLocation());
        if (currentCmp.getStepable())
            currentCmp.steppedOnMe(j);
        if(j.getComponent() != null)
            map.setComponent(whereToPutComponent,j.getComponent());
        else
            map.setComponent(whereToPutComponent,new Ground(whereToPutComponent));

        jumps.remove(j);
    }

    public ArrayList<Oil> getOilList()
    {
        return oilList;
    }

}
