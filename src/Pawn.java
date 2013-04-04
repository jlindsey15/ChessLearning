import java.util.ArrayList;


public class Pawn extends Piece {

	public Pawn(Board board, int theColumn, int theRow, boolean whiteTeam) { //constructor
		super(board, theColumn, theRow, whiteTeam);
	}
	public ArrayList<Position> possiblesMoves() {
		return null;
	}

}
