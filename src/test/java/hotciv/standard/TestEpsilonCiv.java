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
                ".ooooooooooooooo",
                "ooMooooooooooooo",
                "oohooooooooooooo",
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


        game.placeUnitAt(new Position(2,0), new UnitImpl(Player.RED,GameConstants.ARCHER));
        game.placeUnitAt(new Position(2,1), new UnitImpl(Player.RED,GameConstants.ARCHER));
        game.placeUnitAt(new Position(4,3), new UnitImpl(Player.RED,GameConstants.SETTLER));
        game.placeUnitAt(new Position(3,2), new UnitImpl(Player.BLUE, GameConstants.LEGION));
        game.placeCityAt(new Position(1,1), new CityImpl(Player.RED));
        game.placeCityAt(new Position(4,1), new CityImpl(Player.BLUE));

    }

    @Test
    public void testforWinner(){
        //TODO Fake it

        //TODO Test it

        //TODO fake another

        //TODO Test it
    }

    @Test
    public void testforVictor(){
        attackingAttackerIsVictor();
        attackingDefenderIsVictor();
        attackingDestroys();
    }

    @Test
    public void attackingDestroys(){
        // blue's unit attack and destroy red's unit
        game.endOfTurn();
        assertThat(game.moveUnit(new Position(3,2), new Position(2,1)), is(true));

        assertThat(game.getUnitAt(new Position( 3,2)), is(nullValue()));

        assertThat(game.getUnitAt(new Position(2,1)).getOwner(), is(Player.BLUE));

        assertThat(game.getUnitAt( new Position(2,1)).getTypeString(), is(GameConstants.LEGION));
    }

    @Test
    public void attackingDefenderIsVictor(){


    }

    @Test
    public void attackingAttackerIsVictor(){


    }


}
