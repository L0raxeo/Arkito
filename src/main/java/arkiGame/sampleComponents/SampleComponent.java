package arkiGame.sampleComponents;

import l0raxeo.arki.engine.assetFiles.AssetPool;
import l0raxeo.arki.engine.audio.AudioClip;
import l0raxeo.arki.engine.audio.AudioManager;
import l0raxeo.arki.engine.components.Component;
import l0raxeo.arki.engine.components.collisionComponents.RigidBody;
import l0raxeo.arki.engine.eventSystem.EventHandler;
import l0raxeo.arki.engine.input.mouse.MouseManager;
import l0raxeo.arki.engine.scenes.Camera;
import org.joml.Vector2f;

public class SampleComponent extends Component
{
    private AudioClip sampleAudioClip;

    @Override
    public void start()
    {
        sampleAudioClip = AssetPool.getAudioClip("sample_audio");
        EventHandler.getEventTrigger("sampleEvent").subscribe(this, "onClick");
    }

    @Override
    public void update(double dt)
    {
        if (MouseManager.hasPressedInput())
        {
            gameObject.getComponent(RigidBody.class).addForce(new Vector2f(0, -10));
            EventHandler.getEventTrigger("sampleEvent").triggerEvent(this);
        }

        gameObject.transform.rotation = Camera.getDegFromOriginToTarget(
                (int) gameObject.transform.getScreenCenterPosition().x,
                (int) gameObject.transform.getScreenCenterPosition().y,
                MouseManager.getScreenMouseX(),
                MouseManager.getScreenMouseY()
        );
    }

    public void onClick()
    {
        AudioManager.play(sampleAudioClip);
    }

}
