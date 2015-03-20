package com.company;

import java.awt.*;

/**
 *
 * Ősosztály a robot által lerakható pályaelemek számára
 */
public abstract class Component
{
    protected boolean stepable;
    protected Image icon;

    /**
     * Abstract metódus, melyet a származtatott osztályok felülírnak. Ebben a metódusban reagálnak az egyes pályaelemek a játékossal való interakcióra
     * @param step - Step
     */
    public abstract void steppedOnMe(Step step);

    /**
     * Visszaad egy boolean-t attól függűen, hogy az adott pályaelemre rá lehet-e lépni.
     * @return boolean
     */
    public  boolean getStepable(){
        return this.stepable;
    }
}
