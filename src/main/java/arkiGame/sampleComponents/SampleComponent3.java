package arkiGame.sampleComponents;

import l0raxeo.arki.engine.components.Component;
import org.joml.Vector2f;

public class SampleComponent3 extends Component {

    @Override
    public void update(double dt) {
        System.out.println("hi");
        gameObject.transform.moveScreenPosition(new Vector2f(1, 1));
    }

}
