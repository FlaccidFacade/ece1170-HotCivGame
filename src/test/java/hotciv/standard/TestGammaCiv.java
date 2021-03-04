package hotciv.standard;
import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import org.junit.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TestGammaCiv {
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
        game = new GameImpl(layout);
        game.setActionStrategy(new GammaActionStrategy());

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
}
