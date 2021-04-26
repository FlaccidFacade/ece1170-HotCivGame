package hotciv.visual;

import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.standard.UnitImpl;
import hotciv.standard.tools.ActionTool;
import hotciv.stub.StubGame2;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;
import minidraw.standard.NullTool;

/** Template code for exercise FRS 36.43.

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
public class ShowAction {
  
  public static void main(String[] args) {
    Game game = new GameImpl();
    game.placeUnitAt( new Position( 2,0), new UnitImpl(Player.RED, GameConstants.ARCHER));
    game.placeUnitAt( new Position( 2,1), new UnitImpl(Player.RED, GameConstants.ARCHER));


    game.placeUnitAt( new Position( 3,2), new UnitImpl(Player.BLUE, GameConstants.LEGION));

    game.placeUnitAt( new Position( 4,3), new UnitImpl(Player.RED, GameConstants.SETTLER));

    game.placeUnitAt( new Position( 6,4), new UnitImpl(Player.RED, GameConstants.UFO));

    game.placeCityAt( new Position( 6,6), new CityImpl(Player.RED));
    game.placeCityAt( new Position( 9,9), new CityImpl(Player.BLUE));

    DrawingEditor editor = 
      new MiniDrawApplication( "Shift-Click unit to invoke its action",  
                               new HotCivFactory4(game) );
    editor.open();
    editor.showStatus("Shift-Click on unit to see Game's performAction method being called.");

    editor.setTool( new ActionTool(editor,game) );
  }
}
