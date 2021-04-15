package hotciv.framework.Strategies;

import hotciv.framework.Player;
import hotciv.framework.World;

import java.util.Map;

public interface WinningStrategy {
    /** get the winner
     * winner is based off of what your strategy is set to
     */
    Player getWinner(int age, World world, Map<Player, Integer> playerIntegerMap, int round);
}
