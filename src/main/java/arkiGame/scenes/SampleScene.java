package arkiGame.scenes;

import arkiGame.assetLoaders.SampleAssetLoader;
import arkiGame.components.SampleComponent;
import l0raxeo.arki.engine.components.collisionComponents.BoxBounds;
import l0raxeo.arki.engine.components.collisionComponents.RigidBody;
import l0raxeo.arki.engine.components.physicsComponents.Physics;
import l0raxeo.arki.engine.components.textureComponents.ImageTexture;
import l0raxeo.arki.engine.components.textureComponents.RectangleRenderer;
import l0raxeo.arki.engine.dataStructure.AssetPool;
import l0raxeo.arki.engine.input.mouse.MouseManager;
import l0raxeo.arki.engine.objects.GameObject;
import l0raxeo.arki.engine.prefabs.Prefabs;
import l0raxeo.arki.engine.scenes.DefaultScene;
import l0raxeo.arki.engine.scenes.Scene;
import org.joml.Vector2i;
import org.joml.Vector3i;

import java.awt.*;

@DefaultScene()
public class SampleScene extends Scene
{

    private GameObject player;

    @Override
    public void loadResources()
    {
        setBackdrop(Color.DARK_GRAY);
        new SampleAssetLoader().loadAssets();
    }

    @Override
    public void start()
    {
        addGameObject(Prefabs.generate(
                "Test",
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
                "platform",
                new Vector3i(16, 100, 1),
                new Vector2i(300, 32),
                new RectangleRenderer(Color.WHITE, true),
                new RigidBody(1),
                new BoxBounds()
        ));
        player = getGameObject("Test");
    }

    @Override
    public void update(double dt)
    {
        updateSceneGameObjects(dt);

        if (player != null)
        {
            if (MouseManager.hasPressedInput())
                player.getComponent(RigidBody.class).addForce(new Vector2i(0, -10));
        }
    }

    @Override
    public void render(Graphics g)
    {
        renderSceneGameObjects(g);
    }
}
