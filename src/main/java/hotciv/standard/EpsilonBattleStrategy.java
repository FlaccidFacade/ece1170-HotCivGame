package hotciv.standard;

import hotciv.framework.BattleStrategy;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.framework.World;

public class EpsilonBattleStrategy implements BattleStrategy {
    @Override
    public Unit getVictor(Position attacker, Position defender, World world) {
        int combinedAttackStrength = world.getCombinedAttackStrength(attacker);
        int combinedDefenseStrength = world.getCombinedDefenseStrength(defender);

        if(combinedAttackStrength > combinedDefenseStrength){
            return world.getUnitAt(attacker);
        }else if (combinedAttackStrength < combinedDefenseStrength){
            return world.getUnitAt(defender);
        }else{
            return world.getUnitAt(attacker);
        }

    }
}
