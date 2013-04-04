import java.util.ArrayList;


public class Pawn extends Piece {
	public boolean hasMoved = false;

	public Pawn(Board board, int theColumn, int theRow, boolean whiteTeam) { //constructor
		super(board, theColumn, theRow, whiteTeam);
	}
	public ArrayList<Position> possiblesMoves() {
		ArrayList<Position> returned = new ArrayList<Position>();
		if (hasMoved) {
			//returned.add(new Position(position.column, position.row))
		}
	}

}
