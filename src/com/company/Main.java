package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;
/*EZ ITT EGY KOMMENT VIKTOR TÖRÖLD KI */
public class Main {

    public static void main(String[] args) {
        new Main();
    }

    public Main()
    {
        Game game = new Game();
        System.out.println("Milyen nehézségű játékot szeretnél?(1-3)");

        Scanner sc = new Scanner(System.in);
        int lvl = sc.nextInt();
        game.createGame(lvl);
    }

}
