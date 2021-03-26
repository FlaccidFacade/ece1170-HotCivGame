package hotciv.standard;

import hotciv.framework.*;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TestEpsilonCiv {
    private Game game;

    @Before
    public void setUp() {
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
        game = new GameImpl(layout);
        game.setWinningStrategy(new EpsilonWinningStrategy());
        game.setBattleStrategy(new EpsilonBattleStrategy());
        game.setAgingStrategy(new AlphaAgingStrategy());

    }

    @Test
    public void testForWinner(){
        game.placeUnitAt(new Position(2,1), new UnitImpl(Player.RED,GameConstants.LEGION));
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
        assertThat(game.moveUnit(new Position(3,2), new Position(2,1)), is(true));
        //TODO Fake it
        game.placeUnitAt(new Position(3,2), new UnitImpl(Player.BLUE, GameConstants.LEGION));
        game.placeCityAt(new Position(2,1), new CityImpl(Player.BLUE));
        game.placeUnitAt(new Position(1,0), new UnitImpl(Player.RED, GameConstants.LEGION));
        game.endOfTurn();
        game.endOfTurn();
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

    @Test
    public void attackingDestroys(){
        attackerIsDestroyed();
        defenderIsDestroyed();
    }

    @Test
    public void attackerIsDestroyed(){
        game.placeUnitAt(new Position(2,1), new UnitImpl(Player.RED,GameConstants.LEGION));
        game.placeUnitAt(new Position(3,2), new UnitImpl(Player.BLUE, GameConstants.ARCHER));
        game.placeCityAt(new Position(3,2), new CityImpl(Player.BLUE));
        game.placeUnitAt(new Position(2,3), new UnitImpl(Player.BLUE, GameConstants.LEGION));
        game.placeUnitAt(new Position(3,3), new UnitImpl(Player.BLUE, GameConstants.LEGION));
        game.placeUnitAt(new Position(4,3), new UnitImpl(Player.BLUE, GameConstants.LEGION));
        game.placeUnitAt(new Position(4,2), new UnitImpl(Player.BLUE, GameConstants.LEGION));
        game.placeUnitAt(new Position(4,1), new UnitImpl(Player.BLUE, GameConstants.LEGION));
        game.placeUnitAt(new Position(3,1), new UnitImpl(Player.BLUE, GameConstants.LEGION));
        assertThat(game.moveUnit(new Position(2,1), new Position(3,2)), is(false));
        assertThat(game.getUnitAt(new Position(2,2)), is(nullValue()));
        assertThat(game.getUnitAt(new Position(3,2)).getOwner(), is(Player.BLUE));
    }

    @Test
    public void defenderIsDestroyed(){
        game.placeUnitAt(new Position(2,1), new UnitImpl(Player.RED,GameConstants.LEGION));
        game.placeUnitAt(new Position(3,2), new UnitImpl(Player.BLUE, GameConstants.LEGION));
        game.placeCityAt(new Position(3,2), new CityImpl(Player.BLUE));
        game.placeUnitAt(new Position(2,3), new UnitImpl(Player.BLUE, GameConstants.LEGION));
        game.placeUnitAt(new Position(3,3), new UnitImpl(Player.BLUE, GameConstants.LEGION));
        game.placeUnitAt(new Position(4,3), new UnitImpl(Player.BLUE, GameConstants.LEGION));
        game.placeUnitAt(new Position(4,2), new UnitImpl(Player.BLUE, GameConstants.LEGION));
        game.placeUnitAt(new Position(4,1), new UnitImpl(Player.BLUE, GameConstants.LEGION));
        game.placeUnitAt(new Position(3,1), new UnitImpl(Player.BLUE, GameConstants.LEGION));
        // blue's unit attack and destroy red's unit
        game.endOfTurn();
        assertThat(game.moveUnit(new Position(3,2), new Position(2,1)), is(true));

        assertThat(game.getUnitAt(new Position( 3,2)), is(nullValue()));

        assertThat(game.getUnitAt(new Position(2,1)).getOwner(), is(Player.BLUE));

    }

}
