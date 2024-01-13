package l0raxeo.arki.engine.gameObjects;

import l0raxeo.arki.engine.components.Component;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameObject
{

    // Object ID System
    public static int ID_COUNTER = 0;

    private final String name;
    private final List<Component> components;
    private int uid = -1;
    public Transform transform;
    private boolean isDead = false;

    public GameObject(String name, Transform transform, Component... components) {
        this.name = name;
        this.components = new ArrayList<>();
        this.transform = transform;

        for (Component c : components) {
            addComponent(c);
        }

        generateUid();
    }

    public <T extends Component> T getComponent(Class<T> componentClass) {
        for (Component c : components) {
            if (componentClass.isAssignableFrom(c.getClass())) {
                try {
                    return componentClass.cast(c);
                } catch (ClassCastException e) {
                    e.printStackTrace();
                    assert false : "Error: Casting component.";
                }
            }
        }

        return null;
    }

    public Component getComponent(int uid)
    {
        for (Component c : components)
            if (c.uid() == uid)
                return c;

        return null;
    }

    public <T extends Component> boolean hasComponent(Class<T> componentClass)
    {
        for (Component c : components)
            if (componentClass.isAssignableFrom(c.getClass()))
                return true;

        return false;
    }

    public <T extends Component> void removeComponent(Class<T> componentClass)
    {
        for (int i=0; i < components.size(); i++) {
            Component c = components.get(i);
            if (componentClass.isAssignableFrom(c.getClass())) {
                components.remove(i);
                return;
            }
        }
    }

    public void addComponent(Component c)
    {
        c.generateId();
        this.components.add(c);
        c.gameObject = this;
    }

    public void update(double dt) {
        for (Component component : components) {
            component.update(dt);
        }
    }

    public void render(Graphics g)
    {
        for (Component component : components)
            component.render(g);
    }

    public void start() {
        for (Component component : components) {
            component.start();
        }
    }

    public String getName()
    {
        return this.name;
    }

    public int getUid()
    {
        return this.uid;
    }

    public List<Component> getAllComponents()
    {
        return this.components;
    }

    public boolean isDead()
    {
        return isDead;
    }

    public void die()
    {
        this.isDead = true;

        for (Component c : getAllComponents())
            c.onDestroy();
    }

    private void generateUid()
    {
        if (this.uid == -1)
            this.uid = ID_COUNTER++;
    }

}
