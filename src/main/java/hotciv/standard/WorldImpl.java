package hotciv.standard;

import hotciv.framework.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorldImpl implements  World{

    private final Map<Position,Tile> world;
    private List<City> cities;

    public WorldImpl(){
        world = new HashMap<Position,Tile>();
        cities = new ArrayList<>();
        setToPlains();
    }

    public WorldImpl(String[] layout){
        world = new HashMap<Position,Tile>();
        cities = new ArrayList<>();

            String line;
            for ( int r = 0; r < GameConstants.WORLDSIZE; r++ ) {
                line = layout[r];
                for ( int c = 0; c < GameConstants.WORLDSIZE; c++ ) {
                    char tileChar = line.charAt(c);
                    String type = "error";
                    if ( tileChar == '.' ) { type = GameConstants.OCEANS; }
                    if ( tileChar == 'o' ) { type = GameConstants.PLAINS; }
                    if ( tileChar == 'M' ) { type = GameConstants.MOUNTAINS; }
                    if ( tileChar == 'f' ) { type = GameConstants.FOREST; }
                    if ( tileChar == 'h' ) { type = GameConstants.HILLS; }
                    Position p = new Position(r,c);
                    world.put( p, new TileImpl(type));
                }
            }
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
       world.put(p, t);
    }

    @Override
    public void placeCity(Position p, City c) {
        Tile t = world.get(p);
        t.addCity(c);
        cities.add(c);
        //todo see if this line is actually needed
       // world.put(p,t);
    }

    @Override
    public void placeUnit(Position p, Unit u) {
        Tile t = world.get(p);
        t.addUnit(u);

        //todo see if this line is actually needed
        // world.put(p,t);
    }

    @Override
    public void removeUnit(Position p) {
        Tile t = world.get(p);
        t.removeUnit();
    }

    @Override
    public int getSize() {
        return world.size();
    }

    @Override
    public Tile getTileAt(Position p) {
        Tile t = world.get(p);
        return t;
    }

    @Override
    public City getCityAt(Position p) {
        Tile t = world.get(p);
        return t.getCity();
    }

    @Override
    public Unit getUnitAt(Position p) {
        Tile t = world.get(p);
        return t.getUnit();
    }

    @Override
    public String getTerrainAt(Position p) {
        Tile t = world.get(p);
        return t.getTypeString();
    }

    @Override
    public boolean movable(Position from, Position to) {
        boolean ableNeighbor = false;
        boolean ableTerrain = false;
        boolean able = false;


        List<Position> neighbors = getNeighbors(from);

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
        Tile t = world.get(to);
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


    public void updateAllCityResources(){
        for(City c: cities){
            c.harvest();
        }
    }

    public void produceAllCityUnits(){

        for(int r = 0; r < GameConstants.WORLDSIZE; r++) {
            for (int c = 0; c < GameConstants.WORLDSIZE; c++) {
                if(getTileAt(new Position(r,c)).getCity() != null){
                    City city = getCityAt(new Position(r,c));
                    if(city.produceUnit()) {

                        Unit unit = new UnitImpl(city.getOwner(), city.getProduction());
                        Tile tile = getTileAt(new Position(r, c));
                        if (tile.getUnit() == null) {
                            System.out.println("HIT");
                            tile.addUnit(unit);
                        } else {
                            List<Position> neighbors = getNeighbors(new Position(r, c));
                            for (Position p : neighbors) {
                                if ((getTileAt(p).getTypeString() != GameConstants.OCEANS)
                                        && (getTileAt(p).getTypeString() != GameConstants.MOUNTAINS)
                                        && (getTileAt(p).getUnit() == null)
                                ) {
                                    placeUnit(p, unit);
                                }
                            }
                        }
                    }
                }
            }
        }


    }

    private List<Position> getNeighbors(Position center){
        List<Position> neighbors = new ArrayList<>();
        // Define the 'delta' to add to the row for the 8 positions
        int[] rowDelta = new int[] {-1, -1, 0, +1, +1, +1, 0, -1};
        // Define the 'delta' to add to the column for the 8 positions
        int[] columnDelta = new int[] {0, +1, +1, +1, 0, -1, -1, -1};
        //fill neighbor list
        for (int index = 0; index < rowDelta.length; index++) {
            int row = center.getRow() + rowDelta[index];
            int col = center.getColumn() + columnDelta[index];
            if (row >= 0 && col >= 0
                    && row < GameConstants.WORLDSIZE
                    && col < GameConstants.WORLDSIZE)
                neighbors.add(new Position(row, col));
        }
        return neighbors;
    }
}

















