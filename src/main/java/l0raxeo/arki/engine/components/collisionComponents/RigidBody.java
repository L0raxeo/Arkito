package l0raxeo.arki.engine.components.collisionComponents;

import l0raxeo.arki.engine.collision.Collision;
import l0raxeo.arki.engine.collision.CollisionType;
import l0raxeo.arki.engine.components.Component;
import org.joml.Vector2i;

import java.util.ArrayList;
import java.util.List;

public class RigidBody extends Component
{

    public Vector2i velocity;
    private final float friction;

    public RigidBody(float friction)
    {
        this.velocity = new Vector2i();
        this.friction = friction;
    }

    @Override
    public void update(double dt)
    {
        boolean teleportY = false;
        boolean teleportX = false;

        if (gameObject.hasComponent(Bounds.class))
        {
            for (Collision collider : gameObject.getComponent(Bounds.class).findGameObjectsInPath(velocity))
            {
                if (collider.collider.equals(this.gameObject))
                    continue;

                if (collider.type == CollisionType.TOP && velocity.y < 0) {
                    velocity.y = 0;
                    gameObject.transform.position.y = collider.collider.transform.position.y + collider.collider.transform.scale.y;
                    teleportY = true;

                    //friction
                    if (velocity.x < 0)
                        velocity.x += friction / 10;
                    else if (velocity.x > 0)
                        velocity.x -= friction / 10;
                } else if (collider.type == CollisionType.BOTTOM && velocity.y > 0) {
                    velocity.y = 0;
                    gameObject.transform.position.y = collider.collider.transform.position.y - gameObject.transform.scale.y;
                    teleportY = true;

                    //friction
                    if (velocity.x < 0)
                        velocity.x += friction / 10;
                    else if (velocity.x > 0)
                        velocity.x -= friction / 10;
                }

                if (collider.type == CollisionType.RIGHT && velocity.x > 0) {
                    velocity.x = 0;
                    gameObject.transform.position.x = collider.collider.transform.position.x - gameObject.transform.scale.x;
                    teleportX = true;

                    //friction
                    if (velocity.y < 0)
                        velocity.y += friction / 10;
                    else if (velocity.y > 0)
                        velocity.y -= friction / 10;
                } else if (collider.type == CollisionType.LEFT && velocity.x < 0) {
                    velocity.x = 0;
                    gameObject.transform.position.x = collider.collider.transform.position.x + collider.collider.transform.scale.x;
                    teleportX = true;

                    //friction
                    if (velocity.y < 0)
                        velocity.y += friction / 10;
                    else if (velocity.y > 0)
                        velocity.y -= friction / 10;
                }

                for (Component c : gameObject.getAllComponents())
                {
                    c.onCollision(collider);
                }
            }
        }

        if (Math.abs(velocity.x) < 0.1)
            velocity.x = 0;
        if (Math.abs(velocity.y) < 0.1)
            velocity.y = 0;

        if (!teleportX)
            gameObject.transform.position.x += velocity.x * dt;

        if (!teleportY)
            gameObject.transform.position.y += velocity.y * dt;

        if(!moveForces.isEmpty())
        {
            int velX = 0;
            int velY = 0;

            for (Vector2i v : moveForces)
            {
                velX += v.x;
                velY += v.y;
            }

            velocity = new Vector2i(velX, velY);
        }

        moveForces.clear();
    }

    public void addForce(Vector2i force)
    {
        velocity.add(force);
    }

    private final List<Vector2i> moveForces = new ArrayList<>();

    public void move(Vector2i velocity)
    {
        this.velocity = velocity;
    }

    public void moveX(int x)
    {
        this.velocity.x = x;
    }

    public void moveY(int y)
    {
        this.velocity.y = y;
    }

    public Vector2i getVelocity()
    {
        return velocity;
    }

    public float getFriction()
    {
        return friction;
    }

}
