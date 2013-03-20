/*
 * LookBack:
 *
 * Status Of the project:
 * Transformations, translations, piece locking, and line clearing all seemed
 * to work when I would play through several games. There is however one issue
 * I could not resolve. When a game ends due to the spawn point for pieces being
 * blocked, the game freezes after acknowledging the end dialog. I cannot figure
 * out why this happens. My experiences with coding this project showed me how important
 * a detailed design can be. While I thought my design was decent it wasn't nearly
 * specific enough to code the entire project from. With a more detailed design
 * lots of small "gotchas" could have been avoided. For example, The creation of
 * the cells as JButtons for the GUI was being laid out incorrectly.
 * Instead of assigning x,y coordinates from left to right top
 * to bottom, I was assigning coordinates from top to bottom and left to right.
 * This later led to issues with correct positioning of blocks on the grid
 * and a significant amount of time wasted that could have been easily saved.
 *
 * Coding from my design I saw several things that could have improved it.
 * Method signatures were inconsistent and public/private was not taken into
 * consideration leaving me to figure what I should do during coding. By the time coding
 * starts however I should have had a design detailed enough to not have to think and simply
 * implement. However, due to a somewhat inconsistent/ambiguous design "coding" mishaps
 * happened leading to lots of wasted time tracking down mistakes in code.
 *
 * Tetris is a decent project for a future version of the course.
 */

 


import Observables.TetrisGame;
import Observers.Board;
/**
 * Creates objects needed to represent and display a tetris game.
 * @author Ethan
 */
public class Main
{
    public static void main(String[] args) 
    {
        TetrisGame g = new TetrisGame();
        Board b = new Board(g);
        g.attach( b );
    }
}
