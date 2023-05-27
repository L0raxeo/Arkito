package l0raxeo.arki.renderer.postRenderGraphics;

import org.joml.Vector2i;
import org.joml.Vector3f;

public class Line2D
{

    private final Vector2i from;
    private final Vector2i to;
    private Vector3f color;

    private int lifetime;

    public Line2D(Vector2i from, Vector2i to)
    {
        this.from = from;
        this.to = to;
    }

    public Line2D(Vector2i from, Vector2i to, Vector3f color, int lifetime)
    {
        this.from = from;
        this.to = to;
        this.color = color;
        this.lifetime = lifetime;
    }

    public int beginFrame()
    {
        this.lifetime--;
        return this.lifetime;
    }

    public Vector2i getFrom()
    {
        return from;
    }

    public Vector2i getTo()
    {
        return to;
    }

    public Vector2i getStart()
    {
        return from;
    }

    public Vector2i getEnd()
    {
        return to;
    }

    public Vector3f getColor()
    {
        return color;
    }

    public float lengthSquared()
    {
        return new Vector2i(to).sub(from).lengthSquared();
    }

}
