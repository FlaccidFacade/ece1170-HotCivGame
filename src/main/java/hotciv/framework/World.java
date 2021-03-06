package hotciv.framework;

import hotciv.framework.Strategies.GrowthStrategy;
import hotciv.framework.Strategies.ProductionStrategy;

import java.util.List;

public interface World {
    /** this sets the growth strategy
     *
     * @param growthStrategy
     */
    void setGrowthStrategy(GrowthStrategy growthStrategy);

    /** this sets the production strategy
     *
     * @param productionStrategy
     */
    void setProductionStrategy(ProductionStrategy productionStrategy);

    /** puts a tile and position into the world hashmap
     *
     * @param p the position of the tile
     * @param t the tile for the hashmap
     */
    void placeTile(Position p, Tile t);

    /** places a city on the tile
     *
     * @param p the position of the tile
     * @param c the city for the tile
     */
    void placeCity(Position p, City c);

    /** places the unit on a tile
     *
     * @param p the position of the tile
     * @param u the unit for the tile
     */
    void placeUnit(Position p, Unit u);

    /** removes the unit from a tile
     * precondition: the tile at the position needs to have a unit
     * @param p the position of the tile and unit
     */
    void removeUnit(Position p);

    /** removes city from a tile
     *
     * @param p the position of the tile and city
     */
    void removeCity(Position p);

    /** returns the size of this world
     *
     * @return world.size() the size of the hashmap
     */
    int getSize();

    /** returns the tile at position
     *
     * @param p the position of the tile
     * @return t the tile at position p
     */
    Tile getTileAt(Position p);

    /** returns the city on tile at position
     *
     * @param p the position of the tile
     * @return t.getCity() the city on the tile
     */
    City getCityAt(Position p);

    /** returns the unit on the tile at position
     *
     * @param p the position of the tile
     * @return t.getUnit() the unit on the tile
     */
    Unit getUnitAt(Position p);

    /** returns the terrain of tile at position
     *
     * @param p the position of the tile
     * @return t.getTypeString() the string value of the Terrain at position p
     */
    String getTerrainAt(Position p);

    /** returns true if unit at position 'from' can go to position 'to'
     *
     * @param from the tile to start at
     * @param to the tile to stop at
     * @return able the boolean value of the mobility between from and to
     */
    boolean movable(Position from, Position to);

    /** updates city populations
     * based off current set strategy
     */
    void updateAllCityPopulation();

    /** updates city Treasury
     *
     */
    void updateAllCityTreasury();

    /** places a unit for unit production
     *
     */
    void produceAllCityUnits();

    /** restores the moves counts of units
     *
     */
    void updateAllMoveCounts();

    /**
     *
     */
    int getCombinedDefenseStrength(Position center);

    int getCombinedAttackStrength(Position center);

    List<Unit> getAllUnits();

    List<City> getAllCities();

    void addObserver(GameObserver observer);
}
