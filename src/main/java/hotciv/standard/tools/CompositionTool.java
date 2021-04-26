package hotciv.standard.tools;


import java.awt.event.MouseEvent;

import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.view.GfxConstants;
import javafx.geometry.Pos;
import minidraw.framework.*;
import minidraw.standard.AbstractTool;
import minidraw.standard.NullTool;
import minidraw.standard.handlers.*;

/**
 * Selection tool: Uses a internal state pattern to define what type of tool to
 * use in the current situation.
 *
 */

public class CompositionTool extends AbstractTool implements Tool {

    private Position start;
    private Position stop;
    private boolean upEventFlag = false;
    private Game game;
    /**
     * Sub tool to delegate to. The selection tool is in itself a state tool that
     * may be in one of several states given by the sub tool. Class Invariant:
     * fChild tool is never null
     */
    protected Tool fChild;
    /**
     * helper null tool to avoid creating and destroying objects all the time
     */
    protected Tool cachedNullTool;

    /**
     * the figure that is being dragged. If null then its operation is not that of
     * dragging a figure (or a set of figures)
     */
    protected Figure draggedFigure;

    /** the rubber band selection strategy to use. */
    RubberBandSelectionStrategy selectionStrategy;

    /**
     * create the selection tool
     *
     * @param editor
     *          the editor the tool is associated with
     */
    public CompositionTool(DrawingEditor editor, Game game) {
        this(editor, new StandardRubberBandSelectionStrategy());
        this.game = game;
    }

    /**
     * define a selection tool where the SelectAreaTracker takes a special
     * RubberBandSelection strategy.
     *
     * @param editor
     *          the editor to be associated with
     * @param selectionStrategy
     *          the rubberband selection strategy to use
     */
    public CompositionTool(DrawingEditor editor,
                        RubberBandSelectionStrategy selectionStrategy) {
        super(editor);
        fChild = cachedNullTool = new NullTool();
        draggedFigure = null;
        this.selectionStrategy = selectionStrategy;
    }

    /**
     * Handles mouse down events and starts the corresponding tracker.
     *
     * @param e
     *          the mouse event itself
     * @param x
     *          x coordinate
     * @param y
     *          y coordinate
     */
    @Override
    public void mouseDown(MouseEvent e, int x, int y) {

        if(x > 555 && x < 590 && y > 60 && y < 110 ){
            game.endOfTurn();
        }



        start = GfxConstants.getPositionFromXY(x,y);
        boolean columnCheck = start.getColumn() < GameConstants.WORLDSIZE;
        if(!columnCheck) return;

        boolean rowCheck = start.getRow() < GameConstants.WORLDSIZE;
        if(!rowCheck) return;

        if(columnCheck && rowCheck){
            game.setTileFocus(start);
        }
        boolean unitExists = game.getUnitAt(start) != null;
        if(!unitExists) return;



        if(columnCheck && rowCheck && unitExists && !e.isShiftDown()){

            Drawing model = editor().drawing();

            model.lock();
            draggedFigure = model.findFigure(e.getX(), e.getY());

            if (draggedFigure != null) {

                fChild = createDragTracker(draggedFigure);
            } else {
                if (!e.isShiftDown()) {
                    model.clearSelection();
                }
                fChild = createAreaTracker();
            }
            fChild.mouseDown(e, x, y);
        }else if(e.isShiftDown()){
            upEventFlag = true;
            game.performUnitActionAt(start);
            editor.drawing().requestUpdate();
        }
    }

    @Override
    public void mouseDrag(MouseEvent e, int x, int y) {
        upEventFlag = true;
        fChild.mouseDrag(e, x, y);
    }

    @Override
    public void mouseMove(MouseEvent e, int x, int y) {
        fChild.mouseMove(e, x, y);
    }

    @Override
    public void mouseUp(MouseEvent e, int x, int y) {
        if(upEventFlag) {
            editor().drawing().unlock();

            fChild.mouseUp(e, x, y);
            fChild = cachedNullTool;
            draggedFigure = null;

            stop = GfxConstants.getPositionFromXY(x, y);
            boolean columnCheck = start.getColumn() < GameConstants.WORLDSIZE;
            if (!columnCheck) return;

            boolean rowCheck = start.getRow() < GameConstants.WORLDSIZE;
            if (!rowCheck) return;

            if (!game.moveUnit(start, stop)) {
                editor().drawing().requestUpdate();
            }
            upEventFlag = false;
        }
    }

    /**
     * Factory method to create a Drag tracker. It is used to drag a figure.
     *
     * @param f
     *          the figure to drag
     * @return the tool to drag it
     */
    protected Tool createDragTracker(Figure f) {
        return new DragTracker(editor(), f);
    }

    /**
     * Factory method to create an Area Tracker. It is used to select an area.
     *
     * @return the tool to allow dragging figures in an area
     */
    protected Tool createAreaTracker() {
        return new SelectAreaTracker(editor(), selectionStrategy);
    }

}
