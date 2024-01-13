package arkiGame.samplePrefabs;

import arkiGame.sampleComponents.SampleComponent3;
import l0raxeo.arki.engine.assetFiles.AssetPool;
import l0raxeo.arki.engine.components.collisionComponents.BoxBounds;
import l0raxeo.arki.engine.components.collisionComponents.RigidBody;
import l0raxeo.arki.engine.components.renderComponents.ImageTexture;
import l0raxeo.arki.engine.gameObjects.GameObject;
import l0raxeo.arki.engine.gameObjects.prefabs.Prefabs;
import org.joml.Vector2f;
import org.joml.Vector2i;
import org.joml.Vector3f;

public class SamplePrefab3
{

    public static GameObject generate()
    {
        return Prefabs.generate(
                "sample_movement",
                new Vector3f(256, 500, 1),
                new Vector2f(32, 32),
                45,
                new ImageTexture(
                        AssetPool.getBufferedImage("sampleTexture"),
                        new Vector2i(16, 16)
                ),
                new RigidBody(1),
                new BoxBounds(),
                new SampleComponent3()
        );
    }

}
