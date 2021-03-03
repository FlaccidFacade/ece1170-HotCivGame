package hotciv.standard;

import hotciv.framework.*;

import org.junit.*;
import org.junit.experimental.theories.suppliers.TestedOn;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.*;

/** Skeleton class for AlphaCiv test cases

    Updated Oct 2015 for using Hamcrest matchers

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
public class TestAlphaCiv {
  private Game game;

  /** Fixture for alphaciv testing. */
  @Before
  public void setUp() {
    game = new GameImpl();
  }

  // FRS p. 455 states that 'Red is the first player to take a turn'.
  @Test
  public void shouldBeRedAsStartingPlayer() {
    assertThat(game, is(notNullValue()));

     assertThat(game.getPlayerInTurn(), is(Player.RED));
  }

  @Test
  public void redCityPlacedProperly(){
    assertThat(game.getCityAt(new Position(1,1)).getOwner(),is(Player.RED));
  }

  @Test
  public void oceanPlacedProperly(){
    assertThat(game.getTileAt(new Position(1,0)).getTypeString(),is(GameConstants.OCEANS));
  }


  @Test
  public void ownershipForMovement(){
    assertThat(game.moveUnit(new Position(3,2),new Position(3,3)),is(Boolean.FALSE));
  }

  @Test
  public void cityOwnership() {
    City c = game.getCityAt(new Position(1,1));
    assertThat(c.getOwner(), is(Player.RED));
    assertThat(c.getOwner(), is(not(Player.BLUE)));
  }

  @Test
  public void cityPopulationStatic() {
    City c = game.getCityAt(new Position(1,1));
    assertThat(c.getSize(), is(1));
    assertThat(c.getSize(), is(not(0)));
    assertThat(c.getSize(), is(not(2)));
    game.endOfTurn();
    assertThat(c.getSize(), is(1));
  }

  @Test
  public void blueAfterRed() {
    assertThat(game.getPlayerInTurn(), is(Player.RED));
    game.endOfTurn();
    assertThat(game.getPlayerInTurn(), is(Player.BLUE));

  }

  @Test
  public void redAfterBlue() {
    assertThat(game.getPlayerInTurn(), is(Player.RED));
    game.endOfTurn();
    assertThat(game.getPlayerInTurn(), is(Player.BLUE));
    game.endOfTurn();
    assertThat(game.getPlayerInTurn(), is(Player.RED));
  }

  @Test
  public void aging(){
    assertThat(game.getAge(), is(-4000));
    game.endOfTurn();
    game.endOfTurn();
    assertThat(game.getAge(), is(-3900));
    for( int i = 0; i < 100; i++){
      game.endOfTurn();
    }
    assertThat(game.getAge(), is(1100));
  }

  @Test
  public void updatingCityResources() {
    //TODO testing if rounds work city growth and unit production... etc?

    // production is 6 resources red has a city at 1,1 and blue at 4,1 make sure they both have 6

    game.endOfTurn();
    game.endOfTurn();

    City c = game.getCityAt(new Position(1,1));
    assertThat(c.getTreasury(), is(6));
    City c1 = game.getCityAt(new Position(4,1));
    assertThat(c1.getTreasury(), is(6));

  }

  @Test
  public void unitProductionTreasurySpent() {
    //TODO testing if rounds work city growth and unit production... etc?

    // production is 6 resources red has a city at 1,1 and blue at 4,1 make sure they both have 6

    game.endOfTurn();
    game.endOfTurn();
    City c = game.getCityAt(new Position(1,1));
    assertThat(c.getTreasury(), is(6));
    City c1 = game.getCityAt(new Position(4,1));
    assertThat(c1.getTreasury(), is(6));

    game.endOfTurn();
    game.endOfTurn();

    assertThat(c.getTreasury(), is(2));


  }

  @Test
  public void unitProductionPlacedOnMap() {
    assertThat(game.getUnitAt(new Position(1,1)), is(nullValue()));
    game.endOfTurn();
    game.endOfTurn();
    City c = game.getCityAt(new Position(1,1));
    assertThat(c.getTreasury(), is(6));
    assertThat(game.getUnitAt(new Position(1,1)), is(nullValue()));
    game.endOfTurn();
    game.endOfTurn();
    assertThat(game.getUnitAt(new Position(0,1)), is(nullValue()));
    assertThat(game.getUnitAt(new Position(1,1)), is(notNullValue()));
    assertThat(game.getTileAt(new Position(1,1)).getUnit().getTypeString(), is("archer"));

    game.endOfTurn();
    game.endOfTurn();
    assertThat(c.getTreasury(), is(8));

    game.endOfTurn();
    game.endOfTurn();
    assertThat(c.getTreasury(), is(4));
    assertThat(game.getUnitAt(new Position(0,1)), is(notNullValue()));
    assertThat(game.getTileAt(new Position(0,1)).getUnit().getTypeString(), is("archer"));


    game.endOfTurn();
    game.endOfTurn();
    assertThat(c.getTreasury(), is(0));
    assertThat(game.getUnitAt(new Position(0,2)), is(notNullValue()));
    assertThat(game.getTileAt(new Position(0,2)).getUnit().getTypeString(), is("archer"));

  }

  @Test
  public void cityFocusChange(){
    assertThat(game.getCityAt(new Position( 1,1)).getWorkforceBalance(),is(GameConstants.productionFocus));
    game.changeWorkforceFocusInCityAt(new Position(1,1),GameConstants.foodFocus);
    assertThat(game.getCityAt(new Position(1,1)).getWorkforceBalance(), is(GameConstants.foodFocus));
  }

  @Test
  public void cityProductionChange(){

    assertThat(game.getCityAt(new Position(1,1)).getProduction(), is(GameConstants.ARCHER));
    game.changeProductionInCityAt(new Position(1,1), GameConstants.SETTLER);
    assertThat(game.getCityAt(new Position(1,1)).getProduction(), is(GameConstants.SETTLER));
  }

  @Test
  public void redWins(){
   //at 3000BC red must win
    for(int i = 0; i < 20; i++) {
      game.endOfTurn();
    }
    assertThat(game.getWinner(),is(Player.RED));
  }

  @Test
  public void attackingDestroys(){
    //TODO red's unit attack and destroy blue's unit
  }

  @Test
  public void movingAUnit(){
    //TODO test move

  }


  @Test
  public void refuseInvalidMove(){
    //TODO test move

  }

  @Test
  public void unitActionSettlerDoesNothing(){
    //TODO there is a setter at 4,3. use perform unit action at and make the settler do nothing
  }

}
