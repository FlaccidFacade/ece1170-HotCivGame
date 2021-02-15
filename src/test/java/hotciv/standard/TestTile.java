package hotciv.standard;

import hotciv.framework.*;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
public class TestTile{


    private Tile tile;

    @Before
    public void setUp(){
        tile = new TileImpl("plains");
    }
    @Test
    public void getTypeString(){
        assertThat(tile.getTypeString(), is(GameConstants.PLAINS));
    }

    @Test
    public void addUnit(){
        Unit unit= new UnitImpl(Player.RED,"ARCHER");
        assertThat(tile.getUnit(), is(nullValue()));
        tile.addUnit(unit);
        assertThat(tile.getUnit(), is(not(nullValue())));
    }

    @Test
    public void addCity(){
        City city = new CityImpl(Player.RED);
        assertThat(tile.getCity(), is(nullValue()));
        tile.addCity(city);
        assertThat(tile.getCity(), is(not(nullValue())));
    }

    @Test
    public void removeUnit(){
        Unit unit= new UnitImpl(Player.RED,"ARCHER");
        assertThat(tile.getUnit(), is(nullValue()));
        tile.addUnit(unit);
        assertThat(tile.getUnit(), is(not(nullValue())));
        tile.removeUnit();
        assertThat(tile.getUnit(), is(nullValue()));
    }
}