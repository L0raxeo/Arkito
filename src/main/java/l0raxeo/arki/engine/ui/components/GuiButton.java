package l0raxeo.arki.engine.ui.components;

import l0raxeo.arki.engine.ui.GuiLayer;
import l0raxeo.arki.engine.ui.GuiText;
import org.joml.Vector2i;

import java.awt.*;

public class GuiButton extends GuiComponent
{

    private final String text;
    private final Font font;
    private final Color[] color;
    private final boolean solid;
    private final Vector2i textPos;

    private final ClickListener clicker;

    public GuiButton(String name, Vector2i position, Vector2i scale, String text, Font font, Color[] color, boolean solid, ClickListener clicker)
    {
        super(name, position, scale);

        this.text = text;
        this.font = font;
        this.solid = solid;
        this.color = color;
        this.clicker = clicker;

        this.textPos = new Vector2i(position.x + (scale.x / 2), position.y + (scale.y / 2));
    }

    @Override
    public void update()
    {

    }

    @Override
    public void render(Graphics g)
    {
        g.setColor(color[0]);

        if (hovering)
        {
            if (solid)
            {
                g.drawRect(position.x, position.y, scale.x, scale.y);
                GuiText.drawString(g, text, textPos, true, color[0], font);
            }
            else
            {
                g.fillRect(position.x, position.y, scale.x, scale.y);
                GuiText.drawString(g, text, textPos, true, color[1], font);
            }
        }
        else
        {
            if (solid)
            {
                g.fillRect(position.x, position.y, scale.x, scale.y);
                GuiText.drawString(g, text, textPos, true, color[1], font);
            }
            else
            {
                g.drawRect(position.x, position.y, scale.x, scale.y);
                GuiText.drawString(g, text, textPos, true, color[0], font);
            }
        }
    }

    @Override
    public void onClick()
    {
        clicker.onClick();
        GuiLayer.getInstance().selectComponent(null);
    }

}
