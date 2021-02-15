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
        unit = new UnitImpl(Player.RED,"Archer");
    }
    @Test
    public void nameOfTest(){
        //	assertThat();
    }


}
