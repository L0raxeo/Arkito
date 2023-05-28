package l0raxeo.arki.engine.components.physicsComponents;

import l0raxeo.arki.engine.components.Component;
import l0raxeo.arki.engine.components.collisionComponents.RigidBody;

public class Physics extends Component
{

    private RigidBody rigidBody;

    @Override
    public void start() {
        rigidBody = gameObject.getComponent(RigidBody.class);
    }

    @Override
    public void update(double dt)
    {
        if (rigidBody.velocity != null)
            fall();
    }

    private void fall()
    {
        float GRAVITY_ACCELERATION = 1;
        rigidBody.velocity.y += GRAVITY_ACCELERATION;

        int TERMINAL_VELOCITY = 15;
        if (rigidBody.velocity.y > TERMINAL_VELOCITY)
            rigidBody.velocity.y = TERMINAL_VELOCITY;
    }

}
