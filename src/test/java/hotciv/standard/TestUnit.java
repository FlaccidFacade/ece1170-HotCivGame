package hotciv.standard;

import hotciv.framework.*;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.*;

public class TestUnit{


    private Unit unit;

    @Before
    public void setUp(){
        unit = new UnitImpl(Player.RED,GameConstants.ARCHER);
    }
    @Test
    public void getOwner(){
        assertThat(unit.getOwner(),is(Player.RED));
    }

    @Test
    public void getType(){
        assertThat(unit.getTypeString(),is(GameConstants.ARCHER));
    }

    @Test
    public void moving(){
        assertThat(unit.getMoveCount(),is(1));
        unit.move();
        assertThat(unit.getMoveCount(),is(0));
        unit.setMoveCount(2);

        assertThat(unit.getMoveCount(),is(2));
    }
    @Test
    public void checkAttack(){
        assertThat(unit.getAttackingStrength(),is(GameConstants.ARCHER_ATTACKING_STRENGTH));
    }

    @Test
    public void checkDefense(){
        assertThat(unit.getDefensiveStrength(),is(GameConstants.ARCHER_DEFENSIVE_STRENGTH));
    }

}
