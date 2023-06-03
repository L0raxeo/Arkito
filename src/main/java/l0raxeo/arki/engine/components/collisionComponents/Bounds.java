package l0raxeo.arki.engine.components.collisionComponents;

import l0raxeo.arki.engine.collision.Collision;
import l0raxeo.arki.engine.components.Component;
import l0raxeo.arki.engine.objects.GameObject;
import l0raxeo.arki.engine.scenes.SceneManager;
import l0raxeo.arki.renderer.AppWindow;
import org.joml.Vector2f;
import org.joml.Vector2i;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public abstract class Bounds extends Component
{

    public Rectangle bounds;

    public Bounds()
    {
        this.bounds = new Rectangle();
    }

    @Override
    public void update(double dt)
    {
        bounds.setRect(gameObject.transform.getScreenPosition().x, gameObject.transform.getScreenPosition().y, gameObject.transform.scale.x, gameObject.transform.scale.y);
    }

    public List<Collision> findGameObjectsInPath(Vector2f velocity)
    {
        List<Collision> result = new ArrayList<>();

        Rectangle2D predictedBounds = new Rectangle((int) (bounds.x + velocity.x), (int) (bounds.y + velocity.y), bounds.width, bounds.height);

        for (GameObject go : SceneManager.getActiveScene().getGameObjectsWithComponent(Bounds.class))
        {
            if (go.equals(this.gameObject))
                continue;

            if (predictedBounds.intersects(go.getComponent(Bounds.class).bounds))
            {
                result.add(new Collision(gameObject.transform, go, gameObject));
            }
        }

        return result;
    }

}
