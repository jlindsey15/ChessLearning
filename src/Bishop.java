import java.util.ArrayList;


public class Bishop extends Piece{
	public Bishop(Board board, int theColumn, int theRow, boolean whiteTeam) { //constructor
		super(board, theColumn, theRow, whiteTeam);
	}
	public ArrayList<Position> possibleMoves() { //possible moves for a bishop

		ArrayList<Position> returned = new ArrayList<Position>();
		ArrayList<Position> rightUp = getPositionsInDirection(1, 1);
		ArrayList<Position> leftUp = getPositionsInDirection(-1, 1);
		ArrayList<Position> rightDown = getPositionsInDirection(1, -1);
		ArrayList<Position> leftDown = getPositionsInDirection(-1, -1);
		ArrayList<ArrayList<Position>> meta = new ArrayList<ArrayList<Position>>();
		meta.add(rightUp);
		meta.add(leftUp);
		meta.add(rightDown);
		meta.add(leftDown);
		meta = ignoreAfterObstruction(meta);
		for (ArrayList<Position> line : meta) {
			returned.addAll(line);
		}
		returned = removeFriendlyFire(returned);
		return returned;


	}


}
