package l0raxeo.arki.engine.scenes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DefaultScene
{

    String windowTitle();
    int windowWidth();
    int windowHeight();

}
