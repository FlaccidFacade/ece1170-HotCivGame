package hotciv.standard;

import hotciv.framework.*;
import hotciv.utility.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

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

  private List<Tile> board = new ArrayList<>();
  private int age;
  private Player currentTurn;
  private World w;
  private Player winner;

  public GameImpl(){
     age = GameConstants.START_TIME;
     currentTurn = Player.RED;
     w = new WorldImpl();
     w.placeTile(new Position(1,0), new TileImpl(GameConstants.OCEANS));
     w.placeTile(new Position(3,2), new TileImpl(GameConstants.HILLS));
     w.placeTile(new Position(2,2), new TileImpl(GameConstants.MOUNTAINS));
     w.placeUnit(new Position(2,0), new UnitImpl(currentTurn,GameConstants.ARCHER));
     w.placeUnit(new Position(4,3), new UnitImpl(currentTurn,GameConstants.SETTLER));
     w.placeUnit(new Position(3,2), new UnitImpl(Player.BLUE, GameConstants.LEGION));
     w.placeCity(new Position(1,1), new CityImpl(Player.RED));
  }
  public Tile getTileAt( Position p ) { return w.getTileAt(p); }
  public Unit getUnitAt( Position p ) { return w.getUnitAt(p); }
  public City getCityAt( Position p ) { return w.getCityAt(p); }
  public Player getPlayerInTurn() { return currentTurn; }
  public Player getWinner() { return winner ;}
  public int getAge() { return age; }

  public boolean moveUnit( Position from, Position to ) {


    //check if is unit and player has ownership and if unit can move and if distance is allowed
      if((w.getUnitAt(from) != null)
              && (w.movable(from,to))
              && (w.getUnitAt(from).getOwner() == currentTurn)
      ){
          Unit temp = w.getUnitAt(from);
          w.removeUnit(from);
          temp.move();
          w.placeUnit(to,temp);
          return true;
      }
      return false;
  }
  public void endOfTurn() {
    currentTurn.next();
    age -= GameConstants.INCREMENT_TIME/GameConstants.NUMBER_OF_PLAYERS;
    //does game have a time limit? if so check here
      if(age < 3000){
          winner = Player.RED;
      }
  }
  public void changeWorkForceFocusInCityAt( Position p, String balance ) {
      City c = w.getCityAt(p);
      c.setWorkforceBalance(balance);
  }
  public void changeProductionInCityAt( Position p, String unitType ) {
      City c = w.getCityAt(p);
      c.changeProduction(unitType);
  }
  public void performUnitActionAt( Position p ) {

  }


}
