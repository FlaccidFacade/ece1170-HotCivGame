package hotciv.standard.Strategies;

import hotciv.framework.Player;
import hotciv.framework.Strategies.WinningStrategy;
import hotciv.framework.World;

import java.util.Map;

public class EpsilonWinningStrategy implements WinningStrategy {

    @Override
    public Player getWinner(int age, World world, Map<Player, Integer> playerIntegerMap, int round) {

            for (Map.Entry<Player, Integer> entry : playerIntegerMap.entrySet()) {
                if (entry.getValue() == 3) {
                    return entry.getKey();
                }
            }

      return null;
    }
}
