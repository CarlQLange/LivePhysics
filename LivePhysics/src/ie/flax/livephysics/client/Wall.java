package ie.flax.livephysics.client;

import org.jbox2d.dynamics.BodyType;

import com.google.gwt.canvas.dom.client.Context2d;

public class Wall extends PhysicsObject {

    public Wall(float x, float y, int w, int h, BodyType t) {
        super(x, y, w, h, t);
    }

    @Override
    public void draw(Context2d ctx) {
        // blank on purpose
    }

    @Override
    public void update() {
        // blank on purpose
    }

}
