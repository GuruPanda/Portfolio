
/**
 * Move is created at each turn, with coordinates being given by the player. The player
 * may also choose to concede, and effectively quit the game, allowing the other player
 * to win.
 * 
 * @author Matteo Tondi 
 * @version 3.5.2016
 */
public class Move implements MoveInterface
{
    private int xPos;
    private int yPos;
    private boolean conceded;
    
    /**
     * New move makes call to setPosition.
     * @param x   the x coordinate
     * @param y   the y coordinate
     * catches InvalidPositionException if thrown by setPosition.
     * 
     */
    public Move(int x, int y){
        try{
            setPosition(x,y);
        }
        catch(InvalidPositionException i){
            System.err.println("Both x and y values must be positive");
        }
    }
    
    /**
     * Set the position that the Player wishes to use - both x and y coordinate.
     * 
     * @param x   the x coordinate
     * @param y   the y coordinate
     * @return    true indicating value set correctly
     * 
     * @throws  InvalidPositionException   The position is invalid. E.g. both x and y are negative.
     */
    public boolean setPosition(int x, int y) throws InvalidPositionException{
        if(x<0 || y<0){
            throw new InvalidPositionException();
        }
        xPos=x;
        yPos=y;
        return true;
    }
    
    /**
     * Get the x coordinate of the move.
     * 
     * @return the x coordinate.
     */
    public int getXPosition(){
        return xPos;
    }
    
    /**
     * Get the y coordnate of the move.
     * 
     * @return the y coordinate.
     */
    public int getYPosition(){
        return yPos;
    }
    
    //----------------------------------------------------------------------------
   
    /**
     * Has the player conceded in this move?
     * i.e. have they yielded to the fact that their opponent has won before all required
     * moves are made.
     *
     * @return true if the player has conceded.
     */
    public boolean hasConceded(){
        if(conceded){
            return true;
        }
        return false;
    }
    
    /**
     * Indicate that the player has conceded in this move.
     * 
     * @return true indicating conceded is set.
     */
    public boolean setConceded()
    {
        conceded=true;
        return true;
    }
}
