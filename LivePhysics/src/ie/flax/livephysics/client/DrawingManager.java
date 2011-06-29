package ie.flax.livephysics.client;

import java.util.ArrayList;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.RootPanel;

/*
 * The drawing context is accessed by getContext() and the hud accessed by
 * getHud(). Further, the draw() methods should ask this to draw. This will
 * order the things to be drawn by colour, optimising the hell out of it.
 */
public class DrawingManager {
    private final boolean doubleBuffer;
    private final boolean colourManage;
    private String currentColour;
    private static Canvas drawCanvas;
    private static Canvas showCanvas;
    /*
     * To be honest this shouldn't be of pixelblocks, change if moving
     */
    private static ArrayList<GameObject> drawList = new ArrayList<GameObject>();

    /*
     * This constructor sets up the canvases (hud, and the two canvases for
     * buffering).
     */
    public DrawingManager(boolean doubleBuffer, boolean colourManage, int x,
            int y, int w, int h) {
        this.doubleBuffer = doubleBuffer;
        this.colourManage = colourManage;

        setupCanvas(w, h);
        showCanvas.setStyleName("game");
        RootPanel.get().add(showCanvas, x, y);

        if (!doubleBuffer) {
            drawCanvas = showCanvas;
        }
    }

    public void addToDrawList(GameObject objToAdd) {
        if (objToAdd != null) {
            drawList.add(objToAdd);
        }
        if (colourManage) {
            sortDisplayList();
        }
    }

    /*
     * Called in the game's main loop AND NOWHERE ELSE, YA HEAR
     */
    public void draw() {
        // clear all
        getContext().clearRect(0, 0, getCanvasWidth(), getCanvasHeight());
        // go through the display list and draw them to the drawCanvas
        for (GameObject i : drawList) {
            if ((i.colour != currentColour) && (colourManage)) {
                currentColour = i.colour;
                drawCanvas.getContext2d().setFillStyle(currentColour);
            }
            i.draw(getContext());
        }

        // swap buffers
        if (doubleBuffer) {
            swapBuffers();
        }
    }

    public int getCanvasHeight() {
        return showCanvas.getCoordinateSpaceHeight();
    }

    public int getCanvasWidth() {
        return showCanvas.getCoordinateSpaceWidth();
    }

    private Context2d getContext() {
        return drawCanvas.getContext2d();
    }

    private void setupCanvas(int w, int h) {
        drawCanvas = Canvas.createIfSupported();
        drawCanvas.setPixelSize(w, h);
        drawCanvas.setCoordinateSpaceWidth(w);
        drawCanvas.setCoordinateSpaceHeight(h);
        drawCanvas.addDomHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                event.preventDefault();
            }
        }, ClickEvent.getType());

        showCanvas = Canvas.createIfSupported();
        showCanvas.setPixelSize(w, h);
        showCanvas.setCoordinateSpaceWidth(w);
        showCanvas.setCoordinateSpaceHeight(h);
        showCanvas.addDomHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                event.preventDefault();
            }
        }, ClickEvent.getType());
    }

    private void sortDisplayList() {
        // sorts the display list by colour
        // Collections.sort(drawList);
    }

    private void swapBuffers() {
        showCanvas.getContext2d().clearRect(0, 0, getCanvasWidth(),
                getCanvasHeight());
        showCanvas.getContext2d()
                .drawImage(drawCanvas.getCanvasElement(), 0, 0);
    }
}
