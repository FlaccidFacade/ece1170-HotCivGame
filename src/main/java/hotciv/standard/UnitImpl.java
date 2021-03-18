package hotciv.standard;

import hotciv.framework.*;

public class UnitImpl implements Unit {

    private Player owner;

    private String unitType;
    private int attackStrength;
    private int defenseStrength;
    private int moveCount;
    private int cost;
    private String action;
    private boolean fortified;

    public UnitImpl(Player owner, String unittype){
        this.owner = owner;
        fortified = false;
        moveCount = 1;
        switch (unittype){
            case GameConstants.ARCHER:
                this.unitType = unittype;
                this.defenseStrength = GameConstants.ARCHER_DEFENSIVE_STRENGTH;
                this.attackStrength = GameConstants.ARCHER_ATTACKING_STRENGTH;
                this.action = GameConstants.ARCHER_ACTION;
                this.cost = GameConstants.ARCHER_COST;
                break;
            case GameConstants.LEGION:
                this.unitType = unittype;
                this.defenseStrength = GameConstants.LEGION_DEFENSIVE_STRENGTH;
                this.attackStrength = GameConstants.LEGION_ATTACKING_STRENGTH;
                this.action = GameConstants.LEGION_ACTION;
                this.cost = GameConstants.LEGION_COST;
                break;
            case GameConstants.SETTLER:
                this.unitType = unittype;
                this.defenseStrength = GameConstants.SETTLER_DEFENSIVE_STRENGTH;
                this.attackStrength = GameConstants.SETTLER_ATTACKING_STRENGTH;
                this.action = GameConstants.SETTLER_ACTION;
                this.cost = GameConstants.SETTLER_COST;
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
