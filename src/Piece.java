import java.util.ArrayList;


public abstract class Piece { //Superclass for all chess pieces
	private Board myBoard; //the board the piece belongs to
	private Position position;
	public boolean isOnWhiteTeam; //which team you're on
	public Piece() { //default constructor
		;
	}
	public Piece(Board board, int theColumn, int theRow, boolean whiteTeam) { //constructor
		position = new Position(theColumn, theRow);
		isOnWhiteTeam = whiteTeam;
		myBoard = board;
		myBoard.add(this); //constructor adds the piece to the chessboard
	}
	public int getRow() { //obvi
		return position.row;
	}
	public int getColumn() { //obvi
		return position.column;
	}
	public ArrayList<Position> getPositionsInDirection (int horiz, int vert) { 
		//vert and horiz should be either -1, 0, or 1. For example (1, -1) would be diagonal down right since you increase the column number and decrease the row number
																			
		ArrayList<Position> returned = new ArrayList<Position>();
		boolean stop = false;
		Position currentPosition = position;
		while (!stop) {
			currentPosition = new Position(currentPosition.column + horiz, currentPosition.row + vert);
			if (currentPosition.isValid()) {
				returned.add(currentPosition);
			}
			else {
				stop = true;
			}
		}
		return returned;
	}
	
	
	public abstract ArrayList<Position> possiblesMoves();

}
