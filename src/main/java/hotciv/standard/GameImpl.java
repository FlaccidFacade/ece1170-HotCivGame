package hotciv.standard;

import hotciv.framework.*;
import hotciv.framework.Factories.HotCivFactory;
import hotciv.framework.Strategies.*;
import hotciv.standard.Strategies.ZetaWinningStrategy;

import java.util.HashMap;
import java.util.Map;

/** Skeleton implementation of HotCiv.

This source code is from the book
"Flexible, Reliable Software:
Using Patterns and Agile Development"
published 2010 by CRC Press.
Author:
Henrik B Christensen
Department of Computer Science
Aarhus University

Please visit http://www.baerbak.com/ for further information.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

*/

public class GameImpl implements Game {
    private int age;
    private int round =1;
    private Player currentTurn = Player.RED, firstPlayer = currentTurn;
    private Map<Player, Integer> playerSuccessfulAttackMap ;
    private final World world;
    private AgingStrategy agingStrategy;
    private WinningStrategy winningStrategy;
    private ActionStrategy actionStrategy;
    private BattleStrategy battleStrategy;

    public GameImpl(){
        age = GameConstants.START_TIME;
        world = new WorldImpl();
        playerSuccessfulAttackMap = new HashMap<>();
    }

    public GameImpl(String[] layout){
        age = GameConstants.START_TIME;
        world = new WorldImpl(layout);
        playerSuccessfulAttackMap = new HashMap<>();
    }

    public GameImpl(String[] layout, HotCivFactory factory){
        age = GameConstants.START_TIME;
        world = new WorldImpl(layout);
        playerSuccessfulAttackMap = new HashMap<>();
        this.actionStrategy = factory.createActionStrategy();
        this.agingStrategy = factory.createAgingStrategy();
        this.battleStrategy = factory.createBattleStrategy();
        this.setGrowthStrategy(factory.createGrowthStrategy());
        this.setProductionStrategy(factory.createProductionStrategy());
        this.winningStrategy = factory.createWinningStrategy();
    }

    public void setAgingStrategy(AgingStrategy agingStrategy){
        this.agingStrategy = agingStrategy;
    }

    public void setWinningStrategy(WinningStrategy winningStrategy){
        this.winningStrategy = winningStrategy;
    }

    public void setActionStrategy(ActionStrategy actionStrategy){
        this.actionStrategy = actionStrategy;
    }

    public void setBattleStrategy(BattleStrategy battleStrategy){
        this.battleStrategy = battleStrategy;
    }

    public void setGrowthStrategy(GrowthStrategy growthStrategy) { this.world.setGrowthStrategy(growthStrategy); }

    public void setProductionStrategy(ProductionStrategy productionStrategy) {
        this.world.setProductionStrategy(productionStrategy);
    }

    public Tile getTileAt( Position p ) { return world.getTileAt(p); }

    public void placeTileAt( Position p , Tile t) { world.placeTile(p,t);}

    public Unit getUnitAt( Position p ) { return world.getUnitAt(p); }

    public void placeUnitAt( Position p , Unit u) { world.placeUnit(p,u);}

    public City getCityAt( Position p ) { return world.getCityAt(p); }

    public void placeCityAt(Position p , City c) { world.placeCity(p,c);}

    public Player getPlayerInTurn() { return currentTurn; }

    public Player getWinner() {
        return winningStrategy.getWinner(age, world, playerSuccessfulAttackMap, round) ;

    }

    public World getWorld() {
        return world;
    }

    public int getAge() { return age; }

    public boolean moveUnit( Position from, Position to ) {
        boolean isAMove = from != to;
        if ( ! isAMove) return false;

        boolean existsOnFromTile = world.getUnitAt(from) != null;
        if( ! existsOnFromTile) return false;

        boolean playerHasOwnerShip = world.getUnitAt(from).getOwner() == currentTurn;
        if( ! playerHasOwnerShip) return false;

        boolean unitIsMovable = world.movable(from,to);
        if( ! unitIsMovable) return false;

        //make sure unit 'to' has proper ownership
        Unit unitTo = this.getUnitAt(to);
        Unit unitFrom = this.getUnitAt(from);
        if(unitTo != null) {
            if (unitTo.getOwner() != unitFrom.getOwner()) {
                return attack(from, to);
            }else
                return false;
        }

        //check if is unit and player has ownership and if unit can move and if distance is allowed
        if( unitIsMovable &&
            existsOnFromTile &&
            playerHasOwnerShip
        ){
            Unit temp = world.getTileAt(from).getUnit();
            world.removeUnit(from);
            temp.move();
            world.placeUnit(to,temp);
            return true;
        }
        return false;
    }

    public boolean attack(Position attacker, Position defender) {
        boolean attackerWins =
                getUnitAt(attacker).getOwner() == battleStrategy.getVictor(attacker, defender, world).getOwner();

        if (attackerWins) {
            Unit temp = world.getTileAt(attacker).getUnit();
            world.removeUnit(attacker);
            temp.move();
            world.placeUnit(defender, temp);
            //map.put(key, map.get(key) + 1);
            //round test is 19
            if( (winningStrategy instanceof ZetaWinningStrategy && round > 19)) {
                if (playerSuccessfulAttackMap != null && playerSuccessfulAttackMap.get(currentTurn) != null) {
                    playerSuccessfulAttackMap.put(currentTurn, playerSuccessfulAttackMap.get(currentTurn) + 1);
                } else {
                    playerSuccessfulAttackMap.put(currentTurn, (Integer) 1);
                }
            }else{
                if (playerSuccessfulAttackMap != null && playerSuccessfulAttackMap.get(currentTurn) != null) {
                    playerSuccessfulAttackMap.put(currentTurn, playerSuccessfulAttackMap.get(currentTurn) + 1);
                } else {
                    playerSuccessfulAttackMap.put(currentTurn, (Integer) 1);
                }
            }
        } else{
            world.removeUnit(attacker);
        }

        return attackerWins;
    }

    public void endOfTurn() {

        currentTurn = currentTurn.next();

        if(currentTurn == firstPlayer && round > 0){
            round++;
            endOfRound();
        }

    }

    private void endOfRound(){
        age = agingStrategy.ageWorld(age);
        world.updateAllCityTreasury();
        world.produceAllCityUnits();
        world.updateAllMoveCounts();

    }

    public void changeWorkforceFocusInCityAt( Position p, String balance ) {
        City c = world.getCityAt(p);
        c.setWorkforceBalance(balance);
    }

    public void changeProductionInCityAt( Position p, String unitType ) {
        City c = world.getCityAt(p);
        c.changeProduction(unitType);
    }

    public void performUnitActionAt( Position p ) {
       actionStrategy.performAction(p, world);
    }


}
