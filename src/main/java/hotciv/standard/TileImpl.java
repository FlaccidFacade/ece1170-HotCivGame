package hotciv.standard;

import hotciv.framework.*;

import java.io.File;

public class TileImpl implements Tile{
    String type;
    City city;
    Unit unit= null;
    boolean movement;
    File graphic;


    public TileImpl(String t){

        if(t.equalsIgnoreCase(GameConstants.PLAINS)
                || t.equalsIgnoreCase(GameConstants.OCEANS)
                || t.equalsIgnoreCase(GameConstants.FOREST)
                || t.equalsIgnoreCase(GameConstants.HILLS)
                || t.equalsIgnoreCase(GameConstants.MOUNTAINS)
        ) {
            type = t;
        }else{
            type = GameConstants.PLAINS;
        }
        city = null;
        unit = null;

    }

    @Override
    public String getTypeString() {
        return type;
    }

    @Override
    public void addUnit(Unit u){
        unit = u;
    }

    @Override
    public void addCity(City c){
        city = c;
    }

    @Override
    public Unit getUnit() {
        return unit;
    }

    @Override
    public City getCity() {
        return city;
    }

    @Override
    public void removeUnit() {
        unit = null;
    }
}
