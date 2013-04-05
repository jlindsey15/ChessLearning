import java.util.ArrayList;


public class Queen extends Piece {
	public Queen(Board board, int theColumn, int theRow, boolean whiteTeam) { //constructor
		super(board, theColumn, theRow, whiteTeam);
	}
	public ArrayList<Position> possibleMoves() {
		ArrayList<Position> returned = new ArrayList<Position>();
		ArrayList<Position> rightUp = getPositionsInDirection(1, 1);
		ArrayList<Position> leftUp = getPositionsInDirection(-1, 1);
		ArrayList<Position> rightDown = getPositionsInDirection(1, -1);
		ArrayList<Position> leftDown = getPositionsInDirection(-1, -1);
		ArrayList<Position> right = getPositionsInDirection(1, 0);
		ArrayList<Position> left = getPositionsInDirection(-1, 0);
		ArrayList<Position> up = getPositionsInDirection(0, 1);
		ArrayList<Position> down = getPositionsInDirection(0, -1);
		
		ArrayList<ArrayList<Position>> meta = new ArrayList<ArrayList<Position>>();
		meta.add(right);
		meta.add(left);
		meta.add(up);
		meta.add(down);
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
