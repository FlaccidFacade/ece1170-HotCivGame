package hotciv.standard;

import hotciv.framework.*;

public class UnitImpl implements Unit {

    private Player owner;

    private String unitType;
    private int attack;
    private int defense;
    private int moveCount;
    private int cost;
    private String action;

    public UnitImpl(Player Owner, String UnitType){
        owner = Owner;
        moveCount = 1;
        switch (UnitType){
            case GameConstants.ARCHER:
                unitType = UnitType;
                defense = GameConstants.ARCHER_DEFENSIVE_STRENGTH;
                attack = GameConstants.ARCHER_ATTACKING_STRENGTH;
                action = GameConstants.ARCHER_ACTION;
                break;
            case GameConstants.LEGION:
                unitType = UnitType;
                defense = GameConstants.LEGION_DEFENSIVE_STRENGTH;
                attack = GameConstants.LEGION_ATTACKING_STRENGTH;
                action = GameConstants.LEGION_ACTION;
                break;
            case GameConstants.SETTLER:
                unitType = UnitType;
                defense = GameConstants.SETTLER_DEFENSIVE_STRENGTH;
                attack = GameConstants.SETTLER_ATTACKING_STRENGTH;
                action = GameConstants.SETTLER_ACTION;
                break;
            default:
                unitType = GameConstants.SETTLER;
                defense = GameConstants.SETTLER_DEFENSIVE_STRENGTH;
                attack = GameConstants.SETTLER_ATTACKING_STRENGTH;
                action = GameConstants.SETTLER_ACTION;
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
        return defense;
    }

    @Override
    public int getAttackingStrength() {
        return attack;
    }

    @Override
    public void fortify() {
        defense += 1;
        moveCount = 0;
    }

    @Override
    public void buildCity() {

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
