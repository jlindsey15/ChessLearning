import java.util.ArrayList;


public class Pawn extends ChessPiece {
	public boolean hasMoved = false;

	public Pawn(Board board, int theColumn, int theRow, boolean whiteTeam) { //constructor
		super(board, theColumn, theRow, whiteTeam);
	}
	public ArrayList<Position> possibleMoves() {
		ArrayList<Position> returned = new ArrayList<Position>();
		int upOrDown;
		if (isOnWhiteTeam) { //white pawns move up
			upOrDown = 1;
		}
		else { //black pawns move down
			upOrDown = -1;
		}
		if (hasMoved) {
			returned.add(new Position(getColumn(), getRow() + upOrDown));
		}
		else {
			returned.add(new Position(getColumn(), getRow() + 2 * upOrDown));
		}
		returned = removeFriendlyFire(returned); //removes friendly fire
		return returned;
	}

}
