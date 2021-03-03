package hotciv.standard;

import hotciv.framework.*;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.*;


public class TestBetaCiv {
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
    /** Fixture for alphaciv testing. */
    @Before
    public void setUp() {
        game = new GameImpl(layout);
        game.setAgingStrategy( new BetaAgingStrategy());
    }


    @Test
    public void startAge(){

    }

    @Test
    public void stage1(){

    }
    @Test
    public void stage2(){

    }
    @Test
    public void stage3(){

    }

    @Test
    public void stage4(){

    }
    @Test
    public void stage5(){

    }
    @Test
    public void stage6(){

    }






}
