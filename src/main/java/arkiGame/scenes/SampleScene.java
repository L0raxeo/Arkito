package arkiGame.scenes;

import arkiGame.assetLoaders.SampleAssetLoader;
import arkiGame.prefabs.SamplePrefab2;
import l0raxeo.arki.engine.assetFiles.AssetPool;
import l0raxeo.arki.engine.eventSystem.EventTrigger;
import l0raxeo.arki.engine.input.keyboard.KeyManager;
import arkiGame.prefabs.SamplePrefab1;
import l0raxeo.arki.engine.scenes.DefaultScene;
import l0raxeo.arki.engine.scenes.Scene;
import l0raxeo.arki.engine.ui.GuiLayer;
import l0raxeo.arki.engine.ui.GuiText;
import l0raxeo.arki.renderer.AppWindow;

import java.awt.*;
import java.awt.event.KeyEvent;

@DefaultScene()
public class SampleScene extends Scene
{

    private final SampleAssetLoader sampleAssetLoader = new SampleAssetLoader();

    @Override
    public void loadResources()
    {
        setBackdrop(Color.DARK_GRAY);
        sampleAssetLoader.loadAssets(1000);
    }

    @Override
    public void start()
    {
        addGameObject(SamplePrefab1.generate());
        addGameObject(SamplePrefab2.generate());
    }

    @Override
    public void update(double dt)
    {
        updateSceneGameObjects(dt);
    }

    @Override
    public void render(Graphics g)
    {
        renderSceneGameObjects(g);

        GuiText.drawString(g, "press any mouse button to jump", AppWindow.WINDOW_WIDTH / 2, 100, true, Color.WHITE, AssetPool.getFont("assets/fonts/default_font.ttf", 16));
    }

    @Override
    public void onDestroy()
    {
        sampleAssetLoader.unloadAssets();
    }

}
