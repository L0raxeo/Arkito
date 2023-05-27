package l0raxeo.arki.renderer.gameRendering;

import l0raxeo.arki.renderer.AppWindow;
import org.joml.Vector2i;

public class Camera
{

    private static int xOffset;
    private static int yOffset;

    public static void reset()
    {
        xOffset = 0;
        yOffset = 0;
    }

    /**
     * @param position world position
     */
    public static void setPosition(Vector2i position)
    {
        xOffset = -position.x;
        yOffset = position.y;
    }

    public static Vector2i getPosition()
    {
        return new Vector2i(-xOffset(), yOffset());
    }

    /**
     * @param yOrigin in world coords
     * @param yTarget in world coords
     * @return in degrees
     */
    public static float getAngleFromOriginToTarget(int xOrigin, int yOrigin, int xTarget, int yTarget)
    {
        float theta = 0;
        xOrigin += Camera.xOffset();
        yOrigin -= Camera.yOffset();
        if (xTarget < xOrigin && yTarget < yOrigin)
            theta = (float) (Math.toDegrees(Math.atan((float) (yTarget - yOrigin) / (xTarget - xOrigin))) + 180);
        else if (xTarget > xOrigin) {
            theta = (float) Math.toDegrees(Math.atan((float) (yTarget - yOrigin) / (xTarget - xOrigin)));
            if (theta < 0) theta += 360;
        }
        else if (xTarget == xOrigin) {
            if (yTarget > yOrigin)
                theta = 90;
            else if (yTarget < yOrigin)
                theta = 270;
        }
        else
            theta = (float) Math.toDegrees(Math.acos((xTarget - xOrigin) / (Math.sqrt(Math.pow(xTarget - xOrigin, 2) + Math.pow(yTarget - yOrigin, 2)))));

        return theta;
    }

    public static Vector2i screenToWorld(Vector2i scrPos)
    {
        return new Vector2i(scrPos.x, AppWindow.WINDOW_HEIGHT - scrPos.y);
    }

    public static void move(Vector2i vel)
    {
        xOffset -= vel.x;
        yOffset += vel.y;
    }


    public static int xOffset()
    {
        return xOffset;
    }

    public static int yOffset()
    {
        return yOffset;
    }

}
