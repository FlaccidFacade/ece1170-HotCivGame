package hotciv.standard;

import hotciv.framework.AgingStrategy;
import hotciv.framework.GameConstants;

public class AlphaAgingStrategy implements AgingStrategy {

    @Override
    public int ageWorld(int currentAge) {
        return currentAge + GameConstants.INCREMENT_TIME;
    }
}
