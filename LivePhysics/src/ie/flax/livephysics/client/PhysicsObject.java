package ie.flax.livephysics.client;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

public abstract class PhysicsObject extends GameObject {
    /*
     * These hold the PIXEL VALUES. Divide them by the PTM ratio before passing
     * them to box2d ANYWHERE
     */
    Vec2 pos = new Vec2(0, 0);
    float width = 0;
    float height = 0;
    Body body;
    PolygonShape shape;
    float angle = 0.0f;

    public PhysicsObject(float x, float y, int w, int h, BodyType t) {
        super();
        pos.x = x;
        pos.y = y;
        height = h;
        width = w;

        shape = new PolygonShape();
        shape.setAsBox((width / 2) / LivePhysics.PTM_RATIO, (height / 2)
                / LivePhysics.PTM_RATIO);

        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        fd.density = 100;
        fd.friction = 0.9f;
        fd.restitution = 0.1f;

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(pos.x / LivePhysics.PTM_RATIO, pos.y
                / LivePhysics.PTM_RATIO);
        bodyDef.type = t;

        body = LivePhysics.world.createBody(bodyDef);
        body.createFixture(fd);
    }

    @Override
    public void update() {
        pos = body.getPosition();
    }
}
