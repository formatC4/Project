package com.company;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        new Main();
    }

    public Main()
    {

        Object[] options = {"Hard","Medium","Easy"};
        JFrame frame = new JFrame();
        int n = JOptionPane.showOptionDialog(frame,
                "Válassz nehézséget",
                "Nehezség választás!",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[2]);
        Game.getInstance().createGame(n);
        View.getInstance().init(Game.getInstance().getJumps(),Game.getInstance().getMap(),Game.getInstance().getPlayers());
        Controller.getInstance();

    }

}
