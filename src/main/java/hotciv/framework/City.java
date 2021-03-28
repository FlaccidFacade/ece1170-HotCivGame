package hotciv.framework;

/** Represents a city in the game.

Responsibilities:
1) Knows its owner.
2) Knows its population size.

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
public interface City {
  /** return the owner of this city.
   * @return the player that controls this city.
   */
  public Player getOwner();
  
  /** return the size of the population.
   * @return population size.
   */
  public int getPopulation();

  /** return the treasury, i.e. the
   * number of 'money'/production in the
   * city's treasury which can be used to
   * produce a unit in this city
   * @return an integer, the amount of production
   * in the city treasury
   */
  public int getTreasury();

  /** return the type of unit this city is currently producing.
   * @return a string type defining the unit under production,
   * see GameConstants for valid values.
   */
  public String getProduction();

  /** return the work force's focus in this city.
   * @return a string type defining the focus, see GameConstants
   * for valid return values.
   */
  public String getWorkforceBalance();

  /**
   *
   * @return
   */
  public int getFood();

  /**
   *
   */
  public void setFood(int food);

  /**change the type of unit the city is going to produce
   *
   * @param unitType the type of unit the city should be producing
   */
  public void changeProduction(String unitType);

  /** set the treasury of city given an integer value
   *
   * @param treasury the amount the treasury will be set to
   */
  public void setTreasury(int treasury);

  /** set the size of city given an integer value
   * size will never be under 0
   * that is if size is set to a negative value
   * the size will instead be set to 0
   * @param population the value the size will be set to
   */
  public void setPopulation(int population);

  /** declare the owner of the city
   *
   * @param player the player that owns this city
   */
  public void setOwner(Player player);

  /** set the work force focus/balance
   *
   * @param focus the focus of the city either units/productionFocus or people/foodFocus
   */
  public void setWorkforceBalance(String focus);

  /** returns true if it spends treasury to produce a unit
   * precondition: city has enough treasury
   * @return if the city had enough treasury to create a unit
   */
  public boolean produceUnit();


}
