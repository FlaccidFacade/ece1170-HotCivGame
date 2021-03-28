package hotciv.framework.Strategies;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.framework.World;

public interface BattleStrategy {
    /** returns the victor of a battle
     *
     * @param attacker the unit that attacks
     * @param defender the unit that defends
     * @return the unit that succeeds in battle
     */
    public Unit getVictor(Position attacker, Position defender, World world);
}
