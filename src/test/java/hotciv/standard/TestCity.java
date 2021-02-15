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
    public void cityKnowsOwner(){
       assertThat(city.getOwner(),is(Player.RED));
    }

    @Test
    public void cityCanChangeOwner(){
        city.setOwner(Player.BLUE);
        assertThat(city.getOwner(),is(not(Player.RED)));
        assertThat(city.getOwner(),is(Player.BLUE));

    }




}