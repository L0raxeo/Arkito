package l0raxeo.arki.engine.components.renderComponents;

import l0raxeo.arki.engine.components.Component;

import java.awt.*;

public class OvalRenderer extends Component
{

    private final Color color;
    private final boolean fill;

    public OvalRenderer(Color color, boolean fill)
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
            g.fillOval(gameObject.transform.getScreenPosition().x, gameObject.transform.getScreenPosition().y, gameObject.transform.scale.x, gameObject.transform.scale.y);
        else
            g.drawOval(gameObject.transform.getScreenPosition().x, gameObject.transform.getScreenPosition().y, gameObject.transform.scale.x, gameObject.transform.scale.y);
    }

}
