package hotciv.standard;

import hotciv.framework.*;

public class TileImpl implements Tile{
    String type;
    City city;
    Unit unit= null;

    public TileImpl(String type){

        if(type.equalsIgnoreCase(GameConstants.PLAINS)
                || type.equalsIgnoreCase(GameConstants.OCEANS)
                || type.equalsIgnoreCase(GameConstants.FOREST)
                || type.equalsIgnoreCase(GameConstants.HILLS)
                || type.equalsIgnoreCase(GameConstants.MOUNTAINS)
        ) {
            this.type = type;
        }else{
            this.type = GameConstants.PLAINS;
        }
        city = null;
        unit = null;

    }

    @Override
    public String getTypeString() {
        return type;
    }

    @Override
    public void addUnit(Unit unit){
        this.unit = unit;
    }

    @Override
    public void addCity(City city){
        this.city = city;
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
