import java.util.*;
/**
 * The user is able to set the colour of the instance (if not set yet).
 * HumanPlayer is responsible for the human players and asks them to make their move
 * The class also allows them to concede and quit the game. 
 * If any instance of the class has one through the GameManager class, then the finalGameState is set accordingly.
 * 
 * 
 * @author Matteo Tondi 
 * @version 3.5.2016
 */
public class HumanPlayer implements PlayerInterface
{
    private Piece playerColour;
    private Move turn;
    /**
     * Set the colour that this player will be
     * 
     * @param colour  A Piece (RED/BLUE) that this player will be
     * @return   true indicating that the method succeeded
     * 
     * @throws InvalidColourException   A colour other than RED/BLUE was provided
     * @throws ColourAlreadySetException  The colour has already been set for this player.
     */
    public boolean setColour(Piece colour) throws InvalidColourException, ColourAlreadySetException{
        if(playerColour!=null){
            throw new ColourAlreadySetException();
        }
        else if(colour!=Piece.RED && colour!=Piece.BLUE){
            throw new InvalidColourException();
        }
        playerColour=colour;
        return true;
    }

    public Piece getColour(){
        return playerColour;
    }

    /**
     * Informs the player of the final game state. Player has Won, lost.
     * 
     * @param state   either WON or LOST
     * @return   true indicating method has compleated successfully.
     * 
     */
    public boolean finalGameState(GameState state){
        if(state==GameState.LOST){
            System.out.println(playerColour+ " has lost!");
        }
        else{
            System.out.println(playerColour+ " has won!");
        }
        return true;
    }

    //-----------------------------------------------------------------------

    /**
     * Checks theentire board to see if there are any cells still UNSET.
     */
    private boolean freeSpaces(Piece[][] boardView){
        for(int x = 0; x<boardView.length; x++){
            for(int y = 0; y<(boardView[0]).length; y++){
                if(boardView[x][y]==Piece.UNSET){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Ask the player to make a move. If the  user refuses to make the move, then the 
     * game takes the refusal as a concession of the game, and sets the current move to
     * conceded. If user doesnt input y or n then the method reiterates the method and
     * asks again.
     * If user decided to take move, then class asks user to input x and y coordinates.
     * Method then creates a new move with those coordinates.
     * 
     * @param  boardView   the current state of the board
     * @return        a Move object representing the desired place to place a piece
     * 
     * 
     */
    public MoveInterface makeMove(Piece[][] boardView) throws NoValidMovesException{
        if(!freeSpaces(boardView)){
            throw new NoValidMovesException();
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Take move? (y/n)");
        if(sc.nextLine().equals("y")){
            System.out.println("Please give x-coordinate");
            int x = sc.nextInt();
            System.out.println("Please give y-coordinate");
            int y = sc.nextInt();
            turn = new Move(x,y);
        }
        else if(sc.nextLine().equals("n")){
            turn.setConceded();
        }
        else{
            makeMove(boardView);
        }
        return turn;
    }
}
