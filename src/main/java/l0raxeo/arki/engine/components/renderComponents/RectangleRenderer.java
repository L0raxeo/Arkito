package l0raxeo.arki.engine.components.renderComponents;

import l0raxeo.arki.engine.components.Component;

import java.awt.*;

public class RectangleRenderer extends Component
{

    private final Color color;
    private final boolean fill;

    public RectangleRenderer(Color color, boolean fill)
    {
        super();

        this.color = color;
        this.fill = fill;
    }

    @Override
    public void render(Graphics g)
    {
        g.setColor(color);

        if (fill)
            g.fillRect((int) gameObject.transform.getScreenPosition().x, (int) gameObject.transform.getScreenPosition().y, (int) gameObject.transform.scale.x, (int) gameObject.transform.scale.y);
        else
            g.drawRect((int) gameObject.transform.getScreenPosition().x, (int) gameObject.transform.getScreenPosition().y, (int) gameObject.transform.scale.x, (int) gameObject.transform.scale.y);
    }

}
