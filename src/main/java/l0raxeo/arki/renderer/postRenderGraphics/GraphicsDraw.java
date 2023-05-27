package l0raxeo.arki.renderer.postRenderGraphics;

import l0raxeo.arki.renderer.AppWindow;
import org.joml.Vector2i;
import org.joml.Vector3f;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GraphicsDraw
{

    private static final int MAX_LINES = 5000;
    private static final float RGB_SCALE_MULTIPLIER = 0.00392156862f;

    private static final List<Line2D> lines = new ArrayList<>();

    private static boolean started = false;

    public static void beginFrame()
    {
        if (!started)
            started = true;

        for (int i = 0; i < lines.size(); i++)
        {
            if (lines.get(i).beginFrame() < 0)
            {
                lines.remove(i);
                i--;
            }
        }
    }

    public static void render(Graphics g)
    {
        for (int curLineIndex = 0; curLineIndex < Math.min(lines.size(), MAX_LINES); curLineIndex++)
        {
            Line2D line = lines.get(curLineIndex);

            boolean isLineInRender = (line.getFrom().y > AppWindow.WINDOW_HEIGHT && line.getTo().y > AppWindow.WINDOW_HEIGHT) ||
                    (line.getFrom().y < 0 && line.getTo().y < 0) ||
                    (line.getFrom().x < 0 && line.getTo().x < 0) ||
                    (line.getFrom().x > AppWindow.WINDOW_WIDTH && line.getTo().x > AppWindow.WINDOW_WIDTH);

            if (isLineInRender)
                continue;

            Vector2i drawFrom = line.getFrom();
            Vector2i drawTo = line.getTo();
            float rawSlope = (float) (drawTo.y - drawFrom.y) / (drawTo.x - drawFrom.x);
            Vector3f color = line.getColor();

            for (int curPoint = 0; curPoint < 2; curPoint++) // 0 = from; 1 = to
            {
                Vector2i point1 = curPoint == 0 ? line.getFrom() : line.getTo();
                Vector2i point2 = curPoint == 1 ? line.getFrom() : line.getTo();
                if (point1.x > AppWindow.WINDOW_WIDTH) {
                    point1.x = AppWindow.WINDOW_WIDTH;
                    point1.y = (int) ((rawSlope * (point1.x - point2.x)) + point2.y);
                }
                else if (point1.x < 0) {
                    point1.x = 0;
                    point1.y = (int) ((rawSlope * (point1.x - point2.x)) + point2.y);
                }

                if (point1.y > AppWindow.WINDOW_HEIGHT){
                    point1.y = AppWindow.WINDOW_HEIGHT;
                    point1.x = (int) (((point1.y - point2.y) / rawSlope) + point2.x);
                }
                else if (point1.y < 0) {
                    point1.y = 0;
                    point1.x = (int) (((point1.y - point2.y) / rawSlope) + point2.x);
                }

                if (curPoint == 0) drawFrom = point1;
                else drawTo = point1;
            }

            g.setColor(new Color(RGB_SCALE_MULTIPLIER * color.x, RGB_SCALE_MULTIPLIER * color.y, RGB_SCALE_MULTIPLIER * color.z));
            g.drawLine(drawFrom.x, drawFrom.y, drawTo.x, drawTo.y);
        }
    }

    /**
     * all in screen position
     */
    public static void addLine2D(Vector2i from, Vector2i to, Vector3f color, int lifetime)
    {
        GraphicsDraw.lines.add(new Line2D(from, to, color, lifetime));
    }

    /**
     * all in screen position
     */
    public static void addLine2D(Vector2i from, Vector2i to, Color color)
    {
        GraphicsDraw.lines.add(new Line2D(from, to, new Vector3f(color.getRed(), color.getGreen(), color.getBlue()), 1));
    }

}
