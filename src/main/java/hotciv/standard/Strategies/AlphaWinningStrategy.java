package hotciv.standard.Strategies;

import hotciv.framework.Player;
import hotciv.framework.Strategies.WinningStrategy;
import hotciv.framework.World;

import java.util.Map;

public class AlphaWinningStrategy implements WinningStrategy {
    @Override
    public Player getWinner(int age, World world, Map<Player, Integer> playerIntegerMap, int round) {
        if(age >= -3000){
            return Player.RED;
        }
        return null;
    }
}
