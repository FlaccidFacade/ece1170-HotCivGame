package hotciv.standard;

import hotciv.framework.*;

import java.util.List;
import java.util.Map;

public class ZetaWinningStrategy implements WinningStrategy {

    private final WinningStrategy betaWinningStrategy = new BetaWinningStrategy();
    private final WinningStrategy epsilonWinningStrategy = new EpsilonWinningStrategy();

    @Override
    public Player getWinner(int age, World world, Map<Player, Integer> playerIntegerMap, int round) {
//        List<City> cityList = world.getAllCities();
//        Player winner = cityList.get(0).getOwner();
        if(round < 19) {
            return betaWinningStrategy.getWinner(age,world,playerIntegerMap,round);
//            for (City c : cityList) {
//                if (c.getOwner() != winner) {
//                    return null;
//                }
//            }

        }else{
            return epsilonWinningStrategy.getWinner(age,world,playerIntegerMap,round);
//            for (Map.Entry<Player, Integer> entry : playerIntegerMap.entrySet()) {
//                if (entry.getValue() == 3) {
//                    return entry.getKey();
//                }
//            }
        }


        //return winner;
    }
}
