
package Observables;

import Observers.Board;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
/**
 * The TetrisGame class models a tetrisgame.
 * This model keeps track of the currentstate the game is in
 * (waiting state, or game mode state), all observers (guis)
 * that are watching this game, the blocks on the board and their state,
 * the active piece controlled by a client, and the board width and height.
 *
 * @author ewillis
 */
public class TetrisGame extends Observable
{
    //Attributes of the Tetris Game
    private State currentState;
    private ArrayList<Board> observers;
    private Color[][] blocks;
    private final Color BACKGROUND_COLOR = new Color( 127, 127, 127 );
    private Tetrimino activePiece;
    private final int DEFAULT_BOARD_HEIGHT = 18, DEFAULT_BOARD_WIDTH = 10;
    private int boardHeight, boardWidth;

    //Constructors
    /**
     * -This constructor creates a new game with the given board size and a single observer attached to this subject
       for representing this with a GUI.
     * @param b A board to attach as an observer to this Tetris game.
     * @param boardWidth The width of the board for this Tetris game.
     * @param boardHeight The height of the board for this Tetris game.
     */
    public TetrisGame( Board b, int boardWidth, int boardHeight )
    {
        observers = new ArrayList<Board>();
    }
    /**
     * -This constructor creates a new game with the given board size and multiple observers, for multiple players in a single game, are
       attached to this subject for representing it with a GUI
     * @param observers A list of boards that observe this Tetris game.
     * @param boardWidth The width of the board for this Tetris game.
     * @param boardHeight The height of the board for this Tetris game.
     */
    public TetrisGame( ArrayList<Board> observers, int boardWidth, int boardHeight )
    {
        this.observers = observers;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        // TetrisGame defaults to a waiting state when constructed.
        this.currentState = new WaitingState();
        blocks = new Color[ boardWidth ][ boardHeight ];
        // Default all blocks on the board to be gray.
        for( int x = 0; x < this.boardWidth; x++ )
        {
            for( int y = 0; y < this.boardHeight; y++ )
            {
                blocks[ x ][ y ] = BACKGROUND_COLOR;
            }
        }
    }


    /**
     * A default constructor for a TetrisGame that 
     */
    public TetrisGame( )
    {
        this.boardHeight = DEFAULT_BOARD_HEIGHT;
        this.boardWidth = DEFAULT_BOARD_WIDTH;
        observers = new ArrayList<Board>();
        // TetrisGame defaults to a waiting state when constructed.
        this.currentState = new WaitingState();

        this.drawBlocks();
    }



    /**
     * Creates a new tetris game that uses the specified width and height
     * for the board.
     * @param boardHeight the height of the board.
     * @param boardWidth the width of the board.
     */
    public TetrisGame(int boardHeight, int boardWidth)
    {
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        // TetrisGame defaults to a waiting state when constructed.
        this.currentState = new WaitingState();

        // Default all blocks on the board to be gray.
        blocks = new Color[ boardWidth ][ boardHeight ];
        for( int x = 0; x < this.boardWidth; x++ )
        {
            for( int y = 0; y < this.boardHeight; y++ )
            {
                blocks[ x ][ y ] = BACKGROUND_COLOR;
            }
        }
    }

    /**
     * Changes the currentState to a new specified state
     * @param newState the new state of this tetris game.
     */
    public void setState(State newState)
    {
       currentState = newState;
       this.notifyObs();
    }

    /**
     * Attaches a board( observer ) to this tetris game.
     * @param b The board to attach.
     */
    public void attach( Board b )
    {
        observers.add( b );
    }

    /**
     * Removes the specified board(observer) from this tetrisgame
     * @param b the board to remove.
     */
    public void detach( Board b )
    {
        observers.remove( b );
    }

    /**
     * This method is called whenever an attribute changes or the
     * state changes
     */
    public void notifyObs()
    {
        for( Board b : observers )
        {
            b.Update();
        }
    }

    /**
     *
     * @param event the key event forwarded by the GUI
     * @param pressedOrReleased a boolean that identifies whether the keyevent was
     * a keypress or a keyrelease event. True means it is a keypress. False
     * means it is key release
     */
    public void respond( KeyEvent event, boolean pressedOrReleased )
    {
        this.currentState.respond( event, this, pressedOrReleased );
    }


    /**
     * Gets the sate of this tetris game.
     * @return returns the state of the tetris game(each of the blocks on the board as a color)
     */
    public Object[ ][ ] getState()
    {
        Object[ ][ ] state = new Object[ this.boardWidth ][ this.boardHeight ];
        // Copy the board state into the array
        for( int x = 0; x < this.boardWidth; x++ )
        {
            for( int y = 0; y < this.boardHeight; y++ )
            {
                state[ x ][ y ] = blocks[ x ][ y ];
            }
        }
        return state;
    }

    public Color[][] getBlocks()
    {
        return blocks;
    }
    /**
     *
     * @return Returns the height of the tetris board in cells.
     */
    public int getHeight()
    {
        return this.boardHeight;
    }

    /**
     *
     * @return Returns the width of the tetris board in cells.
     */
    public int getWidth()
    {
        return this.boardWidth;
    }

    /**
     * Creates a new activePiece.
     */
    public void createNewPiece()
    {
        //pick a random piece from the starter pieces.
        Random randGen = new Random();
        int pieceType = randGen.nextInt(7);
        activePiece = new Tetrimino( pieceType, this.boardWidth, this.boardHeight );
        //if this new piece doesn't intersect any blocks
        if( !isPieceInvalid( activePiece, null) )
        {
            //then place it on the board.
            addBlocks( activePiece );
        }
        //otherwise the client has lost the game.
        else
        {
            this.notifyObsOfLoss();
        }
        this.notifyObs();
    }

    public void notifyObsOfLoss()
    {
        for( Board b : observers )
        {
            b.displayLossNotification();
        }
    }

    /**
     * Checks if a piece is intersecting blocks already on the board, except for
     * the given blocks. Or if a piece is outside the bounds of the board.
     * @return return true if the piece is in an invalid position, false otherwise.
     */
    private boolean isPieceInvalid( Tetrimino piece, Block[] exclBlocks )
    {
        boolean answer = false;
        int xPos, yPos;

        // get all of the blocks from the piece
        Block[] pieceBlocks = piece.getBlocks();

        // set all blocks in pieceBlocks to null that are in exclBlocks
        if( exclBlocks != null)
        {
            for( int i = 0; i < pieceBlocks.length; i++ )
            {
                for( int j = 0; j < exclBlocks.length; j++ )
                {
                    if( pieceBlocks[ i ].equals(  exclBlocks[ j ] ))
                    {
                        pieceBlocks[ i ] = null;
                        j = exclBlocks.length;
                    }
                }
            }
        }
        // check each non null block against blocks in the tetris board. To see
        // if there is any overlapping
        for( int i = 0; i < pieceBlocks.length && answer == false; i++)
        {
            xPos = pieceBlocks[ i ].getX();
            yPos = pieceBlocks[ i ].getY();
            if( pieceBlocks[ i ] == null )
            {
                i++;
            }
            else
            {
                // If a position on the board has a block in it and it overlaps with this piece
                if( ( xPos < boardWidth && yPos < boardHeight ) && !( blocks[ xPos ][ yPos ].equals( BACKGROUND_COLOR ) ) )
                {
                    answer = true;
                }
            }
        }
        return answer;
    }

    /**
     * Adds the given piece to the board
     * @param activePiece the piece to add to the board.
     */
    private void addBlocks( Tetrimino activePiece )
    {
        Block[] newBlocks = activePiece.getBlocks();

        int xPos, yPos;
        Color newBlockColor;
        for( int i = 0; i < newBlocks.length; i++ )
        {
            xPos = newBlocks[ i ].getX();
            yPos = newBlocks[ i ].getY();
            newBlockColor = newBlocks[ i ].getColor();
            newBlocks[i].toString();
            blocks[ xPos ][ yPos ] = newBlockColor;
        }
        this.notifyObs();
    }

    /**
     * Adds the given list of blocks to the board
     * @param newBlocks the blocks to add to the board.
     */
    private void addBlocks( Block[] newBlocks )
    {
        int xPos, yPos;
        Color newBlockColor;
        for( int i = 0; i < newBlocks.length; i++ )
        {
            xPos = newBlocks[ i ].getX();
            yPos = newBlocks[ i ].getY();
            newBlockColor = newBlocks[ i ].getColor();
            blocks[ xPos ][ yPos ] = newBlockColor;
        }
        this.notifyObs();
    }

    /**
     * Performs a translation on the active piece with the given offsets
     * @param xOffset the x offset of the translation
     * @param yOffset the y offset of the translation
     * @return true if the translation operation was successful, false otherwise.
     */
    public boolean translate(int xOffset, int yOffset)
    {
        boolean answer;
        // remove old piece
        removeBlocks( activePiece );
        // translate piece
        answer = activePiece.translate( xOffset, yOffset, blocks );
        // put new piece on board.
        addBlocks( activePiece );
        // notify observers
        notifyObs();
        return answer;
    }

    /**
     * Removes the given array of blocks from this game
     * @param remBlocks the blocks to remove.
     */
    private void removeBlocks( Block[] remBlocks )
    {
        int curX, curY;
        for( int i = 0; i < remBlocks.length; i++ )
        {
            curX = remBlocks[ i ].getX();
            curY = remBlocks[ i ].getY();
            blocks[ curX ][ curY ] = BACKGROUND_COLOR;
        }
        notifyObs();
    }

    /**
     * Removes the blocks in the given piece from this game
     * @param piece the piece to be removed.
     */
    private void removeBlocks( Tetrimino piece )
    {
        Block[] remBlocks = piece.getBlocks();
        int curX, curY;
        for( int i = 0; i < remBlocks.length; i++ )
        {
            curX = remBlocks[ i ].getX();
            curY = remBlocks[ i ].getY();
            blocks[ curX ][ curY ] = BACKGROUND_COLOR;
        }
        notifyObs();
    }

    /**
     * Locks an active piece, clearing any lines that may result and creating a new piece.
     */
    void lockActivePiece()
    {
        //Look for lines for each unique y coordinate in the active piece.
        Block[] pieceBlocks = activePiece.getBlocks();
        int yValue;
        for( int i = 0; i < pieceBlocks.length; i++ )
        {
            yValue = pieceBlocks[ i ].getY();
            if( checkForLine( yValue ) )
            {
                removeLine( yValue );
                shiftBlocksDown( yValue );
            }
        }
        createNewPiece();

    }


   /**
     * Shifts all blocks down 1 cell vertically to fill in a line that has been
    * removed
     * @param yValue any block with a y value < y value gets shifted down.
     */
    private void shiftBlocksDown( int yValue )
    {
        for( int y = yValue; y > 0; y--)
        {
            for( int x = 0; x < boardWidth; x++ )
            {
                blocks[x][y] = blocks[x][y-1];
            }
        }
    }

    /**
     * Removes a line at the specified yValue.
     * @param yValue the yvalue of the line to remove.
     */
    private void removeLine( int yValue )
    {
        for( int x = 0; x < boardWidth; x++ )
        {
            blocks[x][yValue] =  BACKGROUND_COLOR;
        }
    }

    /**
     * Checks if a line exists at the specified y value
     * @param yValue the yvalue where a possible line is
     * @return returns true if every block horizontally at the y-value is not
     * filled with the background-color. false otherwise.
     */
    private boolean checkForLine( int yValue )
    {
        boolean answer = true;
        for( int x = 0; x < boardWidth && answer; x++ )
        {
            if( blocks[x][yValue].equals( BACKGROUND_COLOR ) )
            {
                answer = false;
            }
        }
        return answer;
    }

    /**
     * Rotates the active piece cw when cwRot is true and ccw when cwRot is false.
     * @param cwRot cwRot when true means cw rotation.
     */
    void transform( boolean cwRot )
    {
        // remove old piece
        removeBlocks( activePiece );
        // translate piece
        activePiece.transform( cwRot, blocks );
        // put new piece on board.
        addBlocks( activePiece );
        // notify observers
        notifyObs();
    }

    
    /**
     * Performs a "hard drop" on the current active piece. Locking it into its final
     * position.
     */
    void hardTranslate()
    {
        // While the piece can move downwards
        while( translate( 0, 1 ) )
        {
            // move it downwards
        }
        // When done lock it.
        lockActivePiece();
    }

    /**
     * Draws the default blocks as the background color.
     */
    public void drawBlocks()
    {
        // Default all blocks on the board to be gray.
        blocks = new Color[ boardWidth ][ boardHeight ];
        for( int x = 0; x < this.boardWidth; x++ )
        {
            for( int y = 0; y < this.boardHeight; y++ )
            {
                blocks[ x ][ y ] = BACKGROUND_COLOR;
            }
        }
        notifyObs();
    }
}
