package arkiGame.components;

import l0raxeo.arki.engine.components.Component;
import l0raxeo.arki.engine.components.collisionComponents.RigidBody;
import l0raxeo.arki.engine.input.mouse.MouseManager;
import org.joml.Vector2i;

public class SampleComponent extends Component
{

    @Override
    public void update(double dt) {
        if (MouseManager.hasPressedInput())
            gameObject.getComponent(RigidBody.class).addForce(new Vector2i(0, -10));

        gameObject.transform.rotation++;
    }

}
