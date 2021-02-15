package hotciv.standard;

import hotciv.framework.City;

import hotciv.framework.Player;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import java.util.*;
public class TestCity{


    private City city;

    @Before
    public void setUp(){
        city = new CityImpl(Player.RED);
    }
    @Test
    public void nameOfTest(){
        //      assertThat();
    }


}