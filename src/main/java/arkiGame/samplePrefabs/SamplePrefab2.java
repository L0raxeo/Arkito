package arkiGame.samplePrefabs;

import l0raxeo.arki.engine.components.collisionComponents.BoxBounds;
import l0raxeo.arki.engine.components.collisionComponents.RigidBody;
import l0raxeo.arki.engine.components.renderComponents.RectangleRenderer;
import l0raxeo.arki.engine.gameObjects.GameObject;
import l0raxeo.arki.engine.gameObjects.prefabs.Prefabs;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.awt.*;

public class SamplePrefab2
{

    public static GameObject generate()
    {
        return Prefabs.generate(
                "sample_platform",
                new Vector3f(16, 100, 1),
                new Vector2f(300, 32),
                new RectangleRenderer(Color.WHITE, true),
                new RigidBody(1),
                new BoxBounds()
        );
    }

}
