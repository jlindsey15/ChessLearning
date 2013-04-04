import java.util.ArrayList;


public class King extends Piece {
	public King(Board board, int theColumn, int theRow, boolean whiteTeam) { //constructor
		super(board, theColumn, theRow, whiteTeam);
	}
	public ArrayList<Position> possiblesMoves() { //returns all possible moves for a king
		ArrayList<Position> returned = new ArrayList<Position>();
		for (int i = -1; i <=1; i++) { //defines the square around the king, not including the king's space
			for (int j = -1; j <=1; j++) {
				if ((i == 0) && (j == 0)) { //don't include the space the king's already on
					;
				}
				else {
					returned.add(new Position(i, j));
				}
			}
		}
		returned = removeFriendlyFire(returned); //removes friendly fire moves
		
		return returned;
	}

}
