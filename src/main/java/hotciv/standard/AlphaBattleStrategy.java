package hotciv.standard;

import hotciv.framework.BattleStrategy;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.framework.World;

public class AlphaBattleStrategy implements BattleStrategy {

    @Override
    public Unit getVictor(Position attacker, Position defender, World world) {
        return world.getUnitAt(attacker);
    }
}
