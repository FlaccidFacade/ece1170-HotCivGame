package hotciv.standard.Factories;

import hotciv.framework.Factories.HotCivFactory;
import hotciv.framework.Strategies.*;

public class AlphaCivFactory implements HotCivFactory {
    @Override
    public ActionStrategy createActionStrategy() {
        return null;
    }

    @Override
    public AgingStrategy createAgingStrategy() {
        return null;
    }

    @Override
    public BattleStrategy createBattleStrategy() {
        return null;
    }

    @Override
    public GrowthStrategy createGrowthStrategy() {
        return null;
    }

    @Override
    public ProductionStrategy createProductionStrategy() {
        return null;
    }

    @Override
    public WinningStrategy createWinningStrategy() {
        return null;
    }
}
