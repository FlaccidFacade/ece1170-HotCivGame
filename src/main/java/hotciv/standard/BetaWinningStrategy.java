package hotciv.standard;

import hotciv.framework.*;

import java.util.Map;

public class BetaWinningStrategy implements WinningStrategy {
    @Override
    public Player getWinner(int age, World world, Map<Player, Integer> playerIntegerMap) {
        Player winner = null;
        for(int r = 0; r < GameConstants.WORLDSIZE; r++) {
            for (int c = 0; c < GameConstants.WORLDSIZE; c++) {
                if(world.getCityAt(new Position(r,c)) != null){
                    if(winner == null){
                        winner = world.getCityAt(new Position(r,c)).getOwner();
                    }else if(winner != world.getCityAt(new Position(r,c)).getOwner()){
                        return null;
                    }

                }
            }
        }
        return winner;
    }
}
