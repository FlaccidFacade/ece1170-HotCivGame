package hotciv.framework;

import hotciv.framework.World;

public interface WinningStrategy {
    /** get the winner
     * winner is based off of what your strategy is set to
     */
    public Player getWinner(int age, World world);
}
