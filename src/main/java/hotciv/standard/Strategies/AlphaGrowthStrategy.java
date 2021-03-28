package hotciv.standard.Strategies;

import hotciv.framework.City;
import hotciv.framework.Strategies.GrowthStrategy;

public class AlphaGrowthStrategy implements GrowthStrategy {
    @Override
    public void grow(City city) {
        city.setPopulation(1);
    }
}
