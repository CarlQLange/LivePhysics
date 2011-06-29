package ie.flax.livephysics.client;

import com.google.gwt.canvas.dom.client.Context2d;

public abstract class GameObject {

    public String colour;

    public GameObject() {
        // add to draw list
        LivePhysics.dm.addToDrawList(this);
        System.out.println("new block");
    }

    public abstract void draw(Context2d context);

    public abstract void update();

}
