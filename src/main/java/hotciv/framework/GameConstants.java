package hotciv.framework;

/** Collection of constants used in HotCiv Game. Note that strings are
 * used instead of enumeration types to keep the set of valid
 * constants open to extensions by future HotCiv variants.  Enums can
 * only be changed by compile time modification.

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
public class GameConstants {
  // The size of the world is set permanently to a 16x16 grid 
  public static final int WORLDSIZE = 16;
  // Valid unit types
  public static final String ARCHER    = "archer";
  public static final String LEGION    = "legion";
  public static final String SETTLER   = "settler";

  //Valid unit attack strengths
  public static final int ARCHER_ATTACKING_STRENGTH = 2;
  public static final int LEGION_ATTACKING_STRENGTH = 4;
  public static final int SETTLER_ATTACKING_STRENGTH = 0;

  //Valid unit defense strengths
  public static final int ARCHER_DEFENSIVE_STRENGTH = 3;
  public static final int LEGION_DEFENSIVE_STRENGTH = 2;
  public static final int SETTLER_DEFENSIVE_STRENGTH = 3;


  // Valid terrain types
  public static final String PLAINS    = "plains";
  public static final String OCEANS    = "ocean";
  public static final String FOREST    = "forest";
  public static final String HILLS     = "hills";
  public static final String MOUNTAINS = "mountain";

  // Valid production balance types
  public static final String productionFocus = "hammer";
  public static final String foodFocus = "apple";

  //City constants
  public static final int POPULATION_SIZE = 1;
  public static final int PRODUCTION = 6;
  //Valid Number of players
  public static final int NUMBER_OF_PLAYERS = 2;

  //Game time
  public static final int START_TIME = 4000;
  public static final int INCREMENT_TIME = 100;

  //
}
