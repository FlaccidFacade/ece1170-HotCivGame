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
import javafx.geometry.Pos;
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
    public void testPlaceUnitAndMoveUnitPrint(){
        Position start = new Position(3,3);
        Position end = new Position(3,4);
        decorator.placeUnitAt(start, new UnitImpl(Player.RED, GameConstants.ARCHER));
        decorator.moveUnit(start, end);
    }

    @Test
    public void testPlaceCityAndChangeWorkforcePrint(){
        Position start = new Position(3,3);
        decorator.placeCityAt(start, new CityImpl(Player.RED));
        decorator.changeWorkforceFocusInCityAt(start, GameConstants.foodFocus);
        decorator.changeWorkforceFocusInCityAt(start, GameConstants.productionFocus);
    }

    @Test
    public void testChangeProductionPrint(){
        Position start = new Position(3,3);
        decorator.placeCityAt(start, new CityImpl(Player.RED));
        decorator.changeProductionInCityAt(start, GameConstants.SETTLER);
        decorator.changeProductionInCityAt(start, GameConstants.ARCHER);
        decorator.changeProductionInCityAt(start, GameConstants.LEGION);
    }

    @Test
    public void testActionPrint(){
        Position start = new Position(3,3);
        decorator.placeUnitAt(start, new UnitImpl(Player.RED, GameConstants.ARCHER));
        decorator.performUnitActionAt(start);
    }

    @Test
    public void testToggleLogging(){
        testGetWinnerPrint();
        testGetAgePrint();
        decorator.toggleLog();
        testGetWinnerPrint();
        testGetAgePrint();
    }
}
