package arkiGame.scenes;

import arkiGame.assetLoaders.SampleAssetLoader;
import arkiGame.components.SampleComponent;
import l0raxeo.arki.engine.components.collisionComponents.BoxBounds;
import l0raxeo.arki.engine.components.collisionComponents.RigidBody;
import l0raxeo.arki.engine.components.physicsComponents.Physics;
import l0raxeo.arki.engine.components.renderComponents.ImageTexture;
import l0raxeo.arki.engine.components.renderComponents.RectangleRenderer;
import l0raxeo.arki.engine.assetFiles.AssetPool;
import l0raxeo.arki.engine.objects.prefabs.Prefabs;
import l0raxeo.arki.engine.scenes.DefaultScene;
import l0raxeo.arki.engine.scenes.Scene;
import org.joml.Vector2i;
import org.joml.Vector3i;

import java.awt.*;

@DefaultScene()
public class SampleScene extends Scene
{

    private SampleAssetLoader sampleAssetLoader;

    @Override
    public void loadResources()
    {
        setBackdrop(Color.DARK_GRAY);
        sampleAssetLoader = new SampleAssetLoader();
        sampleAssetLoader.loadAssets(1000);
    }

    @Override
    public void start()
    {
        addGameObject(Prefabs.generate(
                "sample_object",
                new Vector3i(50, 500, 1),
                new Vector2i(32, 32),
                45,
                new ImageTexture(AssetPool.getBufferedImage("assets/samples/textures/sample_texture.png"), new Vector2i(16, 16)),
                new RigidBody(1),
                new Physics(),
                new BoxBounds(),
                new SampleComponent()
        ));

        addGameObject(Prefabs.generate(
                "sample_platform",
                new Vector3i(16, 100, 1),
                new Vector2i(300, 32),
                new RectangleRenderer(Color.WHITE, true),
                new RigidBody(1),
                new BoxBounds()
        ));
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
