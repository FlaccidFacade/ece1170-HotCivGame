package hotciv.standard;

import hotciv.framework.*;

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
    private final World world;
    private AgingStrategy agingStrat;
    private WinningStrategy winningStrat;
    private ActionStrategy actionStrat;

    public GameImpl(){
        age = GameConstants.START_TIME;
        world = new WorldImpl();
    }

    public GameImpl(String[] layout){
        age = GameConstants.START_TIME;
        world = new WorldImpl(layout);
    }

    public void setAgingStrategy(AgingStrategy aging){
        agingStrat = aging;
    }

    public void setWinningStrategy(WinningStrategy winning){
        winningStrat = winning;
    }

    public void setActionStrategy(ActionStrategy action){
        actionStrat = action;
    }

    public Tile getTileAt( Position p ) { return world.getTileAt(p); }

    public void placeTileAt( Position p , Tile t) { world.placeTile(p,t);}

    public Unit getUnitAt( Position p ) { return world.getUnitAt(p); }

    public void placeUnitAt( Position p , Unit u) { world.placeUnit(p,u);}

    public City getCityAt( Position p ) { return world.getCityAt(p); }

    public void placeCityAt(Position p , City c) { world.placeCity(p,c);}

    public Player getPlayerInTurn() { return currentTurn; }

    public Player getWinner() {
        return winningStrat.getWinner(age, world) ;
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

    public void endOfTurn() {

        currentTurn = currentTurn.next();

        if(currentTurn == firstPlayer && round > 0){
            round++;
            endOfRound();
        }

    }

    public void endOfRound(){
        age = agingStrat.ageWorld(age);
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
       actionStrat.performAction(p, world);
    }


}
