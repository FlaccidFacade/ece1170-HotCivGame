package hotciv.framework;

public interface AgingStrategy {
    /** returns the age of the world
     *
     * @param currentAge the current age of the world
     * @return the age of the world
     */
    public int ageWorld(int currentAge);
}
