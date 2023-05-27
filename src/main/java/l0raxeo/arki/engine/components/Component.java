package l0raxeo.arki.engine.components;

import l0raxeo.arki.engine.objects.GameObject;

import java.awt.*;

public abstract class Component
{

    // component class in general
    public static int ID_COUNTER = 0;
    // associated with individual components/objects
    private int uid = -1;

    public transient GameObject gameObject = null;

    public void start()
    {

    }

    public void update(double dt){}

    public void render(Graphics g) {}

    public void generateId()
    {
        if (this.uid == -1)
            this.uid = ID_COUNTER++;
    }

    public int uid()
    {
        return uid;
    }

    public void onDestroy()
    {

    }

    public void handlePacketArgs(String args) {}

    public static void checkAndUpdateIdCounter(int count)
    {
        if (count > ID_COUNTER)
            ID_COUNTER = count;
    }

}
