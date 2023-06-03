package l0raxeo.arki.renderer;

import l0raxeo.arki.engine.audio.AudioManager;
import l0raxeo.arki.engine.classStructure.ClassFinder;
import l0raxeo.arki.engine.input.keyboard.KeyManager;
import l0raxeo.arki.engine.input.mouse.MouseManager;
import l0raxeo.arki.engine.mainApp.AppConfig;
import l0raxeo.arki.engine.loaders.LoadingScreen;
import l0raxeo.arki.engine.scenes.SceneManager;
import l0raxeo.arki.engine.ui.GuiLayer;
import l0raxeo.arki.renderer.postRenderGraphics.GraphicsDraw;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.*;

public class AppWindow implements Runnable
{

    private static AppWindow instance;

    private JFrame frame;
    private Canvas canvas;
    public static String APP_TITLE;
    public static int WINDOW_WIDTH, WINDOW_HEIGHT;
    public static boolean IS_RESIZEABLE;
    public static Dimension WINDOW_SIZE;
    private Thread thread;
    private boolean running = false;

    private Color backdrop;
    private KeyManager keyListener;
    private MouseManager mouseListener;
    private GuiLayer guiLayer;

    private AppWindow()
    {
        APP_TITLE = "Arkito_L0raxeo";
        WINDOW_WIDTH = 768;
        WINDOW_HEIGHT = 768;
        IS_RESIZEABLE = false;
        WINDOW_SIZE = new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    @Override
    public void run()
    {
        init();
        loop();
        stop();
    }

    private void init()
    {
        loadAppWindowConfiguration();
        createJFrame();
        createCanvas();
        combineJFrameAndCanvas();
        createWindowKeyListener();
        createWindowMouseListener();
        guiLayer = GuiLayer.getInstance();
        setVisible(true);
        SceneManager.initializeScene();
    }

    private void loadAppWindowConfiguration()
    {
        Class<?> appConfig = Objects.requireNonNull(ClassFinder.findAnnotatedClass(ClassFinder.findAllClassesUsingClassLoader("arkiGame"), AppConfig.class));
        APP_TITLE = appConfig.getAnnotation(AppConfig.class).windowTitle();
        WINDOW_WIDTH = appConfig.getAnnotation(AppConfig.class).windowWidth();
        WINDOW_HEIGHT = appConfig.getAnnotation(AppConfig.class).windowHeight();
        IS_RESIZEABLE = appConfig.getAnnotation(AppConfig.class).resizeable();
        WINDOW_SIZE = new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    private void createJFrame()
    {
        frame = new JFrame(APP_TITLE);
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(IS_RESIZEABLE);
        frame.setLocationRelativeTo(null);
    }

    private void createCanvas()
    {
        canvas = new Canvas();
        canvas.setPreferredSize(WINDOW_SIZE);
        canvas.setMaximumSize(WINDOW_SIZE);
        canvas.setMinimumSize(WINDOW_SIZE);
        canvas.setFocusable(false);
    }

    private void combineJFrameAndCanvas()
    {
        frame.add(canvas);
        frame.pack();
    }

    private void createWindowKeyListener()
    {
        keyListener = new KeyManager();
        frame.addKeyListener(keyListener);
    }

    private void createWindowMouseListener()
    {
        mouseListener = new MouseManager();
        canvas.addMouseListener(mouseListener);
        canvas.addMouseMotionListener(mouseListener);
    }

    private void loop()
    {
        long lastTime = System.nanoTime();
        double timePerTick = 1000000000D / 60D;
        int ticks = 0;
        int frames = 0;
        long lastTimer = System.currentTimeMillis();
        double delta = 0;
        while (running)
        {
            long now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            lastTime = now;

            GraphicsDraw.beginFrame();

            while (delta >= 1)
            {
                ticks++;
                update(delta);
                /*
                caps the FPS to 60 but reduces lag spikes
                move render & frames outside of while loop to increase FPS
                    increase lag spikes if on older machine
                 */
                render();
                frames++;
                delta -= 1;
            }

            if (System.currentTimeMillis() - lastTimer >= 1000)
            {
                lastTimer += 1000;
                getFrame().setTitle(APP_TITLE + " | TPS: " + ticks + " FPS: " + frames);
                frames = 0;
                ticks = 0;
            }
        }
    }

    private void update(double dt)
    {
        mouseListener.update();
        keyListener.update();
        SceneManager.getActiveScene().update(dt);
        AudioManager.update();
        updateWindowSize();
    }

    private void updateWindowSize()
    {
        WINDOW_WIDTH = getFrame().getWidth();
        WINDOW_HEIGHT = getFrame().getHeight();
        WINDOW_SIZE = new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    private void render()
    {
        BufferStrategy bs = getCanvas().getBufferStrategy();
        if (bs == null)
        {
            getCanvas().createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        // clear screen
        g.clearRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        // render scene here
        g.setColor(backdrop);
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        SceneManager.getActiveScene().render(g);
        guiLayer.render(g);
        LoadingScreen.renderLoadingScreen(g);
        GraphicsDraw.render(g);
        // end drawing
        bs.show();
        g.dispose();
    }

    public static AppWindow getInstance()
    {
        if (instance == null)
            instance = new AppWindow();

        return instance;
    }

    public void setVisible(boolean isVisible)
    {
        frame.setVisible(isVisible);
    }

    public Canvas getCanvas()
    {
        return canvas;
    }

    public JFrame getFrame()
    {
        return frame;
    }

    public static void setBackdrop(Color color)
    {
        getInstance().backdrop = color;
    }

    public synchronized void start()
    {
        running = true;

        thread = new Thread(this, "Rebound" + "_main");
        thread.start();
    }

    public synchronized void stop()
    {
        if (!running) return;
        running = false;

        try
        {
            thread.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

}
