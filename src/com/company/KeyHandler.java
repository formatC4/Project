package com.company;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 * A KeyHandler osztály figyeli a billentyűnyomásokat, és azokat pufferekben eltárolja későbbi használatra
 */
public class KeyHandler implements KeyListener {

    char puffer[] = new char[4];

    public KeyHandler()
    {
        for (int i = 0;i <4;i++)
            puffer[i] = 'x';
    }

    public char getPuffer(int idx)
    {
        char t = puffer[idx];
        return t;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * A bemenetről kapott karaktereket a nekik megfelelő pufferekbe rakja, attól függően hogy az adott karakter melyik játékoshoz tartozik
     */
    @Override
    public void keyPressed(KeyEvent e) {

        char c = e.getKeyChar();
        if(c == 'a' || c == 'w' || c == 's' || c == 'd')
        {
            puffer[0] = c;
        }
        else if(c == 'k' || c == 'i' || c == 'j' || c == 'l')
        {
            switch (c)
            {
                case 'k': puffer[2] = 's';
                    break;
                case 'j': puffer[2] = 'a';
                    break;
                case 'i': puffer[2] = 'w';
                    break;
                case 'l': puffer[2] = 'd';
                    break;
            }
        }
        else if(c == 'q' || c == 'e')
        {
            puffer[1] = c;
        }
        else if(c == 'u' || c == 'o')
        {
            switch (c)
            {
                case 'u': puffer[3] = 'q';
                    break;
                case 'o': puffer[3] = 'e';
                    break;
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            Controller.getInstance().Pause();
        }

        char c = e.getKeyChar();
        if(c == 'a' || c == 'w' || c == 's' || c == 'd')
        {
            if(puffer[0] == c)
                puffer[0] = 'x';
        }
        else if(c == 'k' || c == 'i' || c == 'j' || c == 'l')
        {
            switch (c)
            {
                case 'k':
                    if(puffer[2] == 's')
                        puffer[2] = 'x';
                    break;
                case 'j':
                    if(puffer[2] == 'a')
                        puffer[2] = 'x';
                    break;
                case 'i':
                    if(puffer[2] == 'w')
                        puffer[2] = 'x';
                    break;
                case 'l':
                    if(puffer[2] == 'd')
                        puffer[2] = 'x';
                    break;
            }
        }
        else if(c == 'q' || c == 'e')
        {
            if(puffer[1] == c)
                puffer[1] = 'x';
        }
        else if(c == 'u' || c == 'o')
        {
            switch (c)
            {
                case 'u':
                    if(puffer[3] == 'q')
                        puffer[3] = 'x';
                    break;
                case 'o':
                    if(puffer[3] == 'e')
                        puffer[3] = 'x';
                    break;
            }
        }

    }
}
