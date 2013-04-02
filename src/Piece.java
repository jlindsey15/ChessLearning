import java.util.ArrayList;


public abstract class Piece { //Superclass for all chess pieces
	private Board myBoard; //the board the piece belongs to
	private int row;
	private int column;
	public boolean isOnWhiteTeam; //which team you're on
	public Piece() { //default constructor
		;
	}
	public Piece(Board board, int theRow, int theColumn, boolean whiteTeam) { //constructor
		row = theRow;
		column = theColumn;
		isOnWhiteTeam = whiteTeam;
		myBoard = board;
		myBoard.add(this); //constructor adds the piece to the chessboard
	}
	public int getRow() { //obvi
		return row;
	}
	public int getColumn() { //obvi
		return column;
	}
	
	public abstract ArrayList<int[]> possiblesMoves();

}
