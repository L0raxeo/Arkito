package l0raxeo.arki.engine.ui.components;

import org.joml.Vector2i;

import java.awt.*;

public abstract class GuiComponent
{

    public String name;
    public boolean hovering;
    public boolean selected;
    protected Vector2i position;
    protected Vector2i scale;
    public final Rectangle bounds;

    public GuiComponent(String name, Vector2i position, Vector2i scale)
    {
        this.name = name;
        this.position = position;
        this.scale = scale;

        this.selected = false;
        this.bounds = new Rectangle(position.x, position.y, scale.x, scale.y);
    }

    public abstract void update();

    public abstract void render(Graphics g);

    public abstract void onClick();

}
