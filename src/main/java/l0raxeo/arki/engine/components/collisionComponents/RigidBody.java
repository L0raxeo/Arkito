package l0raxeo.arki.engine.components.collisionComponents;

import l0raxeo.arki.engine.collision.Collision;
import l0raxeo.arki.engine.collision.CollisionType;
import l0raxeo.arki.engine.components.Component;
import org.joml.Vector2f;
import org.joml.Vector2i;

import java.util.ArrayList;
import java.util.List;

public class RigidBody extends Component
{

    private final List<Vector2i> moveForces = new ArrayList<>();
    public Vector2f velocity;
    private final float friction;

    public RigidBody(float friction)
    {
        this.velocity = new Vector2f();
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
                    gameObject.transform.noCamScreenPosition.y = collider.collider.transform.noCamScreenPosition.y + collider.collider.transform.scale.y;
                    teleportY = true;

                    //friction
                    if (velocity.x < 0)
                        velocity.x += friction / 10;
                    else if (velocity.x > 0)
                        velocity.x -= friction / 10;
                } else if (collider.type == CollisionType.BOTTOM && velocity.y > 0) {
                    velocity.y = 0;
                    gameObject.transform.noCamScreenPosition.y = collider.collider.transform.noCamScreenPosition.y - gameObject.transform.scale.y;
                    teleportY = true;

                    //friction
                    if (velocity.x < 0)
                        velocity.x += friction / 10;
                    else if (velocity.x > 0)
                        velocity.x -= friction / 10;
                }

                if (collider.type == CollisionType.RIGHT && velocity.x > 0) {
                    velocity.x = 0;
                    gameObject.transform.noCamScreenPosition.x = collider.collider.transform.noCamScreenPosition.x - gameObject.transform.scale.x;
                    teleportX = true;

                    //friction
                    if (velocity.y < 0)
                        velocity.y += friction / 10;
                    else if (velocity.y > 0)
                        velocity.y -= friction / 10;
                } else if (collider.type == CollisionType.LEFT && velocity.x < 0) {
                    velocity.x = 0;
                    gameObject.transform.noCamScreenPosition.x = collider.collider.transform.noCamScreenPosition.x + collider.collider.transform.scale.x;
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
            gameObject.transform.noCamScreenPosition.x += velocity.x * dt;

        if (!teleportY)
            gameObject.transform.noCamScreenPosition.y += velocity.y * dt;

        if(!moveForces.isEmpty())
        {
            int velX = 0;
            int velY = 0;

            for (Vector2i v : moveForces)
            {
                velX += v.x;
                velY += v.y;
            }

            velocity = new Vector2f(velX, velY);
        }

        moveForces.clear();
    }

    public void addForce(Vector2f force)
    {
        velocity.add(force);
    }

    public void addForceX(int xForce)
    {
        velocity.x += xForce;
    }

    public void addForceY(int yForce)
    {
        velocity.y += yForce;
    }

    public void subForce(Vector2f force)
    {
        velocity.sub(force);
    }

    public void setForce(Vector2f force)
    {
        velocity = force;
    }

    public void setVelocity(Vector2f velocity)
    {
        this.velocity = velocity;
    }

    public void setVelocityX(int x)
    {
        this.velocity.x = x;
    }

    public void setVelocityY(int y)
    {
        this.velocity.y = y;
    }

    public Vector2f getVelocity()
    {
        return velocity;
    }

    public float getFriction()
    {
        return friction;
    }

}
