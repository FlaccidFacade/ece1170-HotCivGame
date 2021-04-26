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
import minidraw.standard.StandardDrawing;
import minidraw.standard.handlers.*;

/**
 * Selection tool: Uses a internal state pattern to define what type of tool to
 * use in the current situation.
 *
 */

public class SetFocusTool extends AbstractTool implements Tool {

    private Position start;
    private Position stop;

    private Game game;
    /**


    /**
     * create the selection tool
     *
     * @param editor
     *          the editor the tool is associated with
     */
    public SetFocusTool(DrawingEditor editor, Game game) {
       super(editor);
        this.game = game;
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

        start = GfxConstants.getPositionFromXY(x,y);
        boolean columnCheck = start.getColumn() < GameConstants.WORLDSIZE;
        if(!columnCheck) return;

        boolean rowCheck = start.getRow() < GameConstants.WORLDSIZE;
        if(!rowCheck) return;

        if(columnCheck && rowCheck){
            game.setTileFocus(start);
        }
    }

    @Override
    public void mouseDrag(MouseEvent e, int x, int y) {

    }

    @Override
    public void mouseMove(MouseEvent e, int x, int y) {

    }

    @Override
    public void mouseUp(MouseEvent e, int x, int y) {

    }



}
