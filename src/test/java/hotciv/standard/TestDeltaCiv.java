package hotciv.standard;
import hotciv.framework.*;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TestDeltaCiv {
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
        }

        @Test
        public void spotTest1(){
            assertThat(game.getTileAt(new Position(0,0)).getTypeString(), is(GameConstants.OCEANS));
        }

        @Test
        public void spotTest2(){
           assertThat(game.getTileAt(new Position(5,6)).getTypeString(), is(GameConstants.PLAINS));
        }
        @Test
        public void spotTest3(){
            assertThat(game.getTileAt(new Position(12,1)).getTypeString(), is(GameConstants.OCEANS));
        }
        @Test
        public void spotTest4(){
           assertThat(game.getTileAt(new Position(9,2)).getTypeString(),is(GameConstants.FOREST));
        }
}
