package hotciv.standard;

import hotciv.framework.Game;
import hotciv.standard.Strategies.AlphaActionStrategy;
import hotciv.standard.Strategies.AlphaAgingStrategy;
import hotciv.standard.Strategies.AlphaBattleStrategy;
import hotciv.standard.Strategies.AlphaWinningStrategy;
import org.junit.Before;

public class TestEtaCiv {
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
        game.setWinningStrategy( new AlphaWinningStrategy());
        game.setBattleStrategy( new AlphaBattleStrategy());
        game.setActionStrategy( new AlphaActionStrategy());
    }



}
