package l0raxeo.arki.engine.ui;

import l0raxeo.arki.engine.ui.components.GuiComponent;
import org.joml.Vector2i;

import java.awt.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

public class GuiLayer
{

    public static final List<GuiComponent> guiComponents = new ArrayList<>();

    private static GuiComponent selectedComponent = null;

    private GuiLayer() {}

    public static void onMouseMove(Vector2i mousePos)
    {
        for (GuiComponent c : guiComponents)
        {
            c.hovering = c.bounds.contains(mousePos.x, mousePos.y);
            c.update();
        }
    }

    public static void onMouseRelease()
    {
        for (GuiComponent c : guiComponents)
            if (c.hovering) c.onClick();
    }

    public static void render(Graphics g)
    {
        for (GuiComponent c : guiComponents)
            c.render(g);
    }

    /**
     * Clears GUI Layer of all contents {@link GuiComponent}
     */
    public static void clear()
    {
        guiComponents.clear();
    }

    public static void addGuiComponent(GuiComponent c)
    {
        guiComponents.add(c);
    }

    public static void removeGuiComponent(GuiComponent c)
    {
        guiComponents.remove(c);
    }

    public static GuiComponent getGuiComponent(String name)
    {
        for (GuiComponent c : guiComponents)
        {
            if (c.name.equals(name))
                return c;
        }

        return null;
    }

    public static void selectComponent(GuiComponent component)
    {
        if (selectedComponent != null)
            selectedComponent.selected = false;

        if (component != null)
            component.selected = true;

        selectedComponent = component;
    }

    public static GuiComponent getSelectedComponent()
    {
        return selectedComponent;
    }

}
