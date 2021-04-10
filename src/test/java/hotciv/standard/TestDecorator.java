package hotciv.standard;

import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.Decorators.GameWrapper;
import hotciv.standard.Factories.AlphaCivFactory;
import hotciv.standard.Strategies.AlphaActionStrategy;
import hotciv.standard.Strategies.AlphaAgingStrategy;
import hotciv.standard.Strategies.AlphaBattleStrategy;
import hotciv.standard.Strategies.AlphaWinningStrategy;
import org.junit.Before;
import org.junit.Test;

public class TestDecorator {
    private Game decorator;
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
        decorator = new GameWrapper(new GameImpl(layout, new AlphaCivFactory()));
    }

    @Test
    public void testCityPrint(){
        decorator.placeCityAt(new Position(5,5), new CityImpl(Player.RED));
    }

    @Test
    public void testPlayerInTurnPrint(){
        decorator.getPlayerInTurn();
    }

    @Test
    public void testGetWinnerPrint(){
        decorator.getWinner();
        for(int i = 0; i < 30; i++){
            decorator.endOfTurn();
        }
        decorator.getWinner();
    }

    @Test
    public void testGetAgePrint(){
        decorator.getAge();
    }

    @Test
    public void testMoveUnitPrint(){

        //decorator.moveUnit();
    }

    @Test
    public void testChangeWorkforcePrint(){


    }

    @Test
    public void testChangeProductionPrint(){


    }

    @Test
    public void testActionPrint(){


    }

    @Test
    public void testPlaceUnitPrint(){


    }

    @Test
    public void testPlaceCityPrint(){


    }

    @Test
    public void testToggleLogging(){


    }
}
