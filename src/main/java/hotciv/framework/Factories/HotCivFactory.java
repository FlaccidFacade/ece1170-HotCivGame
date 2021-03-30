package hotciv.framework.Factories;

import hotciv.framework.Strategies.*;

public interface HotCivFactory {

    public ActionStrategy createActionStrategy();

    public AgingStrategy createAgingStrategy();

    public BattleStrategy createBattleStrategy();

    public GrowthStrategy createGrowthStrategy();

    public ProductionStrategy createProductionStrategy();

    public WinningStrategy createWinningStrategy();
}
