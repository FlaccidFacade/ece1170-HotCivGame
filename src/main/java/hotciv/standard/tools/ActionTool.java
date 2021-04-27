package hotciv.standard.tools;

import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.view.GfxConstants;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Tool;
import minidraw.standard.AbstractTool;

import java.awt.event.MouseEvent;

public class ActionTool extends AbstractTool implements Tool {
    private Position start;
    private Game game;
    /**
     * Abstract base class for all tools.
     *
     * @param editor the editor (object server) that this tool is associated with.
     */
    public ActionTool(DrawingEditor editor, Game game) {
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

        boolean unitExists = game.getUnitAt(start) != null;
        if(!unitExists) return;

        if(e.isShiftDown()){
            game.performUnitActionAt(start);
            editor.drawing().requestUpdate();
        }

    }
}
