package l0raxeo.arki.engine.scenes;


import l0raxeo.arki.engine.components.Component;
import l0raxeo.arki.engine.objects.GameObject;
import l0raxeo.arki.renderer.AppWindow;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Scene
{

    protected List<GameObject> gameObjects = new ArrayList<>();
    protected Color backdrop = new Color(0, 0, 0, 0);

    public Scene() {

    }

    public void init() {

    }

    public void start() {
        for (GameObject go : gameObjects) {
            go.start();
        }
    }

    public void addGameObject(GameObject go) {
        gameObjects.add(go);
        go.start();
    }

    public GameObject getGameObjectWithUid(int uid)
    {
        int low = 0;
        int high = getGameObjects().size() - 1;

        while (low <= high)
        {
            int midIndex = (low + high) >>> 1;
            Comparable<Integer> midUid = getGameObjects().get(midIndex).getUid();
            int cmp = midUid.compareTo(uid);

            if (cmp < 0) low = midIndex + 1;
            else if (cmp > 0) high = midIndex - 1;
            else return getGameObjects().get(midIndex);
        }

        return null;
    }

    public List<GameObject> getGameObjects()
    {
        return this.gameObjects;
    }

    public List<GameObject> getGameObjectsWithComponent(Class<? extends Component> componentClass)
    {
        List<GameObject> result = new ArrayList<>();

        for (GameObject go : getGameObjects())
            if (go.hasComponent(componentClass))
                result.add(go);

        return result;
    }

    public abstract void update(double dt);
    public abstract void render(Graphics g);

    public void loadResources() {}

    protected void setBackdrop(Color color)
    {
        this.backdrop = color;
        setBackdrop();
    }

    private void setBackdrop()
    {
        AppWindow.setBackdrop(this.backdrop);
    }

    public void onDestroy() {}

}
