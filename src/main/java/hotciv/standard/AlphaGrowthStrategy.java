package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.GrowthStrategy;

public class AlphaGrowthStrategy implements GrowthStrategy {
    @Override
    public void grow(City city) {
        city.setPopulation(1);
    }
}
