package hotciv.standard;

import hotciv.framework.*;

import hotciv.standard.Strategies.BetaAgingStrategy;
import hotciv.standard.Strategies.BetaWinningStrategy;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


public class TestBetaCiv {
    private Game game;
    final String[] layout = new String[] {
            "ohoooooooooooooo",
            ".ooooooooooooooo",
            "ooMooooooooooooo",
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
        game.setAgingStrategy( new BetaAgingStrategy());
        game.setWinningStrategy( new BetaWinningStrategy());
    }

    @Test
    public void startAge(){
        assertThat(game.getAge(), is(-4000));
    }
    @Test
    public void stage1(){
        game.endOfTurn();
        game.endOfTurn();
        assertThat(game.getAge(),is(-3900));
    }
    @Test
    public void stage2(){
        for(int i = 0; i < 78; i ++){
            game.endOfTurn();
        }
        assertThat(game.getAge(),is(-100));


        game.endOfTurn();
        game.endOfTurn();

        assertThat(game.getAge(), is(-1));

        game.endOfTurn();
        game.endOfTurn();

        assertThat(game.getAge(), is(1));

        game.endOfTurn();
        game.endOfTurn();

        assertThat(game.getAge(), is(50));

    }
    @Test
    public void stage3(){
        for(int i = 0; i < 84; i ++){
            game.endOfTurn();
        }

        assertThat(game.getAge(), is(50));

        for(int i = 0; i < 68; i ++){
            game.endOfTurn();
        }
        assertThat(game.getAge(), is(1750));

    }
    @Test
    public void stage4(){
        for(int i = 0; i < 152; i ++){
            game.endOfTurn();
        }

        assertThat(game.getAge(), is(1750));

        for(int i = 0; i < 12; i ++){
            game.endOfTurn();
        }
        assertThat(game.getAge(), is(1900));

    }
    @Test
    public void stage5(){
        for(int i = 0; i < 164; i ++){
            game.endOfTurn();
        }
        assertThat(game.getAge(), is(1900));
        for(int i = 0; i < 28; i ++){
            game.endOfTurn();
        }
        assertThat(game.getAge(), is(1970));
    }
    @Test
    public void stage6(){
        for(int i = 0; i < 192; i ++){
            game.endOfTurn();
        }
        assertThat(game.getAge(), is(1970));
        for(int i = 0; i < 102; i ++){
            game.endOfTurn();
        }
        assertThat(game.getAge(), is(2021));
    }
    @Test
    public void winningStrategyTest(){
        //"conquers all cities in the world"
        //
        //FAKE that red has ownership of all cities
        game.placeCityAt(new Position(1,5), new CityImpl(Player.BLUE));
        game.placeCityAt(new Position(3,8), new CityImpl(Player.BLUE));

        assertThat(game.getWinner(), is(Player.BLUE));
        assertThat(game.getWinner(), is(not(Player.RED)));
        game.placeCityAt(new Position(1,5), new CityImpl(Player.RED));

        assertThat(game.getWinner(), is(nullValue()));

    }


}
