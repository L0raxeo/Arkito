package arkiGame.components;

import l0raxeo.arki.engine.components.Component;
import l0raxeo.arki.engine.components.collisionComponents.RigidBody;
import l0raxeo.arki.engine.input.mouse.MouseManager;
import l0raxeo.arki.engine.scenes.Camera;
import org.joml.Vector2f;

public class SampleComponent1 extends Component
{

    @Override
    public void update(double dt) {
        if (MouseManager.hasPressedInput())
            gameObject.getComponent(RigidBody.class).addForce(new Vector2f(0, -10));

        gameObject.transform.rotation = Camera.getDegFromOriginToTarget(
                (int) gameObject.transform.getScreenCenterPosition().x,
                (int) gameObject.transform.getScreenCenterPosition().y,
                MouseManager.getScreenMouseX(),
                MouseManager.getScreenMouseY()
        );
    }

}
