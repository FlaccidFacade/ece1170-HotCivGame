package hotciv.standard.Decorators;

import hotciv.framework.*;
import hotciv.framework.Strategies.*;

public class GameWrapper implements Game {

    private Game decoratee;
    private Game game;
    public GameWrapper(Game game){
        this.game = game;
    }

    @Override
    public Tile getTileAt(Position p) {
        return game.getTileAt(p);
    }

    @Override
    public Unit getUnitAt(Position p) {
        return game.getUnitAt(p);
    }

    @Override
    public City getCityAt(Position p) {
        return game.getCityAt(p);
    }

    @Override
    public Player getPlayerInTurn() {
        Player current = game.getPlayerInTurn();
        System.out.println("Current player is "
                + current
                + "."
        );
        return current;
    }

    @Override
    public Player getWinner() {
        Player winner = game.getWinner();
        if(winner == null){
            System.out.println(
                    "Game winner is undetermined."
            );
        }else {
            System.out.println("Game winner is "
                    + winner.toString()
                    + "."
            );
        }
        return game.getWinner();
    }

    @Override
    public int getAge() {
        int age = game.getAge();
        System.out.println("Game age is "
                + age
                + "."
        );
        return age;

    }

    @Override
    public boolean moveUnit(Position from, Position to) {
        boolean isMovable = game.moveUnit(from, to);
        if(isMovable){
            System.out.print(game.getPlayerInTurn().toString()
                + " moves "
                + game.getUnitAt(from).getTypeString()
                + " from "
                + from.toString()
                + " to "
                + to.toString()
                + "."
            );
        }

        return isMovable;
    }

    @Override
    public void endOfTurn() {
        System.out.println(game.getPlayerInTurn().toString()
                + "'s end of turn."
        );
        game.endOfTurn();
    }

    @Override
    public void changeWorkforceFocusInCityAt(Position p, String balance) {
        System.out.println(game.getPlayerInTurn().toString()
                + " changes work force focus in city at "
                + p.toString()
                + " to "
                + balance
                + "."
        );
        game.changeWorkforceFocusInCityAt(p, balance);
    }

    @Override
    public void changeProductionInCityAt(Position p, String unitType) {
        System.out.println(game.getPlayerInTurn().toString()
                + " changes production in city at "
                + p.toString()
                + " to "
                + unitType
                + "."
        );
        game.changeProductionInCityAt(p, unitType);
    }

    @Override
    public void performUnitActionAt(Position p) {
        System.out.println(game.getPlayerInTurn().toString()
                + " performs unit action at "
                + p.toString()
                + "."
        );
        game.performUnitActionAt(p);
    }

    @Override
    public void setAgingStrategy(AgingStrategy agingStrategy) {
        game.setAgingStrategy(agingStrategy);
    }

    @Override
    public void setWinningStrategy(WinningStrategy winningStrategy) {
        game.setWinningStrategy(winningStrategy);
    }

    @Override
    public void setActionStrategy(ActionStrategy actionStrategy) {
        game.setActionStrategy(actionStrategy);
    }

    @Override
    public void setBattleStrategy(BattleStrategy battlerStrategy) {
        game.setBattleStrategy(battlerStrategy);
    }

    @Override
    public void setGrowthStrategy(GrowthStrategy growthStrategy) {
        game.setGrowthStrategy(growthStrategy);
    }

    @Override
    public void setProductionStrategy(ProductionStrategy productionStrategy) {
        game.setProductionStrategy(productionStrategy);
    }

    @Override
    public void placeTileAt(Position p, Tile t) {
        game.placeTileAt(p, t);
    }

    @Override
    public void placeUnitAt(Position p, Unit u) {
        System.out.println(u.getOwner().toString()
                + " places "
                + u.getTypeString()
                + " at "
                + p.toString()
                + "."
        );
        game.placeUnitAt(p, u);
    }

    @Override
    public void placeCityAt(Position p, City c) {
        System.out.println(c.getOwner().toString()
                + " places city at "
                + p.toString()
                + "."
        );
        game.placeCityAt(p, c);
    }

    @Override
    public void toggleLog() {
        if(game == decoratee) {
            decoratee = game;
            game = new GameWrapper(game);
        } else{
            game = decoratee;
        }

    }
}