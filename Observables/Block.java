package Observables;

import java.awt.Color;

/**
 * The Block class defines a block. A block is a cell with a different color
 * than the default Background color rgb(127, 127, 127) and is associated with
 * a certain position (x,y)
 * @author ewillis
 */
public class Block implements Cloneable
{
    //Attributes
    int xPos, yPos;
    Color color;

    /**
     * Default constructor
     * @param x x-intercept of this block
     * @param y y-intercept of this block.
     * @param color color of this block
     */
    public Block(int x, int y, Color color)
    {
        this.xPos = x;
        this.yPos = y;
        this.color = color;
    }
    
    /**
     * Sets a new xPos for this block 
     * It is assumed that the resulting xPos will be within the bounds
     * of the board on which this block resides
     * @param newXPos the new X position for this block
     */
    public void setXPos( int newXPos )
    {
        xPos = newXPos;
    }
    
    /**
     * Sets a new yPos for this block
     * It is assumed that the resulting yPos will be within the bounds of the
     * board on which this block resides
     * @param newYPos the new Y position for this block
     */
    public void setYPos( int newYPos )
    {
        yPos = newYPos;
    }
    
    /**
     * Translates this block by the given offsets. The translation occurs
     * by adding the offsets to the current and x and y positions.
     * 
     * It is assumed that the position of the resulting block will be within the bounds
     * of this board.
     * @param xOffset the x Offset of the translation operation
     * @param yOffset the y offset of the translation operation
     */
    public void translate( int xOffset, int yOffset )
    {
        xPos = xPos + xOffset;
        yPos = yPos + yOffset;
    }
    
    /**
     * 
     * @return Returns the color of this block.
     */
    public Color getColor()
    {
        return color;
    }
    
    /**
     * 
     * @return Returns the current x Position of this block
     */
    public int getX()
    {
        return xPos;
    }
    
    /**
     * 
     * @return Returns the current y Position of this block.
     */
    public int getY()
    {
        return yPos; 
    }


    @Override
    public boolean equals( Object other )
    {
        if( ((Block)other).getX() == this.getX() && ((Block)other).getY() == this.getY() )
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public Block clone()
    {
        return new Block( xPos, yPos, color);
    }

    public String toString()
    {
        return "( "+getX()+", "+getY()+" )";
    }
}
