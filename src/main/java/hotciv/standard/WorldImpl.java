package hotciv.standard;

import hotciv.framework.*;
import hotciv.framework.Strategies.GrowthStrategy;
import hotciv.framework.Strategies.ProductionStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorldImpl implements  World{

    private final Map<Position,Tile> map;
    private final List<City> cities;
    private final List<Unit> units;
    private GrowthStrategy growthStrategy;
    private ProductionStrategy productionStrategy;
    private ArrayList<GameObserver> observerList;
    //TODO add population strategy
    //TODO add workforceFocus strategy
    //TODO pass these from Game to here and access cities to harvest and populate properly
    //TODO do this for alpha then go to Eta
    public WorldImpl(){
        map = new HashMap<>();
        cities = new ArrayList<>();
        units = new ArrayList<>();
        observerList = new ArrayList<>();
        setToPlains();
    }

    public WorldImpl(String[] layout){
        map = new HashMap<Position,Tile>();
        cities = new ArrayList<>();
        units = new ArrayList<>();
        observerList = new ArrayList<>();
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
                    map.put( p, new TileImpl(type));
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

    public void addObserver(GameObserver observer){
        observerList.add(observer);
    }

    @Override
    public void setGrowthStrategy(GrowthStrategy growthStrategy){
        this.growthStrategy = growthStrategy;
    }

    @Override
    public void setProductionStrategy(ProductionStrategy productionStrategy){
        this.productionStrategy = productionStrategy;
    }

    @Override
    public void placeTile(Position p, Tile t) {
        map.remove(p);
        map.put(p, t);
    }

    @Override
    public void placeCity(Position p, City c) {
        Tile tile = map.get(p);
        if(!this.ableTerrain(tile)){
            return;
        }else{
            tile.addCity(c);
            cities.add(c);
            map.put(p,tile);
            updateWorldChangedAt(p);
        }

    }

    @Override
    public void placeUnit(Position p, Unit u) {
        Tile tile = map.get(p);
        if(!this.ableTerrain(tile)){
            return;
        }else{
            tile.addUnit(u);
            units.add(u);
            map.put(p,tile);
            updateWorldChangedAt(p);
        }
    }

    @Override
    public void removeUnit(Position p) {
        Tile t = map.get(p);
        t.removeUnit();
        units.remove(t.getUnit());
        updateWorldChangedAt(p);
    }

    @Override
    public void removeCity(Position p) {
        Tile t = map.get(p);
        t.removeCity();
        cities.remove(t.getCity());
        updateWorldChangedAt(p);
    }

    @Override
    public int getSize() {
        return map.size();
    }

    @Override
    public Tile getTileAt(Position p) {
        return map.get(p);
    }

    @Override
    public City getCityAt(Position p) {
        Tile t = map.get(p);
        return t.getCity();
    }

    @Override
    public Unit getUnitAt(Position p) {
        Tile t = map.get(p);
        return t.getUnit();
    }

    @Override
    public String getTerrainAt(Position p) {
        Tile t = map.get(p);
        return t.getTypeString();
    }

    @Override
    public boolean movable(Position from, Position to) {
        boolean ableNeighbor = false;

        List<Position> neighbors = getNeighbors(from);
        //Make sure tile is a neighbor
        for(Position p: neighbors){
            if( p.getRow() == to.getRow() && p.getColumn() == to.getColumn()){
                ableNeighbor = true;
                break;
            }else{
                ableNeighbor = false;
            }
        }
        if(!ableNeighbor) return false;


        boolean ableMoveCount = false;
        //make sure move count is over 0
        Unit unitFrom = map.get(from).getUnit();
        if(unitFrom.getMoveCount() > 0) {
            ableMoveCount = true;
        }else{
            return false;
        }

        boolean ableTerrain = false;
        //make sure tile on 'to' is proper terrain
        Tile t = map.get(to);
        if(! this.ableTerrain(t)){
            return false;
        }else{
            ableTerrain = true;
        }

        boolean able = false;
        //Make sure the tile is a neighbor and terrain and can move and proper ownership
        if(ableNeighbor
                && ableMoveCount
                && ableTerrain
        ){
            able = true;
        }else{
            return false;
        }

        return able;
    }

    @Override
    public void updateAllCityPopulation(){
        for(City c: cities){
            this.growthStrategy.grow(c);
        }
    }

    @Override
    public void updateAllCityTreasury(){
        for(City c: cities){
            this.productionStrategy.produce(c);
        }
    }

    @Override
    public void produceAllCityUnits(){

        for(int r = 0; r < GameConstants.WORLDSIZE; r++) {
            for (int c = 0; c < GameConstants.WORLDSIZE; c++) {
                if(getTileAt(new Position(r,c)).getCity() != null){
                    City city = getCityAt(new Position(r,c));
                    if(city.produceUnit()) {

                        Unit unit = new UnitImpl(city.getOwner(), city.getProduction());
                        Tile tile = getTileAt(new Position(r, c));
                        if (tile.getUnit() == null) {
                            tile.addUnit(unit);
                        } else {
                            List<Position> neighbors = getNeighbors(new Position(r, c));
                            for (Position p : neighbors) {
                                if ((!getTileAt(p).getTypeString().equals(GameConstants.OCEANS))
                                        && (!getTileAt(p).getTypeString().equals(GameConstants.MOUNTAINS))
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

    @Override
    public void updateAllMoveCounts(){
        for(Unit u: units){
            u.setMoveCount(1);
        }
    }

    @Override
    public int getCombinedDefenseStrength(Position center){
        int unitsDefense = getUnitAt(center).getDefensiveStrength();
        int terrainMultiplier = getTerrainMultiplier(center);
        int neighboringDefense = 0;

        List<Position> neighbors = getNeighbors(center);
        for(Position p: neighbors){
            if(getUnitAt(p) != null){
                if (getUnitAt(p).getOwner() == getUnitAt(center).getOwner()) {
                    neighboringDefense += 1;
                }
            }
        }

        int combinedDefense = 0;
        combinedDefense = unitsDefense + neighboringDefense;
        combinedDefense *= terrainMultiplier;

        return combinedDefense;
    }
    @Override
    public int getCombinedAttackStrength(Position center){
        int unitsAttack = getUnitAt(center).getAttackingStrength();
        int terrainMultiplier = getTerrainMultiplier(center);
        int neighboringAttack = 0;

        List<Position> neighbors = getNeighbors(center);
        for(Position p: neighbors){
            if(getUnitAt(p) != null){
                if (getUnitAt(p).getOwner() == getUnitAt(center).getOwner()) {
                    neighboringAttack += 1;
                }
            }
        }

        int combinedAttack = 0;
        combinedAttack = unitsAttack + neighboringAttack;
        combinedAttack *= terrainMultiplier;

        return combinedAttack;
    }

    @Override
    public List<Unit> getAllUnits(){
        return units;
    }

    @Override
    public List<City> getAllCities(){
        return cities;
    }


    private int getTerrainMultiplier(Position position){
        int multiplier = 1;

        if(getCityAt(position) != null){
            multiplier = 3;
        }else if(getTerrainAt(position).equals(GameConstants.HILLS)
                || getTerrainAt(position).equals(GameConstants.FOREST)){
            multiplier = 2;
        }

        return multiplier;
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

    private boolean ableTerrain(Tile tile){
        boolean properTerrain =
                !(tile.getTypeString().equalsIgnoreCase(GameConstants.OCEANS)
                || tile.getTypeString().equalsIgnoreCase(GameConstants.MOUNTAINS));

        return properTerrain;
    }

    private void updateWorldChangedAt(Position position){
        for(GameObserver observer : observerList){
            observer.worldChangedAt(position);
        }
    }
}

















