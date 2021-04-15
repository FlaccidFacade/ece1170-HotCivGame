package hotciv.framework.Factories;

import hotciv.framework.Strategies.*;

public interface HotCivFactory {

    ActionStrategy createActionStrategy();

    AgingStrategy createAgingStrategy();

    BattleStrategy createBattleStrategy();

    GrowthStrategy createGrowthStrategy();

    ProductionStrategy createProductionStrategy();

    WinningStrategy createWinningStrategy();
}
