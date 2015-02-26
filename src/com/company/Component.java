package com.company;

import java.awt.*;

/**
 * Created by nagypeter on 15. 02. 26..
 */
public abstract class Component
{
    protected int role;
    protected Image icon;

    public abstract boolean steppedOnMe();
    public abstract boolean amISteppeble();
}
