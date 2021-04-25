package hotciv.visual;

import hotciv.framework.City;
import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.stub.StubGame1;
import hotciv.view.CityFigure;
import hotciv.view.GfxConstants;
import hotciv.view.MapView;
import minidraw.framework.Drawing;
import minidraw.framework.DrawingEditor;
import minidraw.framework.DrawingView;
import minidraw.framework.Factory;
import minidraw.standard.MiniDrawApplication;
import minidraw.standard.NullTool;
import minidraw.standard.StandardDrawing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

/** Test the CityFigure.
 * 
   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Computer Science Department
     Aarhus University
   
   This source code is provided WITHOUT ANY WARRANTY either 
   expressed or implied. You may study, use, modify, and 
   distribute it for non-commercial purposes. For any 
   commercial use, see http://www.baerbak.com/
 */
public class ShowCity {
  
  public static void main(String[] args) {

    Game game = new StubGame1();

    DrawingEditor editor = 
      new MiniDrawApplication( "Click to see city graphics update...",  
                               new HotCivFactory3(game) );
    editor.open();
    CityStub city = new CityStub();

    CityFigure cf = new CityFigure( city,
                                    new Point( GfxConstants.getXFromColumn(4),
                                               GfxConstants.getYFromRow(7) ) );
    editor.drawing().add(cf);
    editor.setTool( new ChangeCityTool(city, cf) );

  }
}

class ChangeCityTool extends NullTool {
  private CityStub city;
  private CityFigure cityFigure;
  public ChangeCityTool(CityStub c, CityFigure cf) {
    city = c;
    cityFigure = cf;
  }
  public void mouseDown(MouseEvent e, int x, int y) {
    city.makeAChange();
    cityFigure.changed();
  }
}

class HotCivFactory3 implements Factory {
  private Game game;
  public HotCivFactory3(Game g) { game = g; }

  public DrawingView createDrawingView( DrawingEditor editor ) {
    DrawingView view = 
      new MapView(editor, game);
    return view;
  }

  public Drawing createDrawing( DrawingEditor editor ) {
    return new StandardDrawing();
  }

  public JTextField createStatusField( DrawingEditor editor ) {
    return null;
  }
}

// A test stub implementation just to force some graphical updates.
class CityStub implements City {
  boolean redOwns = true;
  // a testing method just to make some
  // state changes
  public void  makeAChange() {
    redOwns = ! redOwns;
  }
  public Player getOwner() {
    return (redOwns ? Player.RED : Player.BLUE);
  }

  @Override
  public int getPopulation() {
    return 0;
  }

  public int getSize() {
    return (redOwns ? 4 : 9);
  }
  public int getTreasury() {
    return 0;
  }
  public String getProduction() {
    return null;
  }

  @Override
  public String getWorkforceBalance() {
    return null;
  }

  @Override
  public int getFood() {
    return 0;
  }

  @Override
  public void setFood(int food) {

  }

  @Override
  public void changeProduction(String unitType) {

  }

  @Override
  public void setTreasury(int treasury) {

  }

  @Override
  public void setPopulation(int population) {

  }

  @Override
  public void setOwner(Player player) {

  }

  @Override
  public void setWorkforceBalance(String focus) {

  }

  @Override
  public boolean produceUnit() {
    return false;
  }

  public String getWorkforceFocus() {
    return null;
  }
}
