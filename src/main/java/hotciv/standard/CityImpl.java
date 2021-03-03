package hotciv.standard;

import hotciv.framework.*;

public class CityImpl implements City{
    private int size;
    private Player owner;
    private String production;
    private int resources;
    private int food;
    private String workforceBalance = GameConstants.productionFocus;
    private int treasury;

    public CityImpl(Player Owner){
        owner = Owner;
        size = GameConstants.POPULATION_SIZE;
        production = GameConstants.ARCHER;
        resources = 0;
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int getTreasury() {
        return treasury;
    }

    @Override
    public String getProduction() {
        return production;
    }

    @Override
    public String getWorkforceBalance() {
        return workforceBalance;
    }

    @Override
    public void changeProduction(String unitType) {
        production = unitType;
    }

    @Override
    public int getResources() {
        return resources;
    }

    @Override
    public void setResources(int newCount) {
        resources = newCount;
    }

    @Override
    public void setOwner(Player player) {
        owner = player;
    }

    @Override
    public void spendResources(int price) {
        resources -= price;
    }

    @Override
    public void setWorkforceBalance(String focus) {
        if(focus.equalsIgnoreCase(GameConstants.productionFocus) ){
            workforceBalance = GameConstants.productionFocus;
        }else if(focus.equalsIgnoreCase(GameConstants.foodFocus)){
            workforceBalance = GameConstants.foodFocus;
        }else{
            workforceBalance = GameConstants.productionFocus;
        }
    }



    @Override
    public void produceUnit(){
        switch (production){
            case GameConstants.ARCHER:
                if(resources >= GameConstants.ARCHER_COST)
                    resources -= GameConstants.ARCHER_COST;
                break;
            case GameConstants.LEGION:
                if(resources >= GameConstants.LEGION_COST)
                resources -= GameConstants.LEGION_COST;
                break;
            case GameConstants.SETTLER:
                if(resources >= GameConstants.SETTLER_COST)
                resources -= GameConstants.SETTLER_COST;
                break;

        }
    }

    @Override
    public void harvest(){
        resources += 6;
    }

}
