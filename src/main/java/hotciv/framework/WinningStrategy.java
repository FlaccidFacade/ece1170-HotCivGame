package hotciv.framework;

import hotciv.framework.World;

import java.util.Map;

public interface WinningStrategy {
    /** get the winner
     * winner is based off of what your strategy is set to
     */
    public Player getWinner(int age, World world, Map<Player, Integer> playerIntegerMap, int round);
}
