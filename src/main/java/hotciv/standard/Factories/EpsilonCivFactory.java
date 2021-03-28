package hotciv.standard.Factories;

import hotciv.framework.Factories.HotCivFactory;
import hotciv.framework.Strategies.*;
import hotciv.standard.Strategies.*;

public class EpsilonCivFactory implements HotCivFactory {
    @Override
    public ActionStrategy createActionStrategy() {
        return new AlphaActionStrategy();
    }

    @Override
    public AgingStrategy createAgingStrategy() {
        return new AlphaAgingStrategy();
    }

    @Override
    public BattleStrategy createBattleStrategy() {
        return new EpsilonBattleStrategy();
    }

    @Override
    public GrowthStrategy createGrowthStrategy() {
        return new AlphaGrowthStrategy();
    }

    @Override
    public ProductionStrategy createProductionStrategy() {
        return new AlphaProductionStrategy();
    }

    @Override
    public WinningStrategy createWinningStrategy() {
        return new EpsilonWinningStrategy();
    }
}
