package hotciv.standard;

import hotciv.framework.*;
import javafx.geometry.Pos;

import java.io.File;

public class TileImpl implements Tile{
    String type;
    Position position;
    City city;
    Unit unit;
    boolean movement;
    File graphic;


    public TileImpl(String t, Position p){
        if(p.getColumn() > -1
                && p.getRow() > -1
                && p.getRow() <= GameConstants.WORLDSIZE
                && p.getColumn() <= GameConstants.WORLDSIZE) {
            position = p;
        }else{
            position = new Position(0,0);
        }
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
    }

    @Override
    public String getTypeString() {
        return type;
    }

    @Override
    public Position getPosition() {
        return position;
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
}
