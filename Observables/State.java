
package Observables;

import java.awt.event.KeyEvent;

/**
 * Definition of the State interface. The state interface allows an object
 * to have a state and to handle the same action dependent upon its state.
 * @author ewillis
 */
public interface State 
{
    public void respond(KeyEvent event, TetrisGame game, boolean pressedOrReleased);
}
