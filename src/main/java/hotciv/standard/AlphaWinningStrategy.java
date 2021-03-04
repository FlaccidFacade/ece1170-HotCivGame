package hotciv.standard;

import hotciv.framework.Player;
import hotciv.framework.WinningStrategy;
import hotciv.framework.World;

public class AlphaWinningStrategy implements WinningStrategy {
    @Override
    public Player getWinner(int age, World world) {
        if(age >= -3000){
            return Player.RED;
        }
        return null;
    }
}
