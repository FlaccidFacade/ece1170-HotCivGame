package hotciv.standard;

import hotciv.framework.Player;
import hotciv.framework.WinningStrategy;
import hotciv.framework.World;

import java.util.Map;
import java.util.HashMap;

public class EpsilonWinningStrategy implements WinningStrategy {

    @Override
    public Player getWinner(int age, World world, Map<Player, Integer> playerIntegerMap) {
        for(Map.Entry<Player,Integer> entry : playerIntegerMap.entrySet()){
            if(entry.getValue() == 3){
                return entry.getKey();
            }
        }
      return null;
    }
}
