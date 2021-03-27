package hotciv.standard;

import hotciv.framework.*;

import java.util.List;
import java.util.Map;

public class BetaWinningStrategy implements WinningStrategy {
    @Override
    public Player getWinner(int age, World world, Map<Player, Integer> playerIntegerMap, int round) {
        List<City> cityList = world.getAllCities();
        Player winner = cityList.get(0).getOwner();

        for( City c : cityList){
            if(c.getOwner() != winner){
                return null;
            }
        }
        return winner;
    }
}
