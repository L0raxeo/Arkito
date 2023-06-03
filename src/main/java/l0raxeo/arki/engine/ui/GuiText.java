package l0raxeo.arki.engine.ui;

import org.joml.Vector2i;

import java.awt.*;

public class GuiText
{

    public static void drawString(Graphics g, String text, Vector2i position, boolean center, Color color, Font font)
    {
        g.setColor(color);
        g.setFont(font);
        int x = position.x;
        int y = position.y;

        if (center)
        {
            FontMetrics fm = g.getFontMetrics(font);
            x = x - fm.stringWidth(text) / 2;
            y = (y - fm.getHeight() / 2) + fm.getAscent();
        }

        g.drawString(text, x, y);
    }

    public static void drawString(Graphics g, String text, int xScreen, int yScreen, boolean center, Color color, Font font)
    {
        g.setColor(color);
        g.setFont(font);

        if (center)
        {
            FontMetrics fm = g.getFontMetrics(font);
            xScreen = xScreen - fm.stringWidth(text) / 2;
            yScreen = (yScreen - fm.getHeight() / 2) + fm.getAscent();
        }

        g.drawString(text, xScreen, yScreen);
    }

}
