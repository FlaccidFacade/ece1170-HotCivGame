package hotciv.standard;

import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class TestZetaCiv {

    private Game game;
    String[] layout = new String[] {
            "oooooooooooooooo",
            "oooooooooooooooo",
            "oooooooooooooooo",
            "oooooooooooooooo",
            "oooooooooooooooo",
            "oooooooooooooooo",
            "oooooooooooooooo",
            "oooooooooooooooo",
            "oooooooooooooooo",
            "oooooooooooooooo",
            "oooooooooooooooo",
            "oooooooooooooooo",
            "oooooooooooooooo",
            "oooooooooooooooo",
            "oooooooooooooooo",
            "oooooooooooooooo"
    };
    @Before
    public void setUp() {
        game = new GameImpl(layout);
        game.setAgingStrategy( new AlphaAgingStrategy());
        game.setWinningStrategy( new ZetaWinningStrategy());
        game.setBattleStrategy( new AlphaBattleStrategy());
        game.setActionStrategy( new AlphaActionStrategy());
    }


    @Test
    public void winningStrategyTestBefore20Rounds(){
        //"conquers all cities in the world"
        //
        //FAKE that red has ownership of all cities
        game.placeCityAt(new Position(1,5), new CityImpl(Player.BLUE));
        game.placeCityAt(new Position(3,8), new CityImpl(Player.BLUE));

        assertThat(game.getWinner(), is(Player.BLUE));
        assertThat(game.getWinner(), is(not(Player.RED)));
        game.placeCityAt(new Position(1,5), new CityImpl(Player.RED));

        assertThat(game.getWinner(), is(nullValue()));

    }

    @Test
    public void winningStrategyTestAfter20Rounds(){
        for(int i = 0; i < 40; i++){
            game.endOfTurn();
        }
        game.placeUnitAt(new Position(2,1), new UnitImpl(Player.RED, GameConstants.LEGION));
        game.placeUnitAt(new Position(3,2), new UnitImpl(Player.BLUE, GameConstants.LEGION));
        game.placeCityAt(new Position(3,2), new CityImpl(Player.BLUE));
        game.placeUnitAt(new Position(2,2), new UnitImpl(Player.BLUE,GameConstants.LEGION));
        game.placeUnitAt(new Position(2,3), new UnitImpl(Player.BLUE, GameConstants.LEGION));
        game.placeUnitAt(new Position(3,3), new UnitImpl(Player.BLUE, GameConstants.LEGION));
        game.placeUnitAt(new Position(4,3), new UnitImpl(Player.BLUE, GameConstants.LEGION));
        game.placeUnitAt(new Position(4,2), new UnitImpl(Player.BLUE, GameConstants.LEGION));
        game.placeUnitAt(new Position(4,1), new UnitImpl(Player.BLUE, GameConstants.LEGION));
        game.placeUnitAt(new Position(3,1), new UnitImpl(Player.BLUE, GameConstants.LEGION));
        game.placeUnitAt(new Position(3,1), new UnitImpl(Player.BLUE, GameConstants.LEGION));
        game.placeUnitAt(new Position(1,2), new UnitImpl(Player.BLUE, GameConstants.LEGION));
        game.placeUnitAt(new Position(1,1), new UnitImpl(Player.BLUE, GameConstants.LEGION));
        game.placeUnitAt(new Position(3,0), new UnitImpl(Player.BLUE, GameConstants.LEGION));
        game.placeUnitAt(new Position(2,0), new UnitImpl(Player.BLUE, GameConstants.LEGION));
        game.placeUnitAt(new Position(0,1), new UnitImpl(Player.BLUE, GameConstants.LEGION));
        // blue's unit attack and destroy red's unit
        game.endOfTurn();
        assertThat(game.getPlayerInTurn(), is(Player.BLUE));
        assertThat(game.moveUnit(new Position(3,2), new Position(2,1)), is(true));
        //TODO Fake it
        game.placeUnitAt(new Position(3,2), new UnitImpl(Player.BLUE, GameConstants.LEGION));
        game.placeCityAt(new Position(2,1), new CityImpl(Player.BLUE));
        game.placeUnitAt(new Position(1,0), new UnitImpl(Player.RED, GameConstants.LEGION));
        game.endOfTurn();
        game.endOfTurn();
        assertThat(game.getPlayerInTurn(), is(Player.BLUE));
        assertThat(game.moveUnit(new Position(2,1), new Position(1,0)) , is(true));

        game.placeUnitAt(new Position(2,1), new UnitImpl(Player.BLUE, GameConstants.LEGION));
        game.placeCityAt(new Position( 1, 0), new CityImpl(Player.BLUE));


        game.placeUnitAt(new Position(0,0), new UnitImpl(Player.RED, GameConstants.LEGION));
        game.endOfTurn();
        game.endOfTurn();
        assertThat(game.moveUnit(new Position(1,0), new Position(0,0)), is(true));
        //TODO Test it
        assertThat(game.getWinner(), is(Player.BLUE));

    }
}
