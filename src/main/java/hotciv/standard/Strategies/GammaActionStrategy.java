package hotciv.standard.Strategies;

import hotciv.framework.*;
import hotciv.framework.Strategies.ActionStrategy;
import hotciv.standard.CityImpl;

public class GammaActionStrategy implements ActionStrategy {
    @Override
    public void performAction(Position p, World w) {
        Unit u = w.getUnitAt(p);
        if(u != null) {
            switch(u.getTypeString()){
                case GameConstants.ARCHER:
                    if(u.getDefensiveStrength() == GameConstants.ARCHER_DEFENSIVE_STRENGTH){
                        u.setDefensiveStrength(u.getDefensiveStrength() * 2);
                        u.setMoveCount(0);
                    }else{
                        u.setDefensiveStrength(u.getDefensiveStrength() / 2);
                        u.setMoveCount(1);
                    }

                    break;
                case GameConstants.SETTLER:
                    w.placeCity(p,new CityImpl(u.getOwner()));
                    w.removeUnit(p);

                    break;
                case GameConstants.LEGION:
                    break;
            }
        }
    }
}
