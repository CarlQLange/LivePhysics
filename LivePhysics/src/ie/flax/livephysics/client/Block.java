package ie.flax.livephysics.client;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;

import com.google.gwt.canvas.dom.client.Context2d;

public class Block extends PhysicsObject {

    public Block(float x, float y, int w, int h, BodyType t) {
        super(x, y, w, h, t);

    }

    @Override
    public void draw(Context2d context) {
        context.save();
        context.translate(pos.x * LivePhysics.PTM_RATIO, pos.y
                * LivePhysics.PTM_RATIO);
        context.rotate(body.getAngle());
        context.scale(LivePhysics.PTM_RATIO, LivePhysics.PTM_RATIO);

        Vec2[] vec2 = shape.getVertices();
        context.fillRect(vec2[0].x - (1 / LivePhysics.PTM_RATIO), vec2[0].y
                - (1 / LivePhysics.PTM_RATIO), vec2[2].x
                + (2.0 / LivePhysics.PTM_RATIO), vec2[2].y
                + (2.0 / LivePhysics.PTM_RATIO));
        context.restore();
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        super.update();
    }

}
