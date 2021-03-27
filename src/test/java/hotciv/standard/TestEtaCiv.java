package hotciv.standard;

import hotciv.framework.Game;
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
