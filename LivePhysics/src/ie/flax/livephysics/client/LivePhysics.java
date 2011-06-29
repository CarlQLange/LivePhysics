package ie.flax.livephysics.client;

import java.util.ArrayList;
import java.util.Date;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.World;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;

public class LivePhysics implements EntryPoint {
    static World world;
    static DrawingManager dm;
    ArrayList<GameObject> objs = new ArrayList<GameObject>();
    final int FRAMES_PER_SECOND = 35;
    static final int PTM_RATIO = 30;
    static int x, y, w, h;

    @Override
    public void onModuleLoad() {
        world = new World(new Vec2(0, 10), true);
        dm = new DrawingManager(false, false, 0, 0,
                Window.getClientWidth() - 1, Window.getClientHeight() - 1);

        objs.add(new Block(40, 40, 40, 40, BodyType.DYNAMIC));
        setupWalls();

        RootPanel.get().addDomHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                objs.add(new Block(event.getClientX(), event.getClientY(), 40,
                        40, BodyType.DYNAMIC));
            }
        }, ClickEvent.getType());

        gameLoop();
    }

    private void gameLoop() {
        Timer timer = new Timer() {
            @Override
            public void run() {
                long oldTime = new Date().getTime();
                world.step(1.0f / FRAMES_PER_SECOND, 10, 10);
                if (!update()) {
                    cancel();
                }
                dm.draw();
                long newTime = new Date().getTime();
                System.out.println(newTime - oldTime);
            }

        };
        timer.scheduleRepeating(1000 / FRAMES_PER_SECOND);

        Timer t = new Timer() {

            @Override
            public void run() {
                objs.add(new Block(Random.nextInt(Window.getClientWidth()), 0,
                        10, 10, BodyType.DYNAMIC));
            }
        };
        // t.scheduleRepeating(200);
    }

    private void setupWalls() {
        // bottom
        objs.add(new Wall(0, dm.getCanvasHeight(), dm.getCanvasWidth() * 2, 1,
                BodyType.STATIC));
        // left
        objs.add(new Wall(0, 0, 1, dm.getCanvasHeight() * 2, BodyType.STATIC));
        // right
        objs.add(new Wall(dm.getCanvasWidth(), 0, 1, dm.getCanvasHeight() * 2,
                BodyType.STATIC));
    }

    private boolean update() {
        for (GameObject i : objs) {
            i.update();
        }
        return true;
    }
}
