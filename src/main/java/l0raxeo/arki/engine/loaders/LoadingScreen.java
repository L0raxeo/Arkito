package l0raxeo.arki.engine.loaders;

import l0raxeo.arki.renderer.AppWindow;

import java.awt.*;

public class LoadingScreen
{

    private static final long NOT_LOADING = -1;
    private static long MIN_DURATION_MILLI = NOT_LOADING;
    private static long minEndTime = NOT_LOADING;
    public static boolean isLoading = false;

    private static LoadingScreenRenderer renderer = null;

    public static void renderLoadingScreen(Graphics g)
    {
        if (isLoading)
        {
            renderer.render(g);
            updateLoadingStatus();
        }
    }

    private static void updateLoadingStatus()
    {
        isLoading = System.currentTimeMillis() < minEndTime;
        renderer = isLoading ? renderer : null;
    }

    public static void load(long minDurationMillis, LoadingScreenRenderer loadingScreenRenderer)
    {
        MIN_DURATION_MILLI = minDurationMillis;
        minEndTime = System.currentTimeMillis() + MIN_DURATION_MILLI;
        renderer = loadingScreenRenderer;
        isLoading = true;
    }

    public static long getMinDurationMilli()
    {
        return MIN_DURATION_MILLI;
    }

}