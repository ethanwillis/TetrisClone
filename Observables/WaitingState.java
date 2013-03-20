package Observables;

import java.awt.event.KeyEvent;

/**
 * Defines actions for client inputs while the TetrisGame is in the waiting state.
 * @author ewillis
 */
public class WaitingState implements State 
{
    TetrisGame game;
    long rate, lastAction, newAction;

    public WaitingState( TetrisGame game )
    {
        rate = 50;
        lastAction = System.currentTimeMillis();
        game.drawBlocks();
    }

    public WaitingState( )
    {
        rate = 50;
        lastAction = System.currentTimeMillis();
    }
    
    /**
     * Responds to input from a client. 
     * No Inputs in this state are rate limited.
     * 
     * The KeyEvent event has associated with it a keycode that corresponds
     * to the different keys on a standard keyboard. 
     * WaitingState needs to respond to the keys Escape and Enter.
     * KeyName: Escape; KeyCode: 27
     * Action : The Program will exit.
     * KeyName: Enter;  KeyCode: 10
     * Action: TetrisGame game will change states.
     * 
     * @param event The KeyEvent that the client performed
     * @param game The TetrisGame that will possibly be modified
     * depending on client input
     * @param pressedOrReleased a boolean that identifies whether the keyevent was
     * a keypress or a keyrelease event. True means it is a keypress. False
     * means it is key release
     */
    public void respond(KeyEvent event, TetrisGame game, boolean pressedOrReleased)
    {
        newAction = System.currentTimeMillis();
        if( (newAction - lastAction) > rate)
        {
            int keyCode = event.getKeyCode();
            if(keyCode == 10) // if the enter key is pressed.
            {
                State newState = new GameModeState( game );
                game.setState(newState);
            }
            else if(keyCode == 27) // If the escape key is pressed
            {
                System.exit(1);
            }
            else // else any other key was pressed
            {
                //ignore extraneous input
            }
        }
        lastAction = newAction;
    }
    

}
