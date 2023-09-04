package l0raxeo.arki.engine.ui.components;

import l0raxeo.arki.engine.ui.GuiLayer;
import l0raxeo.arki.engine.ui.GuiText;
import org.joml.Vector2i;

import java.awt.*;
import java.awt.event.KeyEvent;

public class GuiTextField extends GuiKeyListener
{

    private final ClickListener clicker;

    private final Color[] color;
    private final Font font;
    private String text;
    private final String defaultText;

    public GuiTextField(String name, Vector2i position, Vector2i scale, Font font, Color[] color, ClickListener clicker) {
        super(name, position, scale);

        this.font = font;
        this.defaultText = name;
        this.text = "";
        this.color = color;
        this.selected = false;
        this.clicker = clicker;
    }

    public GuiTextField(String name, Vector2i position, Vector2i scale, Font font, Color[] color) {
        super(name, position, scale);

        this.font = font;
        this.defaultText = name;
        this.text = "";
        this.color = color;
        this.selected = false;
        this.clicker = null;
    }

    public GuiTextField(String name, String defaultText, Vector2i position, Vector2i scale, Font font, Color[] color, ClickListener clicker) {
        super(name, position, scale);

        this.font = font;
        this.defaultText = defaultText;
        this.text = "";
        this.color = color;
        this.selected = false;
        this.clicker = clicker;
    }

    public GuiTextField(String name, String defaultText, Vector2i position, Vector2i scale, Font font, Color[] color) {
        super(name, position, scale);

        this.font = font;
        this.defaultText = defaultText;
        this.text = "";
        this.color = color;
        this.selected = false;
        this.clicker = null;
    }

    @Override
    public void render(Graphics g) {
        if (selected)
            g.setColor(color[0]);
        else
            g.setColor(color[1]);

        g.fillRect(position.x, position.y, scale.x, scale.y);

        if (!selected)
            g.setColor(color[0]);
        else
            g.setColor(color[1]);
        g.fillRect(position.x + 4, position.y + 4, scale.x - 8, scale.y - 8);
        String curText = text;
        if (curText.equals("")) {
            curText = defaultText;
        }
        GuiText.drawString(g,
                curText,
                new Vector2i(position.x + scale.x / 2, position.y + scale.y / 2),
                true,
                Color.WHITE,
                font
        );
    }

    @Override
    public void onClick()
    {
        selected = !selected;

        if (clicker != null && !selected)
            clicker.onClick();

        if (selected)
            GuiLayer.selectComponent(this);
        else
            GuiLayer.selectComponent(null);
    }

    public void keyPress(KeyEvent e)
    {
        if(selected)
        {
            if (e.getKeyCode() == KeyEvent.VK_DELETE || e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                if (text.length() > 0)
                    text = text.substring(0, text.length() - 1);
            }
            else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                onClick();
            }
            else if (!e.isShiftDown()) {
                text = text.concat(String.valueOf(e.getKeyChar()));
            }
        }
    }

    public String getText()
    {
        return !this.text.equals("") ? text : defaultText;
    }

}
