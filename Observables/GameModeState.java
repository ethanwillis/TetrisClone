
package Observables;

import java.awt.event.KeyEvent;

/**
 * Defines actions for client input while TetrisGame game is in the GameModeState
 * @author ewillis
 */
public class GameModeState implements State
{

    long rate, lastAction, newAction;
    /**
     * Default constructor that creates a new active piece for the client
     * when the game starts
     * @param game a reference to the model for performing actions.
     */
    public GameModeState( TetrisGame game )
    {
        rate = 50;
        lastAction = System.currentTimeMillis();
        game.createNewPiece();
        game.notifyObs();
    }
    /**
     * Responds to input from a client. 
     * Certain inputs are rate limited.
     * Times of a previous rate limited action are recorded
     * and subsequent actions are ignored if they occur within a certain
     * timeframe.
     * 
     * The KeyEvent event has associated with it a keycode that corresponds
     * to the different keys on a standard keyboard. 
     * GameModeState responds to the keys Escape, Enter, Left Arrow, Right Arrow,
     * Down Arrow, Spacebar, Up Arrow, Right Ctrl, and a keycode of 242 that is 
     * used to represent a dialog acknowledgement.
     * 
     * KeyName: Escape
     * KeyCode: 27
     * Action : The Program will exit.
     * 
     * KeyName: Left Arrow
     * KeyCode: 37
     * Action: The active piece for the client (block piece) will be transformed 
     * to the left by 1 block ( if legal ) for each unit of a predetermined 
     * amount of time. 
     * 
     * KeyName: Right Arrow
     * KeyCode: 39
     * Action:  The active piece for the client will be transformed to the right 
     * by 1 block. ( if legal ) for each
     * 
     * KeyName: Down Arrow
     * KeyCode: 40
     * Action:  The active piece for the client will be transformed to the right 
     * by 1 block. ( if legal ) for each
     * 
     * KeyName: Spacebar
     * KeyCode: 32
     * Action: The active piece falls to its final position and locks into place.
     * 
     * KeyName: Up Arrow
     * KeyCode: 38
     * Action: The active piece is rotated clockwise by 90 degrees.
     * 
     * KeyName: Right Ctrl
     * KeyCode: 17
     * Action: The active piece is rotated counter clockwise by 90 degrees.
     * 
     * KeyName: Dialog Ack
     * KeyCode: 242
     * Action: The program has displayed a dialog informing the client they have 
     * lost in Game Mode and the client has acknowledged it. The game will then
     * revert to a WaitingState from the GameModeState.
     * 
     * @param event The KeyEvent that the client performed
     * @param game The TetrisGame that will possibly be modified
     * depending on client input
     * @param pressedOrReleased 
     * 
     * 
     */
    public void respond(KeyEvent event, TetrisGame game, boolean pressedOrReleased)
    {
        newAction = System.currentTimeMillis();
        if( (newAction - lastAction) > rate)
        {
            int keyCode = event.getKeyCode();
            if(keyCode == 27) //escape key was pressed by client
            {
                System.exit(1);
            }
            else if( keyCode == 37 ) // left arrow was pressed by client
            {
                game.translate( -1, 0 );
            }
            else if( keyCode == 39 ) // right arrow was pressed by client
            {
                game.translate( 1, 0 );
            }
            else if( keyCode == 40 ) // down arrow was pressed by client
            {
                //check if the piece can move down farther
               if( !game.translate( 0, 1 ) )
               {
                  // If not lock it into place, remove full lines and create a new piece.
                   game.lockActivePiece();
               }
            }
            else if( keyCode == 32 ) // space bar was pressed by client
            {
                game.hardTranslate();
            }
            else if( keyCode == 38 ) // Up arrow was pressed by client
            {
                boolean cwRot = true;
                game.transform( cwRot );
            }
            else if( keyCode == 17 ) // Right ctrl was pressed by client
            {
                boolean cwRot = false;
                game.transform( cwRot );
            }
            else if( keyCode == 242 ) // Loss dialog was acknowledged by client.
            {
                game.setState(new WaitingState());
            }
            else
            {
                // ignore extraneous input
            }
        }
        lastAction = newAction;
    }
    
}
