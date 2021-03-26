package hotciv.standard;

import hotciv.framework.*;

import java.util.List;
import java.util.Map;

public class ZetaWinningStrategy implements WinningStrategy {

    @Override
    public Player getWinner(int age, World world, Map<Player, Integer> playerIntegerMap) {
        //TODO this needs to be same as epsilon after 20 rounds
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
