package l0raxeo.arki.engine.prefabs;

import l0raxeo.arki.engine.components.Component;
import l0raxeo.arki.engine.objects.GameObject;
import l0raxeo.arki.engine.objects.Transform;
import org.joml.Vector2i;
import org.joml.Vector3i;

public class Prefabs {

    public static void test()
    {

    }

    public static GameObject generate(String name, Vector3i pos, Vector2i size, Component... comps)
    {
        GameObject go = new GameObject(name, new Transform(pos.add(0, size.y, 0), size));

        for (Component c : comps)
            if (c != null)
                go.addComponent(c);

        return go;
    }

    public static GameObject generate(String name, Vector3i pos, Vector2i size, float rotation, Component... comps)
    {
        GameObject go = new GameObject(name, new Transform(pos.add(0, size.y, 0), size, rotation));

        for (Component c : comps)
            if (c != null)
                go.addComponent(c);

        return go;
    }

}
