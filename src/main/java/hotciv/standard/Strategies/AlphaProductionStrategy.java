package hotciv.standard.Strategies;

import hotciv.framework.City;
import hotciv.framework.Strategies.ProductionStrategy;

public class AlphaProductionStrategy implements ProductionStrategy {
    @Override
    public void produce(City city) {
        city.setTreasury(city.getTreasury() + 6);
    }
}
