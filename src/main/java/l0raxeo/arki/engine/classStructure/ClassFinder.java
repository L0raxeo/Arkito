package l0raxeo.arki.engine.classStructure;

import java.io.*;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClassFinder
{

    public static Class<?> findAnnotatedClass(Set<Class<?>> classes, Class<? extends Annotation> annotation)
    {
        for (Class<?> c : classes)
        {
            if (c.isAnnotationPresent(annotation))
                return c;
        }

        return null;
    }

    public static Set<Class<?>> findAllClassesInPackage(String packageName) throws IOException, ClassNotFoundException {
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(path);
        Set<Class<?>> classes = new HashSet<>();

        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            classes.addAll(getClassesFromResource(resource, path, packageName));
        }

        return classes;
    }

    private static Set<Class<?>> getClassesFromResource(URL resource, String path, String packageName) throws IOException, ClassNotFoundException {
        Set<Class<?>> classes = new HashSet<>();

        if (resource.getProtocol().equalsIgnoreCase("file")) {
            classes.addAll(getClassesFromFile(resource, packageName));
        } else if (resource.getProtocol().equalsIgnoreCase("jar")) {
            classes.addAll(getClassesFromJar(resource, path));
        }

        return classes;
    }

    private static Set<Class<?>> getClassesFromFile(URL resource, String packageName) throws ClassNotFoundException {
        Set<Class<?>> classes = new HashSet<>();
        File dir = new File(resource.getFile());

        for (File file : Objects.requireNonNull(dir.listFiles())) {
            if (file.getName().endsWith(".class")) {
                String className = packageName + '.' + file.getName().substring(0, file.getName().length() - 6);
                classes.add(Class.forName(className));
            }
        }

        return classes;
    }

    private static Set<Class<?>> getClassesFromJar(URL resource, String path) throws IOException, ClassNotFoundException {
        Set<Class<?>> classes = new HashSet<>();
        String jarPath = resource.getPath().substring(5, resource.getPath().indexOf("!"));
        JarFile jar = new JarFile(URLDecoder.decode(jarPath, StandardCharsets.UTF_8));
        Enumeration<JarEntry> entries = jar.entries();

        while (entries.hasMoreElements()) {
            JarEntry entry = entries.nextElement();
            String entryName = entry.getName();

            if (entryName.endsWith(".class") && entryName.startsWith(path) && entryName.length() > path.length() + 1) {
                String className = entryName.substring(0, entryName.length() - 6).replace('/', '.');
                classes.add(Class.forName(className));
            }
        }

        jar.close();

        return classes;
    }

}
