package l0raxeo.arki.engine.eventSystem;

import java.lang.reflect.Method;
import java.util.HashMap;

public class EventTrigger
{

    private final HashMap<Object, Method> subscribers = new HashMap<>();

    public EventTrigger(String referenceName)
    {
        EventHandler.addEventTrigger(referenceName, this);
    }

    public void triggerEvent(Object sender)
    {
        subscribers.forEach((subscriber, method) -> {
            invokeMethods(sender, subscriber, method);
        });
    }

    private void invokeMethods(Object sender, Object subscriber, Method method)
    {
        try {
            method.invoke(subscriber, sender);
        } catch (Exception e) {
            blindlyInvokeMethods(subscriber, method);
        }
    }

    private void blindlyInvokeMethods(Object subscriber, Method method)
    {
        try {
            method.invoke(subscriber);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void subscribe(Object subscriber, String methodName, Class<?>... parameterTypes)
    {
        try {
            subscribers.put(subscriber, subscriber.getClass().getMethod(methodName, parameterTypes));
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

}
