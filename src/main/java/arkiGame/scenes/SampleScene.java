package arkiGame.scenes;

import arkiGame.sampleAssetLoaders.SampleAssetLoader;
import arkiGame.samplePrefabs.SamplePrefab2;
import l0raxeo.arki.engine.assetFiles.AssetPool;
import arkiGame.samplePrefabs.SamplePrefab1;
import l0raxeo.arki.engine.scenes.DefaultScene;
import l0raxeo.arki.engine.scenes.Scene;
import l0raxeo.arki.engine.ui.GuiText;
import l0raxeo.arki.renderer.AppWindow;

import java.awt.*;

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
        GuiText.drawString(g, "press any mouse button to jump", AppWindow.WINDOW_WIDTH / 2, 100, true, Color.WHITE, AssetPool.getFont("sampleFont16"));
    }

    @Override
    public void onDestroy()
    {
        sampleAssetLoader.unloadAssets();
    }

}
