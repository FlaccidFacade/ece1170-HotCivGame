package hotciv.standard;

import hotciv.framework.*;

public class UnitImpl implements Unit {

    private Player owner;

    private String unitType;

    public UnitImpl(Player Owner, String UnitType){
        owner = Owner;


        if(UnitType.equalsIgnoreCase(GameConstants.ARCHER)
                || UnitType.equalsIgnoreCase(GameConstants.LEGION)
                || UnitType.equalsIgnoreCase(GameConstants.SETTLER)) {
            unitType = UnitType;
        }else{
            unitType = GameConstants.ARCHER;
        }
    }

    @Override
    public String getTypeString() {
        return unitType;
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public int getMoveCount() {
        return GameConstants.UNIT_MOVE_DISTANCE;
    }

    @Override
    public int getDefensiveStrength() {
        int defense = -1;
        switch(unitType.toUpperCase()){
            case "ARCHER" : defense = GameConstants.ARCHER_DEFENSIVE_STRENGTH; break;
            case "LEGION" : defense = GameConstants.LEGION_DEFENSIVE_STRENGTH; break;
            case "SETTLER": defense = GameConstants.SETTLER_DEFENSIVE_STRENGTH; break;
        }
        return defense;
    }

    @Override
    public int getAttackingStrength() {
        int attack = -1;
        switch(unitType.toUpperCase()){
            case "ARCHER" : attack = GameConstants.ARCHER_ATTACKING_STRENGTH; break;
            case "LEGION" : attack = GameConstants.LEGION_ATTACKING_STRENGTH; break;
            case "SETTLER": attack = GameConstants.SETTLER_ATTACKING_STRENGTH; break;
        }
        return attack;
    }
}
