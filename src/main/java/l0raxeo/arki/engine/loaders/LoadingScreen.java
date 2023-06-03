package l0raxeo.arki.engine.loaders;

import l0raxeo.arki.engine.assetFiles.AssetPool;
import l0raxeo.arki.engine.ui.GuiText;
import l0raxeo.arki.renderer.AppWindow;
import org.joml.Vector2i;

import java.awt.*;

public class LoadingScreen
{

    private static final long NOT_LOADING = -1;
    private static long MIN_DURATION_MILLI = NOT_LOADING;

    private static long minEndTime = NOT_LOADING;

    private static boolean isLoading = false;

    public static void renderLoadingScreen(Graphics g)
    {
        if (isLoading)
        {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, AppWindow.WINDOW_WIDTH, AppWindow.WINDOW_HEIGHT);
            GuiText.drawString(
                    g,
                    "Loading...",
                    AppWindow.WINDOW_WIDTH / 2,
                    AppWindow.WINDOW_HEIGHT / 2,
                    true,
                    Color.WHITE,
                    AssetPool.getFont("assets/fonts/default_font.ttf", 24)
            );

            isLoading = System.currentTimeMillis() < minEndTime;
        }
    }

    public static void load(long minDurationMillis)
    {
        MIN_DURATION_MILLI = minDurationMillis;
        minEndTime = System.currentTimeMillis() + MIN_DURATION_MILLI;
        isLoading = true;
    }

    public static long getMinDurationMilli()
    {
        return MIN_DURATION_MILLI;
    }

    public static boolean isLoading()
    {
        return isLoading;
    }

}