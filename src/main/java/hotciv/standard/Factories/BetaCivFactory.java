package hotciv.standard.Factories;

import hotciv.framework.Factories.HotCivFactory;
import hotciv.framework.Strategies.*;
import hotciv.standard.Strategies.*;

public class BetaCivFactory implements HotCivFactory {
    @Override
    public ActionStrategy createActionStrategy() {
        return new AlphaActionStrategy();
    }

    @Override
    public AgingStrategy createAgingStrategy() {
        return new BetaAgingStrategy();
    }

    @Override
    public BattleStrategy createBattleStrategy() {
        return new AlphaBattleStrategy();
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
        return new BetaWinningStrategy();
    }
}
