import java.util.ArrayList;


public class Knight extends ChessPiece {
	public Knight(Board board, int theColumn, int theRow, boolean whiteTeam) { //constructor
		super(board, theColumn, theRow, whiteTeam);
	}
	public ArrayList<Position> possibleMoves() { //all possible knight moves - this is how knights work...
		ArrayList<Position> returned = new ArrayList<Position>();
		int c = getColumn();
		int r = getRow();
		Position toBeAdded;
		toBeAdded = new Position(c + 2, r + 1);
		if (toBeAdded.isValid()) {
			returned.add(toBeAdded);
		}
		toBeAdded = new Position(c + 2, r - 1);
		if (toBeAdded.isValid()) {
			returned.add(toBeAdded);
		}
		toBeAdded = new Position(c - 2, r + 1);
		if (toBeAdded.isValid()) {
			returned.add(toBeAdded);
		}
		toBeAdded = new Position(c - 2, r - 1);
		if (toBeAdded.isValid()) {
			returned.add(toBeAdded);
		}
		toBeAdded = new Position(c + 1, r + 2);
		if (toBeAdded.isValid()) {
			returned.add(toBeAdded);
		}
		toBeAdded = new Position(c + 1, r - 2);
		if (toBeAdded.isValid()) {
			returned.add(toBeAdded);
		}
		toBeAdded = new Position(c - 1, r + 2);
		if (toBeAdded.isValid()) {
			returned.add(toBeAdded);
		}
		toBeAdded = new Position(c - 1, r -2);
		if (toBeAdded.isValid()) {
			returned.add(toBeAdded);
		}
		returned = removeFriendlyFire(returned);
		return returned;
	}

}
