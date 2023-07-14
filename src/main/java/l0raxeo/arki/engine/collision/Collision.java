package l0raxeo.arki.engine.collision;

import l0raxeo.arki.engine.gameObjects.GameObject;
import l0raxeo.arki.engine.gameObjects.Transform;

import static l0raxeo.arki.engine.collision.CollisionType.*;

public class Collision
{

    public final CollisionType type;
    public final GameObject collider;
    public final GameObject origin;

    public Collision(Transform originTransform, GameObject collider, GameObject origin)
    {
        this.collider = collider;
        this.type = resolveCollisionType(originTransform, collider.transform);
        this.origin = origin;
    }

    private CollisionType resolveCollisionType(Transform originTransform, Transform colliderTransform)
    {
        float originLeftX = originTransform.getScreenPosition().x;
        float originRightX = originLeftX + originTransform.scale.x;
        float originTopY = originTransform.getScreenPosition().y;
        float originBottomY = originTopY + originTransform.scale.y;

        float colliderLeftX = colliderTransform.getScreenPosition().x;
        float colliderRightX = colliderLeftX + colliderTransform.scale.x;
        float colliderTopY = colliderTransform.getScreenPosition().y;
        float colliderBottomY = colliderTopY + colliderTransform.scale.y;
        
        float originCenterX = originTransform.getScreenPosition().x + (originTransform.scale.x / 2);
        float originCenterY = originTransform.getScreenPosition().y + (originTransform.scale.y / 2);

        float colliderCenterX = colliderTransform.getScreenPosition().x + (colliderTransform.scale.x / 2);
        float colliderCenterY = colliderTransform.getScreenPosition().y + (colliderTransform.scale.y / 2);

        // Q1
        if (originCenterX < colliderCenterX && originCenterY < colliderCenterY)
        {
            if (originRightX - colliderLeftX > originBottomY - colliderTopY)
                return BOTTOM;

            return RIGHT;
        }
        // Q2
        else if (originCenterX > colliderCenterX && originCenterY < colliderCenterY)
        {
            if (colliderRightX - originLeftX > originBottomY - colliderTopY)
                return BOTTOM;

            return LEFT;
        }
        // Q3
        else if (originCenterX > colliderCenterX && originCenterY > colliderCenterY)
        {
            if (colliderRightX - originLeftX > colliderBottomY - originTopY)
                return TOP;

            return LEFT;
        }
        // Q4
        else if (originCenterX < colliderCenterX && originCenterY > colliderCenterY)
        {
            if (originRightX - colliderLeftX > colliderBottomY - originTopY)
                return TOP;

            return RIGHT;
        }

        return NONE;
    }

}
