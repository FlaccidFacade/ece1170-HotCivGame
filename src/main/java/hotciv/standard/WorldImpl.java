package hotciv.standard;

import hotciv.framework.*;

import java.util.ArrayList;
import java.util.List;

public class WorldImpl implements  World{

    private final Tile[][] world;

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

    @Override
    public boolean movable(Position from, Position to) {
        boolean ableNeighbor = false;
        boolean ableTerrain = false;
        boolean able = false;

        //Lines 86 through 99 are originally from At
        // * 09 May 2018
        // *
        // * @author Henrik Baerbak Christensen, CS @ AU
        List<Position> neighbors = new ArrayList<>();
        // Define the 'delta' to add to the row for the 8 positions
        int[] rowDelta = new int[] {-1, -1, 0, +1, +1, +1, 0, -1};
        // Define the 'delta' to add to the column for the 8 positions
        int[] columnDelta = new int[] {0, +1, +1, +1, 0, -1, -1, -1};
        //fill neighbor list
        for (int index = 0; index < rowDelta.length; index++) {
            int row = from.getRow() + rowDelta[index];
            int col = from.getColumn() + columnDelta[index];
            if (row >= 0 && col >= 0
                    && row < GameConstants.WORLDSIZE
                    && col < GameConstants.WORLDSIZE)
                neighbors.add(new Position(row, col));
        }
        //Make sure tile is a neighbor
        for(Position p: neighbors){
            if(p == to){
                ableNeighbor = true;
                break;
            }else{
                ableNeighbor = false;
            }

        }

        //Make sure the terrain is allowed
        Tile t = world[to.getRow()][to.getColumn()];
        if(t.getTypeString().equalsIgnoreCase(GameConstants.OCEANS)
                || t.getTypeString().equalsIgnoreCase(GameConstants.MOUNTAINS)
        ){
            ableTerrain = false;
        }else{
            ableTerrain = true;
        }

        //Make sure the tile is a neighbor and terrain
        if(ableNeighbor == true && ableTerrain == true){
            able = true;
        }else{
            able = false;
        }

        return able;
    }


}

















