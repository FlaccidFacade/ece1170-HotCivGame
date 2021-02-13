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

    }

    @Override
    public void placeUnit(Position p, Unit u) {

    }

    @Override
    public void removeUnit(Position p) {

    }

    @Override
    public void getSize() {

    }

    @Override
    public Tile getTileAt(Position p) {
        return null;
    }

    @Override
    public City getCityAt(Position p) {
        return null;
    }

    @Override
    public Unit getUnitAt(Position p) {
        return null;
    }

    @Override
    public String getTerrainAt(Position p) {
        return null;
    }
}
