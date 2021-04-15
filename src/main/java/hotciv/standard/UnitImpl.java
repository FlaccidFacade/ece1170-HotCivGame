package hotciv.standard;

import hotciv.framework.*;

public class UnitImpl implements Unit {

    private final Player owner;

    private final String unitType;
    private final int attackStrength;
    private int defenseStrength;
    private int moveCount;
    private final int cost;
    private final String action;

    public UnitImpl(Player owner, String unitType){
        this.owner = owner;
        moveCount = 1;
        switch (unitType){
            case GameConstants.ARCHER:
                this.unitType = unitType;
                this.defenseStrength = GameConstants.ARCHER_DEFENSIVE_STRENGTH;
                this.attackStrength = GameConstants.ARCHER_ATTACKING_STRENGTH;
                this.action = GameConstants.ARCHER_ACTION;
                this.cost = GameConstants.ARCHER_COST;
                break;
            case GameConstants.LEGION:
                this.unitType = unitType;
                this.defenseStrength = GameConstants.LEGION_DEFENSIVE_STRENGTH;
                this.attackStrength = GameConstants.LEGION_ATTACKING_STRENGTH;
                this.action = GameConstants.LEGION_ACTION;
                this.cost = GameConstants.LEGION_COST;
                break;
            case GameConstants.SETTLER:
                this.unitType = unitType;
                this.defenseStrength = GameConstants.SETTLER_DEFENSIVE_STRENGTH;
                this.attackStrength = GameConstants.SETTLER_ATTACKING_STRENGTH;
                this.action = GameConstants.SETTLER_ACTION;
                this.cost = GameConstants.SETTLER_COST;
                break;
            case GameConstants.UFO:
                moveCount = 2;
                this.unitType = unitType;
                this.defenseStrength = GameConstants.UFO_DEFENSIVE_STRENGTH;
                this.attackStrength = GameConstants.UFO_ATTACKING_STRENGTH;
                this.action = GameConstants.UFO_ACTION;
                this.cost = GameConstants.UFO_COST;
                break;
            default:
                this.unitType = GameConstants.SETTLER;
                this.defenseStrength = GameConstants.SETTLER_DEFENSIVE_STRENGTH;
                this.attackStrength = GameConstants.SETTLER_ATTACKING_STRENGTH;
                this.action = GameConstants.SETTLER_ACTION;
                this.cost = GameConstants.SETTLER_COST;
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
        return moveCount;
    }

    @Override
    public int getDefensiveStrength() {
        return defenseStrength;
    }

    @Override
    public int getAttackingStrength() {
        return attackStrength;
    }

    @Override
    public void setDefensiveStrength(int defenseStrength) {
        this.defenseStrength = defenseStrength;
    }

    @Override
    public void move() {
        moveCount--;
    }

    @Override
    public void setMoveCount(int m){
        moveCount = m;
    }

    @Override
    public String getAction(){
        return action;
    }


}
