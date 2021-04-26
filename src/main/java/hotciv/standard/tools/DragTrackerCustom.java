package hotciv.standard.tools;

import minidraw.framework.Drawing;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.framework.Tool;
import minidraw.standard.AbstractTool;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class DragTrackerCustom extends AbstractTool implements Tool {

    private Figure figure;
    private int fLastX, fLastY; // previous mouse position


    public DragTrackerCustom(DrawingEditor editor, Figure figure) {
        super(editor);
        this.figure = figure;

    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y) {
        super.mouseDown(e, x, y);
        fLastX = x;
        fLastY = y;

        Drawing model = editor().drawing();

        if (e.isShiftDown()) {
            model.toggleSelection(figure);
        } else if (!model.selection().contains(figure)) {
            model.clearSelection();
            model.addToSelection(figure);
        }
    }

    @Override
    public void mouseDrag(MouseEvent e, int x, int y) {
        for (Figure f : editor().drawing().selection()) {
            f.moveBy(x - fLastX, y - fLastY);
        }
        fLastX = x;
        fLastY = y;
    }

    @Override
    public void keyDown(KeyEvent evt, int key) {
    }
}
