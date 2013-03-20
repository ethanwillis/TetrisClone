package Observers;

import Observables.Block;
import Observables.TetrisGame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Displays a basic tetris gameboard.
 * @author ewillis
 */
public class Board implements KeyListener
{
    //Attributes
    private TetrisGame subject;
    private JFrame frame;
    private JButton[][] cells;
    private JPanel blockPanel;

    /**
     * Constructs a board with a rate
     * @param g
     */
    public Board( TetrisGame g )
    {
        this.frame = new JFrame("Tetris");
        this.subject = g;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Figure out how many blocks are on the game board.
        int boardHeight = subject.getHeight(), boardWidth = subject.getWidth();
        cells = new JButton[ boardWidth ][ boardHeight ];
        
        Object[][] state = subject.getState();
        // Draw all of the blocks onto the GUI
        this.drawGUI( boardHeight, boardWidth, state );

        //Display the window.
        frame.pack();
        frame.setVisible(true);

    }

    /**
     * Gets the state of the subject and redraws the GUI to faithfully represent
     * the new state of the subject
     */
    public void Update()
    {
        // Figure out how many blocks are on the game board.
        int boardHeight = subject.getHeight(), boardWidth = subject.getWidth();
        Object[][] state = subject.getState();
        // Draw all of the blocks onto the GUI
        this.drawGUI( boardHeight, boardWidth, state );
    }

    /*
     * Handles keyevents for keypresses
     */
    public void keyPressed(KeyEvent e)
    {
        // true denotes that this was a keypress event
    }

    /**
     * Handles keyevents for keyreleases
     */
    public void keyReleased(KeyEvent e)
    {
        // false denotes that this was a keyreleased event.
        subject.respond( e, false );

    }

    /**
     * Handles keyevents for typed keys
     */
    public void keyTyped(KeyEvent e)
    {
    }

    /**
     * Draws the Tetris Board and all blocks on the board as it currently exists.
     */
    private void drawGUI( int boardHeight, int boardWidth, Object[][] state )
    {
        // add each cell to the block panel
        if(blockPanel == null)
        {
            blockPanel = new JPanel( new GridLayout( boardHeight, boardWidth ) );
            // make sure that the panel has keyboard focus
            
        }

        for( int y = 0; y < boardHeight; y++ )
        {
            for( int x = 0; x < boardWidth; x++ )
            {
                if( cells[x][y] == null )
                {
                     cells[x][y] = new JButton();
                     cells[x][y].setFocusable(true);
                     cells[x][y].addKeyListener( this );
                     cells[x][y].setPreferredSize( new Dimension(30, 30) );
                }
                cells[x][y].setBackground( (Color)state[ x ][ y ] );
                
                blockPanel.add( cells[x][y] );
            }
        }
        
        // add the block panel to the frame.
        frame.getContentPane().add(blockPanel);
        frame.setFocusable( true );
        frame.addKeyListener( this );
        blockPanel.setFocusable( true );
        blockPanel.addKeyListener( this );
    }

    /**
     * Displays a dialog telling the client they have lost.
     */
    public void displayLossNotification()
    {
        JOptionPane pane = new JOptionPane();
        pane.showMessageDialog( frame, "You have run out of space to place tetriminoes. Game Over.");
        while( pane.getValue() == JOptionPane.UNINITIALIZED_VALUE)
        {

        }
        //fire a key event with code 242
        new KeyEvent( frame, 1, System.currentTimeMillis(), KeyEvent.ALT_DOWN_MASK, 242, KeyEvent.CHAR_UNDEFINED);
    }
}
