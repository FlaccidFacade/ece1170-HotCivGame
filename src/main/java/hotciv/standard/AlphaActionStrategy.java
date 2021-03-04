package hotciv.standard;

import hotciv.framework.*;

public class AlphaActionStrategy implements ActionStrategy {
    @Override
    public void performAction(Position p, World w) {
        Unit u = w.getUnitAt(p);
        if(u != null) {
            switch(u.getTypeString()){
                case GameConstants.ARCHER:
                    u.setDefensiveStrength(u.getDefensiveStrength() + 1);
                    u.setMoveCount(0);
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
