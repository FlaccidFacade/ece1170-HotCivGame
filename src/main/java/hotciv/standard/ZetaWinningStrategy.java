package hotciv.standard;

import hotciv.framework.*;

import java.util.List;
import java.util.Map;

public class ZetaWinningStrategy implements WinningStrategy {

    @Override
    public Player getWinner(int age, World world, Map<Player, Integer> playerIntegerMap, int round) {
        List<City> cityList = world.getAllCities();
        Player winner = cityList.get(0).getOwner();
        if(round < 19) {

            for (City c : cityList) {
                if (c.getOwner() != winner) {
                    return null;
                }
            }
        }else{
            for (Map.Entry<Player, Integer> entry : playerIntegerMap.entrySet()) {
                if (entry.getValue() == 3) {
                    return entry.getKey();
                }
            }
        }


        return winner;
    }
}
