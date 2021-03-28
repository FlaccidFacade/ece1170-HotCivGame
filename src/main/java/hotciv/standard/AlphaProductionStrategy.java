package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.ProductionStrategy;

public class AlphaProductionStrategy implements ProductionStrategy {
    @Override
    public void produce(City city) {
        city.setTreasury(city.getTreasury() + 6);
    }
}
