package l0raxeo.arki.engine.ui.components;

import org.joml.Vector2i;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GuiImage extends GuiComponent
{

    private BufferedImage image;

    public GuiImage(String name, Vector2i position, Vector2i scale, BufferedImage image) {
        super(name, position, scale);

        this.image = image;
    }

    public GuiImage(String name, Vector2i position, int scaleFactor, BufferedImage image)
    {
        super(name, position, new Vector2i(image.getWidth() * scaleFactor, image.getHeight() * scaleFactor));

        this.image = image;
    }

    @Override
    public void update() {}

    @Override
    public void render(Graphics g)
    {
        g.drawImage(image, position.x, position.y, scale.x, scale.y, null);
    }

    @Override
    public void onClick() {

    }

    public void setImage(BufferedImage image)
    {
        this.image = image;
    }

    public BufferedImage getImage()
    {
        return image;
    }

}
