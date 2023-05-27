package l0raxeo.arki.engine.ui.components;

import l0raxeo.arki.engine.ui.GuiLayer;
import org.joml.Vector2i;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GuiImageButton extends GuiComponent
{

    private final Vector2i buttonSize;
    private final Vector2i imageSize;
    private final BufferedImage[] images;
    private final Color[] colors;

    private final ClickListener clicker;

    public GuiImageButton(String name, Vector2i position, Vector2i buttonSize, Vector2i imageSize, Color[] colors, BufferedImage[] images, ClickListener clicker)
    {
        super(name, position, buttonSize);

        this.buttonSize = buttonSize;
        this.imageSize = imageSize;
        this.colors = colors;
        this.images = images;
        this.clicker = clicker;
    }

    public GuiImageButton(String name, Vector2i position, Vector2i buttonSize, int imageScale, Color[] colors, BufferedImage[] images, ClickListener clicker)
    {
        super(name, position, buttonSize);

        this.buttonSize = buttonSize;
        this.imageSize = new Vector2i(images[0].getWidth() * imageScale, images[0].getHeight() * imageScale);
        this.colors = colors;
        this.images = images;
        this.clicker = clicker;
    }

    @Override
    public void update()
    {

    }

    @Override
    public void render(Graphics g)
    {
        if (hovering)
        {
            g.setColor(colors[1]);
            g.fillRect(position.x - 5, position.y - 5, buttonSize.x + 10, buttonSize.y + 10);
            g.drawImage(images[1], position.x + (buttonSize.x / 2) - (imageSize.x / 2), position.y + (buttonSize.y / 2) - (imageSize.y / 2), imageSize.x, imageSize.y, null);
        }
        else
        {
            g.setColor(colors[0]);
            g.fillRect(position.x, position.y, buttonSize.x, buttonSize.y);
            g.drawImage(images[0], position.x + (buttonSize.x / 2) - (imageSize.x / 2), position.y + (buttonSize.y / 2) - (imageSize.y / 2), imageSize.x, imageSize.y, null);
        }
    }

    @Override
    public void onClick()
    {
        clicker.onClick();
        GuiLayer.getInstance().selectComponent(null);
    }

    /**
     * TODO
     * @return make sure that it scales the image to have the max length/width comply with the border
     * size of the button (i.e. scale the image size to fit the button size).
     */
    private Vector2i scaleToFit()
    {
        return new Vector2i();
    }

}
