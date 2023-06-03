package l0raxeo.arki.engine.scenes;

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

    public static float getDegFromOriginToTarget(int xOriginScreenPosition, int yOriginScreenPosition, int xTargetScreenPosition, int yTargetScreenPosition)
    {
        float theta = 0;
        yOriginScreenPosition = AppWindow.WINDOW_HEIGHT - yOriginScreenPosition;

        if (xTargetScreenPosition < xOriginScreenPosition && yTargetScreenPosition < yOriginScreenPosition)
            theta = (float) (Math.toDegrees(Math.atan((float) (yTargetScreenPosition - yOriginScreenPosition) / (xTargetScreenPosition - xOriginScreenPosition))) + 180);
        else if (xTargetScreenPosition > xOriginScreenPosition) {
            theta = (float) Math.toDegrees(Math.atan((float) (yTargetScreenPosition - yOriginScreenPosition) / (xTargetScreenPosition - xOriginScreenPosition)));
            if (theta < 0) theta += 360;
        }
        else if (xTargetScreenPosition == xOriginScreenPosition) {
            if (yTargetScreenPosition > yOriginScreenPosition)
                theta = 90;
            else if (yTargetScreenPosition < yOriginScreenPosition)
                theta = 270;
        }
        else
            theta = (float) Math.toDegrees(Math.acos((xTargetScreenPosition - xOriginScreenPosition) / (Math.sqrt(Math.pow(xTargetScreenPosition - xOriginScreenPosition, 2) + Math.pow(yTargetScreenPosition - yOriginScreenPosition, 2)))));

        return -theta;
    }

    public static Vector2i screenToWorld(Vector2i scrPos)
    {
        return new Vector2i(scrPos.x, AppWindow.WINDOW_HEIGHT - scrPos.y);
    }

    public static Vector2i worldToScreen(Vector2i worldPos)
    {
        return new Vector2i(worldPos.x, AppWindow.WINDOW_HEIGHT - worldPos.y);
    }

    public static void moveWorldCoords(Vector2i vel)
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
