import java.util.*;
/**
 * The board is responsible for maintaining the current state of the game and indicating if
 * the placement of a new piece is valid. The board is also	responsible	for	determining	if
 * the game	has been won.
 * 
 * @author Matteo Tondi 
 * @version 3.5.2016
 */
public class Board implements BoardInterface{
    private Piece[][] gameBoard;
    private Piece[][] copy; 
    private Piece lastMove;
    private int rows;
    private int cols;
    
    /**
     * Creates new board and makes a call to setBoardSize method.
     * 
     * @param  sizeX      how wide the board will be
     * @param  sizeY      how tall the board will be
     * 
     */
    public Board(int x, int y){
        try{
            setBoardSize(x,y);
        }
        catch(InvalidBoardSizeException i){
            System.err.println("Values x and y must be positive.");
        }
        catch(BoardAlreadySizedException s){
            System.err.println("This should never be reached.");
        }
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
     * 
     * Creates a copy of the board size to be used in the gameWon method.
     * Sets all cells to UNSET to begin with.
     */
    public boolean setBoardSize(int sizeX, int sizeY) throws InvalidBoardSizeException, BoardAlreadySizedException{
        if(sizeX <1 || sizeY <1){
            throw new InvalidBoardSizeException();
        }
        else if(gameBoard!=null){
            throw new BoardAlreadySizedException();
        }
        gameBoard = new Piece[sizeX][sizeY];
        copy = new Piece[sizeX][sizeY];
        rows=sizeX;
        cols=sizeY;
        for(int x = 0; x<sizeX; x++){
            for(int y = 0; y<sizeY; y++){
                gameBoard[x][y]=Piece.UNSET;
            }
        }
        return true;
    }
    
    /**
     * This method will return a two dimentional array of Pieces which represents the current state of the 
     * board. As this is just a copy of the data it is safe to send to a Player.
     * 
     * @returns Piece[][]  a two dimentional representation of the game board.
     * 
     * @throws  NoBoardDefinedException   Thrown when a call is made to this method before the boardSize 
     * method.
     */
    public Piece[][] getBoardView() throws NoBoardDefinedException{
        if(gameBoard==null){
            throw new NoBoardDefinedException();
        }
        return gameBoard;
    }
    
    /**
     * Places a piece on the board at the specified location.
     * 
     * @param colour     the colour of the piece to place (RED or BLUE)
     * @param move       the position where you wish to place a piece
     * @return boolean   true if the piece was placed successfully
     * 
     * @throws PositionAlreadyTakenException   if there is already a Piece in this position
     * @throws InvalidPositionException        if the specified position is invalid - e.g. (-1, -1)
     * @throws InvalidColourException          if the colour being set is invalid. E.g. if you try to place two BLUE pieces one after the other
     */
    public boolean placePiece(Piece colour, MoveInterface move) throws PositionAlreadyTakenException, InvalidPositionException, InvalidColourException, NoBoardDefinedException{
       if(move.getXPosition() < 0 || move.getYPosition() < 0){
           throw new InvalidPositionException();
       }
       else if(gameBoard==null){
           throw new NoBoardDefinedException();
        }
       else if(colour == lastMove){
           throw new InvalidColourException();
       }
       else if(gameBoard[move.getXPosition()][move.getYPosition()]!=Piece.UNSET){
           throw new PositionAlreadyTakenException();
       }
        gameBoard[move.getXPosition()][move.getYPosition()]= colour;
        lastMove = colour;
        return true;
    }
    
    //-------------------------------------------------------------------
    
    /**
     * Checks to see if either player has won.
     * 
     * @return Piece   RED if red has won, BLUE if blue has won, UNSET if neither player has won.
     * 
     * @throws NoBoardDefinedException  Indicates that this method has been called before the boardSize 
     * method
     */
    public Piece gameWon() throws NoBoardDefinedException{
        if(gameBoard==null){
            throw new NoBoardDefinedException();
        }
        else if(checkWin()==true){
            return lastMove;
        }
        else{
            return Piece.UNSET;
        }
    }
    
    /**
     * Queue starts essentially looks to see if there are any cells filled at one side of the
     * grid with a specific colour. (Looks at the top row of the grid to check for RED, 
     * rightmost column for BLUE).
     * 
     * Method makes identical copy of grid.
     * Checks for last move colour and then...
     * -If there are no RED cells in the top row then the game gannot be won, returns false.
     * -If there are no BLUE cells in rightmost column game not won, returns false.
     * If there are any of either, added to starts queue.
     * Each cell in starts queue makes a call to breadthFS methos, then cell is removed from starts.
     */
    private boolean checkWin(){
        Queue <int[]> starts = new LinkedList<int[]>();
        for(int x = 0; x<rows; x++){
            for(int y = 0; y<cols; y++){
                copy[x][y]=gameBoard[x][y];
            }
        }
        if(lastMove==Piece.RED){
            for(int width = 0; width<cols; width++){
                if(copy[0][width]==Piece.RED){
                    int [] start = {0, width};
                    starts.add(start);
                }
            }
            while(!starts.isEmpty()){
                int[] cell = starts.remove();
                if(breadthFS(cell,Piece.RED)){
                    return true;
                }
            }
        }
        else if(lastMove==Piece.BLUE){
            for(int height = 0; height<rows; height++){
                if(copy[height][0]==Piece.BLUE){
                    int [] start = {height, 0};
                    starts.add(start);
                }
            }
            while(!starts.isEmpty()){
                int[] cell = starts.remove();
                if(breadthFS(cell,Piece.BLUE)){
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Cell passed from checkWin method is set to UNSET in copy of board.
     * Makes call to findAdjacents to see if there are any adjacent cells of same colour.
     * If there are, adds to path.
     * If a path can be found from one side of the board to the other made of 
     * adjaent cells, returns true, else false.
     * Checkwin then goes to the next cell in the starts queue and checks that.
     */
    private boolean breadthFS(int[] cell, Piece colour){
        Queue<int[]> path = new LinkedList<int[]>();
        int x = cell[0];
        int y = cell[1];
        copy[x][y]=Piece.UNSET;
        path.add(cell);
        while(!path.isEmpty()){
            int[] c = path.remove();
            for(int[] i: findAdjacents(c,colour)){
                if(copy[i[0]][i[1]]==colour){
                    if(colour==Piece.BLUE && (i[1]+1)==cols){
                        return true;
                    }
                    else if(colour==Piece.RED && (i[0]+1)==rows){
                        return true;
                    }
                    path.add(i);
                    copy[i[0]][i[1]]=Piece.UNSET;
                }
            }
        }
        return false;
    }
    
    /**
     * Looks at all cells on board and checks if current cell is of same colour as @param colour
     * Checks, through call to isAdjacent, if current cell, is  adjacent to @param cell.
     * 
     */
    private Queue<int[]> findAdjacents(int[] cell, Piece colour){
        Queue<int[]> cells = new LinkedList<int[]>();
        int x = cell[0];
        int y = cell[1];
        for(int a = 0; a<rows; a++){
            for(int b = 0; b<cols; b++){
                if(copy[a][b]==colour && isAdjacent(x,y,a,b)){
                    int[] next = {a,b}; 
                    cells.add(next);
                }
            }
        }
        return cells;
    }
    
    /**
     * Given two cell coordinates, checks if they are adjacent, according to hex grid.
     */
    private boolean isAdjacent(int x, int y, int a, int b){
        return ((Math.abs((x+y)-(a+b)))<2 && (Math.abs(x-a))<2 && (Math.abs(y-b))<2);
    }
}