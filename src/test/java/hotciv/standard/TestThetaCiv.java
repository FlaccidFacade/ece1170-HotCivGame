package hotciv.standard;
import hotciv.framework.*;
import hotciv.standard.Factories.GammaCivFactory;
import hotciv.standard.Strategies.ThetaActionStrategy;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class TestThetaCiv {
    private Game game;
    String[] layout = new String[] {
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
    @Before
    public void setUp(){
        game = new GameImpl(layout, new GammaCivFactory());
        game.setActionStrategy(new ThetaActionStrategy());

    }
    @Test
    public void testUnitAction(){
        game.placeUnitAt(new Position(2,1), new UnitImpl(Player.RED, GameConstants.ARCHER));
        assertThat(game.getUnitAt(new Position(2,1)).getMoveCount(),is(1));
        assertThat(game.getUnitAt(new Position(2,1)).getDefensiveStrength(),is(3));
        game.performUnitActionAt(new Position(2,1));
        assertThat(game.getUnitAt(new Position(2,1)).getMoveCount(),is(0));
        assertThat(game.getUnitAt(new Position(2,1)).getDefensiveStrength(),is(6));

        game.performUnitActionAt(new Position(2,1));

        assertThat(game.getUnitAt(new Position(2,1)).getMoveCount(),is(1));
        assertThat(game.getUnitAt(new Position(2,1)).getDefensiveStrength(),is(3));


        game.placeUnitAt(new Position( 3, 1), new UnitImpl(Player.RED,GameConstants.SETTLER));
        assertThat(game.getUnitAt(new Position(3,1)), is(notNullValue()));
        assertThat(game.getCityAt(new Position(3,1)), is(nullValue()));
        game.performUnitActionAt(new Position(3,1));
        assertThat(game.getUnitAt(new Position( 3, 1)), is(nullValue()));
        assertThat(game.getCityAt(new Position(3,1)), is(notNullValue()));

        assertThat(game.getCityAt(new Position(3,1)).getOwner(), is(Player.RED));
    }

    @Test
    public void testUfoExists(){
        game.placeUnitAt(new Position(5,6), new UnitImpl(Player.RED, GameConstants.UFO));
        Unit UFO = game.getUnitAt(new Position(5,6));
        assertThat(UFO.getTypeString(), is(GameConstants.UFO));
        assertThat(UFO.getMoveCount(), is(2));
        assertThat(UFO.getDefensiveStrength(), is(8));
        assertThat(UFO.getAttackingStrength(), is(1));

    }

    @Test
    public void testUFOMoveTwice(){
        game.placeUnitAt(new Position(5,6), new UnitImpl(Player.RED, GameConstants.UFO));
        Unit UFO = game.getUnitAt(new Position(5,6));
        assertThat(UFO.getTypeString(), is(GameConstants.UFO));
        //move to 5,7
        assertThat(game.getPlayerInTurn(), is(Player.RED));

        assertThat(game.getUnitAt(new Position(5,7)), is(nullValue()));
        game.moveUnit(new Position(5,6), new Position(5,7));
        assertThat(game.getUnitAt(new Position(5,6)), is(nullValue()));
        assertThat(game.getUnitAt(new Position(5,7)), is(not(nullValue())));

        //then move to 5,8
        assertThat(game.getPlayerInTurn(), is(Player.RED));
        assertThat(game.getUnitAt(new Position(5,8)), is(nullValue()));
        game.moveUnit(new Position(5,7), new Position(5,8));
        assertThat(game.getUnitAt(new Position(5,7)), is(nullValue()));
        assertThat(game.getUnitAt(new Position(5,8)), is(not(nullValue())));

        //try to move again but fail
        assertThat(game.getPlayerInTurn(), is(Player.RED));
        assertThat(game.getUnitAt(new Position(5,9)), is(nullValue()));
        game.moveUnit(new Position(5,8), new Position(5,9));
        assertThat(game.getUnitAt(new Position(5,9)), is(nullValue()));
        assertThat(game.getUnitAt(new Position(5,8)), is(not(nullValue())));
    }

    @Test
    public void testCitiesProduceUFO(){
        game.placeCityAt(new Position(5,6), new CityImpl(Player.RED));
        assertThat(game.getCityAt(new Position(5,6)), is(not(nullValue())));
        game.changeProductionInCityAt(new Position(5,6), GameConstants.UFO);
        for(int i = 0; i < 20; i++){
            game.endOfTurn();
        }

        assertThat(game.getCityAt(new Position(5,6)).getTreasury(), is(0));
        assertThat(game.getUnitAt(new Position(5,6)).getTypeString(), is(GameConstants.UFO));
    }

    @Test
    public void testUfoOverEnemyCity(){
        game.placeCityAt(new Position(5,6), new CityImpl(Player.BLUE));
        game.placeUnitAt(new Position(5,6), new UnitImpl(Player.RED,GameConstants.UFO));
    }

    @Test
    public void testUfoAbductsEnemyCity(){
        game.placeCityAt(new Position(5,6), new CityImpl(Player.BLUE));
        game.placeUnitAt(new Position(5,6), new UnitImpl(Player.RED,GameConstants.UFO));
        assertThat(game.getUnitAt(new Position(5,6)).getTypeString(), is(GameConstants.UFO));
        assertThat(game.getCityAt(new Position(5,6)).getPopulation(), is(1));
        game.performUnitActionAt(new Position(5,6));
        //cities are destroyed at a population of zero
        assertThat(game.getCityAt(new Position(5,6)), is(nullValue()));
    }

    @Test
    public void testUfoBattlesOverCity(){
        game.placeCityAt(new Position(5,6), new CityImpl(Player.BLUE));
        game.placeUnitAt(new Position(5,7), new UnitImpl(Player.RED,GameConstants.UFO));
        game.placeUnitAt(new Position(5,6), new UnitImpl(Player.BLUE,GameConstants.ARCHER));
        assertThat(game.moveUnit(new Position(5,7),new Position(5,6)), is(Boolean.TRUE));

        assertThat(game.getUnitAt(new Position(5,6)).getOwner(),is(Player.RED));
    }
}
