package l0raxeo.arki.engine.eventSystem;

import java.util.HashMap;

public class EventHandler
{

    private final static HashMap<String, EventTrigger> eventTriggers = new HashMap<>();

    public static EventTrigger getEventTrigger(String referenceName)
    {
        return eventTriggers.get(referenceName);
    }

    public static void addEventTrigger(String referenceName, EventTrigger eventTrigger)
    {
        eventTriggers.put(referenceName, eventTrigger);
    }

}
