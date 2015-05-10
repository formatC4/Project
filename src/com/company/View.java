package com.company;


import javax.swing.*;
import java.awt.*;
import java.nio.DoubleBuffer;
import java.util.*;
import java.awt.Point;
/**
 * Created by nagypeter on 15. 05. 07..
 */
public class View {

    JFrame frame;

    ArrayList<Jump> jumps;
    Map map;
    ArrayList<Player> players;

    private static View instance;

    public static View getInstance()
    {
        if(instance == null)
            instance = new View();
        return instance;
    }

    public void init(ArrayList<Jump> jumps, Map map,ArrayList<Player> players)
    {
        this.jumps = jumps;
        this.map = map;
        this.players = players;

        frame = new JFrame("Game");
        frame.addKeyListener(Controller.getInstance().getHandler());
        frame.setSize(new Dimension(620,640));
        frame.setContentPane(new DrawPanel());

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }


    class DrawPanel extends JPanel{

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            boolean foundP = false;
            for (int i = 0;i<=30;i++) {
                for (int j = 0; j <= 30; j++) {

                    if(!foundP)
                    {
                        Component cmp =  map.getComponent(new Point(j, i));
                        String name = cmp.getClass().getName();
                        if(name.equals(Ground.class.getName()))
                        {
                            g.drawImage(cmp.getIcon(),(int)cmp.getLocation().getX()*20,(int)cmp.getLocation().getY()*20,20,20,null);

                            /*g.setColor(Color.green);
                            g.fillRect(j * 20, i * 20, 20, 20);*/

                        }
                        else if(name.equals(Glue.class.getName()))
                        {
                            g.setColor(Color.red);
                            g.fillRect(j * 20, i * 20, 20, 20);
                        }
                        else if(name.equals(Oil.class.getName()))
                        {
                            g.setColor(Color.black);
                            g.fillRect(j * 20, i * 20, 20, 20);
                        }
                        else if(name.equals(Hole.class.getName()))
                        {
                            g.setColor(Color.blue);
                            g.fillRect(j * 20, i * 20, 20, 20);
                        }
                        else if(name.equals(Wall.class.getName()))
                        {
                            g.setColor(Color.gray);
                            g.fillRect(j * 20, i * 20, 20, 20);
                        }
                    }

                    foundP = false;

                }
            }
            for (Player p : players)
            {
                g.drawImage(p.getIcon(),(int)p.getLocation().getX()*20,(int)p.getLocation().getY()*20,20,20,null);
            }
        }
    }

    public void GameOver()
    {
        Object[] options = {"Ok"};
        Player player = null;
        for (Player p : Game.getInstance().getPlayers())
        {
            if(!p.isDead() && p.isRobot) {
                player = p;
                break;
            }
        }

        JFrame frame = new JFrame();
        int n = JOptionPane.showOptionDialog(frame,
                player.getName()+" Győzött",
                "Game Over!",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
    }




    public  void Refresh()
    {
        frame.repaint();
    }
}
