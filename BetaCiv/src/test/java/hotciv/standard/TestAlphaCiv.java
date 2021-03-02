package hotciv.standard;

import hotciv.framework.*;

import org.junit.*;
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
    // TODO: reenable the assert below to get started...
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
  public void cityFocusChange(){
    assertThat(game.getCityAt(new Position( 1,1)).getWorkforceFocus(),is(GameConstants.productionFocus));
    game.changeWorkForceFocusInCityAt(new Position(1,1),GameConstants.foodFocus);
    assertThat(game.getCityAt(new Position(1,1)).getWorkforceFocus(), is(GameConstants.foodFocus));
  }


  @Test
  public void redWins(){
   //at 3000BC red must win
    for(int i = 0; i < 21; i++)
      game.endOfTurn();

    assertThat(game.getWinner(),is(Player.RED));
  }
}
