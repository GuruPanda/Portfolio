import java.util.*;
/**
 * GameManager has 3 primary methods 
 * - specifyPlayer, through which a player can be assigned a colour
 * - boardSize, which merely creates and sets the board size
 * - playGame, which makes each player take their turn and checks at each stage if anyone
 *          has won.
 * GameManager basically starts and runs the game, makig calls to all the other classes.
 * 
 * @author Matteo Tondi 
 * @version 3.5.2016
 */
public class GameManager implements GameManagerInterface{
    private Piece p1Colour;
    private Piece p2Colour;
    private PlayerInterface p1;
    private PlayerInterface p2;
    private BoardInterface b;
    private MoveInterface moveOne;
    private MoveInterface moveTwo;

    /**
     * Define who will be playing each colour. This method will be called twice for each game once for
     * RED and once for BLUE.
     * 
     * @param  player     the player who will be playing red
     * @param  colour     the enum for a Piece (RED or BLUE)
     * @return boolean    true if the player was successfully set to the specified colour
     * 
     * @throws ColourAlreadySetException  If the colour is alredy allocated to a player
     */
    public boolean specifyPlayer(PlayerInterface player, Piece colour) throws InvalidColourException, ColourAlreadySetException{
        if(p1==null){
            p1=player;
        }
        else if(p2==null){
            p2=player;
        }
        else{
            return false;
        }
        if(colour==p1Colour){
            throw new ColourAlreadySetException();
        }
        try{
            player.setColour(colour);
        }
        catch(InvalidColourException i){
            System.err.println("Error: select either Piece.RED or Piece.BLUE");
        }
        catch(ColourAlreadySetException c){
            System.err.println("Error: colour already set for this player");
        }
        return true;
    }

    /**
     * Specifiy the size of the board that we are playing on. Both numbers must be greater than zero
     * 
     * @param  sizeX      how wide the board will be
     * @param  sizeY      how tall the board will be
     * @returns boolean   true if the board could be set successfully
     * 
     * @throws InvalidBoardSizeException  If either size value is less than one.
     * @throws BoardAlreadySizedException If the board has already been created.
     */
    public boolean boardSize(int sizeX, int sizeY) throws InvalidBoardSizeException, BoardAlreadySizedException{
        b = new Board(sizeX, sizeY);
        return true;
    }

    //--------------------------------------------------------------------------------------------

    /**
     * if p2Wins, makes call to finalGameState in the HumanPlayer class.
     */
    private void p1Wins(){
        p1.finalGameState(GameState.WON);
        p2.finalGameState(GameState.LOST);
    }

    /**
     * if p2Wins, makes call to finalGameState in the HumanPlayer class.
     */
    private void p2Wins(){
        p2.finalGameState(GameState.WON);
        p1.finalGameState(GameState.LOST);
    }

    /**
     * The core of the game manager. This requests each player to make a move and plays these out on the 
     * game board.
     */
    public boolean playGame(){
        int counter = 0;
        Scanner scanner = new Scanner(System.in);
        try{
            if(p1!=null && p2!=null && b!=null){
                while(b.gameWon()==Piece.UNSET){
                    if(counter%2==0){
                        System.out.println("Player 1 turn");
                        moveOne=p1.makeMove(b.getBoardView());
                        if(moveOne.hasConceded()){
                            p2Wins();
                            return true;
                        }
                        else{
                            b.placePiece(p1Colour,moveOne);
                        }
                    }
                    else{
                        System.out.println("Player 2 turn.");
                        moveTwo=p2.makeMove(b.getBoardView());
                        if(moveOne.hasConceded()){
                            p1Wins();
                            return true;
                        }
                        else{
                            b.placePiece(p2Colour,moveTwo);
                        }
                    } 
                    counter++;
                }
                if(b.gameWon()==Piece.BLUE){
                    if(p1Colour==Piece.BLUE){
                        p1Wins();
                    }
                }
                else{
                    if(p1Colour==Piece.BLUE){
                        p2Wins();
                    }
                }
                return true;
            }
        }
        catch(NoValidMovesException x){
            System.out.println("No more moves available. Game is a DRAW");
        }

        catch(NoBoardDefinedException y){
            System.err.println("No board created.");
        }
        catch(InvalidPositionException z){
            System.out.println("x & y above 0");
        }
        catch(InvalidColourException a){
            System.out.println("Colour must be either Piece.BLUE or Piece.RED.");
        }
        catch(PositionAlreadyTakenException b){
            System.out.println("Position already taken.");
        }
        return false;
    }
}


