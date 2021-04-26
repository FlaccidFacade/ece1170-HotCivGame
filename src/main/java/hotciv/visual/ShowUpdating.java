package hotciv.visual;

import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.standard.UnitImpl;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

/** Show how GUI changes can be induced by making
    updates in the underlying domain model.

   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Computer Science Department
     Aarhus University
   
   This source code is provided WITHOUT ANY WARRANTY either 
   expressed or implied. You may study, use, modify, and 
   distribute it for non-commercial purposes. For any 
   commercial use, see http://www.baerbak.com/
 */
public class ShowUpdating {
  
  public static void main(String[] args) {
    Game game = new GameImpl();

    game.placeUnitAt( new Position( 2,0), new UnitImpl(Player.RED, GameConstants.ARCHER));

    game.placeUnitAt( new Position( 3,2), new UnitImpl(Player.BLUE, GameConstants.LEGION));

    game.placeUnitAt( new Position( 4,3), new UnitImpl(Player.RED, GameConstants.SETTLER));

    game.placeUnitAt( new Position( 6,4), new UnitImpl(Player.RED, GameConstants.UFO));

    game.placeCityAt( new Position( 6,6), new CityImpl(Player.RED));
    game.placeCityAt( new Position( 9,9), new CityImpl(Player.BLUE));

    game.changeProductionInCityAt(new Position(9,9), GameConstants.LEGION);
    game.changeWorkforceFocusInCityAt(new Position(9,9), GameConstants.foodFocus);

    DrawingEditor editor = 
      new MiniDrawApplication( "Click anywhere to see Drawing updates",  
                               new HotCivFactory4(game) );
    editor.open();
    editor.setTool( new UpdateTool(editor, game) );

    editor.showStatus("Click anywhere to state changes reflected on the GUI");
                      
    // Try to set the selection tool instead to see
    // completely free movement of figures, including the icon

    // editor.setTool( new SelectionTool(editor) );
  }
}

/** A tool that simply 'does something' new every time
 * the mouse is clicked anywhere; as a visual testing
 * of the 'from Domain to GUI' data flow is coded correctly*/
class UpdateTool extends NullTool {
  private Game game;
  private DrawingEditor editor;
  public UpdateTool(DrawingEditor editor, Game game) {
    this.editor = editor;
    this.game = game;
  }
  private int count = 0;
  public void mouseDown(MouseEvent e, int x, int y) {
    switch(count) {
      case 0: {
        editor.showStatus("State change: Moving archer to (1,1)");
        game.moveUnit(new Position(2, 0), new Position(1, 1));
        break;
      }
      case 1: {
        //This will not work since units can only move once a turn
        editor.showStatus("State change: ");
        //game.;
        break;
      }
      case 2: {
        editor.showStatus("State change: End of Turn (over to blue)");
        game.endOfTurn();
        break;
      }
      case 3: {
        editor.showStatus("State change: End of Turn (over to red)");
        game.endOfTurn();
        break;
      }
      case 4: {
        editor.showStatus("State change: Inspect Unit at (4,3)");
        game.setTileFocus(new Position(4, 3));
        break;
      }
      case 5:{
        editor.showStatus("State change: Instantly age to 1100");

        for(int i = 0 ; i < 100; i ++){
          game.endOfTurn();
        }

        break;
      }
      case 6:{
        editor.showStatus("State change: Focus City at (6,6)");
       game.setTileFocus(new Position(6,6));
        break;
      }
      case 7:{
        editor.showStatus("State change: Focus City at (9,9)");
        game.setTileFocus(new Position(9,9));
        break;
      }
      // TODO: Add more state changes for other things to test
    default: {
      editor.showStatus("No more changes in my list...");
    }
    }
    count ++;
  }
}

