package l0raxeo.arki.engine.ui;

import l0raxeo.arki.engine.ui.components.GuiComponent;
import org.joml.Vector2i;

import java.awt.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

public class GuiLayer
{

    private final List<GuiComponent> guiComponents = new ArrayList<>();
    private static GuiLayer instance = null;

    private GuiComponent selectedComponent = null;

    private GuiLayer() {}

    public void onMouseMove(Vector2i mousePos, Vector2i mouseMoved, Vector2i mouseDragged)
    {
        for (GuiComponent c : getGuiComponents())
        {
            c.hovering = c.bounds.contains(mousePos.x, mousePos.y);
            c.update();
        }
    }

    public void onMouseRelease(int xMouse, int yMouse)
    {
        try
        {
            for (GuiComponent c : getGuiComponents())
                if (c.hovering) c.onClick();
        }
        catch (ConcurrentModificationException ignore) {}
    }

    public void render(Graphics g)
    {
        try
        {
            for (GuiComponent c : getGuiComponents())
                c.render(g);
        }
        catch (ConcurrentModificationException ignore) {}
    }

    /**
     * Clears GUI Layer of all contents {@link GuiComponent}
     */
    public void clear()
    {
        guiComponents.clear();
    }

    public void addGuiComponent(GuiComponent c)
    {
        this.guiComponents.add(c);
    }

    public void removeGuiComponent(GuiComponent c)
    {
        this.guiComponents.remove(c);
    }

    public List<GuiComponent> getGuiComponents()
    {
        return this.guiComponents;
    }

    public GuiComponent getGuiComponent(String name)
    {
        for (GuiComponent c : getGuiComponents())
        {
            if (c.name.equals(name))
                return c;
        }

        return null;
    }

    public static GuiLayer getInstance()
    {
        if (instance == null)
            instance = new GuiLayer();

        return instance;
    }

    public void selectComponent(GuiComponent component)
    {
        if (selectedComponent != null)
            selectedComponent.selected = false;

        if (component != null)
            component.selected = true;

        selectedComponent = component;
    }

    public GuiComponent getSelectedComponent()
    {
        return selectedComponent;
    }

}
