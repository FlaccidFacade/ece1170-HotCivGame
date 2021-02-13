package hotciv.standard;

import hotciv.framework.*;

public class WorldImpl implements  World{

    private Tile[][] world;

    public WorldImpl(){
        world = new Tile[GameConstants.WORLDSIZE][GameConstants.WORLDSIZE];
        setToPlains();
    }

    private void setToPlains(){
        for(int r = 0; r < GameConstants.WORLDSIZE; r++) {
            for (int c = 0; c < GameConstants.WORLDSIZE; c++) {
                Position p = new Position(r,c);
                Tile t = new TileImpl(GameConstants.PLAINS);
                placeTile(p,t);
            }
        }
    }

    @Override
    public void placeTile(Position p, Tile t) {
        world[p.getRow()][p.getColumn()] = t;
    }

    @Override
    public void placeCity(Position p, City c) {
        Tile t = world[p.getRow()][p.getColumn()];
        t.addCity(c);
    }

    @Override
    public void placeUnit(Position p, Unit u) {
        Tile t = world[p.getRow()][p.getColumn()];
        t.addUnit(u);
    }

    @Override
    public void removeUnit(Position p) {
        Tile t = world[p.getRow()][p.getColumn()];
        t.removeUnit();
    }

    @Override
    public int getSize() {
        return world.length;
    }

    @Override
    public Tile getTileAt(Position p) {
        Tile t = world[p.getRow()][p.getColumn()];
        return t;
    }

    @Override
    public City getCityAt(Position p) {
        Tile t = world[p.getRow()][p.getColumn()];
        return t.getCity();
    }

    @Override
    public Unit getUnitAt(Position p) {
        Tile t = world[p.getRow()][p.getColumn()];
        return t.getUnit();
    }

    @Override
    public String getTerrainAt(Position p) {
        Tile t = world[p.getRow()][p.getColumn()];
        return t.getTypeString();
    }
}
