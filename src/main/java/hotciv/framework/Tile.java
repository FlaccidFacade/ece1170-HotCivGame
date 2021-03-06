package hotciv.framework;

/** Tile represents a single territory tile of a given type.

    Responsibilities:
    1) Know its type.

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

public interface Tile {

  /** return the tile type as a string. The set of
   * valid strings are defined by the graphics
   * engine, as they correspond to named image files.
   * @return the type type as string
   */
  String getTypeString();

  /** adds a unit to this
   *
   * @param u
   */
  void addUnit(Unit u);

  /** adds a city to this tile
   *
   * @param c the city that will be added to this tile
   */
  void addCity(City c);

  /** gives this tile's unit
   *
   * @return unit this is the unit on this tile
   */
  Unit getUnit();

  /** gives this tile's city
   *
   * @return city this is the city on this tile
   *
   */
  City getCity();

  /** makes this tile's unit null
   *
   */
  void removeUnit();

  /** makes this tile's city null
   *
   */
  void removeCity();

}
