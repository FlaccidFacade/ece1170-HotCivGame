package hotciv.framework;

public interface ActionStrategy {
    /** performs unit action at position p on world w
     *
     * @param p position of the unit that needs to take action
     * @param w world that the unit is on
     */
    public void performAction(Position p, World w);
}
