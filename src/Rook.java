import java.util.ArrayList;


public class Rook extends ChessPiece {
	public Rook(Board board, int theColumn, int theRow, boolean whiteTeam) { //constructor
		super(board, theColumn, theRow, whiteTeam);
	}
	public ArrayList<Position> possibleMoves() {
		ArrayList<Position> returned = new ArrayList<Position>();
		//gives all the possible "lines" of movement
		ArrayList<Position> right = getPositionsInDirection(1, 0);
		ArrayList<Position> left = getPositionsInDirection(-1, 0);
		ArrayList<Position> up = getPositionsInDirection(0, 1);
		ArrayList<Position> down = getPositionsInDirection(0, -1);
		ArrayList<ArrayList<Position>> meta = new ArrayList<ArrayList<Position>>();
		meta.add(right);
		meta.add(left);
		meta.add(up);
		meta.add(down);
		meta = ignoreAfterObstruction(meta); //can't move through a piece that's in the way  - remove those extra spaces at the end
		for (ArrayList<Position> line : meta) {
			returned.addAll(line);
		}
		returned = removeFriendlyFire(returned);
		return returned;
	}

}
