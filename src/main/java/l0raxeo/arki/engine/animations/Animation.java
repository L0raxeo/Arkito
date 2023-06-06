package l0raxeo.arki.engine.animations;

import java.awt.image.BufferedImage;

public class Animation
{

    private final int frameIntervals;
    private int index;
    private long lastTime, timer;
    private final BufferedImage[] frames;

    public Animation(int frameIntervals, BufferedImage... frames)
    {
        this.frameIntervals = frameIntervals;
        this.frames = frames;
        index = 0;
        timer = 0;
        lastTime = System.currentTimeMillis();
    }

    public void update()
    {
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if (timer > frameIntervals)
        {
            index++;
            timer = 0;

            if (index >= frames.length)
                index = 0;
        }
    }

    public int getIndex()
    {
        return index;
    }

    public BufferedImage getCurrentFrame()
    {
        return frames[index];
    }

}
