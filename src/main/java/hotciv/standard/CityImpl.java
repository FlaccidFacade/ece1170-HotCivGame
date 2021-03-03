package hotciv.standard;

import hotciv.framework.*;

public class CityImpl implements City{
    private int size;
    private Player owner;
    private String production;
    private int treasury;
    private int food;
    private String workforceBalance = GameConstants.productionFocus;
    

    public CityImpl(Player Owner){
        owner = Owner;
        size = GameConstants.POPULATION_SIZE;
        production = GameConstants.ARCHER;
        treasury = 0;
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
    public int getTreasury() {
        return treasury;
    }


    @Override
    public void setOwner(Player player) {
        owner = player;
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
                if(treasury >= GameConstants.ARCHER_COST)
                    treasury -= GameConstants.ARCHER_COST;
                break;
            case GameConstants.LEGION:
                if(treasury >= GameConstants.LEGION_COST)
                treasury -= GameConstants.LEGION_COST;
                break;
            case GameConstants.SETTLER:
                if(treasury >= GameConstants.SETTLER_COST)
                treasury -= GameConstants.SETTLER_COST;
                break;

        }
    }

    @Override
    public void harvest(){
        treasury += 6;
    }

}
