package hotciv.standard.Strategies;

import hotciv.framework.*;
import hotciv.framework.Strategies.WinningStrategy;

import java.util.Map;

public class ZetaWinningStrategy implements WinningStrategy {

    private final WinningStrategy betaWinningStrategy = new BetaWinningStrategy();
    private final WinningStrategy epsilonWinningStrategy = new EpsilonWinningStrategy();

    @Override
    public Player getWinner(int age, World world, Map<Player, Integer> playerIntegerMap, int round) {
        if(round < 19) {
            return betaWinningStrategy.getWinner(age,world,playerIntegerMap,round);


        }else {
            return epsilonWinningStrategy.getWinner(age, world, playerIntegerMap, round);
        }
    }
}
