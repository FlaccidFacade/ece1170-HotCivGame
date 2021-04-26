package hotciv.standard.tools;

import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.view.GfxConstants;
import minidraw.framework.Drawing;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Tool;
import minidraw.standard.AbstractTool;

import java.awt.event.MouseEvent;

public class EndOfTurnTool extends AbstractTool implements Tool {
    private Game game;
    /**
     * Abstract base class for all tools.
     *
     * @param editor the editor (object server) that this tool is associated with.
     */
    public EndOfTurnTool(DrawingEditor editor, Game game) {
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
        if(x > 555 && x < 590 && y > 60 && y < 110 ){
            game.endOfTurn();
        }
    }
}
