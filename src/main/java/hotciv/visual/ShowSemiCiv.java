package hotciv.visual;

import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.CityImpl;
import hotciv.standard.Factories.SemiCivFactory;
import hotciv.standard.GameImpl;
import hotciv.standard.UnitImpl;
import hotciv.standard.tools.CompositionTool;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;

public class ShowSemiCiv {

    final static String[] layout = new String[] {
            "...ooMooooo.....",
            "..ohhoooofffoo..",
            ".oooooMooo...oo.",
            ".ooMMMoooo..oooo",
            "...ofooohhoooo..",
            ".ofoofooooohhoo.",
            "...ooo..........",
            ".ooooo.ooohooM..",
            ".ooooo.oohooof..",
            "offfoooo.offoooo",
            "oooooooo...ooooo",
            ".ooMMMoooo......",
            "..ooooooffoooo..",
            "....ooooooooo...",
            "..ooohhoo.......",
            ".....ooooooooo..",
    };
    public static void main(String[] args){
        Game game = new GameImpl(layout, new SemiCivFactory());

        game.placeUnitAt( new Position( 2,0), new UnitImpl(Player.RED, GameConstants.ARCHER));
        game.placeUnitAt( new Position( 2,1), new UnitImpl(Player.RED, GameConstants.ARCHER));


        game.placeUnitAt( new Position( 3,2), new UnitImpl(Player.BLUE, GameConstants.LEGION));

        game.placeUnitAt( new Position( 4,3), new UnitImpl(Player.RED, GameConstants.SETTLER));

        game.placeUnitAt( new Position( 6,4), new UnitImpl(Player.RED, GameConstants.UFO));

        game.placeUnitAt(new Position( 10,12), new UnitImpl(Player.BLUE, GameConstants.SETTLER));
        game.placeCityAt( new Position( 6,6), new CityImpl(Player.RED));
        game.placeCityAt( new Position( 9,9), new CityImpl(Player.BLUE));
        DrawingEditor editor =
                new MiniDrawApplication( "Click and/or drag any item to see all game actions",
                        new HotCivFactory4(game) );
        editor.open();
        editor.showStatus("Click and drag any item to see Game's proper response.");


        editor.setTool( new CompositionTool(editor,game) );
    }
}
