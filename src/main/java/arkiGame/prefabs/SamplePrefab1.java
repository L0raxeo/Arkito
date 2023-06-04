package arkiGame.prefabs;

import arkiGame.components.SampleComponent;
import l0raxeo.arki.engine.assetFiles.AssetPool;
import l0raxeo.arki.engine.components.collisionComponents.BoxBounds;
import l0raxeo.arki.engine.components.collisionComponents.RigidBody;
import l0raxeo.arki.engine.components.physicsComponents.Physics;
import l0raxeo.arki.engine.components.renderComponents.ImageTexture;
import l0raxeo.arki.engine.gameObjects.GameObject;
import l0raxeo.arki.engine.gameObjects.prefabs.Prefabs;
import org.joml.Vector2f;
import org.joml.Vector2i;
import org.joml.Vector3f;

public class SamplePrefab1
{

    public static GameObject generate()
    {
        return Prefabs.generate(
                "sample_object",
                new Vector3f(50, 500, 1),
                new Vector2f(32, 32),
                45,
                new ImageTexture(AssetPool.getBufferedImage("sampleTexture"), new Vector2i(16, 16)),
                new RigidBody(1),
                new Physics(),
                new BoxBounds(),
                new SampleComponent()
        );
    }

}
