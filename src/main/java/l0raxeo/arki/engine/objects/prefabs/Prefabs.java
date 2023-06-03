package l0raxeo.arki.engine.objects.prefabs;

import l0raxeo.arki.engine.components.Component;
import l0raxeo.arki.engine.objects.GameObject;
import l0raxeo.arki.engine.objects.Transform;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Prefabs {

    public static GameObject generate(String name, Vector3f pos, Vector2f size, Component... comps)
    {
        GameObject go = new GameObject(name, new Transform(pos.add(0, size.y, 0), size));

        for (Component c : comps)
            if (c != null)
                go.addComponent(c);

        return go;
    }

    public static GameObject generate(String name, Vector3f pos, Vector2f size, float rotation, Component... comps)
    {
        GameObject go = new GameObject(name, new Transform(pos.add(0, size.y, 0), size, rotation));

        for (Component c : comps)
            if (c != null)
                go.addComponent(c);

        return go;
    }

}
