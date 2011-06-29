package ie.flax.livephysics.client;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;

import com.google.gwt.canvas.dom.client.Context2d;

public class Block extends PhysicsObject {

    public Block(float x, float y, int w, int h, BodyType t) {
        super(x, y, w, h, t);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void draw(Context2d context) {
        context.save();
        context.translate(pos.x * LivePhysics.PTM_RATIO, pos.y
                * LivePhysics.PTM_RATIO);
        context.rotate(body.getAngle());
        context.scale(LivePhysics.PTM_RATIO, LivePhysics.PTM_RATIO);

        context.beginPath();
        context.moveTo(0, 0);
        Vec2[] vec2 = shape.getVertices();
        for (int q = 0; q < shape.getVertexCount(); q++) {
            context.lineTo(vec2[q].x, vec2[q].y);
        }
        context.lineTo(vec2[0].x, vec2[0].y);
        context.closePath();
        context.fill();
        context.restore();
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        super.update();
    }

}
