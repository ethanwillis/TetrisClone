package Observables;

import java.awt.Color;

/**
 * A tetrimino is a collection of blocks that are translated and transformed 
 * uniformly as a single entity.
 * @author ewillis
 */
public class Tetrimino
{
    //Attributes
    private Block[] blocks = new Block[4];
    private Block[][] blockRotations = new Block[28][4];
    private Color color;
    private int centerX;
    private int curRot;
    private int blockType;
    private int boardWidth, boardHeight;
    private final Color L_COLOR = new Color( 239, 121, 33 ),
                        J_COLOR = new Color( 0, 0, 255 ),
                        T_COLOR = new Color( 173, 77, 156 ),
                        I_COLOR = new Color( 49, 199, 239),
                        O_COLOR = new Color( 247, 211, 8 ),
                        Z_COLOR = new Color( 255, 0, 0 ),
                        S_COLOR = new Color( 0, 255, 0 ),
                        BACKGROUND_COLOR = new Color( 127, 127, 127 );

    
    //Constructors
    /**
     * Creates a new piece based on a piece type
     * @param pieceType the piece type corresponding to 1 of 7 types of pieces
     * 0 - L block
     * 1 - J block
     * 2 - T block
     * 3 - I block
     * 4 - O block
     * 5 - Z block
     * 6 - S block
     * @param boardWidth the width of the board
     * @param boardHeight the height of the board.
     */
    public Tetrimino( int pieceType, int boardWidth, int boardHeight )
    {
        //initialize instance variables
        color = findColor( pieceType );
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        blockType = pieceType;
        curRot = 0;
        centerX = (boardWidth/2) - 2;

        // Pre-populate all of the possible block Rotations

        //L-Block rotations
        //default position
        blockRotations[0][0] = new Block( 0, 0, color );
        blockRotations[0][1] = new Block( 0, 1, color );
        blockRotations[0][2] = new Block( 0, 2, color );
        blockRotations[0][3] = new Block( 1, 2, color );
        //position 2
        blockRotations[1][0] = new Block( 0, 0, color );
        blockRotations[1][1] = new Block( 1, 0, color );
        blockRotations[1][2] = new Block( 2, 0, color );
        blockRotations[1][3] = new Block( 0, 1, color );
        //position 3
        blockRotations[2][0] = new Block( 0, 0, color );
        blockRotations[2][1] = new Block( 1, 0, color );
        blockRotations[2][2] = new Block( 1, 1, color );
        blockRotations[2][3] = new Block( 1, 2, color );
        //position 4
        blockRotations[3][0] = new Block( 2, 0, color );
        blockRotations[3][1] = new Block( 0, 1, color );
        blockRotations[3][2] = new Block( 1, 1, color );
        blockRotations[3][3] = new Block( 2, 1, color );

        //J Block Rotations
        blockRotations[4][0] = new Block( 1, 0, color );
        blockRotations[4][1] = new Block( 1, 1, color );
        blockRotations[4][2] = new Block( 0, 2, color );
        blockRotations[4][3] = new Block( 1, 2, color );

        blockRotations[5][0] = new Block( 0, 0, color );
        blockRotations[5][1] = new Block( 1, 1, color );
        blockRotations[5][2] = new Block( 0, 1, color );
        blockRotations[5][3] = new Block( 2, 1, color );

        blockRotations[6][0] = new Block( 0, 0, color );
        blockRotations[6][1] = new Block( 1, 0, color );
        blockRotations[6][2] = new Block( 0, 1, color );
        blockRotations[6][3] = new Block( 0, 2, color );

        blockRotations[7][0] = new Block( 0, 0, color );
        blockRotations[7][1] = new Block( 1, 0, color );
        blockRotations[7][2] = new Block( 2, 0, color );
        blockRotations[7][3] = new Block( 2, 1, color );

        //T Block Rotations
        blockRotations[8][0] = new Block( 1, 0, color );
        blockRotations[8][1] = new Block( 0, 1, color );
        blockRotations[8][2] = new Block( 2, 1, color );
        blockRotations[8][3] = new Block( 1, 1, color );

        blockRotations[9][0] = new Block( 0, 0, color );
        blockRotations[9][1] = new Block( 0, 1, color );
        blockRotations[9][2] = new Block( 1, 1, color );
        blockRotations[9][3] = new Block( 0, 2, color );

        blockRotations[10][0] = new Block( 0, 0, color );
        blockRotations[10][1] = new Block( 1, 0, color );
        blockRotations[10][2] = new Block( 2, 0, color );
        blockRotations[10][3] = new Block( 1, 1, color );

        blockRotations[11][0] = new Block( 1, 0, color );
        blockRotations[11][1] = new Block( 0, 1, color );
        blockRotations[11][2] = new Block( 1, 2, color );
        blockRotations[11][3] = new Block( 1, 1, color );

        //I Block rotations
        blockRotations[12][0] = new Block( 0, 0, color );
        blockRotations[12][1] = new Block( 0, 1, color );
        blockRotations[12][2] = new Block( 0, 2, color );
        blockRotations[12][3] = new Block( 0, 3, color );

        blockRotations[13][0] = new Block( 0, 0, color );
        blockRotations[13][1] = new Block( 1, 0, color );
        blockRotations[13][2] = new Block( 2, 0, color );
        blockRotations[13][3] = new Block( 3, 0, color );

        blockRotations[14][0] = new Block( 0, 0, color );
        blockRotations[14][1] = new Block( 0, 1, color );
        blockRotations[14][2] = new Block( 0, 2, color );
        blockRotations[14][3] = new Block( 0, 3, color );

        blockRotations[15][0] = new Block( 0, 0, color );
        blockRotations[15][1] = new Block( 1, 0, color );
        blockRotations[15][2] = new Block( 2, 0, color );
        blockRotations[15][3] = new Block( 3, 0, color );

        //O block rotations
        blockRotations[16][0] = new Block( 0, 0, color );
        blockRotations[16][1] = new Block( 1, 0, color );
        blockRotations[16][2] = new Block( 0, 1, color );
        blockRotations[16][3] = new Block( 1, 1, color );

        blockRotations[17][0] = new Block( 0, 0, color );
        blockRotations[17][1] = new Block( 1, 0, color );
        blockRotations[17][2] = new Block( 0, 1, color );
        blockRotations[17][3] = new Block( 1, 1, color );

        blockRotations[18][0] = new Block( 0, 0, color );
        blockRotations[18][1] = new Block( 1, 0, color );
        blockRotations[18][2] = new Block( 0, 1, color );
        blockRotations[18][3] = new Block( 1, 1, color );

        blockRotations[19][0] = new Block( 0, 0, color );
        blockRotations[19][1] = new Block( 1, 0, color );
        blockRotations[19][2] = new Block( 0, 1, color );
        blockRotations[19][3] = new Block( 1, 1, color );

        //Z block rotations
        blockRotations[20][0] = new Block( 0, 0, color );
        blockRotations[20][1] = new Block( 1, 0, color );
        blockRotations[20][2] = new Block( 1, 1, color );
        blockRotations[20][3] = new Block( 2, 1, color );

        blockRotations[21][0] = new Block( 1, 0, color );
        blockRotations[21][1] = new Block( 0, 1, color );
        blockRotations[21][2] = new Block( 0, 2, color );
        blockRotations[21][3] = new Block( 1, 1, color );

        blockRotations[22][0] = new Block( 0, 0, color );
        blockRotations[22][1] = new Block( 1, 0, color );
        blockRotations[22][2] = new Block( 1, 1, color );
        blockRotations[22][3] = new Block( 2, 1, color );

        blockRotations[23][0] = new Block( 1, 0, color );
        blockRotations[23][1] = new Block( 0, 1, color );
        blockRotations[23][2] = new Block( 0, 2, color );
        blockRotations[23][3] = new Block( 1, 1, color );

        //S block rotations
        blockRotations[24][0] = new Block( 1, 0, color );
        blockRotations[24][1] = new Block( 2, 0, color );
        blockRotations[24][2] = new Block( 0, 1, color );
        blockRotations[24][3] = new Block( 1, 1, color );

        blockRotations[25][0] = new Block( 0, 0, color );
        blockRotations[25][1] = new Block( 0, 1, color );
        blockRotations[25][2] = new Block( 1, 1, color );
        blockRotations[25][3] = new Block( 1, 2, color );

        blockRotations[26][0] = new Block( 1, 0, color );
        blockRotations[26][1] = new Block( 2, 0, color );
        blockRotations[26][2] = new Block( 0, 1, color );
        blockRotations[26][3] = new Block( 1, 1, color );

        blockRotations[27][0] = new Block( 0, 0, color );
        blockRotations[27][1] = new Block( 0, 1, color );
        blockRotations[27][2] = new Block( 1, 1, color );
        blockRotations[27][3] = new Block( 1, 2, color );


        //select default piece based on piece type
        this.selectPiece( pieceType );
    }


    /**
     * Transforms the blocks of this tetrimino around the centroid cw or ccw 90 degrees.
     *
     * The process by which this is done is using knowledge of the current angle(default of 0 degrees)
     * finding the difference between the original offset from the origin and the current offset.
     * The next rotation is then pulled from the array and adjusted for offsets accordingly.
     *
     * If the transform is not successful the blocks remain in their original positions.
     */
    public void transform( boolean cw, Color[][] board )
    {
        Block[] oldBlocks = new Block[4];
        oldBlocks[0] = blocks[0];
        oldBlocks[1] = blocks[1];
        oldBlocks[2] = blocks[2];
        oldBlocks[3] = blocks[3];

        //get offsets
        int[] offsets = getOffset();
        //increment rotation
        updateCurRot( cw );
        System.out.println(toString() + " " + curRot +" xOff: " + offsets[0] + " yOff: "+offsets[1]);
        //update the blocks to the new rotation
        blocks[0] = blockRotations[ (blockType * 4) + curRot ][0].clone();
        blocks[1] = blockRotations[ (blockType * 4) + curRot ][1].clone();
        blocks[2] = blockRotations[ (blockType * 4) + curRot ][2].clone();
        blocks[3] = blockRotations[ (blockType * 4) + curRot ][3].clone();
        System.out.println(toString() + " " + curRot +" xOff: " + offsets[0] + " yOff: "+offsets[1]);
        if(!translate( offsets[0], offsets[1], board))
        {
            blocks[0] = oldBlocks[0];
            blocks[1] = oldBlocks[1];
            blocks[2] = oldBlocks[2];
            blocks[3] = oldBlocks[3];
        }
       
    }

    /**
     * Gets the offset between an original piece at the origin and a current piece.
     * @return offset[0] is the x distance between the two pieces offset[1] is the
     * y distance between the two pieces.
     */
    private int[] getOffset()
    {
        //Determine difference in offset from original
            int[] offsets = new int[2];
            int curX, curY, origX, origY;
            Block origBlock = blockRotations[ (blockType * 4) + curRot ][0];
            Block curBlock = blocks[0];
            curX = curBlock.getX();
            curY = curBlock.getY();
            origX = origBlock.getX();
            origY = origBlock.getY();
            System.out.println( blockRotations[ (blockType * 4) + curRot ][0].toString() +" " + curBlock.toString() );
            offsets[0] = curX - origX;
            offsets[1] = curY - origY;
            return offsets;
    }
    
    /**
     * Translates the blocks of this tetrimino. Doesn't allow translation
     * to occur if the translation would put a block of the tetrimino in an invalid position.
     * @param xOffset the x Offset of each block
     * @param yOffset the y Offset of each block
     * @param board The current board
     * @return returns true if the translation was successful. false otherwise.
     */
    public boolean translate( int xOffset, int yOffset, Color[][] board )
    {
        boolean answer = false;
        // If the offsets dont move a block out of the board or onto
        // another block
        if( checkBounds( xOffset, yOffset ) && checkOverlap( xOffset, yOffset, board ) )
        {
            //then translate
            for( int i = 0; i < 4; i++ )
            {
              blocks[ i ].translate( xOffset, yOffset );
            }
            answer = true;
        }
        return answer;
    }

    /**
     * Checks if any blocks will overlap after translation. excluding
     * current positions.
     * @param board the current board
     * @return true if there is no overlap, false otherwise
     */
    private boolean checkOverlap( int xOffset, int yOffset, Color[][] board )
    {
        boolean answer = true;
        int curX, curY;
        for( int i = 0; i < 4 && answer && board != null; i++ )
        {
            curX = blocks[ i ].getX();
            curY = blocks[ i ].getY();
            if( !( board[curX+xOffset][curY+yOffset].equals( BACKGROUND_COLOR ) )  )
            {
                answer = false;
                for( int j = 0; j < 4; j++ )
                {
                    if( blocks[ i ].getX() == (curX + xOffset) && blocks[ i ].getY() == (curY + yOffset))
                    {
                        answer = true;
                    }
                }
            }
        }

        return answer;
    }

    /**
     *  Checks whether this piece will be in an invalid position (outside of the
     * bounds of the board) after being translated.
     * @param xOffset the xoffset for the translation
     * @param yOffset the yoffset for the translation
     * @return return true if the offsets will not move this piece off the board.
     */
    private boolean checkBounds( int xOffset, int yOffset )
    {
        boolean answer = true;
        int curX, curY;
        
        for( int i = 0; i < 4 && answer; i++ )
        {
            curX = blocks[ i ].getX();
            curY = blocks[ i ].getY();
            if( ((curX + xOffset) < 0) || ((curX + xOffset) > boardWidth - 1) ||
                    ((curY + yOffset) < 0) || ((curY + yOffset) > boardHeight - 1) )
            {
                answer = false;
            }

           
        }
        return answer;
    }
    
    /**
     * Gets all of the blocks that compose this tetrimino.
     * @return
     */
    public Block[] getBlocks()
    {
        return blocks;
    }

    /**
     * Sets the blocks that compose this piece to the given blocks.
     * @param blocks
     */
    public void setBlocks(Block[] blocks)
    {
        this.blocks = blocks.clone();
    }

    /**
     * Finds what the color should be for the given piece type
     * @param pieceType the type of piece from 0-6
     * @return the color associated with the given piece type.
     */
    private Color findColor( int pieceType )
    {
        Color answer;
        if( pieceType == 0 )
        {
            answer = L_COLOR;
        }
        else if( pieceType == 1 )
        {
            answer = J_COLOR;
        }
        else if( pieceType == 2 )
        {
            answer = T_COLOR;
        }
        else if( pieceType == 3 )
        {
            answer = I_COLOR;
        }
        else if( pieceType == 4 )
        {
            answer = O_COLOR;
        }
        else if( pieceType == 5 )
        {
            answer = Z_COLOR;
        }
        else if( pieceType == 6 )
        {
            answer = S_COLOR;
        }
        else
        {
            answer = BACKGROUND_COLOR;
        }
        return answer;
    }

    /**
     * Selects the starting rotation for a given piecetype
     * @param pieceType the type of piece to select.
     */
    private void selectPiece( int pieceType )
    {
        if( pieceType == 0)
        {
            blocks[0] = blockRotations[0][0].clone();
            blocks[1] = blockRotations[0][1].clone();
            blocks[2] = blockRotations[0][2].clone();
            blocks[3] = blockRotations[0][3].clone();
        }
        else if( pieceType == 1)
        {
            blocks[0] = blockRotations[4][0].clone();
            blocks[1] = blockRotations[4][1].clone();
            blocks[2] = blockRotations[4][2].clone();
            blocks[3] = blockRotations[4][3].clone();
        }
        else if( pieceType == 2)
        {
            blocks[0] = blockRotations[8][0].clone();
            blocks[1] = blockRotations[8][1].clone();
            blocks[2] = blockRotations[8][2].clone();
            blocks[3] = blockRotations[8][3].clone();
        }
        else if( pieceType == 3)
        {
            blocks[0] = blockRotations[12][0].clone();
            blocks[1] = blockRotations[12][1].clone();
            blocks[2] = blockRotations[12][2].clone();
            blocks[3] = blockRotations[12][3].clone();
        }
        else if( pieceType == 4)
        {
            blocks[0] = blockRotations[16][0].clone();
            blocks[1] = blockRotations[16][1].clone();
            blocks[2] = blockRotations[16][2].clone();
            blocks[3] = blockRotations[16][3].clone();
        }
        else if( pieceType == 5)
        {
            blocks[0] = blockRotations[20][0].clone();
            blocks[1] = blockRotations[20][1].clone();
            blocks[2] = blockRotations[20][2].clone();
            blocks[3] = blockRotations[20][3].clone();
        }
        else if( pieceType == 6)
        {
            blocks[0] = blockRotations[24][0].clone();
            blocks[1] = blockRotations[24][1].clone();
            blocks[2] = blockRotations[24][2].clone();
            blocks[3] = blockRotations[24][3].clone();
        }
        //move the piece to the center of the x axis.
        translate( centerX, 0, null );
    }

    /**
     * Updates the variable that hold what the current degree of rotation is
     * based on whether the piece is being rotated cw or ccw.
     * @param cw
     */
    private void updateCurRot( boolean cw )
    {
        if( cw )
        {
            if( curRot == 3)
            {
                curRot = 0;
            }
            else
            {
                curRot++;
            }
        }
        else
        {
            if( curRot == 0)
            {
                curRot = 3;
            }
            else
            {
                curRot--;
            }
        }
    }

    /**
     *
     * @return String representation of all the blocks that compose this piece.
     */
    public String toString()
    {
        String answer = "";
        for( int i = 0; i < 4; i++ )
        {
            answer = answer+" "+blocks[i].toString();
        }
        return answer;
    }

}
