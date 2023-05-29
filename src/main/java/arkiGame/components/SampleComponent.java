package arkiGame.components;

import l0raxeo.arki.engine.components.Component;

import java.awt.*;

public class SampleComponent extends Component
{

    @Override
    public void update(double dt) {
        gameObject.transform.rotation++;
    }

    @Override
    public void render(Graphics g) {

    }

}
