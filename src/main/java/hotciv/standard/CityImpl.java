package hotciv.standard;

import hotciv.framework.*;

public class CityImpl implements City{
    private int size;
    private Player owner;
    private String production;
    private int resourcesProduced;
    private int foodCount;
    private String workForceFocus = GameConstants.productionFocus;
    private int treasury = 0;

    public CityImpl(Player Owner){
        owner = Owner;
        size = GameConstants.POPULATION_SIZE;
        production = GameConstants.ARCHER;
        resourcesProduced = 0;
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
    public String getWorkforceFocus() {
        return workForceFocus;
    }

    @Override
    public void changeProduction(String unitType) {
        production = unitType;
    }

    @Override
    public int getProductionCount() {
        return resourcesProduced;
    }

    @Override
    public void setProductionCount(int newCount) {
        resourcesProduced = newCount;
    }

    @Override
    public void setOwner(Player player) {
        owner = player;
    }

    @Override
    public void spendProduction(int price) {
        resourcesProduced -= price;
    }

    @Override
    public void setWorkForceFocus(String focus) {
        if(focus.equalsIgnoreCase(GameConstants.productionFocus) ){
            workForceFocus = GameConstants.productionFocus;
        }else if(focus.equalsIgnoreCase(GameConstants.foodFocus)){
            workForceFocus = GameConstants.foodFocus;
        }else{
            workForceFocus = GameConstants.productionFocus;
        }
    }
}
