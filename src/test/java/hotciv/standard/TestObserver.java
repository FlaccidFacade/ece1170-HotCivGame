package hotciv.standard;

import hotciv.framework.Game;
import hotciv.framework.GameObserver;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.Factories.AlphaCivFactory;
import hotciv.stub.StubGame2;
import hotciv.view.CivDrawing;
import hotciv.visual.*;
import minidraw.framework.Drawing;
import minidraw.framework.DrawingEditor;
import minidraw.framework.DrawingView;
import minidraw.framework.Tool;
import minidraw.standard.MiniDrawApplication;
import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.assertThat;

import static org.hamcrest.CoreMatchers.*;
public class TestObserver {

        private Game game;
        private CivDrawing civDrawing;
        final String[] layout = new String[] {
                "...ooMooooo.....",
                "..ohhoooofffoo..",
                ".oooooMooo...oo.",
                ".ooMMMoooo..oooo",
                "...ofooohhoooo..",
                ".ofoofooooohhoo.",
                "...ooo..........",
                ".ooooo.ooohooM..",
                ".ooooo.oohooof..",
                "offfoooo.offoooo",
                "oooooooo...ooooo",
                ".ooMMMoooo......",
                "..ooooooffoooo..",
                "....ooooooooo...",
                "..ooohhoo.......",
                ".....ooooooooo..",
        };
        @Before
        public void setUp(){
                game = new GameImpl(layout, new AlphaCivFactory());
                Game game = new StubGame2();

                DrawingEditor editor =
                        new MiniDrawApplication( "Shift-Click unit to invoke its action",
                                new HotCivFactory4(game) );
                civDrawing = new CivDrawing(editor, game);

        }

        @Test
        public void testSetObserver(){
                GameObserver observerObject = new GameObserver() {
                        @Override
                        public void worldChangedAt(Position pos) {

                        }

                        @Override
                        public void turnEnds(Player nextPlayer, int age) {

                        }

                        @Override
                        public void tileFocusChangedAt(Position position) {

                        }
                };
                game.addObserver(observerObject);
                ArrayList<GameObserver> observers = game.getObserverList();
                assertThat(observers.contains(observerObject), is(Boolean.TRUE));
        }

        @Test
        public void testTurnEndsUpdate(){
                game.addObserver(civDrawing);
                game.endOfTurn();
                //look for observer print message
        }

        @Test
        public void testWorldChangedAt(){

                //look for observer print message
        }
}
