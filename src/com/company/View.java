package com.company;


import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.Point;
/**
 * A View osztály felel a játék ablakos megjelenítéséért, a kirajzolásért és annak frissítéséért
 */
public class View {

    JFrame frame;

    ArrayList<Jump> jumps;
    Map map;
    ArrayList<Player> players;

    JLabel globaTime;
    JLabel Player1;
    JLabel glue1;
    JLabel oil1;
    JLabel speed1;
    JLabel Player2;
    JLabel glue2;
    JLabel oil2;
    JLabel speed2;
    JLabel step1;
    JLabel step2;

    public void setSpeed2(String speed2) {
        this.speed2.setText(speed2);
    }

    public void setOil2(String oil2) {
        this.oil2.setText(oil2);
    }

    public void setGlue2(String glue2) {
        this.glue2.setText(glue2);
    }

    public void setSpeed1(String speed1) {
        this.speed1.setText(speed1);
    }

    public void setGlue1(String glue1) {
        this.glue1.setText(glue1);
    }

    public void setOil1(String oil1) {
        this.oil1.setText(oil1);
    }

    public void setGlobaTime(String globaTime) {
        this.globaTime.setText(globaTime);
    }

    public void setStep1(String step) {
        this.step1.setText(step);
    }

    public void setStep2(String step) {
        this.step2.setText(step);
    }

    private static View instance;

    public static View getInstance()
    {
        if(instance == null)
            instance = new View();
        return instance;
    }

    /**
     * Main Frame , Panelek, Labelek és a canvas létrehozása, beállítása
     */
    public void init(ArrayList<Jump> jumps, Map map,ArrayList<Player> players)
    {
        this.jumps = jumps;
        this.map = map;
        this.players = players;

        frame = new JFrame("Game");
        frame.addKeyListener(Controller.getInstance().getHandler());
        frame.setSize(new Dimension(730, 640));
        frame.setLayout(new BorderLayout());

        DrawPanel dp = new DrawPanel();
        dp.setSize(new Dimension(640,640));
        Font f = new Font("Helvetica",Font.BOLD,12);

        Box box = Box.createVerticalBox();
        globaTime = new JLabel("Global Time: 0");
        globaTime.setFont(f);
        Player1 = new JLabel("Player1");
        Player1.setFont(f);
        Player2 = new JLabel("Player2");
        Player2.setFont(f);
        oil1 = new JLabel("Oil: 3");
        oil2 = new JLabel("Oil: 3");
        speed1 = new JLabel("Speed: 8");
        speed2 = new JLabel("Speed: 8");
        glue1 = new JLabel("Glue: 3");
        glue2 = new JLabel("Glue: 3");
        step1 = new JLabel("Step: 0");
        step2 = new JLabel("Step: 0");

        JPanel controlPanel = new JPanel();
        box.add(globaTime);
        box.add(Player1);
        box.add(glue1);
        box.add(oil1);
        box.add(step1);
        box.add(speed1);
        box.add(Box.createRigidArea(new Dimension(0,10) ));
        box.add(Player2);
        box.add(glue2);
        box.add(oil2);
        box.add(speed2);
        box.add(step2);
        controlPanel.add(box);

        frame.add(dp, BorderLayout.CENTER);
        frame.add(controlPanel,BorderLayout.EAST);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    /**
     * A pálya tényleges kirajzolásárt felelő JPanel ami override-olja a paintComponent metódust
     */
    class DrawPanel extends JPanel{

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);


            for (int i = 0;i<=30;i++) {
                for (int j = 0; j <= 30; j++) {

                        Component cmp =  map.getComponent(new Point(j, i));
                        String name = cmp.getClass().getName();
                        if(name.equals(Ground.class.getName()))
                        {
                            g.drawImage(cmp.getIcon(),(int)cmp.getLocation().getX()*20,(int)cmp.getLocation().getY()*20,20,20,null);

                        }
                        else if(name.equals(Glue.class.getName()))
                        {
                            g.drawImage(cmp.getIcon(),(int)cmp.getLocation().getX()*20,(int)cmp.getLocation().getY()*20,20,20,null);
                        }
                        else if(name.equals(Oil.class.getName()))
                        {
                            g.drawImage(cmp.getIcon(),(int)cmp.getLocation().getX()*20,(int)cmp.getLocation().getY()*20,20,20,null);
                        }
                        else if(name.equals(Hole.class.getName()))
                        {
                            g.drawImage(cmp.getIcon(),(int)cmp.getLocation().getX()*20,(int)cmp.getLocation().getY()*20,20,20,null);
                        }
                        else if(name.equals(Wall.class.getName()))
                        {
                            g.drawImage(cmp.getIcon(),(int)cmp.getLocation().getX()*20,(int)cmp.getLocation().getY()*20,20,20,null);
                        }
                    }

            }
            for (Player p : players)
            {
                g.drawImage(p.getIcon(),(int)p.getLocation().getX()*20,(int)p.getLocation().getY()*20,20,20,null);
            }
        }
    }

    /**
     * A játék végének grafikus megjelenítése
     */
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


    /**
     * Az UI újrarajzolása
     */
    public  void Refresh()
    {
        frame.getContentPane().repaint();
    }
}
