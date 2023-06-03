package l0raxeo.arki.engine.scenes;

import l0raxeo.arki.engine.classStructure.ClassFinder;
import l0raxeo.arki.engine.ui.GuiLayer;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SceneManager
{

    private static final List<Scene> scenes = new ArrayList<>();
    private static Scene currentScene = null;

    private SceneManager() {}

    public static Scene getScene(Class<?> sceneClass)
    {
        for (Scene s : scenes)
            if (s.getClass().equals(sceneClass))
                return s;

        return null;
    }

    public static void changeScene(Class<?> sceneClass)
    {
        if (sceneClass.isInstance(Scene.class))
        {
            assert false : "Class '" + sceneClass + "' is not a subclass of Scene";
            return;
        }

        Scene targetScene = null;

        if (currentScene != null && currentScene.getClass().equals(sceneClass))
        {
            assert false : "Cannot change to current scene '" + currentScene + "'";
        }
        else
        {
            boolean sceneExists = false;

            for (Scene s : scenes)
                if (s.getClass().equals(sceneClass))
                {
                    sceneExists = true;
                    targetScene = s;
                    break;
                }

            if (!sceneExists)
            {
                try {
                    targetScene = (Scene) sceneClass.getDeclaredConstructor().newInstance();
                    scenes.add(targetScene);
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                         NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
            }

            GuiLayer.clear();

            if (currentScene != null)
                currentScene.onDestroy();
            currentScene = targetScene;
            currentScene.loadResources();
            currentScene.init();
            currentScene.start();
        }
    }

    public static void initializeScene()
    {
        Class<?> defaultSceneClass = Objects.requireNonNull(ClassFinder.findAnnotatedClass(ClassFinder.findAllClassesUsingClassLoader("arkiGame.scenes"), DefaultScene.class));
        changeScene(defaultSceneClass);
    }

    public static Scene getActiveScene()
    {
        return currentScene;
    }

}
