package l0raxeo.arki.engine.input.keyboard;

import l0raxeo.arki.engine.ui.GuiLayer;
import l0raxeo.arki.engine.ui.components.GuiComponent;
import l0raxeo.arki.engine.ui.components.GuiKeyListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import static l0raxeo.arki.engine.input.keyboard.KeyState.*;

public final class KeyManager implements KeyListener
{

    /**
     * All registered keys in the program.
     */
    private static final ArrayList<Key> allKeys = new ArrayList<>();

    /**
     * Updates all keys and their states.
     */
    public void update()
    {
        for (Key k : allKeys)
        {
            k.setState();

            if (k.getState() == RELEASED)
                k.queueState(KeyState.IDLE);
            else if (k.getState() == PRESSED)
            {
                k.queueState(HELD);
            }
        }
    }

    // Both instances of each type of method do the
    // same thing, but the first one detects the key
    // associated with the key event's key code, and
    // the second one detects the key associated with
    // its character.
    /**
     * @return true if the specified key
     * associated with the key event is
     * being held.
     */
    public static boolean isHeld(int key)
    {
        for (Key k : allKeys)
        {
            if (k.getKeyCode() == key && k.getState() == HELD)
                return true;
        }

        return false;
    }

    public static boolean isHeld(char key)
    {
        for (Key k : allKeys)
        {
            if (k.getKeyChar() == key && k.getState() == HELD)
                return true;

            if (k.getKeyChar() == key && k.getState() == PRESSED)
                return true;
        }

        return false;
    }

    /**
     * @return true if the specified key
     * associated with the key event is
     * being pressed.
     */
    public static boolean onPress(int key)
    {
        for (Key k : allKeys)
        {
            if (k.getKeyCode() == key && k.getState() == PRESSED)
                return true;
        }

        return false;
    }

    public static boolean onPress(char key)
    {
        for (Key k : allKeys)
        {
            if (k.getKeyChar() == key && k.getState() == PRESSED)
                return true;
        }

        return false;
    }

    /**
     * @return true if the specified key
     * associated with the key event is
     * being released.
     */
    public static boolean onRelease(int key)
    {
        for (Key k : allKeys)
        {
            if (k.getKeyCode() == key && k.getState() == RELEASED)
                return true;
        }

        return false;
    }

    public static boolean onRelease(char key)
    {
        for (Key k : allKeys)
        {
            if (k.getKeyChar() == key && k.getState() == RELEASED)
                return true;
        }

        return false;
    }

    public static boolean hasInput()
    {
        for (Key key : allKeys)
        {
            if (key.getState() == PRESSED || key.getState() == RELEASED || key.getState() == HELD)
                return true;
        }

        return false;
    }

    public static boolean hasPressedInput()
    {
        for (Key key : allKeys)
        {
            if (key.getState() == PRESSED)
                return true;
        }

        return false;
    }

    // Implemented methods

    // Is invoked once key has gone down, and released
    @Override
    public void keyTyped(KeyEvent e) {}

    // Is invoked while key is held
    @Override
    public void keyPressed(KeyEvent e)
    {
        for (GuiComponent c : GuiLayer.guiComponents)
            if (c instanceof GuiKeyListener)
                ((GuiKeyListener) c).keyPress(e);

        for (Key k : allKeys)
        {
            if (k.getState() == HELD && k.getKeyCode() == e.getKeyCode())
                return;

            if (k.getKeyCode() == e.getKeyCode() && k.getState() == KeyState.IDLE)
            {
                k.queueState(PRESSED);
                return;
            }
        }

        allKeys.add(new Key(e.getKeyCode(), PRESSED, e.getKeyChar()));
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        for (Key k : allKeys)
        {
            if (k.getKeyCode() == e.getKeyCode())
            {
                k.queueState(RELEASED);
            }
        }
    }

}
