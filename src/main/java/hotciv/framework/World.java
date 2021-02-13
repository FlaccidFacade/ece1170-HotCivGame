package hotciv.framework;

public interface World {



    public void placeTile(Position p, Tile t);

    public void placeCity(Position p, City c);

    public void placeUnit(Position p, Unit u);

    public void removeUnit(Position p);


    public void getSize();

    public Tile getTileAt(Position p);

    public City getCityAt(Position p);

    public Unit getUnitAt(Position p);

    public String getTerrainAt(Position p);

}
