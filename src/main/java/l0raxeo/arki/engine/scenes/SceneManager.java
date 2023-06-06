package l0raxeo.arki.engine.scenes;

import l0raxeo.arki.engine.classStructure.ClassFinder;
import l0raxeo.arki.engine.ui.GuiLayer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class SceneManager
{

    private static final List<Scene> instantiatedScenes = new ArrayList<>();
    private static Scene activeScene = null;

    private SceneManager() {}

    public static <T extends Scene> Scene getScene(Class<T> sceneClass)
    {
        for (Scene s : instantiatedScenes)
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

        if (activeScene != null && activeScene.getClass().equals(sceneClass))
        {
            assert false : "Cannot change to current scene '" + activeScene + "'";
        }
        else
        {
            boolean sceneExists = false;

            for (Scene s : instantiatedScenes)
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
                    instantiatedScenes.add(targetScene);
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                         NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
            }

            GuiLayer.clear();

            if (activeScene != null)
                activeScene.onDestroy();
            activeScene = targetScene;
            activeScene.loadResources();
            activeScene.init();
            activeScene.start();
        }
    }

    public static void initializeDefaultScene()
    {
        try {
            Set<Class<?>> classesInArkiPackage = ClassFinder.findAllClassesUsingClassLoader("arkiGame.scenes");
            Class<?> defaultSceneClass = Objects.requireNonNull(ClassFinder.findAnnotatedClass(classesInArkiPackage, DefaultScene.class));
            changeScene(defaultSceneClass);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Scene getActiveScene()
    {
        return activeScene;
    }

}
