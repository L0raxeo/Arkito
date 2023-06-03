package arkiGame.scenes;

import arkiGame.assetLoaders.SampleAssetLoader;
import arkiGame.prefabs.SamplePrefab2;
import l0raxeo.arki.engine.eventSystem.EventTrigger;
import l0raxeo.arki.engine.input.keyboard.KeyManager;
import arkiGame.prefabs.SamplePrefab1;
import l0raxeo.arki.engine.scenes.DefaultScene;
import l0raxeo.arki.engine.scenes.Scene;

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
    }

    @Override
    public void onDestroy()
    {
        sampleAssetLoader.unloadAssets();
    }

}
